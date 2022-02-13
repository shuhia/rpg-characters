package com.app.game;

import com.app.game.items.Armor;
import com.app.game.items.Item;
import com.app.game.items.Weapon;
import com.app.game.items.exceptions.InvalidItemException;
import com.app.types.SlotType;

import static com.app.types.ArmorType.PLATE;
import static com.app.types.WeaponType.AXE;

public class ItemFactory {
    public Item createItemOf(String itemType) throws InvalidItemException {
        switch (itemType) {
            case "ARMOR" -> {
                var name = "armor";
                var requiredLevel = 1;
                var type = PLATE;
                var slot = SlotType.BODY;
                var amount = 5;
                var attributes = new Attributes(amount, amount, amount);
                return new Armor(name, requiredLevel, type, slot, attributes);
            }
            case "WEAPON" -> {
                var damage = 7;
                var attackSpeedPerSecond = 1.1;
                String name = "AXE";
                int requiredLevel = 1;
                var type = AXE;
                return new Weapon(name, requiredLevel, type, damage, attackSpeedPerSecond);
            }
            default -> {
                throw new InvalidItemException("Specified type: " + itemType + " does not exist!");
            }
        }
    }


}
