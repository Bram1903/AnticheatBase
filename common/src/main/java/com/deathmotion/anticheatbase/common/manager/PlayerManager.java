package com.deathmotion.anticheatbase.common.manager;

import com.deathmotion.anticheatbase.api.event.impl.ACUserQuitEvent;
import com.deathmotion.anticheatbase.common.ACPlatform;
import com.deathmotion.anticheatbase.common.models.ACPlayer;
import com.github.retrooper.packetevents.netty.channel.ChannelHelper;
import com.github.retrooper.packetevents.protocol.player.User;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerManager {

    private final ACPlatform platform;

    private final ConcurrentHashMap<User, ACPlayer> players = new ConcurrentHashMap<>();
    private final Collection<User> exemptUsers = ConcurrentHashMap.newKeySet();

    public PlayerManager(ACPlatform platform) {
        this.platform = platform;
    }

    public void onLoginPacket(final @NotNull User user) {
        if (!shouldCheck(user)) return;

        ACPlayer player = new ACPlayer(user);
        players.put(user, player);
    }

    public void onLogin(final @NotNull User user) {
        ACPlayer player = players.get(user);
        if (player == null) return;

        player.onLogin();
    }

    public void removeUser(final @NotNull User user) {
        players.remove(user);
    }

    public void onPlayerDisconnect(final @NotNull User user) {
        exemptUsers.remove(user);

        ACPlayer player = players.remove(user);
        if (player == null) return;

        platform.getEventBus().post(new ACUserQuitEvent(player));
    }

    public @Nullable ACPlayer getPlayer(final @NotNull User user) {
        return players.get(user);
    }

    public boolean isExempt(User user) {
        return exemptUsers.contains(user);
    }

    public boolean shouldCheck(User user) {
        if (exemptUsers.contains(user)) return false;
        if (!ChannelHelper.isOpen(user.getChannel())) return false;
        if (user.getUUID() == null) return false;

        // Is a Geyser (Bedrock) player
        if (user.getUUID().getMostSignificantBits() == 0L) {
            exemptUsers.add(user);
            return false;
        }

        return true;
    }
}
