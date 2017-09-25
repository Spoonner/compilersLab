package com.spoonner.compilers.lab1.functions;

public class SearchFunctionImpl implements SearchFunction {

    @Override
    public int matchCount(String key, String currentValue) {
        int isMatched = 0;
        boolean stillMatches = true;
        int counter = 0;
        while (true) {
            if (counter >= key.length() || counter >= currentValue.length())
                break;
            String currentKeyChar = Character.toString(key.charAt(counter));
            String currentValueChar = Character.toString(currentValue.charAt(counter));
            if (currentKeyChar.equalsIgnoreCase(currentValueChar)) {
                counter++;
                isMatched++;
                continue;
            } else {
                break;
            }
        }
        return isMatched;
    }
}
