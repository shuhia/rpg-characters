package com.company.game;

import com.company.types.ArmorType;
import com.company.types.WeaponType;

import static com.company.types.ArmorType.*;
import static com.company.types.WeaponType.*;


public interface ICharacterSpecifications {

    // Mage specifications
    Attributes mageBaseAttributes = new Attributes(1, 1, 8);
    Attributes mageGrowthAttributes = new Attributes(1, 1, 5);
    ArmorType[] mageArmorTypes = {CLOTH};
    WeaponType[] mageWeaponTypes = {STAFF, WAND};
    // Ranger specifications
    Attributes rangerBaseAttributes = new Attributes(1, 5, 1);
    Attributes rangerGrowthAttributes = new Attributes(1, 5, 1);
    WeaponType[] rangerWeaponTypes = {BOW};
    ArmorType[] rangerArmorTypes = {LEATHER, MAIL};
    // Rogue specifications
    Attributes rogueBaseAttributes = new Attributes(2, 6, 1);
    Attributes rogueGrowthAttributes = new Attributes(1, 4, 1);
    WeaponType[] rogueWeaponTypes = {DAGGER, SWORD};
    ArmorType[] rogueArmorTypes = {LEATHER, MAIL};
    // Warrior specifications

    Attributes warriorBaseAttributes = new Attributes(5, 2, 1);
    Attributes warriorGrowthAttributes = new Attributes(3, 2, 1);
    WeaponType[] warriorWeaponTypes = {AXE, HAMMER, SWORD};
    ArmorType[] warriorArmorTypes = {MAIL, PLATE};

    enum characters {
        MAGE, RANGER, ROGUE, WARRIOR
    }


}
