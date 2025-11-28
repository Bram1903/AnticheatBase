package com.deathmotion.anticheatbase.sponge;

import com.google.inject.Inject;
import lombok.Getter;
import org.spongepowered.api.Server;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.lifecycle.StartedEngineEvent;
import org.spongepowered.api.event.lifecycle.StoppingEngineEvent;
import org.spongepowered.plugin.PluginContainer;
import org.spongepowered.plugin.builtin.jvm.Plugin;

import java.nio.file.Path;

@Plugin("anticheatbase")
public class ACSponge {

    private final PluginContainer pluginContainer;

    @Getter
    private final SpongePlatform ac;

    @Inject
    public ACSponge(PluginContainer pluginContainer, @ConfigDir(sharedRoot = false) Path configDirectory) {
        this.pluginContainer = pluginContainer;
        this.ac = new SpongePlatform(configDirectory);
    }

    @Listener
    public void onServerStart(final StartedEngineEvent<Server> event) {
        ac.commonOnInitialize();
        ac.commonOnEnable();
    }

    @Listener
    public void onServerStop(final StoppingEngineEvent<Server> event) {
        ac.commonOnDisable();
    }
}