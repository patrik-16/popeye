package com.popeye.backend.entity;

import com.popeye.backend.enums.Bodypart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class ExerciseToBodypart {
    private List<Exercise> exercise;
    private String unit;
    private List<Bodypart> bodypart;
    private Integer Priorities;

    // empty constructor needed later for firebase
    public ExerciseToBodypart() {
    }

    public ExerciseToBodypart(List<Exercise> exercise, String unit, List<Bodypart> bodypart, Integer priorities) {
        this.exercise = exercise;
        this.unit = unit;
        this.bodypart = bodypart;
        Priorities = priorities;
    }
}
