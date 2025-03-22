/*
 * Decompiled with CFR 0.152.
 */
package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class CSVExportRequest {
    private String name;
    private String[] columns;
    private List<String[]> data;

    public CSVExportRequest(String fileName, Collection<String[]> data, String[] columns) {
        this.setData(new ArrayList<String[]>(data));
        this.setColumns(Arrays.copyOf(columns, columns.length));
        this.setName(fileName);
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setColumns(String[] columns) {
        this.columns = columns;
    }

    private void setData(List<String[]> data) {
        this.data = data;
    }

    public String getName() {
        return this.name;
    }

    public String[] getColumns() {
        return this.columns;
    }

    public List<String[]> getData() {
        return Collections.unmodifiableList(this.data);
    }
}
