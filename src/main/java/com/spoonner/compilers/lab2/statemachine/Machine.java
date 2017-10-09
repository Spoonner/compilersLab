package com.spoonner.compilers.lab2.statemachine;

import java.util.ArrayList;
import java.util.List;

public class Machine {

    private static final String START_STR = "=====>";

    public static List<State> runMachine(List<Signal> signals, State startState) {
        List<State> states = new ArrayList<>(9);
        State currentState = startState;
        for (Signal signal : signals) {
            System.out.println("============================");
            System.out.println(START_STR + "Current state is " + currentState);
            System.out.println(START_STR + "Receiving signal " + signal);
            State state = currentState.next(signal);
            states.add(state);
            if (currentState == state) {
                System.out.println(START_STR + "Staying at state " + state);
            } else {
                System.out.println(START_STR + "Moving to state " + state);
                currentState = state;
            }
        }
        return states;
    }

}
