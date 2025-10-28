package com.deathmotion.anticheatbase.common;

import com.deathmotion.anticheatbase.api.AnticheatBaseAPI;
import com.deathmotion.anticheatbase.api.versioning.ACVersion;
import com.deathmotion.anticheatbase.common.util.ACVersions;
import org.jetbrains.annotations.NotNull;

public class ACPlatformAPI implements AnticheatBaseAPI {

    @Override
    public @NotNull ACVersion getVersion() {
        return ACVersions.CURRENT;
    }
}
