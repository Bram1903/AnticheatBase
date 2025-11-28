package com.deathmotion.anticheatbase.plugin.bukkit.loaders;

import com.deathmotion.anticheatbase.plugin.bukkit.ACBukkitPlatform;
import org.bukkit.plugin.java.JavaPlugin;

public class BukkitLoader extends JavaPlugin {

    private final ACBukkitPlatform ac;

    public BukkitLoader() {
        this.ac = new ACBukkitPlatform(this);
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