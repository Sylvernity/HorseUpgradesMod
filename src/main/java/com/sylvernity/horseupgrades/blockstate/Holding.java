/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.blockstate;

import net.minecraft.util.StringRepresentable;

public enum Holding implements StringRepresentable{
    BAR("bar"),
    HORSESHOE("horseshoe"),
    NONE("none");

    private final String name;

    Holding(String pName) {
        this.name = pName;
    }

    public String toString() {
        return this.getSerializedName();
    }

    public String getSerializedName() {
        return this.name;
    }

    public boolean isConnected() {
        return this != NONE;
    }
}
