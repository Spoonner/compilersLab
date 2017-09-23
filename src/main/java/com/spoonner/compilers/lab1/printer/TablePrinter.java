package com.spoonner.compilers.lab1.printer;

import com.spoonner.compilers.lab1.model.Table;

public abstract class TablePrinter implements Printer<Table> {
    public abstract void print(Table obj);
}
