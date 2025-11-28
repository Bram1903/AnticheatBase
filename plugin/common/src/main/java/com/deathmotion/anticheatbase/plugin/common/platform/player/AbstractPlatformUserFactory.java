package com.deathmotion.anticheatbase.plugin.common.platform.player;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public abstract class AbstractPlatformUserFactory<T> implements PlatformUserFactory {
    public @Nullable PlatformUserCreation create(@NotNull UUID uuid) {
        T nativePlayer = getNativePlayer(uuid);

        if (nativePlayer == null) {
            return null;
        }

        PlatformUser platformUser = createPlatformUser(nativePlayer);
        PlatformPlayer platformPlayer = createPlatformPlayer(nativePlayer);
        return new PlatformUserCreation(platformUser, platformPlayer);
    }

    protected abstract T getNativePlayer(@NotNull UUID uuid);

    protected abstract @NotNull PlatformUser createPlatformUser(@NotNull T nativePlayer);

    protected abstract @Nullable PlatformPlayer createPlatformPlayer(@NotNull T nativePlayer);
}
