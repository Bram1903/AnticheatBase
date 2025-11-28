package com.deathmotion.anticheatbase.bukkit.player;

import com.deathmotion.anticheatbase.common.platform.player.PlatformUser;
import lombok.Getter;
import org.bukkit.entity.Player;

public class BukkitPlatformUser implements PlatformUser {

    @Getter
    private final Player bukkitPlayer;

    public BukkitPlatformUser(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
    }

    @Override
    public boolean hasPermission(String permission) {
        return bukkitPlayer.hasPermission(permission);
    }
}
