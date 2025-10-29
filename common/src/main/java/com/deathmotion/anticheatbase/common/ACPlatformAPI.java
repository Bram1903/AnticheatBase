package com.deathmotion.anticheatbase.common;

import com.deathmotion.anticheatbase.api.AnticheatBaseAPI;
import com.deathmotion.anticheatbase.api.versioning.ACVersion;
import com.deathmotion.anticheatbase.common.util.ACVersions;
import org.jetbrains.annotations.NotNull;

public final class ACPlatformAPI implements AnticheatBaseAPI {

    private final ACPlatform platform;

    public ACPlatformAPI(ACPlatform platform) {
        this.platform = platform;
        platform.getLogger().info("API initialized.");
    }

    @Override
    public @NotNull ACVersion getVersion() {
        return ACVersions.CURRENT;
    }
}
