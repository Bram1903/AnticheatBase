package com.deathmotion.anticheatbase.bukkit;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ACBukkit extends JavaPlugin {
    private final ACBukkitPlatform ahi = new ACBukkitPlatform(this);

    @Override
    public void onLoad() {
        ahi.commonOnInitialize();
    }

    @Override
    public void onEnable() {
        ahi.commonOnEnable();
    }

    @Override
    public void onDisable() {
        ahi.commonOnDisable();
    }
}