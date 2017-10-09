package com.spoonner.compilers.lab2.statemachine;

import java.util.ArrayList;
import java.util.List;

public class Converter {

    private static final String CFR = "cfr";
    private static final String DLM = "dlm";
    private static final String LTR = "ltr";

    public static List<Signal> convertStringList(List<String> list) {
        List<Signal> signals = new ArrayList<>();
        for (String item : list) {
            if (item.equalsIgnoreCase(CFR)) {
                signals.add(Signal.CFR);
            } else if (item.equalsIgnoreCase(DLM)) {
                signals.add(Signal.DLM);
            } else if (item.equalsIgnoreCase(LTR)) {
                signals.add(Signal.LTR);
            }
        }
        return signals;
    }
}
