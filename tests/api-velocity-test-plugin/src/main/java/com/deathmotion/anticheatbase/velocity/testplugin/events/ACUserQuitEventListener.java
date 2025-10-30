package com.deathmotion.anticheatbase.velocity.testplugin.events;

import com.deathmotion.anticheatbase.api.AnticheatBaseAPI;
import com.deathmotion.anticheatbase.api.event.EventSubscription;
import com.deathmotion.anticheatbase.api.event.impl.ACUserQuitEvent;
import com.deathmotion.anticheatbase.velocity.testplugin.ApiTestPlugin;

public class ACUserQuitEventListener {

    private final ApiTestPlugin plugin;
    private EventSubscription subscription;

    public ACUserQuitEventListener(ApiTestPlugin plugin, AnticheatBaseAPI api) {
        this.plugin = plugin;
        this.subscription = api.getEventBus().subscribe(ACUserQuitEvent.class, this::onUserJoin);
    }

    public void unregister() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    private void onUserJoin(ACUserQuitEvent event) {
        plugin.getLogger().info("User " + event.user.getName() + " has quit the server.");
    }
}
