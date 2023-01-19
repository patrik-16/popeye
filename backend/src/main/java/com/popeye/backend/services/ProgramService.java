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
            return generateAdvancedProgram(userinput);
        }
        return null; //TODO handle error exception in frontend
    }


    /***
     * Function that generates Sessions regarding daysPerWeek
     * @param userinput
     * @return a program that is designed concerning Goal, Priorities, daysPerWeek and timePerDay
     */
    private Program generateAdvancedProgram(Userinput userinput) {

        switch (userinput.getDaysPerWeek()) {
            case 1 -> {
                return null; //TODO handle error exception in frontend
            }
            case 2 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);

                Program program = new Program(List.of(sessionOne, sessionTwo));

                return sessionAdaption(userinput, program);
            }
            case 3 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);

                Program program =  new Program(List.of(sessionOne, sessionTwo, sessionThree));

                return sessionAdaption(userinput, program);
            }
            case 4 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);
                ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 4);

                Program program = new Program(List.of(sessionOne, sessionTwo, sessionThree, sessionFour));
                return sessionAdaption(userinput, program);
            }
            case 5 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);
                ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 4);
                ProgramSession sessionFive = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 5);

                Program program = new Program(List.of(sessionOne, sessionTwo, sessionThree, sessionFour, sessionFive));

                return sessionAdaption(userinput, program);
            }
        }
        return null; //TODO handle error exception in frontend
    }

    /***
     * This function does the actual fitting of one programSession -> adaptes time and rest ?? or reduces the amount
     * of Exercises ??? how can we implement this -> intensiveness and extra Gewichtung
     * @param userinput
     * @param currentProgram
     * @return the adapted Program
     */
    private Program sessionAdaption(Userinput userinput, Program currentProgram) {

        //for ProgramSession currentSession : Program<ProgramSession>

        Program adaptedProgram = currentProgram;

        return adaptedProgram;

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