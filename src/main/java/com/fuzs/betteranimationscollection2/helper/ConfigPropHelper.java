package com.fuzs.betteranimationscollection2.helper;

import com.fuzs.betteranimationscollection2.handler.ConfigHandler;
import net.minecraftforge.common.config.Property;

public class ConfigPropHelper {

    public static boolean loadPropBoolean(String name, String category, boolean defaultValue, String comment, boolean restart) {

        String s = comment + " [default: " + defaultValue + "]";
        Property prop = ConfigHandler.config.get(category, name, defaultValue, s);
        prop.setLanguageKey(name).setRequiresMcRestart(restart);
        return prop.getBoolean(defaultValue);

    }

    public static int loadPropInt(String name, String category, int defaultValue, String comment, int min, int max, boolean restart) {

        String s = comment + " [range: " + min + " ~ " + max + ", default: " + defaultValue + "]";
        Property prop = ConfigHandler.config.get(category, name, defaultValue, s, min, max);
        prop.setLanguageKey(name).setRequiresMcRestart(restart).setHasSlidingControl(true);
        return prop.getInt(defaultValue);

    }

    public static double loadPropDouble(String name, String category, double defaultValue, String comment, double min, double max, boolean restart) {

        String s = comment + " [range: " + min + " ~ " + max + ", default: " + defaultValue + "]";
        Property prop = ConfigHandler.config.get(category, name, defaultValue, s, min, max);
        prop.setLanguageKey(name).setRequiresMcRestart(restart).setHasSlidingControl(true);
        return prop.getDouble(defaultValue);

    }

    public static String arrayToCustomString(String[] array) {

        if (array == null || array.length < 1) {
            return "null";
        }

        StringBuilder builder = new StringBuilder();
        for (String s : array) {
            builder.append(s);
            builder.append(", ");
        }

        return builder.substring(0, builder.length() - 2);

    }

}
