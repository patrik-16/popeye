package com.popeye.backend.services;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.lowagie.text.*;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.popeye.backend.entity.Exercise;
import com.popeye.backend.entity.Program;
import com.popeye.backend.entity.ProgramSession;
import com.popeye.backend.entity.Userinput;
import com.popeye.backend.enums.Bodypart;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


//this class should be created into a bean - then the bean can be injected into the controller
@Service
public class PDFGeneratorService {

    @Autowired
    ProgramService programService;

    //the response we want to give back to the user
    public void export(Userinput userinput, HttpServletResponse response) throws IOException {
        Program program = programService.createProgram(userinput);
        //attach a document to the response
        // Creating the Object of Document (a white doc)
        Document document = new Document(PageSize.A4.rotate());
        // Getting instance of PdfWriter - we want to write the document to this outputstream of the response
        PdfWriter.getInstance(document, response.getOutputStream());

        // Opening the created document to change it
        document.open();
        Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        fontTitle.setSize(18);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("Your Program:", fontTitle);
        // Aligning the paragraph in the document
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        // Creating table for each day


        for(int i = 1; i <= userinput.getDaysPerWeek(); i++) {
            Font fontTitle2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle2.setSize(13);
            // Creating paragraph
            String text = "Day " + i;
            System.out.println(text);
            Paragraph paragraph2 = new Paragraph(text, fontTitle2);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph2);
            //Creating a table of the 4 columns
            PdfPTable table = new PdfPTable(userinput.getDaysPerWeek());
            // Setting width of the table, its columns and spacing
            table.setWidthPercentage(100f);
            //table.setWidths(new int[]{3, 3, 3, 3});
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
            for (ProgramSession sessions : program.getProgram()) {
                if (sessions.getDay() == i) {
                    currentDayProgramSessionList = sessions.getExerciseList();
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

                    for (Map.Entry<String, String> map : exercise.getBodypartToEffectiveness().entrySet()) {
                        System.out.println(map.getKey());
                        System.out.println(map.getValue());
                        if (s == 0) {
                            cell.setPhrase(new Phrase("Bodypart", font));
                        } else {
                            cell.setPhrase(new Phrase("", font));
                        }
                        table.addCell(cell);
                        s += 1;
                        table.addCell(String.valueOf(map.getKey()));
                    }

                    // Adding exercise name, reps, rest
                    table.addCell(String.valueOf(exercise.getName()));
                    table.addCell(String.valueOf(exercise.getReps()));
                    table.addCell(String.valueOf(exercise.getRest()));

                }
                // Adding the created table to the document
                document.add(table);
            }
            // Closing the document
            document.close();
        }
    }
}
// https://springjava.com/spring-boot/export-data-into-pdf-file-in-spring-boot
// pdf creation -> use above link (export and Exceptions are needed) if I use the Data from the database
