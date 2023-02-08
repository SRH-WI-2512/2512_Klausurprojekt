package controller;

import java.text.DecimalFormat;
import java.text.ParseException;

public class Tools {
    private final static DecimalFormat DF = new DecimalFormat("#,##0.00 €");

    public static boolean checkInteger(String text) {
        try {
            Integer.parseInt(text);
            return true;
        }
        catch (NumberFormatException ignored) {}
        return false;
    }

    public static int toInteger(String text) {
        int value = 0;
        try {
            value = Integer.parseInt(text);
        }
        catch (NumberFormatException ignored) {}
        return value;
    }

    public static String integerToString(int value) {
        return Integer.toString(value);
    }

    public static String doubleToString(double value) {
        return DF.format(value);
    }

    public static boolean checkDouble(String text) {
        try {
            DF.parse(text);
            return true;
        }
        catch (ParseException ignored) {}
        return false;
    }

    public static double toDouble(String text) {
        double value = 0.0;
        try {
            value = (double) DF.parse(text);
        }
        catch (ParseException ignored) {}
        return value;
    }
}
