package com.deathmotion.anticheatbase.common.platform.player;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Getter
public class PlatformUserCreation {
    private final @NotNull PlatformUser platformUser;
    private final @Nullable PlatformPlayer platformPlayer;

    public PlatformUserCreation(@NotNull PlatformUser platformUser, @Nullable PlatformPlayer platformPlayer) {
        this.platformUser = platformUser;
        this.platformPlayer = platformPlayer;
    }
}
