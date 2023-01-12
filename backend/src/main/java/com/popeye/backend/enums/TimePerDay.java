package com.popeye.backend.enums;

public enum TimePerDay {
    FORTY(2400),
    SIXTY(3600),
    EIGHTY(4800),
    UNLIMITED(-1);

    private final int seconds;

    TimePerDay(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
