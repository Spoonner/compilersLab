package com.spoonner.compilers.lab1.printer;

import com.spoonner.compilers.lab1.model.Table;
import com.spoonner.compilers.lab1.model.TableItem;

public class ConsoleTablePrinter extends TablePrinter {

    @Override
    public void print(Table obj) {
        obj.print();
    }
}
