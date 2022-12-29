package com.popeye.backend.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProgramSession {

    private List<Exercise> exerciseList;
    private int day;

    public ProgramSession(List<Exercise> exerciseList, int day) {
        this.exerciseList = exerciseList;
        this.day = day;
    }

    public int getSecondsPerSession() {
        return exerciseList.stream()
            .mapToInt(exercise -> exercise.getLenghtInSeconds())
            .sum();
    }

    public void updateRestTime(int restTime) {
        for (int i = 0; i < exerciseList.size(); i++) {
            exerciseList.get(i).setRest(restTime);
        }
    }
}
