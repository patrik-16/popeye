package com.popeye.backend.repos;

import com.popeye.backend.entity.Exercise;
import com.popeye.backend.enums.Difficulty;
import com.popeye.backend.enums.Experience;
import com.popeye.backend.enums.Goal;
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

    private List<Exercise> getExercisesByExperienceAndDifficulty(Experience experience, Difficulty difficulty) {
        if (experience == Experience.BEGINNER) {
            return (List<Exercise>) firebaseRepository.getAllDocumentsByCollectionWithCondition("Beginnerexercises", Exercise.class, "difficulty", difficulty);
        } else {
            return Collections.emptyList();
        }
    }

    private List<Exercise> getExercisesByExperienceAndDifficultyAndGoal(Experience experience, Difficulty difficulty, Goal goal) {
        if (experience == Experience.BEGINNER) { // not sure if that IF is needed, we handle that above
            return (List<Exercise>) firebaseRepository.getAllDocumentsByCollectionWithCondition("Beginnerexercises", Exercise.class, "difficulty", difficulty);
        } else if (experience == Experience.ADVANCED) {
            switch (goal) {
                case HYPERTROPHY -> {
                    return (List<Exercise>) firebaseRepository.getAllDocumentsByCollectionWithCondition("Hypertrophyexercises", Exercise.class, "difficulty", difficulty);
                }
                case STRENGTH -> {
                    return (List<Exercise>) firebaseRepository.getAllDocumentsByCollectionWithCondition("Strengthexercises", Exercise.class, "difficulty", difficulty);
                }
                case CONDITIONING -> {
                    return (List<Exercise>) firebaseRepository.getAllDocumentsByCollectionWithCondition("Conditioningexercises", Exercise.class, "difficulty", difficulty);
                }
            }
        }
        return null;
    }

    public List<Exercise> getAllBeginnerExercisesByDifficulty(Difficulty difficulty) {
        return getExercisesByExperienceAndDifficulty(Experience.BEGINNER, difficulty);
    }

    public List<Exercise> getAllAdvancedExercisesByDifficultyAndGoal(Difficulty difficulty, Goal goal) {
        return getExercisesByExperienceAndDifficultyAndGoal(Experience.ADVANCED, difficulty, goal);
    }
}
