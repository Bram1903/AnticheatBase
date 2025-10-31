package com.deathmotion.anticheatbase.bukkit.testplugin.events;

import com.deathmotion.anticheatbase.api.AnticheatBaseAPI;
import com.deathmotion.anticheatbase.api.event.EventSubscription;
import com.deathmotion.anticheatbase.api.event.impl.ACUserJoinEvent;
import com.deathmotion.anticheatbase.bukkit.testplugin.ApiTestPlugin;

public class ACUserJoinEventListener {

    private final ApiTestPlugin plugin;
    private EventSubscription subscription;

    public ACUserJoinEventListener(ApiTestPlugin plugin, AnticheatBaseAPI api) {
        this.plugin = plugin;
        this.subscription = api.getEventBus().subscribe(ACUserJoinEvent.class, this::onUserJoin);
    }

    public void unregister() {
        if (subscription != null) {
            subscription.unsubscribe();
            subscription = null;
        }
    }

    private void onUserJoin(ACUserJoinEvent event) {
        plugin.getLogger().info("User " + event.getUser().getName() + " joined the server.");
    }
}
