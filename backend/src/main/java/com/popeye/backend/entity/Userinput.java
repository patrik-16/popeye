package com.popeye.backend.entity;

import com.popeye.backend.enums.Bodypart;
import com.popeye.backend.enums.Experience;
import com.popeye.backend.enums.Goal;
import com.popeye.backend.enums.TimePerDay;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Userinput {
    private Integer age;
    private Experience experience;
    private List<Bodypart> priorities;
    private Integer daysPerWeek;
    private TimePerDay timePerDay;
    private Goal goal;
}
