package com.deathmotion.anticheatbase.loader.bukkit;

import com.deathmotion.anticheatbase.loader.common.ACPlatformLoader;
import com.deathmotion.anticheatbase.loader.common.entities.enums.PlatformType;
import lombok.Getter;

@Getter
public class ACBukkitPlatformLoader extends ACPlatformLoader {

    private final ACBukkitLoader plugin;

    public ACBukkitPlatformLoader(ACBukkitLoader plugin) {
        super(plugin, PlatformType.Bukkit);
        this.plugin = plugin;
    }
}