package com.deathmotion.anticheatbase.plugin.bukkit;

import com.deathmotion.anticheatbase.plugin.common.ACPlatform;
import com.deathmotion.anticheatbase.plugin.common.platform.player.PlatformUserFactory;
import lombok.Getter;

@Getter
public class ACBukkitPlatform extends ACPlatform {

    private final ACBukkit plugin;

    public ACBukkitPlatform(ACBukkit plugin) {
        this.plugin = plugin;
    }

    @Override
    public PlatformUserFactory getPlatformUserFactory() {
        return plugin.getBukkitPlatformUserFactory();
    }
}