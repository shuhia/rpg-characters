package com.company.game;

import com.company.types.Slot;

public abstract class Item {
    String name;


    int requiredLevel;
    Slot slot;
    Enum type;

    protected Item(String name, int requiredLevel, Slot slot, Enum type) {
        this.name = name;
        this.requiredLevel = requiredLevel;
        this.slot = slot;
        this.type = type;
    }

    protected abstract Enum getType();

    protected Slot getSlot() {
        return this.slot;
    }

    protected int getRequiredLevel() {
        return requiredLevel;
    }
}
