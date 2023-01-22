package com.popeye.backend.utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.popeye.backend.enums.Bodypart;
import org.springframework.stereotype.Service;

@Service
public class MapsForStrengthPrograms {
    public final Multimap<String, Integer> fullbodyStrength1;
    public final Multimap<String, Integer> fullbodyStrength2;
    public final Multimap<String, Integer> fullbodyStrength3;

    public final Multimap<String, Integer> strengthUpper1;
    public final Multimap<String, Integer> strengthLower1;
    public final Multimap<String, Integer> strengthUpper2;
    public final Multimap<String, Integer> strengthLower2;


    public MapsForStrengthPrograms() {
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
        this.fullbodyStrength3 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 100),
                new MapUtilsHelper(Bodypart.CHEST, 100),
                new MapUtilsHelper(Bodypart.LATS, 100),
                new MapUtilsHelper(Bodypart.BICEP, 90)
        );

        this.strengthUpper1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.CHEST, 100),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 100),
                new MapUtilsHelper(Bodypart.LATS, 100),
                new MapUtilsHelper(Bodypart.TRAPS, 100)
        );
        this.strengthLower1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 100),
                new MapUtilsHelper(Bodypart.QUADS, 80),
                new MapUtilsHelper(Bodypart.HAMS, 100),
                new MapUtilsHelper(Bodypart.HAMS, 80)
        );
        this.strengthUpper2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT, 100),
                new MapUtilsHelper(Bodypart.CHEST, 100),
                new MapUtilsHelper(Bodypart.LATS, 100),
                new MapUtilsHelper(Bodypart.TRAPS, 100)
        );
        this.strengthLower2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 100),
                new MapUtilsHelper(Bodypart.QUADS, 90),
                new MapUtilsHelper(Bodypart.HAMS, 100),
                new MapUtilsHelper(Bodypart.HAMS, 90)
        );
    }
}
