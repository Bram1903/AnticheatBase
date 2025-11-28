package com.deathmotion.anticheatbase.loader.plugin.bukkit;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class ACBukkitLoader extends JavaPlugin {

    private final ACBukkitPlatformLoader acLoader = new ACBukkitPlatformLoader(this);

    @Override
    public void onLoad() {
        acLoader.commonOnInitialize();
    }

    @Override
    public void onEnable() {
        acLoader.commonOnEnable();
    }

    @Override
    public void onDisable() {
        acLoader.commonOnDisable();
    }
}