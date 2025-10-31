package com.deathmotion.anticheatbase.bungee;

import com.deathmotion.anticheatbase.bungee.player.BungeePlatformUserFactory;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public final class ACBungee extends Plugin {
    private final BungeePlatform ac = new BungeePlatform(this);

    @Getter
    private final BungeePlatformUserFactory bungeePlatformUserFactory = new BungeePlatformUserFactory();

    @Override
    public void onEnable() {
        ac.commonOnInitialize();
        ac.commonOnEnable();
    }

    @Override
    public void onDisable() {
        ac.commonOnDisable();
    }
}
