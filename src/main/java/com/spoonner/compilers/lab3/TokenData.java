package com.spoonner.compilers.lab3;

import com.spoonner.compilers.lab1.model.Table;
import com.spoonner.compilers.lab1.serialization.StringFormatter;

public class TokenData implements StringFormatter<TokenData> {

    private String ID;
    private String name;
    private String value;
    private DataType type;

    public TokenData() {

    }


    @Override
    public TokenData restoreFromString(String objString) {
        return null;
    }

    @Override
    public String printObject() {
        return null;
    }
}
