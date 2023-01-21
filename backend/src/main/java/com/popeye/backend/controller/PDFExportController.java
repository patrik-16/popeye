package com.popeye.backend.controller;

import com.popeye.backend.services.PDFGeneratorService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PDFExportController {

    private final PDFGeneratorService pdfGeneratorService;

    // when application runs: it makes a bean (an object) of the class PDFGeneratorService and injects this object
    // into this constructor (and then we can use the service)
    public PDFExportController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @CrossOrigin
    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse httpServletResponse) throws IOException {
        //attach the pdf as response

        httpServletResponse.setContentType("application/pdf");
        //create the date
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-mm-dd:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        //create the response Header - tells the browser that it gets the created pdf
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        httpServletResponse.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(httpServletResponse);
    }
}