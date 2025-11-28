package com.deathmotion.anticheatbase.api;

import java.util.concurrent.CompletableFuture;

public final class AnticheatBase {
    private static final CompletableFuture<AnticheatBaseAPI> futureInstance = new CompletableFuture<>();
    private static AnticheatBaseAPI instance;

    private AnticheatBase() {
        // Private constructor to prevent instantiation
    }

    public static void init(AnticheatBaseAPI api) {
        if (instance != null || futureInstance.isDone()) {
            throw new IllegalStateException("Anticheat api is already initialized");
        }
        instance = api;
        futureInstance.complete(api);
    }

    public static AnticheatBaseAPI get() {
        if (instance == null) {
            throw new IllegalStateException("The anticheat api is not loaded. Ensure the anticheat base is initialized before accessing the api.");
        }
        return instance;
    }


    public static CompletableFuture<AnticheatBaseAPI> getAsync() {
        if (instance != null) {
            return CompletableFuture.completedFuture(instance);
        }
        return futureInstance;
    }
}
