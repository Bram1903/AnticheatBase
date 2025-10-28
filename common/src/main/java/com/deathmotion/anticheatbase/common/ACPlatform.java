package com.deathmotion.anticheatbase.common;

import lombok.Getter;

@Getter
public abstract class ACPlatform<P> {
    @Getter
    private static ACPlatform<?> instance;

    public void commonOnInitialize() {
        instance = this;
    }

    public void commonOnEnable() {

    }

    public void commonOnDisable() {

    }

}
