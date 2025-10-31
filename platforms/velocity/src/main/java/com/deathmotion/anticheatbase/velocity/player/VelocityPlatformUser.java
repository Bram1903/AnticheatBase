package com.deathmotion.anticheatbase.velocity.player;

import com.deathmotion.anticheatbase.common.platform.player.PlatformUser;
import com.velocitypowered.api.proxy.Player;
import lombok.Getter;

public class VelocityPlatformUser implements PlatformUser {

    @Getter
    private final Player velocityPlayer;

    public VelocityPlatformUser(Player velocityPlayer) {
        this.velocityPlayer = velocityPlayer;
    }

    @Override
    public boolean hasPermission(String permission) {
        return velocityPlayer.hasPermission(permission);
    }
}
