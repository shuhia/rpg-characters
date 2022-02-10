package com.company;

import java.util.Arrays;
import java.util.Collections;

import static com.company.CharacterSpecifications.*;

public class Warrior extends Character {
    protected Warrior(String name) {
        super(name, warriorBaseAttributes, warriorGrowthAttributes);
        // Set warrior starting attributes
        // Set equip limitations
        this.equipableItems.addAll(Arrays.asList(warriorWeaponTypes));
        Collections.addAll(this.equipableItems, warriorArmorTypes);


    }


    @Override
    public double getTotalDamagePerSecond() {
        // • Character DPS = Weapon DPS * (1 + TotalMainPrimaryAttribute/100)
        double weaponDamagePerSecond = 1;
        if (equipped.containsKey(Slot.WEAPON)) {
            weaponDamagePerSecond = ((Weapon) equipped.get(Slot.WEAPON)).getDamagePerSecond();
        }
        var totalMainAttribute = getTotalAttributes().strength;
        return weaponDamagePerSecond * (1 + totalMainAttribute / 100);
    }
}
