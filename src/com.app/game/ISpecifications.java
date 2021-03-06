package com.app.game;

import com.app.game.enums.ArmorType;
import com.app.game.enums.WeaponType;

import static com.app.game.enums.ArmorType.*;
import static com.app.game.enums.WeaponType.*;

/**
 * This interface contains character specifications. It consists of base, growth and item types constants.
 */
public interface ISpecifications {
    // Base attributes are attributes that character starts with
    // Growth are attributes gets added to total attributes each time a character levels up.
    enum characters {
        MAGE, RANGER, ROGUE, WARRIOR
    }
    // Mage specifications
    interface MageSpec{
    Attributes mageBaseAttributes = new Attributes(1, 1, 8);
    Attributes mageGrowthAttributes = new Attributes(1, 1, 5);
    ArmorType[] mageArmorTypes = {CLOTH};
    WeaponType[] mageWeaponTypes = {STAFF, WAND};
    }
    // Ranger specifications
    interface RangerSpec{
    Attributes rangerBaseAttributes = new Attributes(1, 5, 1);
    Attributes rangerGrowthAttributes = new Attributes(1, 5, 1);
    WeaponType[] rangerWeaponTypes = {BOW};
    ArmorType[] rangerArmorTypes = {LEATHER, MAIL};
    }
    // Rogue specifications
    interface RogueSpec{
    Attributes rogueBaseAttributes = new Attributes(2, 6, 1);
    Attributes rogueGrowthAttributes = new Attributes(1, 4, 1);
    WeaponType[] rogueWeaponTypes = {DAGGER, SWORD};
    ArmorType[] rogueArmorTypes = {LEATHER, MAIL};
    }
    // Warrior specifications

    interface WarriorSpec{
    Attributes warriorBaseAttributes = new Attributes(5, 2, 1);
    Attributes warriorGrowthAttributes = new Attributes(3, 2, 1);
    WeaponType[] warriorWeaponTypes = {AXE, HAMMER, SWORD};
    ArmorType[] warriorArmorTypes = {MAIL, PLATE};
    }




}
