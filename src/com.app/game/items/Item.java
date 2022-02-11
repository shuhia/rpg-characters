package com.app.game.items;

import com.app.types.SlotType;

public abstract class Item {
    private String name;
    private int requiredLevel;
    private SlotType slotType;
    Enum type;

    protected Item(String name, int requiredLevel, SlotType slotType, Enum type) {
        this.setName(name);
        this.setRequiredLevel(requiredLevel);
        this.setSlotType(slotType);
        this.type = type;
    }

    public abstract Enum getType();

    public SlotType getSlot() {
        return this.getSlotType();
    }

    public int getRequiredLevel() {
        return requiredLevel;
    }

    public void printStats() {
        System.out.println("---------------------------------------");
        System.out.println("name: " + getName());
        System.out.println("requiredLevel: " + getRequiredLevel());
        System.out.println("slot: " + getSlotType());
        System.out.println("type: " + type);
        System.out.println("---------------------------------------");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }
}
