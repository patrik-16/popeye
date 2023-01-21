package com.popeye.backend.services;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
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
     * Function that generates Sessions regarding daysPerWeek and fullbody, lower, upper...
     * @param: userinput
     * @return a program that is designed concerning Goal, Priorities, daysPerWeek and timePerDay
     */
    private Program generateAdvancedProgram(Userinput userinput) {

        Multimap<String, Integer> fullbody1 = ArrayListMultimap.create();
        fullbody1.put("FRONTDELT", 100);
        fullbody1.put("CHEST", 100);
        fullbody1.put("LATS", 100);
        fullbody1.put("QUADS", 100);
        fullbody1.put("HAMS", 100);
        fullbody1.put("ABS", 100);
        fullbody1.put("CALVES", 100);
        Multimap<String, Integer> fullbody2 = ArrayListMultimap.create();
        fullbody2.put("SIDEDELT", 100);
        fullbody2.put("UPPERCHEST", 100);
        fullbody2.put("TRAPS", 100);
        fullbody2.put("QUADS", 100);
        fullbody2.put("HAMS", 100);
        fullbody2.put("BICEP", 100);
        fullbody2.put("TRICEP", 100);

        Multimap<String, Integer> upper = ArrayListMultimap.create();
        upper.put("FRONTDELT", 100);
        upper.put("REARDELT", 100);
        upper.put("UPPERCHEST", 100);
        upper.put("LATS", 100);
        upper.put("TRAPS", 100);
        upper.put("BICEP", 100);
        upper.put("TRICEP", 100);
        Multimap<String, Integer> lower = ArrayListMultimap.create();
        lower.put("QUADS", 100);
        lower.put("QUADS", 90);
        lower.put("HAMS", 100);
        lower.put("HAMS", 90);
        lower.put("CALVES", 100);
        lower.put("CALVES", 90);
        lower.put("ABS", 100);

        Multimap<String, Integer> upper1 = ArrayListMultimap.create();
        upper1.put("SIDEDELT", 100);
        upper1.put("REARDELT", 100);
        upper1.put("UPPERCHEST", 100);
        upper1.put("LATS", 90);
        upper1.put("TRAPS", 90);
        upper1.put("BICEP", 100);
        upper1.put("TRICEP", 100);
        Multimap<String, Integer> lower1 = ArrayListMultimap.create();
        lower1.put("QUADS", 90);
        lower1.put("QUADS", 80);
        lower1.put("HAMS", 100);
        lower1.put("HAMS", 90);
        lower1.put("HAMS", 80);
        lower1.put("CALVES", 100);
        lower1.put("CALVES", 90);
        Multimap<String, Integer> upper2 = ArrayListMultimap.create();
        upper2.put("FRONTDELT", 90);
        upper2.put("REARDELT", 90);
        upper2.put("CHEST", 90);
        upper2.put("LATS", 100);
        upper2.put("TRAPS", 100);
        upper2.put("BICEP", 80);
        upper2.put("TRICEP", 80);
        Multimap<String, Integer> lower2 = ArrayListMultimap.create();
        lower2.put("QUADS", 100);
        lower2.put("QUADS", 90);
        lower2.put("QUADS", 80);
        lower2.put("HAMS", 90);
        lower2.put("HAMS", 80);
        lower2.put("CALVES", 100);
        lower2.put("CALVES", 90);

        Multimap<String, Integer> push1 = ArrayListMultimap.create();
        push1.put("FRONTDELT", 100);
        push1.put("CHEST", 100);
        push1.put("CHEST", 90);
        push1.put("SIDEDELT", 100);
        push1.put("TRICEP", 100);
        push1.put("UPPERCHEST", 100);
        push1.put("ABS", 100);
        Multimap<String, Integer> pull1 = ArrayListMultimap.create();
        pull1.put("LATS", 100);
        pull1.put("TRAPS", 100);
        pull1.put("BICEP", 100);
        pull1.put("BICEP", 90);
        pull1.put("LATS", 90);
        pull1.put("TRAPS", 90);
        pull1.put("REARDELT", 90);
        Multimap<String, Integer> legs = ArrayListMultimap.create();
        legs.put("QUADS", 100);
        legs.put("QUADS", 90);
        legs.put("QUADS", 80);
        legs.put("HAMS", 100);
        legs.put("HAMS", 90);
        legs.put("HAMS", 80);
        legs.put("CALVES", 100);
        Multimap<String, Integer> push2 = ArrayListMultimap.create();
        push2.put("FRONTDELT", 80);
        push2.put("CHEST", 80);
        push2.put("SIDEDELT", 90);
        push2.put("TRICEP", 90);
        push2.put("UPPERCHEST", 100);
        push2.put("UPPERCHEST", 90);
        push2.put("ABS", 90);
        Multimap<String, Integer> pull2 = ArrayListMultimap.create();
        pull2.put("LATS", 100);
        pull2.put("TRAPS", 100);
        pull2.put("BICEP", 90);
        pull2.put("BICEP", 80);
        pull2.put("LATS", 80);
        pull2.put("TRAPS", 80);
        pull2.put("REARDELT", 90);

        Multimap<String, Integer> fullbodyStrength1 = ArrayListMultimap.create();
        fullbodyStrength1.put("QUADS", 100);
        fullbodyStrength1.put("CHEST", 100);
        fullbodyStrength1.put("LATS", 90);
        fullbodyStrength1.put("BICEP", 90);
        Multimap<String, Integer> fullbodyStrength2 = ArrayListMultimap.create();
        fullbodyStrength2.put("QUADS", 90);
        fullbodyStrength2.put("UPPERCHEST", 100);
        fullbodyStrength2.put("TRAPS", 90);
        fullbodyStrength2.put("TRICEP", 90);
        Multimap<String, Integer> fullbodyStrength3 = ArrayListMultimap.create();
        fullbodyStrength3.put("QUADS", 100);
        fullbodyStrength3.put("CHEST", 100);
        fullbodyStrength3.put("LATS", 100);
        fullbodyStrength3.put("BICEP", 90);

        Multimap<String, Integer> strengthUpper1= ArrayListMultimap.create();
        strengthUpper1.put("CHEST", 100);
        strengthUpper1.put("UPPERCHEST", 100);
        strengthUpper1.put("LATS", 100);
        strengthUpper1.put("TRAPS", 100);
        Multimap<String, Integer> strengthLower1 = ArrayListMultimap.create();
        strengthLower1.put("QUADS", 100);
        strengthLower1.put("QUADS", 80);
        strengthLower1.put("HAMS", 100);
        strengthLower1.put("HAMS", 80);
        Multimap<String, Integer> strengthUpper2= ArrayListMultimap.create();
        strengthUpper2.put("FRONTDELT", 100);
        strengthUpper2.put("CHEST", 100);
        strengthUpper2.put("LATS", 100);
        strengthUpper2.put("TRAPS", 100);
        Multimap<String, Integer> strengthLower2 = ArrayListMultimap.create();
        strengthLower2.put("QUADS", 100);
        strengthLower2.put("QUADS", 90);
        strengthLower2.put("HAMS", 100);
        strengthLower2.put("HAMS", 90);

        Multimap<String, Integer> conditioningFullbody1 = ArrayListMultimap.create();
        conditioningFullbody1.put("FRONTDELT", 90);
        conditioningFullbody1.put("CHEST", 90);
        conditioningFullbody1.put("LATS", 90);
        conditioningFullbody1.put("QUADS", 90);
        conditioningFullbody1.put("HAMS", 90);
        conditioningFullbody1.put("ABS", 90);
        conditioningFullbody1.put("CALVES", 90);
        Multimap<String, Integer> conditioningFullbody2 = ArrayListMultimap.create();
        conditioningFullbody2.put("SIDEDELT", 90);
        conditioningFullbody2.put("UPPERCHEST", 90);
        conditioningFullbody2.put("TRAPS", 90);
        conditioningFullbody2.put("QUADS", 90);
        conditioningFullbody2.put("HAMS", 90);
        conditioningFullbody2.put("BICEP", 90);
        conditioningFullbody2.put("TRICEP", 90);

        Multimap<String, Integer> conditioningUpper = ArrayListMultimap.create();
        conditioningUpper.put("FRONTDELT", 90);
        conditioningUpper.put("REARDELT", 100);
        conditioningUpper.put("UPPERCHEST", 90);
        conditioningUpper.put("LATS", 90);
        conditioningUpper.put("TRAPS", 90);
        conditioningUpper.put("BICEP", 90);
        conditioningUpper.put("TRICEP", 90);
        Multimap<String, Integer> conditioningLower = ArrayListMultimap.create();
        conditioningLower.put("QUADS", 90);
        conditioningLower.put("QUADS", 80);
        conditioningLower.put("HAMS", 90);
        conditioningLower.put("HAMS", 80);
        conditioningLower.put("CALVES", 100);
        conditioningLower.put("CALVES", 90);
        conditioningLower.put("ABS", 100);

        Multimap<String, Integer> conditioningUpper1 = ArrayListMultimap.create();
        conditioningUpper1.put("SIDEDELT", 90);
        conditioningUpper1.put("REARDELT", 100);
        conditioningUpper1.put("UPPERCHEST", 90);
        conditioningUpper1.put("LATS", 90);
        conditioningUpper1.put("TRAPS", 90);
        conditioningUpper1.put("BICEP", 90);
        conditioningUpper1.put("TRICEP", 90);
        Multimap<String, Integer> conditioningLower1 = ArrayListMultimap.create();
        conditioningLower1.put("QUADS", 90);
        conditioningLower1.put("QUADS", 80);
        conditioningLower1.put("HAMS", 90);
        conditioningLower1.put("HAMS", 80);
        conditioningLower1.put("CALVES", 100);
        conditioningLower1.put("CALVES", 90);
        conditioningLower1.put("ABS", 90);
        Multimap<String, Integer> conditioningUpper2 = ArrayListMultimap.create();
        conditioningUpper2.put("FRONTDELT", 80);
        conditioningUpper2.put("REARDELT", 90);
        conditioningUpper2.put("CHEST", 90);
        conditioningUpper2.put("LATS", 80);
        conditioningUpper2.put("TRAPS", 80);
        conditioningUpper2.put("BICEP", 80);
        conditioningUpper2.put("TRICEP", 80);
        Multimap<String, Integer> conditioningLower2 = ArrayListMultimap.create();
        conditioningLower2.put("QUADS", 90);
        conditioningLower2.put("QUADS", 80);;
        conditioningLower2.put("HAMS", 90);
        conditioningLower2.put("HAMS", 80);
        conditioningLower2.put("CALVES", 100);
        conditioningLower2.put("CALVES", 90);
        conditioningLower2.put("ABS", 90);

        Multimap<String, Integer> conditioningPush1 = ArrayListMultimap.create();
        conditioningPush1.put("FRONTDELT", 90);
        conditioningPush1.put("CHEST", 90);
        conditioningPush1.put("CHEST", 80);
        conditioningPush1.put("SIDEDELT", 100);
        conditioningPush1.put("TRICEP", 90);
        conditioningPush1.put("UPPERCHEST", 90);
        conditioningPush1.put("ABS", 90);
        Multimap<String, Integer> conditioningPull1 = ArrayListMultimap.create();
        conditioningPull1.put("LATS", 90);
        conditioningPull1.put("TRAPS", 90);
        conditioningPull1.put("BICEP", 90);
        conditioningPull1.put("BICEP", 80);
        conditioningPull1.put("LATS", 80);
        conditioningPull1.put("TRAPS", 80);
        conditioningPull1.put("REARDELT", 90);
        Multimap<String, Integer> conditioningLegs = ArrayListMultimap.create();
        conditioningLegs.put("QUADS", 90);
        conditioningLegs.put("QUADS", 80);
        conditioningLegs.put("HAMS", 90);
        conditioningLegs.put("HAMS", 80);
        conditioningLegs.put("CALVES", 100);
        conditioningLegs.put("CALVES", 90);
        conditioningLegs.put("ABS", 100);
        Multimap<String, Integer> conditioningPush2 = ArrayListMultimap.create();
        conditioningPush2.put("FRONTDELT", 80);
        conditioningPush2.put("CHEST", 80);
        conditioningPush2.put("SIDEDELT", 90);
        conditioningPush2.put("TRICEP", 80);
        conditioningPush2.put("UPPERCHEST", 100);
        conditioningPush2.put("UPPERCHEST", 90);
        conditioningPush2.put("ABS", 90);
        Multimap<String, Integer> conditioningPull2 = ArrayListMultimap.create();
        conditioningPull2.put("LATS", 90);
        conditioningPull2.put("TRAPS", 90);
        conditioningPull2.put("BICEP", 90);
        conditioningPull2.put("BICEP", 80);
        conditioningPull2.put("LATS", 80);
        conditioningPull2.put("TRAPS", 80);
        conditioningPull2.put("REARDELT", 90);

        switch (userinput.getDaysPerWeek()) {
            case 1 -> {
                return null; //TODO handle error exception in frontend
            }
            case 2 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);

                if (userinput.getGoal().equals(Goal.STRENGTH)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, fullbodyStrength1), sessionTwo.sessionAdaptation(userinput, fullbodyStrength2)));
                }

                if (userinput.getGoal().equals(Goal.CONDITIONING)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, conditioningFullbody1), sessionTwo.sessionAdaptation(userinput, conditioningFullbody2)));
                }

                return new Program(List.of(sessionOne.sessionAdaptation(userinput, fullbody1), sessionTwo.sessionAdaptation(userinput, fullbody2)));
            }
            case 3 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);

                if (userinput.getGoal().equals(Goal.STRENGTH)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, fullbodyStrength1), sessionTwo.sessionAdaptation(userinput, fullbodyStrength2), sessionThree.sessionAdaptation(userinput, fullbodyStrength3)));
                }

                if (userinput.getGoal().equals(Goal.CONDITIONING)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, conditioningUpper), sessionTwo.sessionAdaptation(userinput, conditioningLower), sessionThree.sessionAdaptation(userinput, conditioningFullbody1)));
                }

                return new Program(List.of(sessionOne.sessionAdaptation(userinput, upper), sessionTwo.sessionAdaptation(userinput, lower), sessionThree.sessionAdaptation(userinput, fullbody1)));
            }
            case 4 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.EASY, userinput.getGoal()), 3);
                ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.EASY, userinput.getGoal()), 4);

                if (userinput.getGoal().equals(Goal.STRENGTH)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, strengthUpper1), sessionTwo.sessionAdaptation(userinput, strengthLower1), sessionThree.sessionAdaptation(userinput, strengthUpper2),
                            sessionFour.sessionAdaptation(userinput, strengthLower2)));
                }

                if (userinput.getGoal().equals(Goal.CONDITIONING)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, conditioningUpper1), sessionTwo.sessionAdaptation(userinput, conditioningLower1), sessionThree.sessionAdaptation(userinput, conditioningUpper2),
                            sessionFour.sessionAdaptation(userinput, conditioningLower2)));
                }

                return new Program(List.of(sessionOne.sessionAdaptation(userinput, upper1), sessionTwo.sessionAdaptation(userinput, lower1), sessionThree.sessionAdaptation(userinput, upper2),
                        sessionFour.sessionAdaptation(userinput, lower2)));
            }
            case 5 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);
                ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.EASY, userinput.getGoal()), 4);
                ProgramSession sessionFive = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.EASY, userinput.getGoal()), 5);

                if (userinput.getGoal().equals(Goal.STRENGTH)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, fullbodyStrength1), sessionTwo.sessionAdaptation(userinput, fullbodyStrength2), sessionThree.sessionAdaptation(userinput, fullbodyStrength3),
                            sessionFour.sessionAdaptation(userinput, strengthUpper2), sessionFive.sessionAdaptation(userinput, strengthLower2)));
                }

                if (userinput.getGoal().equals(Goal.CONDITIONING)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, conditioningPush1), sessionTwo.sessionAdaptation(userinput, conditioningPull1), sessionThree.sessionAdaptation(userinput, conditioningLegs),
                            sessionFour.sessionAdaptation(userinput, conditioningPush2), sessionFive.sessionAdaptation(userinput, conditioningPull2)));
                }

                return new Program(List.of(sessionOne.sessionAdaptation(userinput, push1), sessionTwo.sessionAdaptation(userinput, pull1), sessionThree.sessionAdaptation(userinput, legs),
                        sessionFour.sessionAdaptation(userinput, push2), sessionFive.sessionAdaptation(userinput, pull2)));
            }
        }
        return null; //TODO handle error exception in frontend
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