package com.deathmotion.anticheatbase.common;

import com.deathmotion.anticheatbase.api.AnticheatBase;
import com.deathmotion.anticheatbase.common.events.EventBusImpl;
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

    private EventBusImpl eventBus;
    private PlayerManager playerManager;

    public void commonOnInitialize() {
        instance = this;
    }

    public void commonOnEnable() {
        logger = Logger.getLogger("AntiCheatBase");

        eventBus = new EventBusImpl();
        playerManager = new PlayerManager(this);

        PacketEvents.getAPI().getEventManager().registerListener(new PacketPlayerJoinQuit(this));

        api = new ACPlatformAPI(this);
        AnticheatBase.init(api);
        logger.info("AntiCheatBase enabled.");
    }

    public void commonOnDisable() {
        logger.info("AntiCheatBase disabled.");
    }

}
