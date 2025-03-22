/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javafx.scene.control.Alert
 *  javafx.scene.control.Alert$AlertType
 */
package util;

import javafx.scene.control.Alert;

public class Alerts {
    public static Alert infoAlert(String message) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setHeaderText("SUCCESS");
        a.setTitle("SYSTEM MESSAGE");
        a.setContentText(message);
        return a;
    }

    public static Alert errorAlert(String message) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText("ERROR");
        a.setTitle("INPUT ERROR");
        a.setContentText(message);
        return a;
    }
}
