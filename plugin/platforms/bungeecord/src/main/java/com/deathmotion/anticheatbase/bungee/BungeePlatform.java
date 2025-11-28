package com.deathmotion.anticheatbase.bungee;

import com.deathmotion.anticheatbase.common.ACPlatform;
import com.deathmotion.anticheatbase.common.platform.player.PlatformUserFactory;
import lombok.Getter;

@Getter
public class BungeePlatform extends ACPlatform {

    private final ACBungee plugin;

    public BungeePlatform(ACBungee plugin) {
        super(true);
        this.plugin = plugin;
    }

    @Override
    public PlatformUserFactory getPlatformUserFactory() {
        return plugin.getBungeePlatformUserFactory();
    }
}
