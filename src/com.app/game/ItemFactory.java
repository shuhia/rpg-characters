package com.app.game;

import com.app.game.enums.ArmorType;
import com.app.game.enums.SlotType;
import com.app.game.enums.WeaponType;
import com.app.game.exceptions.InvalidItemException;
import com.app.game.items.Armor;
import com.app.game.items.Item;
import com.app.game.items.Weapon;

/**
 * This class is used for creating items with some selected properties and some random properties.
 *
 */
public class ItemFactory {
    /**
     * Creates an item of specified type with level 1. If weapon then max damage is set to 100. If armor then max attributes is set to 100
     * @param itemType - type of item. Can be ARMOR or WEAPON
     * @return returns an item. Else throws.
     * @throws InvalidItemException
     */
    public Item createItemOf(String itemType) throws InvalidItemException {
        switch (itemType.toUpperCase()) {
            case "ARMOR" -> {
                var name = "armor";
                var requiredLevel = 1;
                var type = getRandomElement(ArmorType.values());
                var slot = SlotType.BODY;
                var amount = Math.random() * 100;
                var attributes = new Attributes(amount, amount, amount);
                return new Armor(name, requiredLevel, type, slot, attributes);
            }
            case "WEAPON" -> {
                var damage = Math.random() * 100;
                var attackSpeedPerSecond = Math.random() * 2;
                String name = "AXE";
                int requiredLevel = 1;
                var type = getRandomElement(WeaponType.values());

                return new Weapon(name, requiredLevel, type, damage, attackSpeedPerSecond);
            }
            default -> throw new InvalidItemException("Specified type: " + itemType + " does not exist!");

        }
    }

    /**
     * Returns a random element from array of any type
     * @param values - the array containing elements
     * @param <E> - generic term E
     * @return - return value of type E
     */
    private <E> E getRandomElement(E[] values) {
        return values[(int) Math.floor(Math.random() * values.length)];
    }


}
