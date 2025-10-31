package com.deathmotion.anticheatbase.common.util;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.util.List;

@UtilityClass
public class MiscUtil {

    public String format(String string, @NotNull Object... objects) {
        for (int i = 0; i < objects.length; i++) {
            string = string.replace("{" + i + "}", objects[i].toString());
        }

        return string;
    }

    public double[] listToArray(List<? extends Number> list) {
        return list
                .stream()
                .mapToDouble(Number::doubleValue)
                .toArray();
    }

}
