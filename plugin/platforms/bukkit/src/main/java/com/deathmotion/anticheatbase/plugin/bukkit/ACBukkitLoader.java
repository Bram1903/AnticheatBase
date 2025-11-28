package com.deathmotion.anticheatbase.plugin.bukkit;

import com.deathmotion.anticheatbase.loader.plugin.common.ACPlatformLoader;
import com.deathmotion.anticheatbase.loader.plugin.common.ACPluginLoader;
import org.bukkit.plugin.java.JavaPlugin;

public class ACBukkitLoader implements ACPluginLoader {

    private final ACBukkitPlatform ac;

    public ACBukkitLoader() {
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
