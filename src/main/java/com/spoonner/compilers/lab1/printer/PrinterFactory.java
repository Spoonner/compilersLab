package com.spoonner.compilers.lab1.printer;

public class PrinterFactory {
    public static Printer newPrinter(String param) {
        if (param.equalsIgnoreCase("CONSOLE")) {
            return new ConsoleTablePrinter();
        } else {
            throw new UnsupportedOperationException();
        }
    }
}
