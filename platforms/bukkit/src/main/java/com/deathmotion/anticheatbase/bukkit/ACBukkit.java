package com.deathmotion.anticheatbase.bukkit;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ACBukkit extends JavaPlugin {
    private final BukkitAnticheat ahi = new BukkitAnticheat(this);

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