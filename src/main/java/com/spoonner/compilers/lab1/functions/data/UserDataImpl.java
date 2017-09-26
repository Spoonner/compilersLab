package com.spoonner.compilers.lab1.functions.data;

import com.spoonner.compilers.lab1.serialization.StringFormatter;

/**
 * Model class to represent user data stored in NON KEY field of the table
 */
public class UserDataImpl implements StringFormatter<UserDataImpl> {

    private String data;

    public UserDataImpl() {}

    public UserDataImpl(String userData) {
        this.data = userData;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String userData) {
        this.data = userData;
    }

    @Override
    public UserDataImpl restoreFromString(String objString) {
        this.data = objString;
        return this;
    }

    @Override
    public String printObject() {
        return data;
    }

    @Override
    public String toString() {
        return data;
    }

    @Override
    public boolean equals(Object obj) {
        return data.equals(data);
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }
}
