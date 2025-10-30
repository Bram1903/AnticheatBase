package com.deathmotion.anticheatbase.common;

import com.deathmotion.anticheatbase.api.AnticheatBaseAPI;
import com.deathmotion.anticheatbase.api.event.EventBus;
import com.deathmotion.anticheatbase.api.versioning.ACVersion;
import com.deathmotion.anticheatbase.common.util.ACVersions;
import org.jetbrains.annotations.NotNull;

public final class ACPlatformAPI implements AnticheatBaseAPI {

    private final ACPlatform platform;

    public ACPlatformAPI(ACPlatform platform) {
        this.platform = platform;
    }

    @Override
    public @NotNull ACVersion getVersion() {
        return ACVersions.CURRENT;
    }

    @Override
    public @NotNull EventBus getEventBus() {
        return platform.getEventBus();
    }
}
