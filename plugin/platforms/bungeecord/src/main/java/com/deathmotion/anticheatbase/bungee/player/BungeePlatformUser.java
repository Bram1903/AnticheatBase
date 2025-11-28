package com.deathmotion.anticheatbase.bungee.player;

import com.deathmotion.anticheatbase.common.platform.player.PlatformUser;
import lombok.Getter;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeePlatformUser implements PlatformUser {

    @Getter
    private final ProxiedPlayer bungeePlayer;

    public BungeePlatformUser(ProxiedPlayer bungeePlayer) {
        this.bungeePlayer = bungeePlayer;
    }

    @Override
    public boolean hasPermission(String permission) {
        return bungeePlayer.hasPermission(permission);
    }
}
