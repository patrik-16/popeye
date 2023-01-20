package com.popeye.backend.entity;

import com.google.common.collect.Multimap;
import com.popeye.backend.enums.Bodypart;
import com.popeye.backend.enums.Difficulty;
import com.popeye.backend.enums.Goal;
import com.popeye.backend.enums.TimePerDay;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import static com.popeye.backend.constants.ProgramConstants.SECONDS_PER_SET;

@Getter
@Setter
public class ProgramSession {

    private List<Exercise> exerciseList;
    private int day;
    private Goal goal;

    public ProgramSession(List<Exercise> exerciseList, int day) {
        this.exerciseList = exerciseList;
        this.day = day;
    }

    public ProgramSession(List<Exercise> exerciseList, int day, Goal goal) {
        this.exerciseList = exerciseList;
        this.day = day;
        this.goal = goal;
    }

    public int getSecondsPerSession() {
        return this.exerciseList.stream()
            .mapToInt(exercise -> exercise.returnLengthInSeconds())
            .sum();
    }

    public void updateRestTime(int restTime) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            this.exerciseList.get(i).setRest(restTime);
        }
    }

    public void updateSetNumberByDifficulty(Difficulty difficulty, int newSetNumber) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            if (this.exerciseList.get(i).getDifficulty().equals(difficulty.getValue())) {
                this.exerciseList.get(i).setSets(newSetNumber);
            }
        }
    }

    public void updateSetNumberByDifficultyAndBodypart(Difficulty difficulty, Bodypart bodypart, int newSetNumber) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            if (this.exerciseList.get(i).getDifficulty() == difficulty) {
                if (this.exerciseList.get(i).getBodypartToEffectiveness().containsKey(bodypart.toString())) {
                    this.exerciseList.get(i).setSets(newSetNumber);
                }
            }
        }
    }

    public void deleteExerciseByBodypart(Bodypart bodypart) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            if (this.exerciseList.get(i).getBodypartToEffectiveness().containsKey(bodypart.toString())) {
                this.exerciseList.removeIf(exercise -> exercise.getBodypartToEffectiveness().containsKey(bodypart.toString()));
            }
        }
    }

    public ProgramSession sessionAdaptation(Userinput userinput, Multimap<String, Integer> filteringMap) {

        //TODO: saved all exercises of session created in ProgramService, if we need more exercises later for priority
        //List<Exercise> filteredList = new ArrayList<>(this.exerciseList);
        List<Exercise> filteredList = new ArrayList<>();

        filteringMap.forEach(
                (key, value)
                        -> {
                    for (int i = 0; i < this.exerciseList.size(); i++) {
                        if (this.exerciseList.get(i).getBodypartToEffectiveness().containsKey(key)) {
                            String effectiveness = this.exerciseList.get(i).getBodypartToEffectiveness().get(key);
                            if (Integer.valueOf(effectiveness).equals(value)) {
                                filteredList.add(this.exerciseList.get(i));
                            }
                        }
                    }
                }
        );

        //finally remove all existing exercises that aren't used for this program after filtering and add the filtered list in the current Program Session
        this.exerciseList.removeAll(exerciseList);
        this.exerciseList = filteredList;

        this.timeAdaptation(userinput);

        //TODO: delete this printing thing - just for me to check if its working
        for (Exercise currentExercise : this.exerciseList) {
            System.out.println("Day: " + this.getDay() + ": " + currentExercise.getName() + currentExercise.getReps() + currentExercise.getDifficulty() + " rest time: " + currentExercise.getRest());
        }
        System.out.println(this.getDay() + "Day - all in one: " + this.exerciseList.size() + "exercises and total time: " + this.getSecondsPerSession());

        return this;
    }

    /***
     * This function focus on the userinput timePerDay and does the corresponding calculation
     * @param userinput
     * @return the adapted Exercises
     */
    private void timeAdaptation(Userinput userinput) {
        System.out.println(userinput.getTimePerDay());
        switch (userinput.getTimePerDay()) {
            case FORTY -> this.updateRestTime(calculateRestTime(TimePerDay.FORTY));
            case SIXTY -> this.updateRestTime(calculateRestTime(TimePerDay.SIXTY));
            case EIGHTY -> this.updateRestTime(calculateRestTime(TimePerDay.EIGHTY));
            case UNLIMITED -> {
                System.out.println("This Person wants to train real hard");//TODO: add two more exercises? ;
                this.updateRestTime(calculateRestTime(TimePerDay.EIGHTY) + 10);
            }
        }
    }

    private int calculateRestTime(TimePerDay timePerDay) {
        int secondsExercises = 0;
        for(Exercise e : this.exerciseList) {
            secondsExercises += SECONDS_PER_SET * e.getSets();
        }
        // e.g. 6 exercises: 5 rests
        return ((timePerDay.getSeconds() - secondsExercises) / this.exerciseList.size() - 1) / 3;
    }
}
