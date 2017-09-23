package com.spoonner.compilers.lab1.model;

import com.spoonner.compilers.lab1.functions.ResultExtractor;
import com.spoonner.compilers.lab1.functions.SearchFunction;

import java.util.*;

public class Table<T> {

    private List<TableItem<T>> itemList;

    public Table() {
        itemList = new ArrayList<>();
    }

    public boolean addRow(TableItem<T> newRow) {
        return itemList.add(newRow);
    }

    public boolean removeRow(TableItem<T> row) {
        return itemList.remove(row);
    }

    public boolean removeRowByKey(String key) {
        return itemList.remove(new TableItem<>(key));
    }

    public TableItem binarySearch(TableItem item) throws NoSuchElementException {
        Collections.sort(itemList);
        int index = Collections.binarySearch(itemList, item);
        if (index > 0) {
            return itemList.get(index);
        } else {
            throw new NoSuchElementException();
        }
    }

    public List<TableItem<T>> getItems() {
        return itemList;
    }

    public List<Integer> searchRow(TableItem<T> keyRow, SearchFunction searchFunction, ResultExtractor resultExtractor) {

        if (resultExtractor == ResultExtractor.ONE) {
            return seachOneRow(keyRow, searchFunction);
        } else {

            if (resultExtractor == ResultExtractor.ALL) {
                throw new UnsupportedOperationException();
            } else {
                throw new UnsupportedOperationException();
            }
        }

    }

    private List<Integer> seachOneRow(TableItem<T> keyRow, SearchFunction searchFunction) {

        List<Integer> searchMatchIndexes = new ArrayList<>();
        HashMap<TableItem<T>, Integer> matchMap = new HashMap<>();

        for (TableItem<T> item : itemList) {
            matchMap.put(item, searchFunction.matchCount(item.getKey(), keyRow.getKey()));
        }

        TreeSet<Map.Entry<TableItem<T>, Integer>> entryResultSet = new TreeSet<>(matchMap.entrySet());
        Collections.sort(new ArrayList<>(entryResultSet), Comparator.comparingInt(Map.Entry::getValue));

        //TODO: check sort order



        return searchMatchIndexes;
    }


    public List<Integer> searchRowByKey(String key, SearchFunction function, ResultExtractor extractor) {
        return searchRow(new TableItem<>(key), function, extractor);
    }
}
