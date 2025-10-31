package com.deathmotion.anticheatbase.sponge;

import com.deathmotion.anticheatbase.common.ACPlatform;
import com.google.inject.Inject;
import org.spongepowered.api.config.ConfigDir;

import java.nio.file.Path;

public class SpongePlatform extends ACPlatform {

    private final Path configDirectory;

    @Inject
    public SpongePlatform(@ConfigDir(sharedRoot = false) Path configDirectory) {
        this.configDirectory = configDirectory;
    }

}
