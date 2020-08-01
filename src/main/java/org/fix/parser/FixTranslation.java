package org.fix.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FixTranslation {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("------------------------\n" +
                "|     FIXer Menu       |\n" +
                "------------------------\n" +
                "|Parse FIX Messages (1)|\n" +
                "|Compare FIX Msgs   (2)|\n" +
                "|                      |\n" +
                "------------------------");
        System.out.println("Enter your choice :: ");
        String choice = br.readLine();
        if (choice.equals("1")) {
            System.out.println("Enter Raw Fix: ");
            String rawMsg = br.readLine().trim();
            String parsedMsg = Fixer.translate(rawMsg);
            System.out.println("\n" + "Parsed FIX Message\n" + "*******************\n" + parsedMsg);
        } else if (choice.equals("2")){
            System.out.println("Enter 1st Fix: ");
            String f1 = br.readLine().trim();
            System.out.println("Enter 2nd Fix: ");
            String f2 = br.readLine().trim();
            String res = Fixer.compareFix(Fixer.convertToAMap(f1), Fixer.convertToAMap(f2));
            if (res.equals("")){
                System.out.println("No mismatches reported");
            } else {
                System.out.println(res);
            }
        } else {
            System.out.println("Incorrect Option :: Exiting!");
        }
    }
}
