package com.popeye.backend.repos;

import com.popeye.backend.entity.Exercise;
import com.popeye.backend.enums.Difficulty;
import com.popeye.backend.enums.Experience;
import com.popeye.backend.enums.Goal;
import com.popeye.backend.repos.FirebaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ExerciseRepository {
    @Autowired
    FirebaseRepository firebaseRepository;

    public List<Exercise> getAllExercisesByExperience(Experience experience) {
        if (experience == Experience.BEGINNER) {
            return getAllBeginnerExercises();
        } else {
            return null;
        }
    }

    private List<Exercise> getAllBeginnerExercises() {
        List<Exercise> allBeginnerExercises = (List<Exercise>) firebaseRepository.getAllDocumentsByCollection("Beginnerexercises", Exercise.class);
        return allBeginnerExercises;
    }

    public List<Exercise> getAllBeginnerExercisesByDifficulty(Difficulty difficulty) {
        return getExercisesByExperienceAndDifficulty(Experience.BEGINNER, difficulty);
    }

    public List<Exercise> getAllAdvancedExercisesByDifficulty(Difficulty difficulty) {
        return getExercisesByExperienceAndDifficulty(Experience.ADVANCED, difficulty);
    }

    private List<Exercise> getExercisesByExperienceAndDifficulty(Experience experience, Difficulty difficulty) {
        if (experience == Experience.BEGINNER) {
            return (List<Exercise>) firebaseRepository.getAllDocumentsByCollectionWithCondition("Beginnerexercises", Exercise.class, "difficulty", difficulty.getValue());
        } else {
            //TODO implement for advanced experience
            return Collections.emptyList();
        }
    }
}
