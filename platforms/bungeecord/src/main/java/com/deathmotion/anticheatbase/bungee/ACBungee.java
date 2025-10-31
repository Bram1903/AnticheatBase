package com.deathmotion.anticheatbase.bungee;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public final class ACBungee extends Plugin {
    private final BungeePlatform ac = new BungeePlatform(this);

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
