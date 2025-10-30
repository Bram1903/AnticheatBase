package com.deathmotion.anticheatbase.common;

import com.deathmotion.anticheatbase.api.AnticheatBase;
import com.deathmotion.anticheatbase.common.events.packet.PacketPlayerJoinQuit;
import com.deathmotion.anticheatbase.common.manager.PlayerManager;
import com.github.retrooper.packetevents.PacketEvents;
import lombok.Getter;

import java.util.logging.Logger;

@Getter
public abstract class ACPlatform {

    @Getter
    private static ACPlatform instance;

    private Logger logger;
    private ACPlatformAPI api;

    private PlayerManager playerManager;

    public void commonOnInitialize() {
        instance = this;
    }

    public void commonOnEnable() {
        logger = Logger.getLogger("AntiCheatBase");
        playerManager = new PlayerManager(this);

        api = new ACPlatformAPI(this);
        AnticheatBase.init(api);

        PacketEvents.getAPI().getEventManager().registerListener(new PacketPlayerJoinQuit(this));

        logger.info("AntiCheatBase enabled.");
    }

    public void commonOnDisable() {
        logger.info("AntiCheatBase disabled.");
    }

}
