package com.app.tests;

import com.app.exceptions.InvalidArmorException;
import com.app.exceptions.InvalidWeaponException;
import com.app.game.Attributes;
import com.app.game.Character;
import com.app.game.ISpecifications;
import com.app.game.characters.Warrior;
import com.app.game.items.Armor;
import com.app.game.items.Weapon;
import com.app.types.SlotType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.app.types.ArmorType.*;
import static com.app.types.WeaponType.AXE;
import static com.app.types.WeaponType.STAFF;
import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    //1) If a character tries to equip a high level weapon, InvalidWeaponException should be thrown.
    //o Use the warrior, and the axe, but set the axes level to 2.

    Character warrior = null;


    @BeforeEach
    void before() {
        warrior = new Warrior("Warrior");
    }

    @AfterEach
    void after() {
        warrior = null;
    }

    //7) Calculate DPS if no weapon is equipped.
    //o Take warrior at level 1
    //o Expected DPS = 1*(1 + (5 / 100))
    @Test
    void GetTotalDamagePerSecond_WithLevelOneCharacter_ExpectLowDamage() {
        double lowDamagePerSecond = 1 * (1 + (ISpecifications.warriorBaseAttributes.getStrength() / 100));
        double totalDamagePerSecond = warrior.getTotalDamagePerSecond();
        assertEquals(lowDamagePerSecond, totalDamagePerSecond);

    }

    //8) Calculate DPS with valid weapon equipped.
    //o Take warrior level 1.
    //o Equip axe.
    //o Expected DPS = (7 * 1.1)*(1 + (5 / 100))


    @Test
    void GetTotalDamagePerSecond_WithValidWeapon_ExpectMoreDamage() throws Exception {
        Weapon axe = createAxe();
        warrior.equip(axe);
        var expected = (7 * 1.1) * (1 + (ISpecifications.warriorBaseAttributes.getStrength() / 100));
        var acutal = warrior.getTotalDamagePerSecond();
        assertEquals(expected, acutal);
    }


    //9) Calculate DPS with valid weapon and armor equipped.
    //o Take warrior level 1.
    //o Equip axe.
    //o Equip plate body armor.
    //o Expected DPS = (7 * 1.1) * (1 + ((5+1) / 100))
    @Test
    void GetTotalDamagePerSecond_WithValidWeaponAndArmor_ExpectMoreDamage() throws Exception {
        Weapon axe = createAxe();
        var name = "armor";
        var requiredLevel = 1;
        var type = PLATE;
        var slot = SlotType.BODY;
        var amount = 5;
        var attributes = new Attributes(amount, amount, amount);
        var armor = new Armor(name, requiredLevel, type, slot, attributes);
        warrior.equip(axe);
        warrior.equip(armor);
        // • Character DPS = (WeaponDamage+AttacksPerSecond) * (1 + (BasePrimaryAttribute+amount)/100)
        var expected = (7 * 1.1) * (1 + ((ISpecifications.warriorBaseAttributes.getStrength() + amount) / 100));
        var actual = warrior.getTotalDamagePerSecond();
        assertEquals(expected, actual);
    }


    @Test
    void EquipHighLevelWeapon_WithLowLevelCharacter_AndThrowExpection() {
        assertThrows(InvalidWeaponException.class, () -> {
            Character c = new Warrior("Warrior");
            equipHighLevelWeapon(c);
        });
    }

    @Test
    void EquipHighLevelArmor_WithLowLevelCharacter_AndThrowExpection() {
        assertThrows(InvalidArmorException.class, () -> {
            Character c = new Warrior("Warrior");
            equipHighLevelArmor(c);
        });
    }

    @Test
    void EquipWeapon_WithIncompatibleCharacter_AndThrowExpection() {
        assertThrows(InvalidWeaponException.class, () -> {
            Character c = new Warrior("Warrior");
            equipInvalidTypeOfWeapon(c);
        });
    }

    @Test
    void EquipArmor_WithIncompatibleCharacter_AndThrowExpection() {
        assertThrows(InvalidArmorException.class, () -> {
            Character c = new Warrior("Warrior");
            equipInvalidTypeOfArmor(c);
        });
    }

    @Test
    void EquipValidWeapon_WithCharacter_AndExpectTrue() {
        assertDoesNotThrow(() -> {
            Character c = new Warrior("Warrior");
            assertTrue(equipValidWeapon(c));
        });
    }

    @Test
    void EquipValidArmor_WithCharacter_AndExpectTrue() {
        assertDoesNotThrow(() -> {
            Character c = new Warrior("Warrior");
            assertTrue(equipValidArmor(c));
        });
    }

    private Weapon createAxe() {
        var damage = 7;
        var attackSpeedPerSecond = 1.1;
        return new Weapon("AXE", 1, AXE, damage, attackSpeedPerSecond);
    }

    void equipHighLevelWeapon(Character c) throws Exception {
        var name = "weapon";
        var level = 1000;
        var type = AXE;
        var damage = 0;
        var attackSpeed = 0;
        var item = new Weapon(name, level, type, damage, attackSpeed);
        c.equip(item);
    }

    //2) If a character tries to equip a high level armor piece, InvalidArmorException should be thrown.
    //o Use the warrior, and the plate body armor, but set the armor’s level to 2.
    void equipHighLevelArmor(Character c) throws Exception {
        var name = "armor";
        var level = 1000;
        var type = MAIL;
        var slot = SlotType.BODY;
        var attributes = new Attributes();
        var item = new Armor(name, level, type, slot, attributes);
        c.equip(item);
    }

    //3) If a character tries to equip the wrong weapon type, InvalidWeaponException should be thrown.
    //o Use the warrior and the bow.
    void equipInvalidTypeOfWeapon(Character c) throws Exception {
        var name = "weapon";
        var level = 1;
        var type = STAFF;
        var damage = 0;
        var attackSpeed = 0;
        var item = new Weapon(name, level, type, damage, attackSpeed);
        c.equip(item);
    }

    //4) If a character tries to equip the wrong armor type, InvalidArmorException should be thrown.
    //o Use the warrior and the cloth armor.
    void equipInvalidTypeOfArmor(Character c) throws Exception {
        var name = "armor";
        var level = 1;
        var type = CLOTH;
        var slot = SlotType.BODY;
        var attributes = new Attributes();
        var item = new Armor(name, level, type, slot, attributes);
        c.equip(item);
    }

    //5) If a character equips a valid weapon, a Boolean true should be returned.
    boolean equipValidWeapon(Character c) throws Exception {
        var name = "weapon";
        var level = 1;
        var type = AXE;
        var damage = 0;
        var attackSpeed = 0;
        var item = new Weapon(name, level, type, damage, attackSpeed);
        return c.equip(item);
    }

    //6) If a character equips a valid armor piece, a Boolean true should be returned.
    boolean equipValidArmor(Character c) throws Exception {
        var name = "armor";
        var level = 1;
        var type = PLATE;
        var slot = SlotType.BODY;
        var attributes = new Attributes();
        var item = new Armor(name, level, type, slot, attributes);
        return c.equip(item);
    }

}