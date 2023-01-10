package com.popeye.backend.pdf;

import com.popeye.backend.controller.ProgramRestController;
import com.popeye.backend.entity.Exercise;
import com.popeye.backend.entity.Program;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.popeye.backend.entity.ProgramSession;
import com.popeye.backend.entity.Userinput;
import com.popeye.backend.enums.Bodypart;
import com.popeye.backend.repos.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;

import static ch.qos.logback.core.util.StatusPrinter.print;

public class PDFcreator {
    // https://springjava.com/spring-boot/export-data-into-pdf-file-in-spring-boot
    // pdf creation -> use above link (export and Exceptions are needed) if I use the Data from the database


    public Document generatePDF(Userinput userinput, List<ProgramSession> programSessionList) throws FileNotFoundException {
        // Creating the Object of Document
        Document document = new Document(PageSize.A4);
        // Getting instance of PdfWriter
        PdfWriter.getInstance(document, new FileOutputStream("currentPDFprogram.pdf"));

        /*
        Do we want File encryption? NO must requirement therefor I guess NO
        https://www.baeldung.com/java-pdf-creation
        PdfReader pdfReader = new PdfReader("HelloWorld.pdf");
PdfStamper pdfStamper
  = new PdfStamper(pdfReader, new FileOutputStream("encryptedPdf.pdf"));

pdfStamper.setEncryption(
  "userpass".getBytes(),
  ".getBytes(),
  0,
  PdfWriter.ENCRYPTION_AES_256
);

pdfStamper.close();
        */

        // Opening the created document to change it
        document.open();
        // Creating font
        // Setting font style and size
        Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
        fontTiltle.setSize(20);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("Your Program", fontTiltle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Creating table for each day
        for(int i = 0; i < userinput.getDaysPerWeek(); i++) {
            fontTiltle.setSize(20);
            // Creating paragraph
            String text = "Day " + i;
            Paragraph paragraph2 = new Paragraph(text, fontTiltle);
            document.add(paragraph2);
            //Creating a table of the 4 columns
            PdfPTable table = new PdfPTable(userinput.getDaysPerWeek());
            // Setting width of the table, its columns and spacing
            table.setWidthPercentage(100f);
            table.setWidths(new int[]{3, 3, 3, 3});
            table.setSpacingBefore(5);
            // Create Table Cells for the table header
            PdfPCell cell = new PdfPCell();
            // Setting the background color and padding of the table cell
            cell.setBackgroundColor(CMYKColor.BLUE);
            cell.setPadding(5);
            // Creating font
            // Setting font style and size
            Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
            font.setColor(CMYKColor.WHITE);
            // Adding headings in the created table cell or  header
            // Adding Cell to table
            List<Exercise> currentDayProgramSessionList = null;
            for (ProgramSession exerciseList : programSessionList) {
                if (exerciseList.getDay() == i) {
                    currentDayProgramSessionList = (List<Exercise>) exerciseList;
                }
            }

            for (Exercise exercise : currentDayProgramSessionList) {
                int k = 0;
                if (currentDayProgramSessionList == null) {
                    //TODO: Error handling
                    System.out.println("list is empty!");
                } else {
                    k += 1;
                    cell.setPhrase(new Phrase(k + ":", font));
                    table.addCell(cell);
                    cell.setPhrase(new Phrase("Reps", font));
                    table.addCell(cell);
                    cell.setPhrase(new Phrase("Rest", font));
                    table.addCell(cell);
                    int s = 0;
                    for(Bodypart bodypart : exercise.getBodypart()) {
                        if (s == 0) {
                            cell.setPhrase(new Phrase("Bodypart", font));
                        } else {
                            cell.setPhrase(new Phrase("", font));
                        }
                        table.addCell(cell);
                        s += 1;

                        table.addCell(String.valueOf(bodypart));
                    }

                    // Adding exercise name, reps, rest
                    table.addCell(String.valueOf(exercise.getName()));
                    table.addCell(String.valueOf(exercise.getReps()));
                    table.addCell(String.valueOf(exercise.getRest()));

                }
                // Adding the created table to the document
                document.add(table);
            }
        }
        // Closing the document
        document.close();

        return document;
    }
}
