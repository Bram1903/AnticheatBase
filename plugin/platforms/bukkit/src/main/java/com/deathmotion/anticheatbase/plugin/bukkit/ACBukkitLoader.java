package com.deathmotion.anticheatbase.plugin.bukkit;

import com.deathmotion.anticheatbase.loader.plugin.shared.ACPluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class ACBukkitLoader implements ACPluginLoader {

    private final ACBukkitPlatform ac;

    public ACBukkitLoader(JavaPlugin plugin) {
        this.ac = new ACBukkitPlatform(plugin);
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
