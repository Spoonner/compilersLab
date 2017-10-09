package com.spoonner.compilers.lab2.statemachine;

public enum Signal {

    DLM("DLM"),
    CFR("CFR"),
    LTR("LTR");

    private String name;

    Signal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
