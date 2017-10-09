package com.spoonner;


import com.spoonner.compilers.lab1.functions.ResultExtractor;
import com.spoonner.compilers.lab1.functions.SearchFunctionImpl;
import com.spoonner.compilers.lab1.functions.data.UserDataImpl;
import com.spoonner.compilers.lab1.model.Table;
import com.spoonner.compilers.lab1.model.TableItem;
import org.junit.Test;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Unit test for Lab work #1
 */

public class AppTest {

    private Table<UserDataImpl> table = new Table<>(UserDataImpl.class);
    private final String FILE_NAME = "data_60.data";

    public void initTable() {
        table.loadFromFile(FILE_NAME);
    }

    @Test
    public void addToEmptyTable() {
        TableItem<UserDataImpl> item = new TableItem<>("mmn3ut83");
        item.setUserData(new UserDataImpl("userData"));
        table.addRow(item);
        assertEquals(1, table.getItems().size());
    }

    @Test
    public void addDuplicate() {
        initTable();
        int oldSize = table.getItems().size();
        TableItem<UserDataImpl> duplicate = new TableItem<>("mmn3ut83");
        boolean result = table.addRow(duplicate);
        int newSize = table.getItems().size();
        assertEquals(oldSize, newSize);
        assertFalse(result);
    }

    @Test
    public void removeFromEmptyTable() {
        boolean result = table.removeRow(new TableItem<>("mmn3ut83"));
        assertEquals(0, table.getItems().size());
        assertFalse(result);
    }

    @Test
    public void removeNonExistingElement() {
        initTable();
        int oldSize = table.getItems().size();
        boolean result = table.removeRow(new TableItem<>("noSuchItem"));
        int newSize = table.getItems().size();
        assertFalse(result);
        assertEquals(oldSize, newSize);
    }

    @Test
    public void removeExistingElement() {
        initTable();
        int oldSize = table.getItems().size();
        table.removeRow(new TableItem<>("9pejtm7e"));
        int newSize = table.getItems().size();
        assertNotEquals(oldSize, newSize);
        assertEquals(newSize, oldSize - 1);
    }

    @Test
    public void binarySearchExistingElement() {
        initTable();
        String existingKey = "7y8j9azh";
        int index = table.binarySearch(new TableItem<>(existingKey));
        assertNotEquals(-1, index);
    }

    @Test(expected = NoSuchElementException.class)
    public void binarySearchNonExistingElement() {
        initTable();
        table.binarySearch(new TableItem<>("no_such_element"));
    }

    @Test
    public void linearSearchExistingElement() {
        initTable();
        String existingKey = "7y8j9azh";
        int index = table.linearSearch(new TableItem<>(existingKey));
        assertNotEquals(-1, index);
    }

    @Test
    public void linearSearchNonExistingElement() {
        initTable();
        int index = table.linearSearch(new TableItem("non_existed"));
        assertTrue(index < 0);
    }

    @Test
    public void customSearch() {
        initTable();
        List<Integer> indexes = table.searchRow(new TableItem<>("7y8j9azh"), new SearchFunctionImpl(), ResultExtractor.ONE);
        assertTrue(indexes.size() == 1);
        assertTrue(indexes.get(0) > 0);
    }

}
