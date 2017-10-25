package com.spoonner.compilers.lab2.statemachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<String> signalsStr = Arrays.asList("dlm", "cfr", "cfr", "ltr", "ltr", "ltr", "dlm");
        System.out.println(">>>>Sending signal sequence: ");
        System.out.println(signalsStr+"\n\n");
        List<Signal> signalList = Converter.convertStringList(signalsStr);
        List<State> list = Machine.runMachine(signalList, State.STATE_0);

        System.out.println("\n\n>>>>> STATES MACHINE WAS IN <<<<<");
        String s = list.stream()
                .map(State::toString)
                .collect(Collectors.joining(" --> "));

        System.out.println(s);
    }

}
