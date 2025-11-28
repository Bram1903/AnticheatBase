package com.deathmotion.anticheatbase.loader.plugin.common;

import lombok.Getter;

import java.util.logging.Logger;

@Getter
public abstract class ACPlatformLoader {

    @Getter
    private static ACPlatformLoader instance;

    @Getter
    private final boolean isProxy;

    private Logger logger;

    public ACPlatformLoader() {
        this.isProxy = false;
    }

    public ACPlatformLoader(boolean isProxy) {
        this.isProxy = isProxy;
    }

    public void commonOnInitialize() {
        instance = this;
    }

    public void commonOnEnable() {
        logger = Logger.getLogger("AntiCheatBaseLoader");
        logger.info("AntiCheatBase loader enabled.");
    }

    public void commonOnDisable() {
        logger.info("AntiCheatBase loader disabled.");
    }
}
