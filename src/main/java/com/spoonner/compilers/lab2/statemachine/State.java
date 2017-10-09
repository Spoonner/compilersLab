package com.spoonner.compilers.lab2.statemachine;

/**
 * 3 - > 7 dlm
 * 5 - > 8 cfr
 * 3 - > 2 ltr
 */
public enum State {

    STATE_0("S0") {
        @Override
        public State next(Signal signal) {
            if (signal == null)
                return this;
            return STATE_1;
        }
    },

    STATE_1("S1") {
        @Override
        public State next(Signal signal) {
            if (signal == null)
                return this;
            return STATE_2;
        }
    },

    STATE_2("S2") {
        @Override
        public State next(Signal signal) {
            if (signal == null)
                return this;
            return STATE_3;
        }
    },

    STATE_3("S3") {
        @Override
        public State next(Signal signal) {
            switch (signal) {
                case CFR:
                    return STATE_4;

                case DLM:
                    return STATE_7;

                case LTR:
                    return STATE_2;

                default:
                    return this;
            }
        }
    },

    STATE_4("S4") {
        @Override
        public State next(Signal signal) {
            if (signal == null)
                return this;
            return STATE_5;
        }
    },

    STATE_5("S5") {
        @Override
        public State next(Signal signal) {
            return (signal == Signal.CFR) ? STATE_8 : (signal == null) ? this: STATE_6;
        }
    },

    STATE_6("S6") {
        @Override
        public State next(Signal signal) {
            if (signal == null)
                return this;
            return STATE_7;
        }
    },

    STATE_7("S7") {
        @Override
        public State next(Signal signal) {
            if (signal == null)
                return this;
            return STATE_8;
        }
    },

    STATE_8("S8") {
        @Override
        public State next(Signal signal) {
            if (signal == null)
                return this;
            return STATE_9;
        }
    },

    STATE_9("S9") {
        @Override
        public State next(Signal signal) {
            return STATE_9;
        }
    };

    private String name;

    State(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    abstract public State next(Signal signal);
}
