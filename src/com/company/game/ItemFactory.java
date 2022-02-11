package com.company.game;

import com.company.exceptions.InvalidItemException;
import com.company.types.ArmorType;
import com.company.types.Slot;
import com.company.types.WeaponType;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.company.types.ArmorType.PLATE;
import static com.company.types.WeaponType.AXE;

public class ItemFactory {
    public Item createItemOf(String itemType) throws InvalidItemException {
        switch(itemType){
            case "ARMOR" -> {
                var name = "armor";
                var requiredLevel = 1;
                var type = PLATE;
                var slot = Slot.BODY;
                var amount = 5;
                var attributes = new Attributes(amount, amount, amount);
                return new Armor(type, name, requiredLevel, slot, attributes);
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
                throw new InvalidItemException("Specified type: "+itemType.toString() + " does not exist!");
            }
        }
    }


}
