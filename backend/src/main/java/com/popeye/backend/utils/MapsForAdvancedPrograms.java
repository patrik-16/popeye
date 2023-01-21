package com.popeye.backend.utils;

import com.google.common.collect.Multimap;
import com.popeye.backend.enums.Bodypart;
import org.springframework.stereotype.Service;

@Service
public class MapsForAdvancedPrograms {

    public final Multimap<String, Integer> fullbodyStrength1;
    public final Multimap<String, Integer> fullbodyStrength2;

    public MapsForAdvancedPrograms() {
        this.fullbodyStrength1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 100),
                new MapUtilsHelper(Bodypart.CHEST, 100),
                new MapUtilsHelper(Bodypart.LATS, 90),
                new MapUtilsHelper(Bodypart.BICEP, 90)
        );
        this.fullbodyStrength2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 90),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 100),
                new MapUtilsHelper(Bodypart.TRAPS, 90),
                new MapUtilsHelper(Bodypart.TRICEP, 90)
        );
    }
}