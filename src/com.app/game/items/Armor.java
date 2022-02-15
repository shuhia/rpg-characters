package com.app.game.items;

import com.app.game.Attributes;
import com.app.game.enums.ArmorType;
import com.app.game.enums.SlotType;
import com.app.game.exceptions.InvalidArmorException;

public class Armor extends Item {

    Attributes attributes;

    public Armor(String name, int requiredLevel, ArmorType type, SlotType slot, Attributes attributes) {
        super(name, requiredLevel, slot, type);
        this.attributes = attributes;
    }

    public Enum<ArmorType> getType() {
        return type;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    @Override
    public void printStats() {
        super.printStats();
        System.out.println(attributes);
    }

    @Override
    public void throwInvalid(String message) throws Exception {
        throw new InvalidArmorException("armor " + message);
    }
}
