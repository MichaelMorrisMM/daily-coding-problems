package michaelmorrismm.dailycodingproblems.airlinesparser;

import java.io.*;

/**
 * @author Michael Morris
 *
 * Interview homework assignment by 3M
 *
 * Homework instructions
 *
 * In Java 8, please process this attached dataset and output the calculated data points below in an XML schema
 * you define. Please submit your code and resulting XML for review.
 *
 * ·         Total number of airports
 * ·         Total number of flights
 * ·         Percentage of total flights delayed by "security"
 * ·         Percentage of total flights delayed by "carrier"
 * ·         Percentage of total flights delayed by "national aviation system"
 * ·         Airport with the highest number of delays due to "security"
 * ·         Airport with the lowest number of delays due to "security"
 * ·         Airport with the most total flights
 *
 */
public class Main {

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            throw new IllegalArgumentException("Please provide path to airlines file");
        }
        File airlinesFile = new File(args[0]);
        if (!airlinesFile.exists() || !airlinesFile.canRead()) {
            throw new FileNotFoundException("Unable to read airlines file");
        }

        OutputStream output = System.out;

        if (args.length > 1) {
            File outputFile = new File(args[1]);
            if (!outputFile.exists()) {
                if (!outputFile.createNewFile()) {
                    throw new IOException("Unable to create output file");
                }
            } else if (!outputFile.canWrite()) {
                throw new IOException("Unable to write to output file");
            }
            output = new FileOutputStream(outputFile);
        }

        Parser parser = new Parser(airlinesFile, output);
        parser.parse();
    }

}
