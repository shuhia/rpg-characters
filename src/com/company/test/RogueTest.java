package com.company.test;

import com.company.game.Attributes;
import com.company.game.Character;
import com.company.game.Mage;
import com.company.game.Rogue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.company.game.ICharacterSpecifications.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RogueTest {
    Character character;
    Attributes baseAttributes;
    Attributes growthAttributes;

    @BeforeEach
    void setup() {
        character = new Rogue();
        baseAttributes = rogueBaseAttributes;
        growthAttributes = rogueGrowthAttributes;
    }

    @AfterEach
    void teardown() {
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
    void GetTotalAttributes_FromCharacterLevelOneToLevelTwo_HasIncreasedAttributes() {
        // Arrange
        var oldAttributes = character.getTotalAttributes();
        var expectedAttributes = oldAttributes.add(growthAttributes).toString();
        // Act
        character.levelUp();
        var newAttributes = character.getTotalAttributes().toString();
        // Assert
        assertEquals(newAttributes, expectedAttributes);
    }

}