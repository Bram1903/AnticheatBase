package com.deathmotion.anticheatbase.loader.plugin.bukkit;

import com.deathmotion.anticheatbase.loader.plugin.common.ACPlatformLoader;
import com.deathmotion.anticheatbase.loader.plugin.common.PlatformType;
import lombok.Getter;

@Getter
public class ACBukkitPlatformLoader extends ACPlatformLoader {

    private final ACBukkitLoader plugin;

    public ACBukkitPlatformLoader(ACBukkitLoader plugin) {
        super(plugin, PlatformType.Bukkit);
        this.plugin = plugin;
    }
}