package com.company.game;

import com.company.types.ArmorType;
import com.company.types.Slot;

public class Armor extends Item {

    Attributes attributes;

    public Armor(ArmorType type, String name, int requiredLevel, Slot slot, Attributes attributes) {
        super(name, requiredLevel, slot, type);
        this.attributes = attributes;
    }

    public Enum<ArmorType> getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
