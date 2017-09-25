package com.spoonner.compilers.lab1.model;

import com.spoonner.compilers.lab1.serialization.StringFormatter;

import java.io.Serializable;

public class TableItem<T extends StringFormatter<T>> implements Comparable<TableItem<T>>, Serializable {

    private String key;
    private T userData;

    private Class<T> clazz;

    public TableItem() {

    }

    public TableItem(Class<T> clazz) {
        this.clazz = clazz;
    }

    public TableItem(String key) {
        this.key = key;
    }

    public void setKey(String key) {
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
        return this.getKey().compareTo(o.getKey());
    }

    public String getKey() {
        return key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TableItem<?> tableItem = (TableItem<?>) o;

        return key != null ? key.equals(tableItem.key) : tableItem.key == null;
    }

    @Override
    public int hashCode() {
        return key != null ? key.hashCode() : 0;
    }

    @Override
    public String toString() {
        return key + "\t" + (userData == null ? "" : userData.toString());
    }

    public TableItem<T> deserializeString(String objStr) {
        TableItem<T> item = new TableItem<>();
        String[] splitted = objStr.split(":");
        String dataStr = null;
        if (splitted.length == 2) {
            dataStr = splitted[1];
        }
        String key = splitted[0];
        this.key = key;
        try {
            T userData = clazz.newInstance();
            if (dataStr != null)
                userData.restoreFromString(dataStr);
            else {
                userData = null;
            }
            this.userData = userData;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return this;
    }

    public String serializeObject() {
        return key + ":" + (userData == null ? "" : userData.printObject());
    }

}
