/*
 * Decompiled with CFR 0.152.
 */
package autopilot;

import autopilot.OutputDocument;
import java.util.regex.Pattern;

public class Section {
    private String parent = null;
    private final String name;
    private boolean binary = false;
    private double score = 0.0;
    private StringBuilder builder = new StringBuilder();
    private OutputDocument document = null;

    Section(String name) {
        this.name = name;
    }

    public void write(Object o) {
        this.builder.append(String.valueOf(o)).append("\n");
    }

    public String result() {
        return this.builder.toString();
    }

    boolean isBinary() {
        return this.binary;
    }

    void setBinary(boolean binary) {
        this.binary = binary;
    }

    double getScore(boolean flat) {
        return this.score + (flat || this.document == null ? 0.0 : this.document.getScore());
    }

    void setScore(double score) {
        this.score = score;
    }

    public String getName() {
        return this.parent == null ? this.name : String.valueOf(this.parent) + "_" + this.name;
    }

    public OutputDocument document() {
        if (this.document == null) {
            this.document = new OutputDocument();
            this.document.setParentSection(this);
        }
        return this.document;
    }

    OutputDocument getDocument() {
        return this.document;
    }

    String documentOutput(boolean xml) {
        return this.document == null ? "" : this.document.exportSections("sub", String.valueOf(this.name), xml);
    }

    public String[] getSectionData() {
        return this.builder.toString().split(Pattern.quote("\n"));
    }

    public boolean isDocumentEmpty() {
        return this.document == null || this.document.isEmpty();
    }

    public boolean isEmpty() {
        return this.builder.length() == 0 && this.isDocumentEmpty();
    }

    public int documentSize() {
        return this.document == null ? 0 : this.document.numberOfSections();
    }

    void setDocument(OutputDocument document) {
        this.document = document;
        document.setParentSection(this);
    }

    public String getParent() {
        return this.parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
