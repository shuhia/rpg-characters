package com.app.game;

import com.app.game.enums.ArmorType;
import com.app.game.enums.SlotType;
import com.app.game.enums.WeaponType;
import com.app.game.items.Armor;
import com.app.game.items.Item;
import com.app.game.items.Weapon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/***
 * This class is used to specify required fields and methods to create a character of any type.
 */
public abstract class Character {
    public String name;
    public int level = 1;
    public Attributes baseAttributes;
    public Attributes levelUpAttributeGain;
    public HashMap<SlotType, Item> equipped;
    public ArrayList<Enum<?>> equipableItems;
    public ArrayList<Item> inventory;

    public Character(String name, Attributes baseAttributes, Attributes levelUpAttributeGain) {
        this.name = name;
        this.baseAttributes = baseAttributes;
        this.equipped = new HashMap();
        this.levelUpAttributeGain = levelUpAttributeGain;
        this.equipableItems = new ArrayList<>(3);
        this.inventory = new ArrayList<>(12);
    }

    public Character(String name, Attributes baseAttributes, Attributes levelUpAttributeGain, WeaponType[] weaponTypes, ArmorType[] armorTypes) {
        this.name = name;
        this.baseAttributes = baseAttributes;
        this.equipped = new HashMap();
        this.levelUpAttributeGain = levelUpAttributeGain;
        this.equipableItems = new ArrayList<>(3);
        Collections.addAll(this.equipableItems, weaponTypes);
        Collections.addAll(this.equipableItems, armorTypes);
        this.inventory = new ArrayList<>(12);
    }


    public int getLevel() {
        return level;
    }

    public int levelUp() {
        this.level++;
        return this.level;
    }

    public boolean equip(Item item) throws Exception {
        if (this.level < item.getRequiredLevel()) {
            item.throwInvalid("requires level: " + item.getRequiredLevel());
        }
        if (!this.equipableItems.contains(item.getType())) {
            item.throwInvalid("type:" + item.getType() + " Cannot be equipped by " + this.getClass().getSimpleName());
        }
        // Put items in equipped items
        this.equipped.put(item.getSlot(), item);
        return true;
    }

    /***
     * Sums all attribute bonuses from armors
     * @return sum of all attribute bonuses
     */
    public Attributes getAttributesFromArmor() {
        // Get all armor
        // sum attributes from each armor
        return equipped.values().stream().filter(Armor.class::isInstance).map((armor) -> ((Armor) armor).getAttributes()).reduce(new Attributes(), Attributes::add);
    }

    /**
     * Calculates attribute gains based on level
     *
     * @return total attribute gains from level
     */
    public Attributes getAttributesFromLevel() {
        // attributeGain * level.
        // All characters starts with level.
        return this.levelUpAttributeGain.multiplyWith(this.level - 1);
    }

    /***
     * Gets total attribute based on characters primary attribute
     * @return total primary attribute
     */
    public abstract double getTotalPrimaryAttribute();

    /**
     * Get total attribute by summing attributes from level, armor and base attributes.
     *
     * @return total attributes
     */
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

    /**
     * Calculates weapon damage per second based on equipped weapon.
     *
     * @return returns total weapon damage per second. Returns 1 if no weapon is equipped
     */
    public double getWeaponDamagePerSecond() {
        double weaponDamagePerSecond = 1;
        if (equipped.containsKey(SlotType.WEAPON)) {
            weaponDamagePerSecond = ((Weapon) equipped.get(SlotType.WEAPON)).getDamagePerSecond();
        }
        return weaponDamagePerSecond;
    }


    public double getTotalDamagePerSecond() {
        double weaponDamagePerSecond = getWeaponDamagePerSecond();
        return weaponDamagePerSecond * getDamageFromAttribute(getTotalPrimaryAttribute());
    }

    protected double getDamageFromAttribute(double attribute) {
        return 1 + attribute / 100;
    }

    /***
     * Adds item to inventory
     * @param item
     */
    public void addItem(Item item) {
        this.inventory.add(item);
    }

    /***
     * Prints every item in inventory
     */
    public void printItems() {
        this.inventory.forEach((this::addItem));
    }
}
