package com.company;

import java.util.Collections;

import static com.company.CharacterSpecifications.*;

public class Warrior extends Character {
    protected Warrior(String name) {
        super(name, warriorBaseAttributes, warriorGrowthAttributes);
        Collections.addAll(this.equipableItems, warriorWeaponTypes);
        Collections.addAll(this.equipableItems, warriorArmorTypes);
    }


    @Override
    public double getTotalDamagePerSecond() {
        // • Character DPS = Weapon DPS * (1 + TotalMainPrimaryAttribute/100)
        double weaponDamagePerSecond = getWeaponDamagePerSecond();
        return weaponDamagePerSecond * getDamageFromAttribute(getTotalPrimaryAttribute());
    }

    @Override
    protected double getTotalPrimaryAttribute() {
        return getTotalAttributes().strength;
    }

    private double getDamageFromAttribute(double attribute) {
        return 1 + attribute / 100;
    }


}
