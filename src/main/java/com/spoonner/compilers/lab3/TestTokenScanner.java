package com.spoonner.compilers.lab3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Stream;

public class TestTokenScanner {

    private final String FILENAME = "pascal_src/assignment.pas";
    private boolean expectedColon = false;
    private boolean readingString = false;
    private boolean readingNumber = false;
    private String tokenName = "";
    private Set<String> declaredVars = new HashSet<>(20);

    enum DataType {
        INTEGER, REAL, BOOLEAN;
    }

    static class VariableData {
        private String name;
        private DataType type;
        private String value;

        public VariableData(String name, DataType type, String value) {
            this.name = name;
            this.type = type;
            this.value = value;
        }
    }

    static class Token {

    }

    private boolean isEndOfLine(char ch) {
        return Character.toString(ch).equals("\n");
    }

    private boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    private boolean isAlphabatic(char ch) {
        return Character.isAlphabetic(ch);
    }

    public void scan() {
        /*try (Stream<String> lines = Files.lines(Paths.get(FILENAME))) {
            lines.forEach(string -> {

                Scanner scanner = new Scanner(string).useDelimiter("");
                while (scanner.hasNext()) {
                    char currentChar = scanner.next().toLowerCase().charAt(0);


                }

            });

        } catch (IOException e) {
            e.printStackTrace();
        }
*/


        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(FILENAME)).useDelimiter("");
            while (scanner.hasNext()) {
                //loop through file
                char current = scanner.next().toLowerCase().charAt(0);
                if (isAlphabatic(current)) {
                    tokenName += Character.toString(current);
                } else if (isDigit(current)) {

                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void proceedCharacter(char c) {

    }


}
