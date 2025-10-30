package com.deathmotion.anticheatbase.bukkit.testplugin;

import com.deathmotion.anticheatbase.api.AnticheatBase;
import com.deathmotion.anticheatbase.bukkit.testplugin.events.ACUserJoinEventListener;
import com.deathmotion.anticheatbase.bukkit.testplugin.events.ACUserQuitEventListener;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.Nullable;

@Getter
public final class ApiTestPlugin extends JavaPlugin {

    private @Nullable ACUserJoinEventListener acUserJoinEventListener;
    private @Nullable ACUserQuitEventListener acUserQuitEventListener;

    @Override
    public void onEnable() {
        AnticheatBase.getAsync().thenAccept(api -> {
            getLogger().info("Hooked into AnticheatBase version " + api.getVersion() + ".");

            acUserJoinEventListener = new ACUserJoinEventListener(this, api);
            acUserQuitEventListener = new ACUserQuitEventListener(this, api);
        });
    }

    @Override
    public void onDisable() {
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
