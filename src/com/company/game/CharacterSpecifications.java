package com.company.game;

import com.company.types.ArmorType;
import com.company.types.WeaponType;

import static com.company.types.ArmorType.*;
import static com.company.types.WeaponType.*;

public abstract class CharacterSpecifications {
    // Mage specifications
    public static Attributes mageBaseAttributes = new Attributes(1, 1, 8);
    public static Attributes mageGrowthAttributes = new Attributes(1, 1, 5);
    public static ArmorType[] mageArmorTypes = {CLOTH};
    public static WeaponType[] mageWeaponTypes = {STAFF, WAND};

    // Ranger specifications
    public static Attributes rangerBaseAttributes = new Attributes(1, 5, 1);
    public static Attributes rangerGrowthAttributes = new Attributes(1, 5, 1);
    public static WeaponType[] rangerWeaponTypes = {BOW};
    public static ArmorType[] rangerArmorTypes = {LEATHER, MAIL};

    // Rogue specifications
    public static Attributes rougeBaseAttributes = new Attributes(2, 6, 1);
    public static Attributes rougeGrowthAttributes = new Attributes(1, 4, 1);
    public static WeaponType[] rogueWeaponTypes = {DAGGER, SWORD};
    public static ArmorType[] rogueArmorTypes = {LEATHER, MAIL};

    // Warrior specifications
    public static Attributes warriorBaseAttributes = new Attributes(5, 2, 1);
    public static Attributes warriorGrowthAttributes = new Attributes(3, 2, 1);
    public static WeaponType[] warriorWeaponTypes = {AXE, HAMMER, SWORD};
    public static ArmorType[] warriorArmorTypes = {MAIL, PLATE};

}
