package com.spoonner.compilers.lab1.main;

import com.spoonner.compilers.lab1.functions.ResultExtractor;
import com.spoonner.compilers.lab1.functions.SearchFunctionImpl;
import com.spoonner.compilers.lab1.functions.data.UserDataImpl;
import com.spoonner.compilers.lab1.model.Table;
import com.spoonner.compilers.lab1.model.TableItem;
import com.spoonner.compilers.lab1.printer.Printer;
import com.spoonner.compilers.lab1.printer.PrinterFactory;

import java.util.List;

public class Main {

    public static String[] strs = {"aa", "zz", "bb", "gg", "yy", "ss", "qq"};
    public static TableItem<UserDataImpl> key;

    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        /*Table<UserDataImpl> userDataTable = new Table<>(UserDataImpl.class);
        for (int i = 0; i < strs.length; i++) {
            TableItem<UserDataImpl> item = new TableItem<>(strs[i]);
            item.setUserData(new UserDataImpl("data"));
            userDataTable.addRow(item);
            key = item;
        }
        Printer printer = PrinterFactory.newPrinter("CONSOLE");
        printer.print(userDataTable);

        //binary search
        int res = userDataTable.binarySearch(new TableItem<>("bb"));
        System.out.println("binary search: " + res);

        //search by reference
        res = userDataTable.searchByReference(key);
        System.out.println("search by ref: "+ res);

        //linear search
        res = userDataTable.linearSearch(key);
        System.out.println("linear search: "+ res);

        //custom search
        List<Integer> ints = userDataTable.searchRow(key, new SearchFunctionImpl(), ResultExtractor.ONE);
        System.out.println("search by custom func: "+ ints);

        System.out.println("Removing gg");
        userDataTable.removeRow(new TableItem<>("gg"));
        printer.print(userDataTable);


        System.out.println("Adding ccc");
        userDataTable.addRow(new TableItem<>("ccc"));
        printer.print(userDataTable);

        userDataTable.saveToFile("dataFile.data");*/

        Table<UserDataImpl> userDataTable = new Table<>(UserDataImpl.class);
        userDataTable.loadFromFile("dataFile.data");
        Printer printer = PrinterFactory.newPrinter("CONSOLE");
        printer.print(userDataTable);

    }

}
