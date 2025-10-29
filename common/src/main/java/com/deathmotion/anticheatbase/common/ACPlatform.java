package com.deathmotion.anticheatbase.common;

import com.deathmotion.anticheatbase.api.AnticheatBase;
import com.deathmotion.anticheatbase.common.api.ACPlatformAPI;
import lombok.Getter;

import java.util.logging.Logger;

@Getter
public abstract class ACPlatform {

    @Getter
    private static ACPlatform instance;

    private Logger logger;
    private ACPlatformAPI api;

    public void commonOnInitialize() {
        instance = this;

        logger = Logger.getLogger("AntiCheatBase");
        logger.info("AntiCheatBase initialized.");
    }

    public void commonOnEnable() {
        api = new ACPlatformAPI(this);
        AnticheatBase.init(api);

        logger.info("AntiCheatBase enabled.");
    }

    public void commonOnDisable() {
        logger.info("AntiCheatBase disabled.");
    }

}
