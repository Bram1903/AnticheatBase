package com.deathmotion.anticheatbase.plugin.bukkit.player;

import com.deathmotion.anticheatbase.plugin.common.platform.player.AbstractPlatformUserFactory;
import com.deathmotion.anticheatbase.plugin.common.platform.player.PlatformPlayer;
import com.deathmotion.anticheatbase.plugin.common.platform.player.PlatformUser;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BukkitPlatformUserFactory extends AbstractPlatformUserFactory<Player> {

    @Override
    protected Player getNativePlayer(@NotNull UUID uuid) {
        return Bukkit.getPlayer(uuid);
    }

    @Override
    protected @NotNull PlatformUser createPlatformUser(@NotNull Player nativePlayer) {
        return new BukkitPlatformUser(nativePlayer);
    }

    @Override
    protected @Nullable PlatformPlayer createPlatformPlayer(@NotNull Player nativePlayer) {
        return new BukkitPlatformPlayer(nativePlayer);
    }
}
