package com.spoonner.compilers.test_lab3_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public final class Emulator {

    public static void main(String[] args) throws FileNotFoundException {
//        System.out.println("Scanner output:");
        ArrayList<Token> tokenArrayList = TokenScanner.scan(new File("pascal_src/assignment.pas"));

        tokenArrayList
                .forEach(System.out::println);


        SyntaxAnalyzer analyzer = new SyntaxAnalyzer();
        analyzer.setTokenList(tokenArrayList);
        boolean res = analyzer.validate(SyntaxAnalyzer.VerboseType.VERBOSE);

        System.out.println(res);

        analyzer.printErrors();


//        System.out.println("\nParser output:");
//        Parser.setTokenArrayListIterator(tokenArrayList);
//
//        Byte[] instructions = Parser.parse();
//        Simulator.setInstructions(instructions);

//        System.out.println("\nOutput:");
//        Simulator.simulate();


    }
}
