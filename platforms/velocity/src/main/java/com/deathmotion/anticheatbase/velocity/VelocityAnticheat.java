package com.deathmotion.anticheatbase.velocity;

import com.deathmotion.anticheatbase.common.ACPlatform;
import com.google.inject.Inject;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import com.velocitypowered.api.proxy.ProxyServer;

import java.nio.file.Path;

public class VelocityAnticheat extends ACPlatform<ProxyServer> {

    private final ProxyServer proxy;
    private final Path dataDirectory;

    @Inject
    public VelocityAnticheat(ProxyServer proxy, @DataDirectory Path dataDirectory) {
        this.proxy = proxy;
        this.dataDirectory = dataDirectory;
    }
}
