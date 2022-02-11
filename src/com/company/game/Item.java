package com.company.game;

import com.company.types.Slot;

import java.util.Arrays;

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

    public void printStats() {
        System.out.println("---------------------------------------");
        System.out.println("name: "+name);
        System.out.println("requiredLevel: "+requiredLevel);
        System.out.println("slot: "+ slot);
        System.out.println("type: "+type);
        System.out.println("---------------------------------------");

    }
}
