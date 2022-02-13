package com.app.game;

import com.app.game.items.Armor;
import com.app.game.items.Item;
import com.app.game.items.Weapon;
import com.app.game.items.exceptions.InvalidItemException;
import com.app.types.ArmorType;
import com.app.types.SlotType;
import com.app.types.WeaponType;

public class ItemFactory {
    public Item createItemOf(String itemType) throws InvalidItemException {
        switch (itemType) {
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
            default -> {
                throw new InvalidItemException("Specified type: " + itemType + " does not exist!");
            }
        }
    }

    private <E> E getRandomElement(E[] values) {
        return values[(int) Math.floor(Math.random() * values.length)];
    }


}
