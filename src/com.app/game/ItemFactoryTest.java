package com.app.game;

import com.app.game.exceptions.InvalidItemException;
import com.app.game.items.Item;
import com.app.game.items.Weapon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ItemFactoryTest {
    @Test
    void createItemOf() {
        var factory = new ItemFactory();
        Item item = null;
        try {
            item = factory.createItemOf("WEAPON");
        } catch (InvalidItemException e) {
            e.printStackTrace();
        }
        System.out.println(item.toString());
        assertTrue(item instanceof Weapon);
    }
}