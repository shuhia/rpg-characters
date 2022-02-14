package com.app.game;

import com.app.game.items.Armor;
import com.app.game.items.Item;
import com.app.game.items.Weapon;
import com.app.game.items.exceptions.InvalidArmorException;
import com.app.game.items.exceptions.InvalidWeaponException;
import com.app.types.ArmorType;
import com.app.types.SlotType;
import com.app.types.WeaponType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import static com.app.game.ISpecifications.warriorArmorTypes;
import static com.app.game.ISpecifications.warriorWeaponTypes;

public abstract class Character {
    public String name;
    public int level = 1;
    public Attributes baseAttributes;
    public Attributes levelUpAttributeGain;
    public HashMap<SlotType, Item> equipped;
    public ArrayList<Enum> equipableItems;
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
        Collections.addAll(this.equipableItems, warriorWeaponTypes);
        Collections.addAll(this.equipableItems, warriorArmorTypes);
        this.inventory = new ArrayList<>(12);
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
        if(this.level < item.getRequiredLevel()){
            item.throwInvalid("requires level: "+item.getRequiredLevel());
        }
        if (!this.equipableItems.stream().anyMatch((type) -> type == item.getType())) {
            item.throwInvalid("type:" + item.getType()+ " Cannot be equipped by "+this.getClass().getSimpleName());
        }
        this.equipped.put(item.getSlot(), item);
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

    public boolean addItem(Item item) {
        return this.inventory.add(item);
    }

    public void printItems() {

    }
}
