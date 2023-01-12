package com.popeye.backend.enums;

public enum Bodypart {
    FRONTDELT,
    SIDEDELT,
    REARDELT,
    BICEP,
    TRICEP,
    CHEST,
    UPPERCHEST,
    LATS,
    TRAPS,
    QUADS,
    GLUTES,
    HAMS,
    ABS,
    CALVES,

    DELTS,
    LEGS,
    BACK;

    Bodypart() {

    }

    private enum Effectiveness {
        TEN(10),
        TWENTY(20),
        THIRTY(30),
        FORTY(40),
        FIFTY(50),
        SIXTY(60),
        SEVENTY(70),
        EIGHTY(80),
        NINETY(90),
        HUNDRED(100);

        private final int effectiveness;

        Effectiveness(int effectiveness) {
            this.effectiveness = effectiveness;
        }

        public int getEffectiveness() {
            return effectiveness;
        }
    }
}
