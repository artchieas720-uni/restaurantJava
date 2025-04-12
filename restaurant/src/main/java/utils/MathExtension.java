package utils;

public class MathExtension {
    public static double roundingDecimals(double value) {
        return Math.round(value * 100.0) / 100.0;
    }
}
