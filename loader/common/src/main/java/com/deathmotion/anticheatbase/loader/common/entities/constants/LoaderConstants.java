package com.deathmotion.anticheatbase.loader.common.entities.constants;

import com.deathmotion.anticheatbase.loader.common.entities.enums.PlatformType;

import java.util.Locale;

public final class LoaderConstants {
    public static final String PLUGIN_BASE_PACKAGE = "com.deathmotion.anticheatbase.plugin";
    public static final String PLUGIN_MAIN_ENTRY = "PluginLoader";

    public static String getPluginEntryPoint(PlatformType type) {
        return PLUGIN_BASE_PACKAGE + "." + type.toString().toLowerCase(Locale.ROOT) + ".loaders." + PLUGIN_MAIN_ENTRY;
    }
}
