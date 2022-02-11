package com.app.game.items;

import com.app.game.Attributes;
import com.app.types.ArmorType;
import com.app.types.SlotType;

public class Armor extends Item {

    Attributes attributes;

    public Armor(ArmorType type, String name, int requiredLevel, SlotType slotType, Attributes attributes) {
        super(name, requiredLevel, slotType, type);
        this.attributes = attributes;
    }

    public Enum<ArmorType> getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
