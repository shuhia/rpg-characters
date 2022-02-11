package com.company.game;

import com.company.excpetions.InvalidArmorException;
import com.company.excpetions.InvalidWeaponException;
import com.company.types.ArmorType;
import com.company.types.Slot;
import com.company.types.WeaponType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.company.game.ICharacterSpecifications.warriorArmorTypes;
import static com.company.game.ICharacterSpecifications.warriorWeaponTypes;

public abstract class Character {
    public String name;
    public int level = 1;
    public Attributes baseAttributes;
    public Attributes levelUpAttributeGain;
    public HashMap<Slot, Item> equipped;
    public ArrayList<Enum> equipableItems;

    public Character(String name, Attributes baseAttributes, Attributes levelUpAttributeGain) {
        this.name = name;
        this.baseAttributes = baseAttributes;
        this.equipped = new HashMap();
        this.levelUpAttributeGain = levelUpAttributeGain;
        this.equipableItems = new ArrayList<>(3);
    }
    public Character(String name, Attributes baseAttributes, Attributes levelUpAttributeGain, WeaponType[] weaponTypes, ArmorType[] armorTypes) {
        this.name = name;
        this.baseAttributes = baseAttributes;
        this.equipped = new HashMap();
        this.levelUpAttributeGain = levelUpAttributeGain;
        this.equipableItems = new ArrayList<>(3);
        Collections.addAll(this.equipableItems, warriorWeaponTypes);
        Collections.addAll(this.equipableItems, warriorArmorTypes);
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

    public boolean equip(Item item) throws Exception {
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

    public Attributes getAttributesFromArmor() {
        return equipped.values().stream().filter(Armor.class::isInstance).map((armor) -> ((Armor) armor).getAttributes()).reduce(new Attributes(), Attributes::add);
    }

    public Attributes getAttributesFromLevel() {
        return this.levelUpAttributeGain.multiplyWith(this.level - 1);
    }

    public abstract double getTotalPrimaryAttribute();

    public Attributes getTotalAttributes() {
        Attributes armor = getAttributesFromArmor();
        Attributes level = getAttributesFromLevel();
        Attributes base = this.baseAttributes;
        // Total attributes = attributes from armor + base attributes + attributeGains from level
        return base.add(armor).add(level);
    }

    public void printStats() {
        System.out.println("name: " + name);
        System.out.println("level: " + level);
        getTotalAttributes().print();
        System.out.println("total damage per second: " + getTotalDamagePerSecond());
    }

    public double getWeaponDamagePerSecond() {
        double weaponDamagePerSecond = 1;
        if (equipped.containsKey(Slot.WEAPON)) {
            weaponDamagePerSecond = ((Weapon) equipped.get(Slot.WEAPON)).getDamagePerSecond();
        }
        return weaponDamagePerSecond;
    }

    public double getTotalDamagePerSecond(){
        double weaponDamagePerSecond = getWeaponDamagePerSecond();
        return weaponDamagePerSecond * getDamageFromAttribute(getTotalPrimaryAttribute());
    };


    protected double getDamageFromAttribute(double attribute) {
        return 1 + attribute / 100;
    }
}
