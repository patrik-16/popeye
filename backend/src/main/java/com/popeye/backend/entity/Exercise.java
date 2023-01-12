package com.popeye.backend.entity;

import com.popeye.backend.constants.ProgramConstants;
import com.popeye.backend.enums.Bodypart;
import com.popeye.backend.enums.Difficulty;
import com.popeye.backend.enums.Experience;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;

@Getter
@Setter
@ToString
public class Exercise {
    private Integer reps;
    private Float intensiveness;
    private Integer rest;
    private Integer sets;
    private String name;
    //private List<Bodypart> bodypart; we do hashmaps now
    private HashMap<Bodypart, Float> bodypartToEffectiveness;
    private Experience experience;
    private Difficulty difficulty;


    // empty constructor needed later for firebase
    public Exercise() {
    }

    public int returnLengthInSeconds() {
        return this.sets * ProgramConstants.SECONDS_PER_SET + this.sets * this.rest;
    }
}
