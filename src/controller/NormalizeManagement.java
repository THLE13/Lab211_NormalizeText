package controller;

import common.Library;
import view.Menu;
import common.Algorithms;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NormalizeManagement extends Menu<String> {

    static String[] mc = {"Normalize Text", "Exit"};

    protected Library library;
    protected Algorithms algorithm;

    public NormalizeManagement() {
        super("NORMALIZE TEXT SYSTEM", mc);
        library = new Library();
        algorithm = new Algorithms();
    }

    @Override
    public void execute(int n) {
        switch (n) {
            case 1:
                BufferedReader br = null;
                try {
                    int countLine = algorithm.countLine();
                    int count = 1;
                    br = new BufferedReader(new FileReader(new File("text.txt")));
                    PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("output.txt", true)));
                    String line;
                    //write until end file
                    while ((line = br.readLine()) != null) {
                        //check line empty
                        if (algorithm.isLineEmpty(line)) {
                            continue;
                        }
                        line = algorithm.formatOneSpace(line);
                        line = algorithm.formatSpecialCharacters(line);
                        line = algorithm.afterDotUpperCase(line);
                        line = algorithm.noSpaceQuotes(line);
                        line = algorithm.firstUpercase(line);
                        line = algorithm.lastAddDot(line);
                        pw.print(line);
                        if (count < countLine) {
                            pw.print(System.getProperty("line.separator"));
                        }
                        count++;
                    }
                    br.close();
                    pw.close();
                    System.out.println("Normalize successful.");
                } catch (FileNotFoundException ex) {
                    System.err.println("Can't not found");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                break;

            case 2:
                System.exit(0);
                break;
            default:
                System.out.println("Your choice invalid! Pls input another choice");
        }
    }
}
