/*
 * Decompiled with CFR 0.152.
 */
package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public final class MyFileLogWriter {
    private static File outputLogFile;
    private static FileWriter writer;

    public static void initializeMyFileWriter() {
        outputLogFile = new File("output.txt");
        try {
            writer = new FileWriter(outputLogFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void println(String message) {
        try {
            writer.write(message);
            writer.write(System.getProperty("line.separator"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveLogFile() {
        try {
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
