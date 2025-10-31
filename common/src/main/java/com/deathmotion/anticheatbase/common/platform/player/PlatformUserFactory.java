package com.deathmotion.anticheatbase.common.platform.player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public interface PlatformUserFactory {
    @Nullable PlatformUserCreation create(@NotNull UUID uuid);
}
