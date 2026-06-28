package net.justmili.libs.v1.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtil {
    /**
     * Any integer, long, double or float can be input as-is,
     * and it'll just be turned into a double
     * @param ticks
     * @return
     */
    public static double ticksToSeconds(double ticks) {
        double minutes = ticks / 20;
        return BigDecimal.valueOf(minutes).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public static double ticksToMinutes(double ticks) {
        double minutes = ticks / 20 / 60;
        return BigDecimal.valueOf(minutes).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public static double ticksToHours(double ticks) {
        double hours = ticks / 20 / 60 / 60;
        return BigDecimal.valueOf(hours).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
    public static double ticksToDays(double ticks) {
        double hours = ticks / 20 / 60 / 60 / 24;
        return BigDecimal.valueOf(hours).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
