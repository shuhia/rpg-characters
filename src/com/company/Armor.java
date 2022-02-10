package com.company;

public class Armor extends Item {
    ArmorType type;
    Attributes attributes;

    public Armor(ArmorType type, String name, int requiredLevel, Slot slot, Attributes attributes) {
        super(name, requiredLevel, slot);
        this.type = type;
        this.attributes = attributes;
    }

    public ArmorType getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
