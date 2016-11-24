package com.motanttron;

import java.io.*;
import java.util.Scanner;

/**
 * Created by calin on 24.11.2016.
 */
public class Utils {

    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Reads input as a string from a file.
     *
     * @param path path of File
     * @return the String representation
     * @throws IOException
     */
    public static String safeStringFromFile(String path) {
        String result = "";
        try {
            File file = new File(path);
            if (!file.exists()) {
//                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            StringBuilder fileContents = new StringBuilder((int) file.length());
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine()).append(LINE_SEPARATOR);
            }
            result = fileContents.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
