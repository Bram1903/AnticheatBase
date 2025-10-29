package com.deathmotion.anticheatbase.common;

import com.deathmotion.anticheatbase.api.AnticheatBase;
import lombok.Getter;

import java.util.logging.Logger;

@Getter
public abstract class ACPlatform {
    @Getter
    private static ACPlatform instance;

    private final Logger logger;

    public ACPlatform() {
        logger = Logger.getLogger("AntiCheatBase");
    }

    public void commonOnInitialize() {
        instance = this;
        AnticheatBase.init(new ACPlatformAPI());

        logger.info("AntiCheatBase initialized.");
    }

    public void commonOnEnable() {
        logger.info("AntiCheatBase enabled.");
    }

    public void commonOnDisable() {
        logger.info("AntiCheatBase disabled.");
    }

}
