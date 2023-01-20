package com.popeye.backend.controller;

import com.popeye.backend.entity.Exercise;
import com.popeye.backend.entity.Program;
import com.popeye.backend.entity.Userinput;
import com.popeye.backend.repos.FirebaseRepository;
import com.popeye.backend.services.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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
    @CrossOrigin
    @PostMapping("/hypertrophyexercises")
    public void hypertrophyExercises(@RequestBody List<Exercise> hypertrophyExercises) {
        for (Exercise ex : hypertrophyExercises) {
            firebaseRepository.createWithDocumentId("Hypertrophyexercises", ex.getName(), ex);
        }
    }

    @CrossOrigin
    @PostMapping("/strengthexercises")
    public void strengthExercises(@RequestBody List<Exercise> strengthExercises) {
        for (Exercise ex : strengthExercises) {
            firebaseRepository.createWithDocumentId("Strengthexercises", ex.getName(), ex);
        }
    }

    @CrossOrigin
    @PostMapping("/conditioningexercises")
    public void conditioningExercises(@RequestBody List<Exercise> conditioningExercises) {
        for (Exercise ex : conditioningExercises) {
            firebaseRepository.createWithDocumentId("Conditioningexercises", ex.getName(), ex);
        }
    }

    @CrossOrigin
    @PostMapping("/beginnerexercises")
    public void beginnerExercises(@RequestBody List<Exercise> beginnerExercises) {
        for (Exercise ex : beginnerExercises) {
            firebaseRepository.createWithDocumentId("Beginnerexercises", ex.getName(), ex);
        }
    }

    @CrossOrigin
    @PostMapping("/generateprogram")
    public Program getProgram(@RequestBody Userinput userinput) {
        return programService.createProgram(userinput);
    }


    @PostMapping("/test")
    public Exercise testEx() {
        Exercise m = new Exercise();
        HashMap<String, String> s = new HashMap<>();
        s.put("LEGS", "8");
        s.put("LATS", "6");
        s.put("CHEST", "0.5");
        m.setBodypartToEffectiveness(s);
        return m;
    }
}
