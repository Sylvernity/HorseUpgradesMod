package sylvernity.horseupgrades.blockstate;

import net.minecraft.util.StringRepresentable;

public enum Material implements StringRepresentable {
    IRON("iron"),
    GOLD("gold"),
    DIAMOND("diamond"),
    NONE("none");

    private final String name;

    Material(String pName) {
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