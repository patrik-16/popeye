package com.popeye.backend.entity;

import com.popeye.backend.constants.ProgramConstants;
import com.popeye.backend.enums.Bodypart;
import com.popeye.backend.enums.Experience;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Exercise {
    private Integer reps;
    private Float intensiveness;
    private Integer rest;
    private Integer sets;
    private String name;
    private List<Bodypart> bodypart;
    private Experience experience;
    private Integer difficulty;

    // empty constructor needed later for firebase
    public Exercise() {
    }

    public int returnLengthInSeconds() {
        return this.sets * ProgramConstants.SECONDS_PER_SET + this.sets * this.rest;
    }
}
