/*
 * Decompiled with CFR 0.152.
 */
package autopilot;

import java.io.FileOutputStream;
import java.io.IOException;

public final class AutoUtils {
    public static void makeFile(String fileName, String data) {
        byte[] bytes = data.getBytes();
        try {
            Throwable throwable = null;
            Object var4_6 = null;
            try (FileOutputStream out = new FileOutputStream(fileName);){
                out.write(bytes);
            }
            catch (Throwable throwable2) {
                if (throwable == null) {
                    throwable = throwable2;
                } else if (throwable != throwable2) {
                    throwable.addSuppressed(throwable2);
                }
                throw throwable;
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
