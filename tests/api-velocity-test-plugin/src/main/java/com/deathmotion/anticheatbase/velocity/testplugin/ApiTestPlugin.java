package com.deathmotion.anticheatbase.velocity.testplugin;

import com.deathmotion.anticheatbase.api.AnticheatBase;
import com.deathmotion.anticheatbase.velocity.testplugin.events.ACUserJoinEventListener;
import com.deathmotion.anticheatbase.velocity.testplugin.events.ACUserQuitEventListener;
import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.slf4j.Logger;

public final class ApiTestPlugin {

    @Getter
    private final Logger logger;

    private @Nullable ACUserJoinEventListener acUserJoinEventListener;
    private @Nullable ACUserQuitEventListener acUserQuitEventListener;

    @Inject
    public ApiTestPlugin(Logger logger) {
        this.logger = logger;
    }

    @Subscribe
    public void onProxyInitialization(ProxyInitializeEvent ignoredEvent) {
        AnticheatBase.getAsync().thenAccept(api -> {
            logger.info("Hooked into AnticheatBase version {}.", api.getVersion());

            acUserJoinEventListener = new ACUserJoinEventListener(this, api);
            acUserQuitEventListener = new ACUserQuitEventListener(this, api);
        });
    }

    @Subscribe
    public void onProxyShutdown(ProxyInitializeEvent ignoredEvent) {
        if (acUserJoinEventListener != null) {
            acUserJoinEventListener.unregister();
            acUserJoinEventListener = null;
        }

        if (acUserQuitEventListener != null) {
            acUserQuitEventListener.unregister();
            acUserQuitEventListener = null;
        }
    }
}
