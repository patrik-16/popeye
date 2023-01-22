package com.popeye.backend.utils;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class MapFiller {

    public static Multimap<String, Integer> fillMap(MapUtilsHelper...args) { //wir geben mind. 1 bis unendlich viele objekte
        Multimap<String, Integer> map = ArrayListMultimap.create();
        for (MapUtilsHelper tmp : args) {
            map.put(tmp.getBodypart().toString(), tmp.getEffectiveness());
        }
        return map;
    }

}