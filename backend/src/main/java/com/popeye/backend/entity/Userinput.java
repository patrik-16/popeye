package com.popeye.backend.entity;

import com.popeye.backend.enums.Bodypart;
import com.popeye.backend.enums.Experience;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Userinput {
    private Integer age;
    private Experience experience;
    private List<Bodypart> priorities;
    private Integer daysPerWeek;
    private float timePerDay;

}
