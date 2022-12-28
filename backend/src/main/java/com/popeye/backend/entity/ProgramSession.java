package com.popeye.backend.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProgramSession {

    private List<Exercise> exerciseList;
    private int day;
    private float timePerSession;

    public ProgramSession(List<Exercise> exerciseList, int day, float timePerSession) {
        this.exerciseList = exerciseList;
        this.day = day;
        this.timePerSession = timePerSession;
    }
}
