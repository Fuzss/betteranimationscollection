package com.fuzs.betteranimationscollection2.helper;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.ArrayUtils;

import java.util.Arrays;

public class ConfigHelper {

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

    public static boolean getConfigBoolean(ForgeConfigSpec.ConfigValue<Boolean> value) {
        return value != null && value.get();
    }

    public static String[] getEnumDescription(String comment, Class<? extends Enum<?>> clazz) {

        String[] comments = new String[]{comment, "Valid values:"};
        String[] modes = Arrays.stream(clazz.getEnumConstants()).map(Enum::toString).toArray(String[]::new);
        return ArrayUtils.addAll(comments, modes);

    }

}
