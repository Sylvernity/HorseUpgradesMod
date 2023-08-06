package com.sylvernity.horseupgrades.blockstate;

import net.minecraft.util.StringRepresentable;

public enum Material implements StringRepresentable {
    NONE("none"),
    IRON("iron"),
    GOLD("gold"),
    DIAMOND("diamond"),
    NETHERITE("netherite");

    private final String name;

    Material(String pName) {
        this.name = pName;
    }

    @Override
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