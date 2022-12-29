package com.popeye.backend.services;

import com.popeye.backend.entity.ProgramSession;
import com.popeye.backend.repos.ExerciseRepository;
import com.popeye.backend.entity.Exercise;
import com.popeye.backend.entity.Userinput;
import com.popeye.backend.enums.Experience;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class ProgramService {
    @Autowired
    ExerciseRepository exerciseRepository;

    public List<ProgramSession> createBeginnerProgram(Userinput userinput) {
        List<Exercise> beginnerExercises = exerciseRepository.getAllExercisesByExperience(Experience.BEGINNER);

//will be important for ordering by goal
/*        for (int i = 0; i < beginnerExercises.size(); i++) {
            if (beginnerExercises.get(i).getBodypart().containsAll(userinput.getPriorities())) {

                Exercise foundEx = beginnerExercises.get(i);
                beginnerExercises.remove(beginnerExercises.get(i));
                beginnerExercises.add(0, foundEx);
            }
        }*/
        List<Exercise> dayOne = new ArrayList<>();
        List<Exercise> dayTwo = new ArrayList<>();
        List<Exercise> dayThree = new ArrayList<>();
        List<Exercise> dayFour = new ArrayList<>();
        List<Exercise> dayFive = new ArrayList<>();
        List<ProgramSession> finishedProgram = new ArrayList<>();

        ProgramSession sessionOne = new ProgramSession(dayOne, 1,0);
        ProgramSession sessionTwo = new ProgramSession(dayTwo, 2,0);
        ProgramSession sessionThree = new ProgramSession(dayThree, 3,0);
        ProgramSession sessionFour = new ProgramSession(dayFour, 4,0);
        ProgramSession sessionFive = new ProgramSession(dayFive, 5,0);

        float adaptTimePerSession = 0.0f; //seconds
        float timePerSet = 70.0f; //seconds

        // TO DO: create enums to compare
        if (userinput.getDaysPerWeek() == 1) {
            return Collections.emptyList(); //if empty list return error, or we could just not let him choose 1
        } else if (userinput.getDaysPerWeek() == 2) {
            for (int i = 0; i < beginnerExercises.size(); i++) {
                if (beginnerExercises.get(i).getDifficulty() == 3) {
                    dayOne.add(beginnerExercises.get(i));
                }
                if (beginnerExercises.get(i).getDifficulty() == 2) {
                    dayTwo.add(beginnerExercises.get(i));
                }
            }
//session one set time per session to user input
            for (int i = 0; i < dayOne.size(); i++) {
                adaptTimePerSession = dayOne.size()*dayOne.get(i).getSets()*timePerSet +
                dayOne.size()*dayOne.get(i).getSets()*dayOne.get(i).getRest();
            }
            sessionOne.setTimePerSession(adaptTimePerSession);

            if (userinput.getTimePerDay() == 40) { //2400 seconds
                if (sessionOne.getTimePerSession() > 2400) {
                    for (int i = 0; i < dayOne.size(); i++) {
                        dayOne.get(i).setRest(60.0f);
                    }
                    for (int i = 0; i < dayOne.size(); i++) {
                        adaptTimePerSession = dayOne.size()*dayOne.get(i).getSets()*timePerSet +
                                dayOne.size()*dayOne.get(i).getSets()*dayOne.get(i).getRest();
                    }
                    sessionOne.setTimePerSession(adaptTimePerSession);
                }
            }

//session two set time per session to user input
            for (int i = 0; i < dayTwo.size(); i++) {
                adaptTimePerSession = dayTwo.size()*dayTwo.get(i).getSets()*timePerSet +
                        dayTwo.size()*dayTwo.get(i).getSets()*dayTwo.get(i).getRest();
            }
            sessionTwo.setTimePerSession(adaptTimePerSession);

            finishedProgram.add(sessionOne);
            finishedProgram.add(sessionTwo);

        } else if (userinput.getDaysPerWeek() == 3) {
            for (int i = 0; i < beginnerExercises.size(); i++) {
                if (beginnerExercises.get(i).getDifficulty() == 3) {
                    dayOne.add(beginnerExercises.get(i));
                }
                if (beginnerExercises.get(i).getDifficulty() == 2) {
                    dayTwo.add(beginnerExercises.get(i));
                }
                if (beginnerExercises.get(i).getDifficulty() == 1) {
                    dayThree.add(beginnerExercises.get(i));
                }
            }
            finishedProgram.add(sessionOne);
            finishedProgram.add(sessionTwo);
            finishedProgram.add(sessionThree);

        } else if (userinput.getDaysPerWeek() == 4) {
            for (int i = 0; i < beginnerExercises.size(); i++) {
                if (beginnerExercises.get(i).getDifficulty() == 3) {
                    dayOne.add(beginnerExercises.get(i));
                }
                if (beginnerExercises.get(i).getDifficulty() == 2) {
                    dayTwo.add(beginnerExercises.get(i));
                }
                if (beginnerExercises.get(i).getDifficulty() == 1) {
                    dayThree.add(beginnerExercises.get(i));
                }
            }
            dayFour.addAll(dayOne);
            finishedProgram.add(sessionOne);
            finishedProgram.add(sessionTwo);
            finishedProgram.add(sessionThree);
            finishedProgram.add(sessionFour);

        } else if (userinput.getDaysPerWeek() > 4) {
            for (int i = 0; i < beginnerExercises.size(); i++) {
                if (beginnerExercises.get(i).getDifficulty() == 3) {
                    dayOne.add(beginnerExercises.get(i));
                }
                if (beginnerExercises.get(i).getDifficulty() == 2) {
                    dayTwo.add(beginnerExercises.get(i));
                }
                if (beginnerExercises.get(i).getDifficulty() == 1) {
                    dayThree.add(beginnerExercises.get(i));
                }
            }
            dayFour.addAll(dayOne);
            dayFive.addAll(dayTwo);
            finishedProgram.add(sessionOne);
            finishedProgram.add(sessionTwo);
            finishedProgram.add(sessionThree);
            finishedProgram.add(sessionFour);
            finishedProgram.add(sessionFive);
        }
        return finishedProgram;
    }
}