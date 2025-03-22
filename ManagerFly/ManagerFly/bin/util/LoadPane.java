/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.fxml.FXMLLoader
 *  javafx.scene.Node
 */
package util;

import java.io.IOException;
import java.net.URL;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public final class LoadPane {
    private LoadPane() {
    }

    public static <T extends Node> T LoadFXML(Class<?> cls, String path) throws IOException {
        return (T)((Node)FXMLLoader.load((URL)cls.getResource(path)));
    }
}
