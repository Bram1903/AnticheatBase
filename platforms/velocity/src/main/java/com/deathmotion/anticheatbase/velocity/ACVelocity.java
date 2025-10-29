package com.deathmotion.anticheatbase.velocity;

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

    @Getter
    private final ACVelocityPlatform ac;

    @Inject
    public ACVelocity(ProxyServer server, @DataDirectory Path dataDirectory) {
        this.server = server;
        this.ac = new ACVelocityPlatform(server, dataDirectory);
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