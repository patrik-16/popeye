package com.popeye.backend.utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.popeye.backend.enums.Bodypart;
import org.springframework.stereotype.Service;

@Service
public class MapsForHypertrophyPrograms {

    public final Multimap<String, Integer> fullbody1;
    public final Multimap<String, Integer> fullbody2;

    public final Multimap<String, Integer> upper;
    public final Multimap<String, Integer> lower;

    public final Multimap<String, Integer> upper1;
    public final Multimap<String, Integer> lower1;
    public final Multimap<String, Integer> upper2;
    public final Multimap<String, Integer> lower2;

    public final Multimap<String, Integer> push1;
    public final Multimap<String, Integer> pull1;
    public final Multimap<String, Integer> legs;
    public final Multimap<String, Integer> push2;
    public final Multimap<String, Integer> pull2;



    public MapsForHypertrophyPrograms() {
        this.fullbody1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT, 100),
                new MapUtilsHelper(Bodypart.CHEST, 100),
                new MapUtilsHelper(Bodypart.LATS, 100),
                new MapUtilsHelper(Bodypart.QUADS, 100),
                new MapUtilsHelper(Bodypart.HAMS, 100),
                new MapUtilsHelper(Bodypart.ABS, 100),
                new MapUtilsHelper(Bodypart.CALVES, 100)
        );
        this.fullbody2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.SIDEDELT, 100),
                new MapUtilsHelper(Bodypart.UPPERCHEST, 100),
                new MapUtilsHelper(Bodypart.TRAPS, 100),
                new MapUtilsHelper(Bodypart.QUADS, 100),
                new MapUtilsHelper(Bodypart.HAMS, 100),
                new MapUtilsHelper(Bodypart.BICEP, 100),
                new MapUtilsHelper(Bodypart.TRICEP, 100)
        );

        this.upper = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT,100),
                new MapUtilsHelper(Bodypart.REARDELT,100),
                new MapUtilsHelper(Bodypart.UPPERCHEST,100),
                new MapUtilsHelper(Bodypart.LATS,100),
                new MapUtilsHelper(Bodypart.TRAPS,100),
                new MapUtilsHelper(Bodypart.BICEP,100),
                new MapUtilsHelper(Bodypart.TRICEP,100)
        );
        this.lower = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS,100),
                new MapUtilsHelper(Bodypart.QUADS,90),
                new MapUtilsHelper(Bodypart.HAMS,100),
                new MapUtilsHelper(Bodypart.HAMS,90),
                new MapUtilsHelper(Bodypart.CALVES,100),
                new MapUtilsHelper(Bodypart.CALVES,90),
                new MapUtilsHelper(Bodypart.ABS,100)
        );

        this.upper1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.SIDEDELT,100),
                new MapUtilsHelper(Bodypart.REARDELT,100),
                new MapUtilsHelper(Bodypart.UPPERCHEST,100),
                new MapUtilsHelper(Bodypart.LATS,90),
                new MapUtilsHelper(Bodypart.TRAPS,90),
                new MapUtilsHelper(Bodypart.BICEP,100),
                new MapUtilsHelper(Bodypart.TRICEP,100)
        );
        this.lower1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS,90),
                new MapUtilsHelper(Bodypart.QUADS,80),
                new MapUtilsHelper(Bodypart.HAMS,100),
                new MapUtilsHelper(Bodypart.HAMS,90),
                new MapUtilsHelper(Bodypart.HAMS,80),
                new MapUtilsHelper(Bodypart.CALVES,100),
                new MapUtilsHelper(Bodypart.CALVES,90)
        );
        this.upper2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT,90),
                new MapUtilsHelper(Bodypart.REARDELT,90),
                new MapUtilsHelper(Bodypart.CHEST,90),
                new MapUtilsHelper(Bodypart.LATS,100),
                new MapUtilsHelper(Bodypart.TRAPS,100),
                new MapUtilsHelper(Bodypart.BICEP,80),
                new MapUtilsHelper(Bodypart.TRICEP,80)
        );
        this.lower2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS,100),
                new MapUtilsHelper(Bodypart.QUADS,90),
                new MapUtilsHelper(Bodypart.QUADS,80),
                new MapUtilsHelper(Bodypart.HAMS,90),
                new MapUtilsHelper(Bodypart.HAMS,80),
                new MapUtilsHelper(Bodypart.CALVES,100),
                new MapUtilsHelper(Bodypart.CALVES,90)
        );

        this.push1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT,100),
                new MapUtilsHelper(Bodypart.CHEST,100),
                new MapUtilsHelper(Bodypart.CHEST,90),
                new MapUtilsHelper(Bodypart.SIDEDELT,100),
                new MapUtilsHelper(Bodypart.TRICEP,100),
                new MapUtilsHelper(Bodypart.UPPERCHEST,100),
                new MapUtilsHelper(Bodypart.ABS,100)
        );
        this.pull1 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.LATS,100),
                new MapUtilsHelper(Bodypart.TRAPS,100),
                new MapUtilsHelper(Bodypart.BICEP,100),
                new MapUtilsHelper(Bodypart.BICEP,90),
                new MapUtilsHelper(Bodypart.LATS,90),
                new MapUtilsHelper(Bodypart.TRAPS,90),
                new MapUtilsHelper(Bodypart.REARDELT,90)
        );
        this.legs = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.QUADS,100),
                new MapUtilsHelper(Bodypart.QUADS,90),
                new MapUtilsHelper(Bodypart.QUADS,80),
                new MapUtilsHelper(Bodypart.HAMS,100),
                new MapUtilsHelper(Bodypart.HAMS,90),
                new MapUtilsHelper(Bodypart.HAMS,80),
                new MapUtilsHelper(Bodypart.CALVES,100)
        );
        this.push2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.FRONTDELT,80),
                new MapUtilsHelper(Bodypart.CHEST,80),
                new MapUtilsHelper(Bodypart.SIDEDELT,90),
                new MapUtilsHelper(Bodypart.TRICEP,90),
                new MapUtilsHelper(Bodypart.UPPERCHEST,100),
                new MapUtilsHelper(Bodypart.UPPERCHEST,90),
                new MapUtilsHelper(Bodypart.ABS,90)
        );
        this.pull2 = MapFiller.fillMap(
                new MapUtilsHelper(Bodypart.LATS,100),
                new MapUtilsHelper(Bodypart.TRAPS,100),
                new MapUtilsHelper(Bodypart.BICEP,90),
                new MapUtilsHelper(Bodypart.BICEP,80),
                new MapUtilsHelper(Bodypart.LATS,80),
                new MapUtilsHelper(Bodypart.TRAPS,80),
                new MapUtilsHelper(Bodypart.REARDELT,90)
        );

    }
}
