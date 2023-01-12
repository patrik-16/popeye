package com.popeye.backend.entity;

import com.popeye.backend.enums.Bodypart;
import com.popeye.backend.enums.Difficulty;
import com.popeye.backend.enums.Goal;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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
                //if (this.exerciseList.get(i).getBodypart().contains(bodypart)) {
                //    this.exerciseList.get(i).setSets(newSetNumber);
                //}
            }
        }
    }

    public void deleteExerciseByBodypart(Bodypart bodypart) {
        for (int i = 0; i < this.exerciseList.size(); i++) {
            //if (this.exerciseList.get(i).getBodypart().contains(bodypart)) {
            //    this.exerciseList.removeIf(exercise -> exercise.getBodypart().contains(bodypart));
            //}
        }
    }
}
