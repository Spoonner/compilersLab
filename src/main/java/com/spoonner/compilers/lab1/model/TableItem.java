package com.spoonner.compilers.lab1.model;

import java.io.Serializable;

public class TableItem<T> implements Comparable<TableItem<T>>, Serializable {

    private String key;
    private T userData;

    public TableItem(String key) {
        this.key = key;
    }

    public T getUserData() {
        return userData;
    }

    public void setUserData(T data) {
        this.userData = data;
    }

    @Override
    public int compareTo(TableItem o) {
        return 0;
    }

    public String getKey() {
        return key;
    }

    @Override
    public int hashCode() {
        return key.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return key.equals(obj);
    }

    @Override
    public String toString() {
        return key + "\t" + userData.toString();
    }
}
