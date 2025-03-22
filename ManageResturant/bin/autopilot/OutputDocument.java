/*
 * Decompiled with CFR 0.152.
 */
package autopilot;

import autopilot.Section;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Pattern;

public class OutputDocument {
    private Section parentSection = null;
    private char nextName = (char)65;
    private Map<String, Section> sections = new TreeMap<String, Section>();

    public Section nextSection() {
        String parentName = this.parentSection == null ? null : this.parentSection.getName();
        Section section = new Section(String.valueOf(this.nextName));
        section.setParent(parentName);
        char c = this.nextName;
        this.nextName = (char)(c + '\u0001');
        this.sections.put(String.valueOf(c), section);
        return section;
    }

    Section nextSection(String name) {
        String parentName = this.parentSection == null ? null : this.parentSection.getName();
        Section section = new Section(name);
        section.setParent(parentName);
        this.sections.put(name, section);
        return section;
    }

    public Section existingSection(String name) {
        return this.sections.get(name);
    }

    public String exportSections() {
        return this.exportSections(null, null, true);
    }

    public String exportNormal() {
        return this.exportSections(null, null, false);
    }

    public Map<String, Section> sections() {
        return Collections.unmodifiableMap(this.sections);
    }

    public String exportSections(String documentName, String parentName, boolean xml) {
        StringBuilder builder = new StringBuilder();
        if (documentName == null) {
            documentName = this.getDirName();
        }
        builder.append(xml ? "<Document name='" : "").append(xml ? documentName : "").append(xml ? "' count='" : "").append(xml ? Integer.valueOf(this.sections.size()) : "").append(xml ? "'>\n" : "");
        for (Map.Entry<String, Section> entry : this.sections.entrySet()) {
            builder.append(xml ? "<Section name='" : "").append(xml ? (parentName == null ? "" : String.valueOf(parentName) + "_") : "").append(xml ? entry.getKey() : "").append(xml ? "'>\n" : "");
            builder.append(xml ? "<data>\n" : "");
            builder.append(entry.getValue().result());
            builder.append(xml ? "</data>\n" : "\n");
            builder.append(entry.getValue().documentOutput(xml));
            builder.append(xml ? "</Section>\n" : "");
        }
        builder.append(xml ? "</Document>\n" : "");
        return builder.toString();
    }

    private String getDirName() {
        String[] path = System.getProperty("user.dir").split(Pattern.quote(File.separator));
        return path.length > 0 ? path[path.length - 1] : "unknown_project";
    }

    double getScore() {
        double sum = 0.0;
        for (Section section : this.sections.values()) {
            sum += section.getScore(false);
        }
        return sum;
    }

    public boolean isEmpty() {
        for (Section section : this.sections.values()) {
            if (section.isEmpty()) continue;
            return false;
        }
        return true;
    }

    public int numberOfSections() {
        return this.sections.size();
    }

    public Map<String, Section> flatSections() {
        ArrayList<Section> sections = new ArrayList<Section>(this.sections.values());
        for (Section section : this.sections.values()) {
            OutputDocument document = section.getDocument();
            if (document == null) continue;
            sections.addAll(document.flatSections().values());
        }
        HashMap<String, Section> map = new HashMap<String, Section>();
        for (Section section : sections) {
            map.put(section.getName(), section);
        }
        return map;
    }

    public void setParentSection(Section parentSection) {
        this.parentSection = parentSection;
        for (Section section : this.sections.values()) {
            section.setParent(parentSection.getName());
        }
    }
}
