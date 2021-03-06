package com.spoonner.compilers.lab1.model;

import com.spoonner.compilers.lab1.functions.ResultExtractor;
import com.spoonner.compilers.lab1.functions.SearchFunction;
import com.spoonner.compilers.lab1.serialization.StringFormatter;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 *
 * @param <T> - data that table represents (same as in TableItems)
 */
public class Table<T extends StringFormatter<T>> {

    private List<TableItem<T>> itemList;
    private Class<T> clazz;

    public Table(Class<T> clazz) {
        itemList = new ArrayList<>();
        this.clazz = clazz;
    }

    public boolean addRow(TableItem<T> newRow) {
        boolean result = true;
        if (itemList.contains(newRow)) {
            return false;
        }
        itemList.add(newRow);
        Collections.sort(itemList);
        return result;
    }

    public boolean removeRow(TableItem<T> row) {
        boolean result = itemList.remove(row);
        Collections.sort(itemList);
        return result;
    }

    public boolean removeRowByKey(String key) {
        return itemList.remove(new TableItem<>(key));
    }

    public int binarySearch(TableItem item) throws NoSuchElementException {
        int index = Collections.binarySearch(itemList, item, Comparator.comparing(TableItem::getKey));
        if (index >= 0) {
            return index;
        } else {
            throw new NoSuchElementException();
        }
    }

    public int binarySearchByKey(String key) {
        return binarySearch(new TableItem<>(key));
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

        List< Map.Entry <TableItem<T>, Integer> > entryResultList = new LinkedList<>(matchMap.entrySet());
        Collections.sort(entryResultList, (o1, o2) -> {
            int res = o2.getValue() - o1.getValue();
            if (res == 0) {
                return o1.getKey().getKey().compareTo(o2.getKey().getKey());
            }
            return res;
        });

        TableItem<T> found = entryResultList.get(0).getKey();
        searchMatchIndexes.add(itemList.indexOf(found));
        return searchMatchIndexes;
    }


    public List<Integer> searchRowByKey(String key, SearchFunction function, ResultExtractor extractor) {
        return searchRow(new TableItem<>(key), function, extractor);
    }

    public void loadFromFile(String filename) {
        try (Stream<String> stream = Files.lines(Paths.get(filename))) {
            stream.forEach(s -> addRow(new TableItem<>(clazz).deserializeString(s)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        int i = 0;
        for (TableItem<T> item : itemList) {
            System.out.println(i + "\t" + item.toString());
            i++;
        }
    }

    public void saveToFile(String fileName) {
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            for (TableItem<T> item : itemList) {
                writer.write(item.serializeObject());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int searchByReference(TableItem<T> itemToFind) {
        for (TableItem<T> item : itemList) {
            if (item == itemToFind)
                return itemList.indexOf(item);
        }
        return -1;
    }

    public TableItem<T> get(int index) {
        return itemList.get(index);
    }

    public int linearSearch(TableItem<T> itemToFind) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).equals(itemToFind))
                return i;
        }
        throw new NoSuchElementException();
    }

    public int linearSearchByKey(String key) {
        return linearSearch(new TableItem<>(key));
    }
}