package com.company;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Character {
    String name;
    int level = 1;
    Attributes baseAttributes;
    Attributes levelUpAttributeGain;
    HashMap<Slot, Item> equipped;
    ArrayList<WeaponType> equipableWeapons;

    protected Character(String name, Attributes baseAttributes, Attributes levelUpAttributeGain) {
        this.name = name;
        this.baseAttributes = baseAttributes;
        this.equipped = new HashMap();
        this.levelUpAttributeGain = levelUpAttributeGain;
        this.equipableWeapons = new ArrayList<>(3);
    }
    public void levelUp() {
        this.level++;
    }



    protected void equip(Item item) {
        if (this.level >= item.requiredLevel) {
            if (item instanceof Weapon)
                if (this.equipableWeapons.stream().anyMatch((type) -> type == item.getType())) {
                    this.equipped.put(item.getSlot(), item);
                } else {
                    // Throw not equipable
                }
            else if (item instanceof Armor) {
                this.equipped.put(item.getSlot(), item);
            }
        } else {
            // Throw level
        }

    }

    protected abstract double getTotalDamagePerSecond();

    protected Attributes getTotalAttributes() {
        Attributes armorAttributes = equipped.values().stream().filter(Armor.class::isInstance).map((armor) -> ((Armor) armor).getAttributes()).reduce(new Attributes(), Attributes::add);
        Attributes levelAttributes = this.levelUpAttributeGain.multiplyWith(this.level);
        // Total attributes = attributes from armor + base attributes + attributeGains from level
        Attributes sumOfAttributes = this.baseAttributes.add(armorAttributes).add(levelAttributes);
        return sumOfAttributes;
    }

    protected void printStats() {
        System.out.println("name: " + name);
        System.out.println("level: " + level);
        getTotalAttributes().print();
        System.out.println("total damage per second: " + getTotalDamagePerSecond());
    }

}
