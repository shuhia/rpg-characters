package com.app.game;

import com.app.game.exceptions.InvalidItemException;
import com.app.game.items.Item;
import com.app.game.items.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemFactoryTest {
    @Test
    void createItemOfWeapon_ExpectTypeWeapon() {
        var factory = new ItemFactory();
        Item item = null;
        try {
            item = factory.createItemOf("WEAPON");
            System.out.println(item.toString());
        } catch (InvalidItemException e) {
            e.printStackTrace();
        }

        assertTrue(item instanceof Weapon);
    }

    @Test
    void createItemOfArmor_ExpectTypeArmor() {
        var factory = new ItemFactory();
        Item item = null;
        try {
            item = factory.createItemOf("ARMOR");
            System.out.println(item.toString());
        } catch (InvalidItemException e) {
            e.printStackTrace();
        }

        assertTrue(item instanceof Weapon);
    }
}