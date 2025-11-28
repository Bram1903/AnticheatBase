package com.deathmotion.anticheatbase.plugin.bukkit;

import com.deathmotion.anticheatbase.plugin.bukkit.player.BukkitPlatformUserFactory;
import com.deathmotion.anticheatbase.plugin.common.ACPlatform;
import com.deathmotion.anticheatbase.plugin.common.platform.player.PlatformUserFactory;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ACBukkitPlatform extends ACPlatform {

    private final JavaPlugin plugin;

    @Getter
    private final BukkitPlatformUserFactory bukkitPlatformUserFactory = new BukkitPlatformUserFactory();

    public ACBukkitPlatform(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public PlatformUserFactory getPlatformUserFactory() {
        return bukkitPlatformUserFactory;
    }
}