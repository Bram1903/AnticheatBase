package com.deathmotion.anticheatbase.plugin.common;

import com.deathmotion.anticheatbase.api.AnticheatBase;
import com.deathmotion.anticheatbase.plugin.common.events.EventBusImpl;
import com.deathmotion.anticheatbase.plugin.common.events.packet.PacketPlayerJoinQuit;
import com.deathmotion.anticheatbase.plugin.common.manager.PlayerManager;
import com.deathmotion.anticheatbase.plugin.common.platform.player.PlatformUserFactory;
import com.github.retrooper.packetevents.PacketEvents;
import lombok.Getter;

import java.util.logging.Logger;

@Getter
public abstract class ACPlatform {

    @Getter
    private static ACPlatform instance;

    @Getter
    private final boolean isProxy;

    private Logger logger;
    private ACPlatformAPI api;

    private EventBusImpl eventBus;
    private PlayerManager playerManager;

    public ACPlatform() {
        this.isProxy = false;
    }

    public ACPlatform(boolean isProxy) {
        this.isProxy = isProxy;
    }

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

    public abstract PlatformUserFactory getPlatformUserFactory();
}
