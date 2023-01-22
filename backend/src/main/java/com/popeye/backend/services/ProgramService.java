package com.popeye.backend.services;

import com.popeye.backend.entity.*;
import com.popeye.backend.enums.*;
import com.popeye.backend.repos.ExerciseRepository;
import com.popeye.backend.utils.MapsForConditioningPrograms;
import com.popeye.backend.utils.MapsForHypertrophyPrograms;
import com.popeye.backend.utils.MapsForStrengthPrograms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProgramService {
    @Autowired
    ExerciseRepository exerciseRepository;
    @Autowired
    MapsForHypertrophyPrograms mapsForHypertrophyPrograms;
    @Autowired
    MapsForStrengthPrograms mapsForStrengthPrograms;
    @Autowired
    MapsForConditioningPrograms mapsForConditioningPrograms;

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
     * @param userinput from the user
     * @return a program that is designed concerning Goal, Priorities, daysPerWeek and timePerDay
     */
    private Program generateAdvancedProgram(Userinput userinput) {

        switch (userinput.getDaysPerWeek()) {
            case 1 -> {
                //will not happen, because user can only decide between 2 and 5
                return null; //TODO handle error exception in frontend
            }
            case 2 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);

                if (userinput.getGoal().equals(Goal.STRENGTH)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForStrengthPrograms.fullbodyStrength1), sessionTwo.sessionAdaptation(userinput, mapsForStrengthPrograms.fullbodyStrength2)));
                }

                if (userinput.getGoal().equals(Goal.CONDITIONING)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningFullbody1), sessionTwo.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningFullbody2)));
                }

                return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForHypertrophyPrograms.fullbody1), sessionTwo.sessionAdaptation(userinput, mapsForHypertrophyPrograms.fullbody2)));
            }
            case 3 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.MEDIUM, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);

                if (userinput.getGoal().equals(Goal.STRENGTH)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForStrengthPrograms.fullbodyStrength1), sessionTwo.sessionAdaptation(userinput, mapsForStrengthPrograms.fullbodyStrength2), sessionThree.sessionAdaptation(userinput, mapsForStrengthPrograms.fullbodyStrength3)));
                }

                if (userinput.getGoal().equals(Goal.CONDITIONING)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningUpper), sessionTwo.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningLower), sessionThree.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningFullbody1)));
                }

                return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForHypertrophyPrograms.upper), sessionTwo.sessionAdaptation(userinput, mapsForHypertrophyPrograms.lower), sessionThree.sessionAdaptation(userinput, mapsForHypertrophyPrograms.fullbody1)));
            }
            case 4 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.EASY, userinput.getGoal()), 3);
                ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.EASY, userinput.getGoal()), 4);

                if (userinput.getGoal().equals(Goal.STRENGTH)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForStrengthPrograms.strengthUpper1), sessionTwo.sessionAdaptation(userinput, mapsForStrengthPrograms.strengthLower1), sessionThree.sessionAdaptation(userinput, mapsForStrengthPrograms.strengthUpper2),
                            sessionFour.sessionAdaptation(userinput, mapsForStrengthPrograms.strengthLower2)));
                }

                if (userinput.getGoal().equals(Goal.CONDITIONING)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningUpper1), sessionTwo.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningLower1), sessionThree.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningUpper2),
                            sessionFour.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningLower2)));
                }

                return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForHypertrophyPrograms.upper1), sessionTwo.sessionAdaptation(userinput, mapsForHypertrophyPrograms.lower1), sessionThree.sessionAdaptation(userinput, mapsForHypertrophyPrograms.upper2),
                        sessionFour.sessionAdaptation(userinput, mapsForHypertrophyPrograms.lower2)));
            }
            case 5 -> {
                ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 1);
                ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 2);
                ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.HARD, userinput.getGoal()), 3);
                ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.EASY, userinput.getGoal()), 4);
                ProgramSession sessionFive = new ProgramSession(exerciseRepository.getAllAdvancedExercisesByDifficultyAndGoal(Difficulty.EASY, userinput.getGoal()), 5);

                if (userinput.getGoal().equals(Goal.STRENGTH)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForStrengthPrograms.fullbodyStrength1), sessionTwo.sessionAdaptation(userinput, mapsForStrengthPrograms.fullbodyStrength2), sessionThree.sessionAdaptation(userinput, mapsForStrengthPrograms.fullbodyStrength3),
                            sessionFour.sessionAdaptation(userinput, mapsForStrengthPrograms.strengthUpper2), sessionFive.sessionAdaptation(userinput, mapsForStrengthPrograms.strengthLower2)));
                }

                if (userinput.getGoal().equals(Goal.CONDITIONING)) {
                    return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningPush1), sessionTwo.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningPull1), sessionThree.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningLegs),
                            sessionFour.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningPush2), sessionFive.sessionAdaptation(userinput, mapsForConditioningPrograms.conditioningPull2)));
                }

                return new Program(List.of(sessionOne.sessionAdaptation(userinput, mapsForHypertrophyPrograms.push1), sessionTwo.sessionAdaptation(userinput, mapsForHypertrophyPrograms.pull1), sessionThree.sessionAdaptation(userinput, mapsForHypertrophyPrograms.legs),
                        sessionFour.sessionAdaptation(userinput, mapsForHypertrophyPrograms.push2), sessionFive.sessionAdaptation(userinput, mapsForHypertrophyPrograms.pull2)));
            }
        }
        return null; //TODO handle error exception in frontend
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