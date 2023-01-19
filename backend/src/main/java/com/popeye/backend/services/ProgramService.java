package com.popeye.backend.services;

import com.popeye.backend.entity.*;
import com.popeye.backend.enums.*;
import com.popeye.backend.repos.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.StreamingHttpOutputMessage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class ProgramService {
    @Autowired
    ExerciseRepository exerciseRepository;

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

        HashMap<String, Integer> fullbody = new HashMap<String, Integer>();
        fullbody.put("CHEST", 70);
        fullbody.put("LATS", 70);
        fullbody.put("TRAPS", 70);
        fullbody.put("QUADS", 70);
        fullbody.put("GLUTES", 70);
        fullbody.put("HAMS", 70);
        fullbody.put("CALVES", 70);
        fullbody.put("ABS", 70);
        HashMap<String, Integer> upper = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> lower = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> upper1 = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> lower1 = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> upper2 = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> lower2 = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> push1 = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> pull1 = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> push2 = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);
        HashMap<String, Integer> pull2 = new HashMap<String, Integer>();
        fullbody.put("QUADS", 70);
        fullbody.put("TRICEP", 70);
        fullbody.put("CHEST", 70);


        switch (userinput.getDaysPerWeek()) {
            case 1 -> {
                return null; //TODO handle error exception in frontend
            }
            case 2 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);

                ProgramSession filteredSessionOne = sessionAdaptation(userinput, sessionOne, fullbody);
                ProgramSession filteredSessionTwo = sessionAdaptation(userinput, sessionTwo, fullbody);
                Program program = new Program(List.of(filteredSessionOne, filteredSessionTwo));

                return programAdaptation(userinput, program);
            }
            case 3 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);

                ProgramSession filteredSessionOne = sessionAdaptation(userinput, sessionOne, upper);
                ProgramSession filteredSessionTwo = sessionAdaptation(userinput, sessionTwo, lower);
                ProgramSession filteredSessionThree = sessionAdaptation(userinput, sessionThree, fullbody);
                Program program =  new Program(List.of(filteredSessionOne, filteredSessionTwo, filteredSessionThree));

                return programAdaptation(userinput, program);
            }
            case 4 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);
                ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 4);

                ProgramSession filteredSessionOne = sessionAdaptation(userinput, sessionOne, upper1);
                ProgramSession filteredSessionTwo = sessionAdaptation(userinput, sessionTwo, lower1);
                ProgramSession filteredSessionThree = sessionAdaptation(userinput, sessionThree, upper2);
                ProgramSession filteredSessionFour = sessionAdaptation(userinput, sessionFour, lower2);
                Program program =  new Program(List.of(filteredSessionOne, filteredSessionTwo, filteredSessionThree, filteredSessionFour));

                return programAdaptation(userinput, program);
            }
            case 5 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);
                ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 4);
                ProgramSession sessionFive = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 5);

                ProgramSession filteredSessionOne = sessionAdaptation(userinput, sessionOne, push1);
                ProgramSession filteredSessionTwo = sessionAdaptation(userinput, sessionTwo, push2);
                ProgramSession filteredSessionThree = sessionAdaptation(userinput, sessionThree, push1);
                ProgramSession filteredSessionFour = sessionAdaptation(userinput, sessionFour, push2);
                ProgramSession filteredSessionFive = sessionAdaptation(userinput, sessionFive, pull2);

                Program program =  new Program(List.of(filteredSessionOne, filteredSessionTwo, filteredSessionThree, filteredSessionFour, filteredSessionFive));

                return programAdaptation(userinput, program);
            }
        }
        return null; //TODO handle error exception in frontend
    }

    private ProgramSession sessionAdaptation(Userinput userinput, ProgramSession programSession, HashMap<String, Integer> filteringMap) {

        List<Exercise> filteredExercises = new ArrayList<>();

        filteringMap.forEach(
            (key, value)
                ->  {
                    for (int i = 0; i < programSession.getExerciseList().size(); i++) {
                        if (programSession.getExerciseList().get(i).getBodypartToEffectiveness().containsKey(key)) {
                            String effectivness = programSession.getExerciseList().get(i).getBodypartToEffectiveness().get(key);
                            if (Integer.valueOf(effectivness) >= Integer.valueOf(value)) {
                                filteredExercises.add(programSession.getExerciseList().get(i));

                            }
                        }
                      }
                    System.out.println(key + " " + value);
                }
        );

        return new ProgramSession(filteredExercises, programSession.getDay());
    }


    /***
     * This function does the actual fitting of one programSession -> adaptes time and rest ?? or reduces the amount
     * of Exercises ??? how can we implement this -> intensiveness and extra Gewichtung
     * @param userinput
     * @param currentProgram
     * @return the adapted Program
     */
    private Program programAdaptation(Userinput userinput, Program currentProgram) {

        List<Exercise> remainingExercises;
        //String priority = userinput.

        //one ProgramSession per day
        for (ProgramSession currentSession : currentProgram.getProgram()) {

            for(int i = userinput.getPriorities().size(); i > 0; i--) {
                Bodypart bodypart = userinput.getPriorities().get(i - 1);
                //currentSession = currentSession.stream().

            }
            int countTimePerSession = 0;
                for (Exercise currentExercise : currentSession.getExerciseList()) {
                    countTimePerSession += currentExercise.returnLengthInSeconds();

                    //check time:
                    if(userinput.getTimePerDay().getSeconds() < countTimePerSession) {

                    }


                    System.out.println("Day: " + currentSession.getDay() + ": " + countTimePerSession);
                }
        }

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