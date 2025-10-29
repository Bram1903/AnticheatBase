package com.deathmotion.anticheatbase.common;

import com.deathmotion.anticheatbase.api.AnticheatBase;
import lombok.Getter;

import java.util.logging.Logger;

@Getter
public abstract class ACPlatform {
    @Getter
    private static ACPlatform instance;

    private Logger logger;

    public void commonOnInitialize() {
        instance = this;
        logger = Logger.getLogger("AntiCheatBase");
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
