package com.app.game.items;

import com.app.types.SlotType;

public abstract class Item {
    Enum type;
    private String name;
    private int requiredLevel;
    private SlotType slotType;

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

    public void setRequiredLevel(int requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public void printStats() {
        System.out.println("name: " + getName());
        System.out.println("requiredLevel: " + getRequiredLevel());
        System.out.println("slot: " + getSlotType());
        System.out.println("type: " + type);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SlotType getSlotType() {
        return slotType;
    }

    public void setSlotType(SlotType slotType) {
        this.slotType = slotType;
    }
    public abstract void throwInvalid(String message) throws Exception;
}
