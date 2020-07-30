package org.fix.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FixTranslation {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter Raw Fix: ");
        String rawMsg = br.readLine();
        String parsedMsg = Fixer.translate(rawMsg);
        System.out.println("\n" + "Parsed FIX Message\n" + "*******************\n" + parsedMsg);
    }
}
