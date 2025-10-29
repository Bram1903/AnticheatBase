package com.deathmotion.anticheatbase.common.models;

import com.deathmotion.anticheatbase.api.models.ACUser;
import com.deathmotion.anticheatbase.common.ACPlatform;
import com.deathmotion.anticheatbase.common.manager.PlayerManager;
import com.github.retrooper.packetevents.protocol.player.User;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

/*
    This class contains data bound to a specific player. The constructor gets called when a player joins the server
    and will get disposed of when the player leaves the server.
 */
public class ACPlayer implements ACUser {

    @Getter
    private final UUID uuid;
    @Getter
    private final User user;

    public ACPlayer(@NotNull User user) {
        this.uuid = user.getUUID();
        this.user = user;
    }

    public void onLogin() {
        PlayerManager playerManager = ACPlatform.getInstance().getPlayerManager();
        if (!playerManager.shouldCheck(user)) {
            playerManager.removeUser(user);
            return;
        }

        ACPlatform.getInstance().getLogger().info("User " + user.getName() + " logged in.");
    }

    @Override
    public @NotNull String getName() {
        return user.getName();
    }
}
