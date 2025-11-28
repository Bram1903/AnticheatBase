package com.deathmotion.anticheatbase.common.models;

import com.deathmotion.anticheatbase.api.event.impl.ACUserJoinEvent;
import com.deathmotion.anticheatbase.api.models.ACUser;
import com.deathmotion.anticheatbase.common.ACPlatform;
import com.deathmotion.anticheatbase.common.manager.PlayerManager;
import com.deathmotion.anticheatbase.common.platform.player.PlatformPlayer;
import com.deathmotion.anticheatbase.common.platform.player.PlatformUser;
import com.deathmotion.anticheatbase.common.platform.player.PlatformUserCreation;
import com.github.retrooper.packetevents.protocol.player.User;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Represents a player in the AntiCheatBase system. This object is bound to a single player and gets removed once the player leaves the server / proxy.
 */
public class ACPlayer implements ACUser {

    @Getter
    private final UUID uuid;
    @Getter
    private final User user;

    @Getter
    private PlatformUser platformUser;

    /**
     * Only available when the plugin is run on a backend server (so not a proxy).
     */
    @Getter
    private @Nullable PlatformPlayer platformPlayer;

    public ACPlayer(@NotNull User user) {
        this.uuid = user.getUUID();
        this.user = user;
    }

    public void onLogin() {
        ACPlatform platform = ACPlatform.getInstance();
        PlayerManager playerManager = platform.getPlayerManager();

        PlatformUserCreation platformUserCreation = platform.getPlatformUserFactory().create(uuid);
        if (platformUserCreation == null) {
            playerManager.removeUser(user);
            return;
        }

        platformUser = platformUserCreation.getPlatformUser();
        platformPlayer = platformUserCreation.getPlatformPlayer();

        if (!playerManager.shouldCheck(user, platformUser)) {
            playerManager.removeUser(user);
            return;
        }

        ACPlatform.getInstance().getEventBus().post(new ACUserJoinEvent(this));
    }

    @Override
    public @NotNull String getName() {
        return user.getName();
    }
}
