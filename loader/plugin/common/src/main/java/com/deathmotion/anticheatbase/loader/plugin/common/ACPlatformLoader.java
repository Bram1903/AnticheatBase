package com.deathmotion.anticheatbase.loader.plugin.common;

import com.deathmotion.anticheatbase.loader.plugin.common.utils.ByteClassLoader;
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
        logger.info("Loading AntiCheatBase for platform: " + platformType.name());

        try {
            String jarPath = "TODO";

            File pluginJarFile = new File(jarPath);
            ByteClassLoader classLoader = new ByteClassLoader(pluginJarFile);

            String mainClassName = "com.deathmotion.anticheatbase.plugin.bukkit.ACBukkitLoader";

            this.pluginLoader = classLoader
                    .loadClass(mainClassName, false)
                    .asSubclass(ACPluginLoader.class)
                    .newInstance();

            logger.info("Loaded ACPluginLoader: " + this.pluginLoader.getClass().getName());

            this.pluginLoader.onEnable();
        } catch (Exception ex) {
            logger.severe("Failed to load platform plugin: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void commonOnDisable() {
        if (pluginLoader != null) {
            pluginLoader.onDisable();
            logger.info("AntiCheatBase plugin disabled.");
        }

        logger.info("AntiCheatBase loader disabled.");
    }
}
