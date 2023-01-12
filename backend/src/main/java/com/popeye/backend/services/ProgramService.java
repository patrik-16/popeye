package com.popeye.backend.services;

import com.popeye.backend.entity.*;
import com.popeye.backend.enums.*;
import com.popeye.backend.repos.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    @Autowired
    ExerciseRepository exerciseRepository;
    /*will be important for ordering by goal
        for (int i = 0; i < beginnerExercises.size(); i++) {
            if (beginnerExercises.get(i).getBodypart().containsAll(userinput.getPriorities())) {

                Exercise foundEx = beginnerExercises.get(i);
                beginnerExercises.remove(beginnerExercises.get(i));
                beginnerExercises.add(0, foundEx);
            }
        }*/

    public Program createProgram(Userinput userinput) {
        if (userinput.getExperience().equals(Experience.BEGINNER)) {
            switch (userinput.getDaysPerWeek()) {
                case 1:
                    return null; //TODO handle error exception in frontend
                case 2:
                    return generateTwoDayBeginnerProgram(userinput);
                case 3:
                    return generateThreeDayBeginnerProgram(userinput);
                case 4:
                    return generateFourDayBeginnerProgram(userinput);
                case 5:
                    return generateFiveDayBeginnerProgram(userinput);
            }
        } else {
            switch (userinput.getGoal()) {
                case HYPERTROPHY -> generateTwoDayHypertrophyProgram(userinput); //TODO implement generic method
                case STRENGTH -> generateStrengthProgram(userinput);
                case CONDITIONING -> generateConditioningProgram(userinput);
            }
        }
        return null; //TODO handle error exception in frontend
    }

    private Program generateTwoDayHypertrophyProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, Goal.HYPERTROPHY), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, Goal.HYPERTROPHY), 2);

        return new Program(List.of(sessionOne, sessionTwo));

    }

    private void generateStrengthProgram(Userinput userinput) {
    }

    private void generateConditioningProgram(Userinput userinput) {
    }

    private Program generateTwoDayBeginnerProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.HARD), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 2);

        if (userinput.getTimePerDay() == TimePerDay.FORTY) {
            if (sessionOne.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionOne.updateRestTime(60);
                sessionOne.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionTwo.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionTwo.updateRestTime(60);
                sessionTwo.deleteExerciseByBodypart(Bodypart.ABS);
            }

        } else if (userinput.getTimePerDay() == TimePerDay.SIXTY) {
            sessionOne.updateSetNumberByDifficulty(Difficulty.HARD, 3);
            sessionTwo.updateSetNumberByDifficulty(Difficulty.MEDIUM, 3);

        } else if (userinput.getTimePerDay() == TimePerDay.EIGHTY) {
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.QUADS, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.CHEST, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.LATS, 4);
            sessionOne.updateRestTime(120);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionTwo.updateRestTime(120);

        } else if (userinput.getTimePerDay() == TimePerDay.UNLIMITED) {
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.QUADS, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.CHEST, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.LATS, 4);
            sessionOne.updateRestTime(180);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionTwo.updateRestTime(180);
        }
        return new Program(List.of(sessionOne, sessionTwo));
    }

    private Program generateThreeDayBeginnerProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.HARD), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 2);
        ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.EASY), 3);

        if (userinput.getTimePerDay() == TimePerDay.FORTY) {
            if (sessionOne.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionOne.updateRestTime(60);
                sessionOne.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionTwo.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionTwo.updateRestTime(60);
                sessionTwo.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionThree.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionThree.updateRestTime(60);
                sessionThree.deleteExerciseByBodypart(Bodypart.ABS);
            }

        } else if (userinput.getTimePerDay() == TimePerDay.SIXTY) {
            sessionOne.updateSetNumberByDifficulty(Difficulty.HARD, 3);
            sessionTwo.updateSetNumberByDifficulty(Difficulty.MEDIUM, 3);
            sessionThree.updateSetNumberByDifficulty(Difficulty.EASY, 3);

        } else if (userinput.getTimePerDay() == TimePerDay.EIGHTY) {
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.QUADS, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.CHEST, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.LATS, 4);
            sessionOne.updateRestTime(120);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionTwo.updateRestTime(120);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.GLUTES, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.DELTS, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.TRAPS, 4);
            sessionThree.updateRestTime(120);

        } else if (userinput.getTimePerDay() == TimePerDay.UNLIMITED) {
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.QUADS, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.CHEST, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.LATS, 4);
            sessionOne.updateRestTime(180);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionTwo.updateRestTime(180);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.GLUTES, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.DELTS, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.TRAPS, 4);
            sessionThree.updateRestTime(180);
        }
        return new Program(List.of(sessionOne, sessionTwo, sessionThree));
    }

    private Program generateFourDayBeginnerProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.HARD), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 2);
        ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.EASY), 3);
        ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 4);

        if (userinput.getTimePerDay() == TimePerDay.FORTY) {
            if (sessionOne.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionOne.updateRestTime(60);
                sessionOne.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionTwo.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionTwo.updateRestTime(60);
                sessionTwo.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionThree.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionThree.updateRestTime(60);
                sessionThree.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionFour.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionFour.updateRestTime(60);
                sessionFour.deleteExerciseByBodypart(Bodypart.ABS);
            }

        } else if (userinput.getTimePerDay() == TimePerDay.SIXTY) {
            sessionOne.updateSetNumberByDifficulty(Difficulty.HARD, 3);
            sessionTwo.updateSetNumberByDifficulty(Difficulty.MEDIUM, 3);
            sessionThree.updateSetNumberByDifficulty(Difficulty.EASY, 3);
            sessionFour.updateSetNumberByDifficulty(Difficulty.MEDIUM, 3);

        } else if (userinput.getTimePerDay() == TimePerDay.EIGHTY) {
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.QUADS, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.CHEST, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.LATS, 4);
            sessionOne.updateRestTime(120);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionTwo.updateRestTime(120);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.GLUTES, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.DELTS, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.TRAPS, 4);
            sessionThree.updateRestTime(120);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionFour.updateRestTime(120);

        } else if (userinput.getTimePerDay() == TimePerDay.UNLIMITED) {
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.QUADS, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.CHEST, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.LATS, 4);
            sessionOne.updateRestTime(180);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionTwo.updateRestTime(180);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.GLUTES, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.DELTS, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.TRAPS, 4);
            sessionThree.updateRestTime(180);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionFour.updateRestTime(180);
        }
        return new Program(List.of(sessionOne, sessionTwo, sessionThree, sessionFour));
    }

    private Program generateFiveDayBeginnerProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.HARD), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 2);
        ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.EASY), 3);
        ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 4);
        ProgramSession sessionFive = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.EASY), 5);

        if (userinput.getTimePerDay() == TimePerDay.FORTY) {
            if (sessionOne.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionOne.updateRestTime(60);
                sessionOne.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionTwo.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionTwo.updateRestTime(60);
                sessionTwo.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionThree.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionThree.updateRestTime(60);
                sessionThree.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionFour.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionFour.updateRestTime(60);
                sessionFour.deleteExerciseByBodypart(Bodypart.ABS);
            }
            if (sessionFive.getSecondsPerSession() > TimePerDay.FORTY.getSeconds()) {
                sessionFive.updateRestTime(60);
                sessionFive.deleteExerciseByBodypart(Bodypart.ABS);
            }

        } else if (userinput.getTimePerDay() == TimePerDay.SIXTY) {
            sessionOne.updateSetNumberByDifficulty(Difficulty.HARD, 3);
            sessionTwo.updateSetNumberByDifficulty(Difficulty.MEDIUM, 3);
            sessionThree.updateSetNumberByDifficulty(Difficulty.EASY, 3);
            sessionFour.updateSetNumberByDifficulty(Difficulty.MEDIUM, 3);
            sessionFive.updateSetNumberByDifficulty(Difficulty.EASY, 3);

        } else if (userinput.getTimePerDay() == TimePerDay.EIGHTY) {
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.QUADS, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.CHEST, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.LATS, 4);
            sessionOne.updateRestTime(120);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionTwo.updateRestTime(120);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.GLUTES, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.DELTS, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.TRAPS, 4);
            sessionThree.updateRestTime(120);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionFour.updateRestTime(120);
            sessionFive.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.GLUTES, 4);
            sessionFive.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.DELTS, 4);
            sessionFive.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.TRAPS, 4);
            sessionFive.updateRestTime(120);

        } else if (userinput.getTimePerDay() == TimePerDay.UNLIMITED) {
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.QUADS, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.CHEST, 4);
            sessionOne.updateSetNumberByDifficultyAndBodypart(Difficulty.HARD, Bodypart.LATS, 4);
            sessionOne.updateRestTime(180);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionTwo.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionTwo.updateRestTime(180);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.GLUTES, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.DELTS, 4);
            sessionThree.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.TRAPS, 4);
            sessionThree.updateRestTime(180);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.GLUTES, 4);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.DELTS, 4);
            sessionFour.updateSetNumberByDifficultyAndBodypart(Difficulty.MEDIUM, Bodypart.TRAPS, 4);
            sessionFour.updateRestTime(180);
            sessionFive.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.GLUTES, 4);
            sessionFive.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.DELTS, 4);
            sessionFive.updateSetNumberByDifficultyAndBodypart(Difficulty.EASY, Bodypart.TRAPS, 4);
            sessionFive.updateRestTime(180);
        }
        return new Program(List.of(sessionOne, sessionTwo, sessionThree, sessionFour, sessionFive));
    }
}