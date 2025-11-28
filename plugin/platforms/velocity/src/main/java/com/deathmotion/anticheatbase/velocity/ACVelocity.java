package com.deathmotion.anticheatbase.velocity;

import com.deathmotion.anticheatbase.velocity.player.VelocityPlatformUserFactory;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.event.proxy.ProxyShutdownEvent;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;
import lombok.Getter;

import java.nio.file.Path;

public class ACVelocity {

    private final ProxyServer server;
    private final Path dataDirectory;

    @Getter
    private final ACVelocityPlatform ac;

    @Getter
    private final VelocityPlatformUserFactory platformUserFactory;

    @Inject
    public ACVelocity(ProxyServer server, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.dataDirectory = dataDirectory;

        this.platformUserFactory = new VelocityPlatformUserFactory(server);
        this.ac = new ACVelocityPlatform(this);
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent ignoredEvent) {
        ac.commonOnInitialize();
        ac.commonOnEnable();
    }

    @Subscribe()
    public void onProxyShutdown(ProxyShutdownEvent ignoredEvent) {
        ac.commonOnDisable();
    }
}