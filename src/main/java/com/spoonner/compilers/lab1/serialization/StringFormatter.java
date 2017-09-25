package com.spoonner.compilers.lab1.serialization;

public interface StringFormatter<T> {
    T restoreFromString(String objString);
    String printObject();
}
