package com.company;

public abstract class Item {
    String name;
    int requiredLevel;
    Slot slot;

    protected Item(String name, int requiredLevel, Slot slot) {
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.slot = slot;
    }

    protected abstract Enum getType();

    protected Slot getSlot() {
        return this.slot;
    }
}
