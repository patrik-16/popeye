package com.popeye.backend.controller;

import com.popeye.backend.entity.Exercise;
import com.popeye.backend.entity.Program;
import com.popeye.backend.entity.ProgramSession;
import com.popeye.backend.entity.Userinput;
import com.popeye.backend.pdf.PDFcreator;
import com.popeye.backend.repos.FirebaseRepository;
import com.popeye.backend.services.ProgramService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLConnection;
import java.util.List;

@RestController
//return JSON
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProgramRestController {

        @Autowired
        FirebaseRepository firebaseRepository;

        @Autowired
        ProgramService programService;

        //Requestbody wie bei HTTP methoden
        @PostMapping("/hypertrophyexercises")
        public void hypertrophyExercises(@RequestBody List<Exercise> hypertrophyExercises) {
            for (Exercise ex : hypertrophyExercises) {
                firebaseRepository.createWithDocumentId("Hypertrophyexercises", ex.getName(), ex);
            }
        }

        @PostMapping("/strengthexercises")
        public void strengthExercises(@RequestBody List<Exercise> strengthExercises) {
            for (Exercise ex : strengthExercises) {
                firebaseRepository.createWithDocumentId("Strengthexercises", ex.getName(), ex);
            }
        }

        @PostMapping("/conditioningexercises")
        public void conditioningExercises(@RequestBody List<Exercise> conditioningExercises) {
            for (Exercise ex : conditioningExercises) {
                firebaseRepository.createWithDocumentId("Conditioningexercises", ex.getName(), ex);
            }
        }

        @PostMapping("/beginnerexercises")
        public void beginnerExercises(@RequestBody List<Exercise> beginnerExercises) {
            for (Exercise ex : beginnerExercises) {
                firebaseRepository.createWithDocumentId("Beginnerexercises", ex.getName(), ex);
            }
        }

        @PostMapping("/beginnerprogram")
    public Program getBeginnerProgram(@RequestBody Userinput userinput) {
        return programService.createBeginnerProgram(userinput);
    }


    @RequestMapping("/download/{fileName:.+}")
    public void downloadPDFprogram(HttpServletRequest request, HttpServletResponse response, @PathVariable("currentPDFprogram.pdf") String fileName) throws IOException {
        String path = "src/main/java/com/popeye/backend/" + fileName;
        File file = new File(path);
        if (file.exists()) {
            String mimeType = URLConnection.guessContentTypeFromName(file.getName()); // for you it would be application/pdf
            if (mimeType == null) mimeType = "application/octet-stream";
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
            response.setContentLength((int) file.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
        }
    }


}
