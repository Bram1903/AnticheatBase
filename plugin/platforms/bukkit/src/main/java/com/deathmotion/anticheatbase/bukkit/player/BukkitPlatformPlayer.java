package com.deathmotion.anticheatbase.bukkit.player;

import com.deathmotion.anticheatbase.common.platform.player.PlatformPlayer;
import lombok.Getter;
import org.bukkit.entity.Player;

public class BukkitPlatformPlayer implements PlatformPlayer {

    @Getter
    private final Player bukkitPlayer;

    public BukkitPlatformPlayer(Player bukkitPlayer) {
        this.bukkitPlayer = bukkitPlayer;
    }
}