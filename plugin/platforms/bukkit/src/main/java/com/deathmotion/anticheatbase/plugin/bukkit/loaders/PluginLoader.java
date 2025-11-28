package com.deathmotion.anticheatbase.plugin.bukkit.loaders;

import com.deathmotion.anticheatbase.loader.common.ACPlatformLoader;
import com.deathmotion.anticheatbase.loader.common.ACPluginLoader;
import com.deathmotion.anticheatbase.plugin.bukkit.ACBukkitPlatform;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginLoader implements ACPluginLoader {

    private final ACBukkitPlatform ac;

    public PluginLoader() {
        ACPlatformLoader platformLoader = ACPlatformLoader.getInstance();
        JavaPlugin plugin = (JavaPlugin) platformLoader.getLoaderInstance();
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
