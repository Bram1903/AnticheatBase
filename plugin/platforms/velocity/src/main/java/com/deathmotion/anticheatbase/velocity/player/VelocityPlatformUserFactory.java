package com.deathmotion.anticheatbase.velocity.player;

import com.deathmotion.anticheatbase.common.platform.player.AbstractPlatformUserFactory;
import com.deathmotion.anticheatbase.common.platform.player.PlatformPlayer;
import com.deathmotion.anticheatbase.common.platform.player.PlatformUser;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class VelocityPlatformUserFactory extends AbstractPlatformUserFactory<Player> {

    private final ProxyServer server;

    public VelocityPlatformUserFactory(ProxyServer server) {
        this.server = server;
    }

    @Override
    protected Player getNativePlayer(@NotNull UUID uuid) {
        return server.getPlayer(uuid).orElse(null);
    }

    @Override
    protected @NotNull PlatformUser createPlatformUser(@NotNull Player nativePlayer) {
        return new VelocityPlatformUser(nativePlayer);
    }

    @Override
    protected @Nullable PlatformPlayer createPlatformPlayer(@NotNull Player nativePlayer) {
        return null;
    }
}
