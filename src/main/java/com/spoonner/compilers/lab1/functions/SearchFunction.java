package com.spoonner.compilers.lab1.functions;

//Define interface to be used as a parameter to a search method
//Analogue to pointer to function in CHAR/CHAR++
public interface SearchFunction {
    //number of characters that meets the search criteria (the more you get, the more strings match)
    int matchCount(String key, String currentValue);
}
