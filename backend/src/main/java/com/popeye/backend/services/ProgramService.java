package com.popeye.backend.services;

import com.popeye.backend.entity.*;
import com.popeye.backend.enums.Difficulty;
import com.popeye.backend.enums.TimePerDay;
import com.popeye.backend.repos.ExerciseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgramService {
    @Autowired
    ExerciseRepository exerciseRepository;

    public Program createBeginnerProgram(Userinput userinput) {

/*will be important for ordering by goal
        for (int i = 0; i < beginnerExercises.size(); i++) {
            if (beginnerExercises.get(i).getBodypart().containsAll(userinput.getPriorities())) {

                Exercise foundEx = beginnerExercises.get(i);
                beginnerExercises.remove(beginnerExercises.get(i));
                beginnerExercises.add(0, foundEx);
            }
        }*/

        // TODO: create enums to compare
        if (userinput.getDaysPerWeek() == 1) {
            return null; //TODO handle error exception in frontend
        } else if (userinput.getDaysPerWeek() == 2) {
            return generateTwoDayBeginnerProgram(userinput);
        } else if (userinput.getDaysPerWeek() == 3) {
            return generateThreeDayBeginnerProgram(userinput);
        } else if (userinput.getDaysPerWeek() == 4) {
            return generateFourDayBeginnerProgram(userinput);
        } else if (userinput.getDaysPerWeek() == 5) {
            return generateFiveDayBeginnerProgram(userinput);
        }
        return null; //TODO handle error exception in frontend
    }

    private Program generateTwoDayBeginnerProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.HARD), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 2);

        //session one set time per session to user input
        if (userinput.getTimePerDay() == TimePerDay.FOURTY) {
            if (sessionOne.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionOne.updateRestTime(60);
            }
            if (sessionTwo.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionTwo.updateRestTime(60);
            }
        }

        return new Program(List.of(sessionOne, sessionTwo));
    }
    private Program generateThreeDayBeginnerProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.HARD), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 2);
        ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.EASY), 3);

        //session one set time per session to user input
        if (userinput.getTimePerDay() == TimePerDay.FOURTY) {
            if (sessionOne.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionOne.updateRestTime(60);
            }
            if (sessionTwo.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionTwo.updateRestTime(60);
            }
            if (sessionThree.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionThree.updateRestTime(60);
            }
        }
        return new Program(List.of(sessionOne, sessionTwo, sessionThree));
    }

    private Program generateFourDayBeginnerProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.HARD), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 2);
        ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.EASY), 3);
        ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 4);

        //session one set time per session to user input
        if (userinput.getTimePerDay() == TimePerDay.FOURTY) {
            if (sessionOne.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionOne.updateRestTime(60);
            }
            if (sessionTwo.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionTwo.updateRestTime(60);
            }
            if (sessionThree.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionThree.updateRestTime(60);
            }
            if (sessionFour.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionFour.updateRestTime(60);
            }
        }
        return new Program(List.of(sessionOne, sessionTwo, sessionThree, sessionFour));
    }

    private Program generateFiveDayBeginnerProgram(Userinput userinput) {
        ProgramSession sessionOne = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.HARD), 1);
        ProgramSession sessionTwo = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 2);
        ProgramSession sessionThree = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.EASY), 3);
        ProgramSession sessionFour = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.MEDIUM), 4);
        ProgramSession sessionFive = new ProgramSession(exerciseRepository.getAllBeginnerExercisesByDifficulty(Difficulty.EASY), 5);

        //session one set time per session to user input
        if (userinput.getTimePerDay() == TimePerDay.FOURTY) {
            if (sessionOne.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionOne.updateRestTime(60);
            }
            if (sessionTwo.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionTwo.updateRestTime(60);
            }
            if (sessionThree.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionThree.updateRestTime(60);
            }
            if (sessionFour.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionFour.updateRestTime(60);
            }
            if (sessionFive.getSecondsPerSession() > TimePerDay.FOURTY.getSeconds()) {
                sessionFive.updateRestTime(60);
            }
        }
        return new Program(List.of(sessionOne, sessionTwo, sessionThree, sessionFour));
    }
}