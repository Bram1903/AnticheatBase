package com.deathmotion.anticheatbase.common.api;

import com.deathmotion.anticheatbase.api.AnticheatBaseAPI;
import com.deathmotion.anticheatbase.api.event.EventBus;
import com.deathmotion.anticheatbase.api.versioning.ACVersion;
import com.deathmotion.anticheatbase.common.ACPlatform;
import com.deathmotion.anticheatbase.common.api.impl.EventBusImpl;
import com.deathmotion.anticheatbase.common.util.ACVersions;
import org.jetbrains.annotations.NotNull;

public final class ACPlatformAPI implements AnticheatBaseAPI {

    private final ACPlatform platform;
    private final EventBus eventBus;

    public ACPlatformAPI(ACPlatform platform) {
        this.platform = platform;
        this.eventBus = new EventBusImpl();

        platform.getLogger().info("API initialized.");
    }

    @Override
    public @NotNull ACVersion getVersion() {
        return ACVersions.CURRENT;
    }

    @Override
    public @NotNull EventBus getEventBus() {
        return eventBus;
    }
}
