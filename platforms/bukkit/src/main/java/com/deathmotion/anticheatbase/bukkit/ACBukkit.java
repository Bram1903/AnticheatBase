package com.deathmotion.anticheatbase.bukkit;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ACBukkit extends JavaPlugin {
    private final ACBukkitPlatform ac = new ACBukkitPlatform(this);

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