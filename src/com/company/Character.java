package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Character {
    protected String name;
    protected int level = 1;
    protected Attributes baseAttributes;
    protected Attributes levelUpAttributeGain;
    protected HashMap<Slot, Item> equipped;
    protected ArrayList<Enum> equipableItems;

    protected Character(String name, Attributes baseAttributes, Attributes levelUpAttributeGain) {
        this.name = name;
        this.baseAttributes = baseAttributes;
        this.equipped = new HashMap();
        this.levelUpAttributeGain = levelUpAttributeGain;
        this.equipableItems = new ArrayList<>(3);
    }

    protected int getLevel() {
        return level;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    protected int levelUp() {
        this.level++;
        return this.level;
    }

    protected boolean equip(Item item) throws Exception {
        if (this.level >= item.requiredLevel) {
            if (item instanceof Weapon)
                if (this.equipableItems.stream().anyMatch((type) -> type == item.getType())) {
                    this.equipped.put(item.getSlot(), item);
                } else {
                    // Throw InvalidWeaponException
                    throw new InvalidWeaponException("Weapon of type " + item.getType() + " is not allowed for a " + this.getClass().getSimpleName());
                }
            else if (item instanceof Armor) {
                if (this.equipableItems.stream().anyMatch((type) -> type == item.getType())) {
                    this.equipped.put(item.getSlot(), item);
                } else {
                    // Throw InvalidArmorException
                    throw new InvalidArmorException("Armor of type " + item.getType() + " is not allowed for a " + this.getClass().getSimpleName());
                }
            }
        } else {
            // Throw InvalidWeaponException
            if (item instanceof Weapon)
                throw new InvalidWeaponException("Character does not meet the required level: " + item.getRequiredLevel());
            // Throw InvalidArmorException
            if (item instanceof Armor)
                throw new InvalidArmorException("Character does not meet the required level: " + item.getRequiredLevel());

        }
        return true;
    }

    protected Attributes getAttributesFromArmor() {
        return equipped.values().stream().filter(Armor.class::isInstance).map((armor) -> ((Armor) armor).getAttributes()).reduce(new Attributes(), Attributes::add);
    }

    protected Attributes getAttributesFromLevel() {
        return this.levelUpAttributeGain.multiplyWith(this.level - 1);
    }

    protected abstract double getTotalPrimaryAttribute();

    protected Attributes getTotalAttributes() {
        Attributes armor = getAttributesFromArmor();
        Attributes level = getAttributesFromLevel();
        Attributes base = this.baseAttributes;
        // Total attributes = attributes from armor + base attributes + attributeGains from level
        return base.add(armor).add(level);
    }

    protected void printStats() {
        System.out.println("name: " + name);
        System.out.println("level: " + level);
        getTotalAttributes().print();
        System.out.println("total damage per second: " + getTotalDamagePerSecond());
    }

    protected double getWeaponDamagePerSecond() {
        double weaponDamagePerSecond = 1;
        if (equipped.containsKey(Slot.WEAPON)) {
            weaponDamagePerSecond = ((Weapon) equipped.get(Slot.WEAPON)).getDamagePerSecond();
        }
        return weaponDamagePerSecond;
    }

    protected abstract double getTotalDamagePerSecond();

}
