package com.motanttron;

import java.io.IOException;

/**
 * L0
 * F1) Add two matrixes, for every operation, create a new thread
 * F2) Multiply
 * F3) Ascendent sort of main diagonal & the ones parallel to it
 *
 * Keep in mind:
 *  Methods for reading & show matrix
 */
public class Main {
    public static void main(String[] args) throws IOException {
        final String file = Utils.readFile("file.txt");
        System.out.println(file);
    }

}
