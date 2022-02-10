package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Character {
    String name;
    int level = 1;
    Attributes baseAttributes;
    Attributes levelUpAttributeGain;
    HashMap<Slot, Item> equipped;
    ArrayList<Enum> equipableItems;

    protected Character(String name, Attributes baseAttributes, Attributes levelUpAttributeGain) {
        this.name = name;
        this.baseAttributes = baseAttributes;
        this.equipped = new HashMap();
        this.levelUpAttributeGain = levelUpAttributeGain;
        this.equipableItems = new ArrayList<>(3);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int levelUp() {
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
                    throw new InvalidWeaponException("Weapon is not suited for this class");
                }
            else if (item instanceof Armor) {
                if (this.equipableItems.stream().anyMatch((type) -> type == item.getType())) {
                    this.equipped.put(item.getSlot(), item);
                } else {
                    // Throw InvalidArmorException
                    throw new InvalidArmorException("Weapon is not suited for this class");
                }
            }
        } else {
            // Throw InvalidWeaponException
            if (item instanceof Weapon) throw new InvalidWeaponException("Character does not meet the required level");
            // Throw InvalidArmorException
            if (item instanceof Armor) throw new InvalidArmorException("Character does not meet the required level");

        }
        return true;
    }

    protected abstract double getTotalDamagePerSecond();

    protected Attributes getTotalAttributes() {
        Attributes armor = getAttributesFromArmor();
        Attributes level = getAttributesFromLevel();
        Attributes base = this.baseAttributes;
        // Total attributes = attributes from armor + base attributes + attributeGains from level
        return base.add(armor).add(level);
    }

    private Attributes getAttributesFromArmor() {
        return equipped.values().stream().filter(Armor.class::isInstance).map((armor) -> ((Armor) armor).getAttributes()).reduce(new Attributes(), Attributes::add);
    }

    private Attributes getAttributesFromLevel() {
        return this.levelUpAttributeGain.multiplyWith(this.level - 1);
    }

    protected void printStats() {
        System.out.println("name: " + name);
        System.out.println("level: " + level);
        getTotalAttributes().print();
        System.out.println("total damage per second: " + getTotalDamagePerSecond());
    }

}
