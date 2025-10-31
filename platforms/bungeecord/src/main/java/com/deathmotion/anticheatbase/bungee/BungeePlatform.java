package com.deathmotion.anticheatbase.bungee;

import com.deathmotion.anticheatbase.common.ACPlatform;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class BungeePlatform extends ACPlatform {

    private final Plugin plugin;

    public BungeePlatform(Plugin plugin) {
        this.plugin = plugin;
    }
}
