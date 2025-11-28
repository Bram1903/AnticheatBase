package com.deathmotion.anticheatbase.bungee.player;

import com.deathmotion.anticheatbase.common.platform.player.AbstractPlatformUserFactory;
import com.deathmotion.anticheatbase.common.platform.player.PlatformPlayer;
import com.deathmotion.anticheatbase.common.platform.player.PlatformUser;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class BungeePlatformUserFactory extends AbstractPlatformUserFactory<ProxiedPlayer> {

    @Override
    protected ProxiedPlayer getNativePlayer(@NotNull UUID uuid) {
        return ProxyServer.getInstance().getPlayer(uuid);
    }

    @Override
    protected @NotNull PlatformUser createPlatformUser(@NotNull ProxiedPlayer nativePlayer) {
        return new BungeePlatformUser(nativePlayer);
    }

    @Override
    protected @Nullable PlatformPlayer createPlatformPlayer(@NotNull ProxiedPlayer nativePlayer) {
        return null;
    }
}
