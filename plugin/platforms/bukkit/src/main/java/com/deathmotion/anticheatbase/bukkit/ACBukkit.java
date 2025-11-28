package com.deathmotion.anticheatbase.bukkit;

import com.deathmotion.anticheatbase.bukkit.player.BukkitPlatformUserFactory;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ACBukkit extends JavaPlugin {

    private final ACBukkitPlatform ac = new ACBukkitPlatform(this);

    @Getter
    private final BukkitPlatformUserFactory bukkitPlatformUserFactory = new BukkitPlatformUserFactory();

    @Override
    public void onLoad() {
        ac.commonOnInitialize();
    }

    @Override
    public void onEnable() {
        ac.commonOnEnable();
    }

    @Override
    public void onDisable() {
        ac.commonOnDisable();
    }
}