package com.company;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.CharacterSpecifications.warriorBaseAttributes;
import static com.company.CharacterSpecifications.warriorGrowthAttributes;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WarriorTest {
    Character character;
    Attributes baseAttributes;
    Attributes growthAttributes;

    @BeforeEach
    void setUp() {
        character = new Warrior("Test Warrior");
        baseAttributes = warriorBaseAttributes;
        growthAttributes = warriorGrowthAttributes;
    }

    @AfterEach
    void tearDown() {
        character = null;
        baseAttributes = null;
        growthAttributes = null;
    }

    //1) A character is level 1 when created.
    @Test
    void Character_WithNoExperience_IsLevelOne() {

        var level = character.getLevel();
        // Assert
        assertEquals(1, level);
    }

    // 2) When a character gains a level, it should be level 2.
    @Test
    void LevelUp_WithNoExperience_IsLevelTwo() {

        var level = character.levelUp();
        // Assert
        assertEquals(2, level);
    }

    // Each character class is created with the proper default attributes.
    @Test
    void Character_IsLevelOne_HasCorrectBaseAttributes() {
        // Arrange
        var actualBaseAttributes = character.getTotalAttributes().toString();
        var expectedBaseAttributes = baseAttributes.toString();
        // Act
        // Assert
        assertEquals(expectedBaseAttributes, actualBaseAttributes);
    }

    /**
     * * 4) Each character class has their attributes increased when leveling up.
     * * o Create each class once, level them up once.
     * * o Use the base attributes, plus one instance of the level up as the expected.
     * * o E.g. Warrior -> levelup() -> ( Strength = 8, Dexterity = 4, Intelligence = 2) expected.
     * * o This results in four test methods.
     */
    @Test
    void GetTotalAttributes_FromWarriorLevelOneToLevelTwo_HasIncreasedAttributes() {
        // Arrange
        var oldAttributes = character.getTotalAttributes();
        var expectedAttributes = oldAttributes.add(growthAttributes).toString();
        // Act
        character.levelUp();
        var newAttributes = character.getTotalAttributes().toString();
        // Assert
        assertEquals(newAttributes, expectedAttributes);
    }


    @Test
    void Equip_LevelOneCharacterWithLevelTwoWeapon_AndThrowInvalidWeaponException() {
        var item = new Weapon("Axe", 2, WeaponType.AXE, 10.0, 10.0);
        assertThrows(InvalidWeaponException.class, () -> character.equip(item));
    }

    @Test
    void Equip_LevelOneCharacterWithLevelTwoArmor_AndThrowInvalidArmorException() {
        var item = new Armor(ArmorType.PLATE, "Armor", 2, Slot.BODY, new Attributes());
        assertThrows(InvalidArmorException.class, () -> character.equip(item));
    }

}