/*
        Horse Upgrades Minecraft Mod
        Copyright (C) 2023 Sylvernity
*/

package com.sylvernity.horseupgrades.blockstate;

import net.minecraft.util.StringRepresentable;
import org.jetbrains.annotations.NotNull;

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

    public @NotNull String getSerializedName() {
        return this.name;
    }

}