package com.deathmotion.anticheatbase.loader.common;

import com.deathmotion.anticheatbase.loader.common.entities.constants.LoaderConstants;
import com.deathmotion.anticheatbase.loader.common.entities.enums.PlatformType;
import com.deathmotion.anticheatbase.loader.common.utils.ByteClassLoader;
import lombok.Getter;

import java.io.File;
import java.util.logging.Logger;

@Getter
public abstract class ACPlatformLoader {

    @Getter
    private static ACPlatformLoader instance;

    private final Object loaderInstance;
    private final PlatformType platformType;

    private Logger logger;

    private ACPluginLoader pluginLoader;

    public ACPlatformLoader(Object loaderInstance, PlatformType platformType) {
        this.loaderInstance = loaderInstance;
        this.platformType = platformType;
        instance = this;
    }

    public void commonOnInitialize() {
    }

    public void commonOnEnable() {
        logger = Logger.getLogger("AntiCheatBaseLoader");

        try {
            // TODO: This is just for testing, and will be moved to the license server
            String jarPath = "C:\\AnticheatBase-Bukkit-1.0.0-SNAPSHOT.jar";

            ByteClassLoader classLoader = new ByteClassLoader(new File(jarPath));

            this.pluginLoader = classLoader
                    .loadClass(LoaderConstants.getPluginEntryPoint(platformType), false)
                    .asSubclass(ACPluginLoader.class)
                    .newInstance();

            logger.info("Successfully injected the anticheat plugin.");
            this.pluginLoader.onEnable();
        } catch (Exception ex) {
            logger.severe("Failed to load platform plugin: " + ex.getMessage());
        }
    }

    public void commonOnDisable() {
        if (pluginLoader != null) {
            pluginLoader.onDisable();
            logger.info("AntiCheatBase plugin disabled.");
        }
    }
}
