package com.deathmotion.anticheatbase.common.events.packet;

import com.deathmotion.anticheatbase.common.ACPlatform;
import com.deathmotion.anticheatbase.common.manager.PlayerManager;
import com.deathmotion.anticheatbase.common.models.ACPlayer;
import com.github.retrooper.packetevents.event.*;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;
import com.github.retrooper.packetevents.protocol.player.User;

public class PacketPlayerJoinQuit extends PacketListenerAbstract {

    private final ACPlatform platform;
    private final PlayerManager playerManager;

    public PacketPlayerJoinQuit(ACPlatform platform) {
        this.platform = platform;
        this.playerManager = platform.getPlayerManager();
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Login.Server.LOGIN_SUCCESS) {
            event.getTasksAfterSend().add(() -> playerManager.addUser(event.getUser()));
            platform.getLogger().info("Added user " + event.getUser().getName() + " to the player manager.");
        }
    }

    @Override
    public void onUserConnect(UserConnectEvent event) {
        if (event.getUser().getConnectionState() == ConnectionState.PLAY && !playerManager.isExempt(event.getUser())) {
            event.setCancelled(true);
        }
    }

    @Override
    public void onUserLogin(UserLoginEvent event) {
        User user = event.getUser();

        ACPlayer acPlayer = playerManager.getPlayer(user);
        if (acPlayer == null) return;

        acPlayer.onLogin();
    }

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        ACPlayer acPlayer = playerManager.removeUser(event.getUser());
        if (acPlayer == null) return;

        platform.getLogger().info("User " + event.getUser().getName() + " disconnected.");
    }
}