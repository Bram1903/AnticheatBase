package com.deathmotion.anticheatbase.velocity;

import com.deathmotion.anticheatbase.common.ACPlatform;
import com.deathmotion.anticheatbase.common.platform.player.PlatformUserFactory;

public class ACVelocityPlatform extends ACPlatform {

    private final ACVelocity plugin;

    public ACVelocityPlatform(ACVelocity plugin) {
        super(true);
        this.plugin = plugin;
    }

    @Override
    public PlatformUserFactory getPlatformUserFactory() {
        return plugin.getPlatformUserFactory();
    }
}
