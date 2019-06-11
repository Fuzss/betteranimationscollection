package com.fuzs.betteranimationscollection2.helper;

import com.fuzs.betteranimationscollection2.config.ConfigHandler;
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

}
