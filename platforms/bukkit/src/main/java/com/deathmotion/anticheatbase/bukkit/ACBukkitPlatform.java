package com.deathmotion.anticheatbase.bukkit;

import com.deathmotion.anticheatbase.common.ACPlatform;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ACBukkitPlatform extends ACPlatform {

    private final JavaPlugin plugin;

    public ACBukkitPlatform(JavaPlugin plugin) {
        this.plugin = plugin;
    }
}