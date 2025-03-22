/*
 * Decompiled with CFR 0.152.
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import utils.CSVExportRequest;

public final class CSVExporter {
    private static final String FILE_SEPERATOR = ",";
    private static final String NEW_LINE = "\n";
    private static final String QUOTE = "\"";
    private static final String EXT = ".csv";

    public static void export(String fileName, Collection<String[]> data, String[] columns) {
        CSVExporter.export(new CSVExportRequest(fileName, data, columns));
    }

    public static void export(CSVExportRequest request) {
        CSVExporter.export(request, null);
    }

    public static void export(CSVExportRequest request, ExportCallBack callBack) {
        CSVExporter.export(request, callBack, false);
    }

    public static void export(CSVExportRequest request, ExportCallBack callBack, boolean async) {
        Thread s = new Thread(() -> {
            block6: {
                try {
                    PrintWriter writer = new PrintWriter(String.valueOf(request.getName()) + EXT, "UTF-8");
                    int columns = request.getColumns().length;
                    int i = 0;
                    while (i < columns) {
                        writer.print(QUOTE + request.getColumns()[i] + QUOTE + (i == columns - 1 ? NEW_LINE : FILE_SEPERATOR));
                        ++i;
                    }
                    i = 0;
                    while (i < request.getData().size()) {
                        int j = 0;
                        while (j < columns) {
                            writer.print(QUOTE + request.getData().get(i)[j] + QUOTE + (j == columns - 1 ? NEW_LINE : FILE_SEPERATOR));
                            ++j;
                        }
                        ++i;
                    }
                    writer.close();
                    if (callBack != null) {
                        callBack.callBack(String.valueOf(request.getName()) + EXT, true);
                    }
                }
                catch (FileNotFoundException | UnsupportedEncodingException e) {
                    if (callBack == null) break block6;
                    callBack.callBack(String.valueOf(request.getName()) + EXT, false);
                }
            }
        });
        if (async) {
            s.start();
        } else {
            s.run();
        }
    }

    public static List<String[]> importCSV(String path) throws IOException {
        String line;
        ArrayList<String[]> list = new ArrayList<String[]>();
        BufferedReader br = new BufferedReader(new FileReader(new File(path)));
        while ((line = br.readLine()) != null) {
            String[] entries = line.split(FILE_SEPERATOR);
            list.add(entries);
        }
        return list;
    }

    @FunctionalInterface
    public static interface ExportCallBack {
        public void callBack(String var1, boolean var2);
    }
}
