package com.popeye.backend.utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.popeye.backend.enums.Bodypart;
import org.springframework.stereotype.Service;

@Service
public class MapsForConditioningPrograms {
    public final Multimap<String, Integer> conditioningFullbody1;
    public final Multimap<String, Integer> conditioningFullbody2;

    public final Multimap<String, Integer> conditioningUpper;
    public final Multimap<String, Integer> conditioningLower;

    public final Multimap<String, Integer> conditioningUpper1;
    public final Multimap<String, Integer> conditioningLower1;
    public final Multimap<String, Integer> conditioningUpper2;
    public final Multimap<String, Integer> conditioningLower2;

    public final Multimap<String, Integer> conditioningPush1;
    public final Multimap<String, Integer> conditioningPull1;
    public final Multimap<String, Integer> conditioningLegs;
    public final Multimap<String, Integer> conditioningPush2;
    public final Multimap<String, Integer> conditioningPull2;

    public MapsForConditioningPrograms() {
        this.conditioningFullbody1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT, 90),
                new MapUtilsHelper(Bodypart.CHEST, 90),
                new MapUtilsHelper(Bodypart.LATS, 90),
                new MapUtilsHelper(Bodypart.QUADS, 90),
                new MapUtilsHelper(Bodypart.HAMS, 90),
                new MapUtilsHelper(Bodypart.ABS, 90),
                new MapUtilsHelper(Bodypart.CALVES, 90)
        );
        this.conditioningFullbody2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.SIDEDELT, 90),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 90),
                new MapUtilsHelper(Bodypart.TRAPS, 90),
                new MapUtilsHelper(Bodypart.QUADS, 90),
                new MapUtilsHelper(Bodypart.HAMS, 90),
                new MapUtilsHelper(Bodypart.BICEP, 90),
                new MapUtilsHelper(Bodypart.TRICEP, 90)
        );

        this.conditioningUpper = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT, 90),
                new MapUtilsHelper(Bodypart.REARDELT, 100),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 90),
                new MapUtilsHelper(Bodypart.LATS, 90),
                new MapUtilsHelper(Bodypart.TRAPS, 90),
                new MapUtilsHelper(Bodypart.BICEP, 90),
                new MapUtilsHelper(Bodypart.TRICEP, 90)
        );
        this.conditioningLower = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 90),
                new MapUtilsHelper(Bodypart.QUADS, 80),
                new MapUtilsHelper(Bodypart.HAMS, 90),
                new MapUtilsHelper(Bodypart.HAMS, 80),
                new MapUtilsHelper(Bodypart.CALVES, 100),
                new MapUtilsHelper(Bodypart.CALVES, 90),
                new MapUtilsHelper(Bodypart.ABS, 100)
        );
        this.conditioningUpper1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.SIDEDELT, 90),
                new MapUtilsHelper(Bodypart.REARDELT, 100),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 90),
                new MapUtilsHelper(Bodypart.LATS, 90),
                new MapUtilsHelper(Bodypart.TRAPS, 90),
                new MapUtilsHelper(Bodypart.BICEP, 90),
                new MapUtilsHelper(Bodypart.TRICEP, 90)
        );
        this.conditioningLower1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 90),
                new MapUtilsHelper(Bodypart.QUADS, 80),
                new MapUtilsHelper(Bodypart.HAMS, 90),
                new MapUtilsHelper(Bodypart.HAMS, 80),
                new MapUtilsHelper(Bodypart.CALVES, 100),
                new MapUtilsHelper(Bodypart.CALVES, 90),
                new MapUtilsHelper(Bodypart.ABS, 90)
        );
        this.conditioningUpper2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT, 80),
                new MapUtilsHelper(Bodypart.REARDELT, 90),
                new MapUtilsHelper(Bodypart.CHEST, 90),
                new MapUtilsHelper(Bodypart.LATS, 80),
                new MapUtilsHelper(Bodypart.TRAPS, 80),
                new MapUtilsHelper(Bodypart.BICEP, 80),
                new MapUtilsHelper(Bodypart.TRICEP, 80)
        );
        this.conditioningLower2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 90),
                new MapUtilsHelper(Bodypart.QUADS, 80),
                new MapUtilsHelper(Bodypart.HAMS, 90),
                new MapUtilsHelper(Bodypart.HAMS, 80),
                new MapUtilsHelper(Bodypart.CALVES, 100),
                new MapUtilsHelper(Bodypart.CALVES, 90),
                new MapUtilsHelper(Bodypart.ABS, 90)
        );

        this.conditioningPush1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT, 90),
                new MapUtilsHelper(Bodypart.CHEST, 90),
                new MapUtilsHelper(Bodypart.CHEST, 80),
                new MapUtilsHelper(Bodypart.SIDEDELT, 100),
                new MapUtilsHelper(Bodypart.TRICEP, 90),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 90),
                new MapUtilsHelper(Bodypart.ABS, 90)
        );
        this.conditioningPull1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.LATS, 90),
                new MapUtilsHelper(Bodypart.TRAPS, 90),
                new MapUtilsHelper(Bodypart.BICEP, 90),
                new MapUtilsHelper(Bodypart.BICEP, 80),
                new MapUtilsHelper(Bodypart.LATS, 80),
                new MapUtilsHelper(Bodypart.TRAPS, 80),
                new MapUtilsHelper(Bodypart.REARDELT, 90)
        );
        this.conditioningLegs = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS, 90),
                new MapUtilsHelper(Bodypart.QUADS, 80),
                new MapUtilsHelper(Bodypart.HAMS, 90),
                new MapUtilsHelper(Bodypart.HAMS, 80),
                new MapUtilsHelper(Bodypart.CALVES, 100),
                new MapUtilsHelper(Bodypart.CALVES, 90),
                new MapUtilsHelper(Bodypart.ABS, 100)
        );
        this.conditioningPush2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT, 80),
                new MapUtilsHelper(Bodypart.CHEST, 80),
                new MapUtilsHelper(Bodypart.SIDEDELT, 90),
                new MapUtilsHelper(Bodypart.TRICEP, 80),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 100),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 90),
                new MapUtilsHelper(Bodypart.ABS, 90)
        );
        this.conditioningPull2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.LATS, 90),
                new MapUtilsHelper(Bodypart.TRAPS, 90),
                new MapUtilsHelper(Bodypart.BICEP, 90),
                new MapUtilsHelper(Bodypart.BICEP, 80),
                new MapUtilsHelper(Bodypart.LATS, 80),
                new MapUtilsHelper(Bodypart.TRAPS, 80),
                new MapUtilsHelper(Bodypart.REARDELT, 90)
        );
    }
}
