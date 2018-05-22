package com.agh.format;

public enum LogFormat {
    NCSA("dd/MMM/yyyy:hh:mm:ss"),
    UNIX("yyyy-mm-d hh:mm:ss");

    private final String format;

    LogFormat(String format) {
        this.format = format;
    }

    @Override
    public String toString() {
        return format;
    }
}
