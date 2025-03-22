/*
 * Decompiled with CFR 0.152.
 */
package util;

import java.time.Duration;
import java.time.LocalDateTime;

public class InputValidetions {
    public static boolean validatePositivesNum(int num) {
        return num > 0;
    }

    public static boolean validateLastAferFirst(LocalDateTime first, LocalDateTime last) {
        return last.isAfter(first);
    }

    public static boolean validateName(String s) {
        int i = 0;
        while (i < s.length()) {
            if (!(s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' || s.charAt(i) >= 'a' && s.charAt(i) <= 'z' || s.charAt(i) == ' ')) {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static boolean validatePositiveIntegerOrZero(String s) {
        int i = 0;
        while (i < s.length()) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
            ++i;
        }
        return true;
    }

    public static boolean validateTimeZone(int timeZone) {
        return timeZone <= 12 && timeZone >= -12;
    }

    public static boolean validateTailNum(String s) {
        if (s.length() > 2 && s.charAt(0) >= 'A' && s.charAt(0) <= 'Z' && s.charAt(1) == '-') {
            String subS = s.substring(2);
            int i = 0;
            while (i < subS.length()) {
                if (!(subS.charAt(i) >= 'A' && subS.charAt(i) <= 'Z' || subS.charAt(i) >= 'a' && subS.charAt(i) <= 'z' || subS.charAt(i) >= '0' && subS.charAt(i) <= '9')) {
                    return false;
                }
                ++i;
            }
            return true;
        }
        return false;
    }

    public static boolean validateDiffPilots(String id1, String id2) {
        return !id1.equals(id2);
    }

    public static boolean validateDiffAirports(int dep, int land) {
        return dep != land;
    }

    public static boolean validateDepDate(LocalDateTime dep) {
        long daysBetween;
        LocalDateTime today = LocalDateTime.now();
        return dep.isAfter(today) && (daysBetween = Duration.between(today, dep).toDays()) >= 60L;
    }
}
