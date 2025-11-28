package com.deathmotion.anticheatbase.common.events.packet;

import com.deathmotion.anticheatbase.common.ACPlatform;
import com.deathmotion.anticheatbase.common.manager.PlayerManager;
import com.github.retrooper.packetevents.event.*;
import com.github.retrooper.packetevents.protocol.ConnectionState;
import com.github.retrooper.packetevents.protocol.packettype.PacketType;

public class PacketPlayerJoinQuit extends PacketListenerAbstract {

    private final PlayerManager playerManager;

    public PacketPlayerJoinQuit(ACPlatform platform) {
        this.playerManager = platform.getPlayerManager();
    }

    @Override
    public void onPacketSend(PacketSendEvent event) {
        if (event.getPacketType() == PacketType.Login.Server.LOGIN_SUCCESS) {
            event.getTasksAfterSend().add(() -> playerManager.onLoginPacket(event.getUser()));
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
        playerManager.onLogin(event.getUser());
    }

    @Override
    public void onUserDisconnect(UserDisconnectEvent event) {
        playerManager.onPlayerDisconnect(event.getUser());
    }
}