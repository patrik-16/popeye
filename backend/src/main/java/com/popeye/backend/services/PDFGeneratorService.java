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
import com.popeye.backend.enums.Experience;
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
        fontTitle.setSize(16);
        // Creating paragraph
        Paragraph paragraph1 = new Paragraph("Your Program:", fontTitle);
        paragraph1.setAlignment(Paragraph.ALIGN_CENTER);
        // Adding the created paragraph in the document
        document.add(paragraph1);
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        Font fontTextPart = FontFactory.getFont(FontFactory.HELVETICA);
        fontTextPart.setSize(12);
        Paragraph textPart = new Paragraph("Follow this program for the next 6 weeks and you will reach your goals. You can do the " + userinput.getDaysPerWeek() + " sessions in any order, but each session needs to be done once a week.", fontTextPart);
        textPart.setAlignment(Paragraph.ALIGN_LEFT);
        document.add(textPart);
        if(userinput.getExperience().equals(Experience.BEGINNER)) {
            Paragraph textPart2 = new Paragraph("You entered the following parameters: You are a beginner and want to work out for " + userinput.getDaysPerWeek() + " days a week.", fontTextPart);
            textPart2.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(textPart2);
        } else {
            Paragraph textPart2 = new Paragraph("You entered the following parameters: You are an intermediate training " + userinput.getGoal() + " and want to work out for " + userinput.getDaysPerWeek() + " days a week.", fontTextPart);
            textPart2.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(textPart2);
        }
        // Creating table for each day
        int i = 0;
        for (ProgramSession sessions : program.getProgram()) {
            i += 1;
            document.add(new Paragraph("\n"));
            Font fontTitle2 = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            fontTitle2.setSize(12);
            // Creating Session ...
            String text = "Session " + i;
            Paragraph paragraph2 = new Paragraph(text, fontTitle2);
            paragraph2.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(paragraph2);

            //Creating a table of the 6 columns
            PdfPTable table = new PdfPTable(6);
            table.setWidthPercentage(100f);
            table.setSpacingBefore(8);
            // Create Table Cells for the table header
            PdfPCell cell = new PdfPCell();
            // Setting the background color and padding of the table cell
            cell.setBackgroundColor(CMYKColor.DARK_GRAY);
            cell.setPadding(3);
            // Creating font
            // Setting font style and size
            Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLDOBLIQUE);
            font.setColor(CMYKColor.WHITE);
            // Adding headings in the created table cell or  header
            cell.setPhrase(new Phrase("Exercise", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Bodypart", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Sets", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Reps", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Rest", font));
            table.addCell(cell);
            cell.setPhrase(new Phrase("Intensiveness", font));
            table.addCell(cell);
            // Adding Cell to table
            for (Exercise exercise : sessions.getExerciseList()) {
                if (exercise == null) {
                    document.add(new Paragraph("Something went wrong!", fontTitle2));
                } else {
                    // Adding exercise name, reps, rest
                    table.addCell(String.valueOf(exercise.getName()));
                    for (Map.Entry<String, String> map : exercise.getBodypartToEffectiveness().entrySet()) {
                        //System.out.println(map.getKey());
                        //System.out.println(map.getValue());
                        table.addCell(String.valueOf(map.getKey()));
                    }
                    table.addCell(String.valueOf(exercise.getSets()));
                    table.addCell(String.valueOf(exercise.getReps()));
                    table.addCell(String.valueOf(exercise.getRest()));
                    table.addCell(String.valueOf(exercise.getIntensiveness()));
                }
            }
            // Adding the created table to the document
            document.add(table);

            Paragraph textPart3 = new Paragraph("In this session you trained " + sessions.getSecondsPerSession() / 60 + " minutes.", fontTextPart);
            textPart3.setAlignment(Paragraph.ALIGN_LEFT);
            document.add(textPart3);
        }
        //footer with page number
        HeaderFooter footer = new HeaderFooter(new Phrase(), true);
        footer.setAlignment(Element.ALIGN_RIGHT);
        footer.setBorder(Rectangle.TOP);
        document.setFooter(footer);

        // Closing the document
        document.close();
    }
}
// https://springjava.com/spring-boot/export-data-into-pdf-file-in-spring-boot
// pdf creation -> use above link (export and Exceptions are needed) if I use the Data from the database
