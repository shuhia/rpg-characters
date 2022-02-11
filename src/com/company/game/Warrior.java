package com.company.game;

import java.util.Collections;

import static com.company.game.ICharacterSpecifications.*;


public class Warrior extends Character {
    public Warrior(String name) {
        super(name, warriorBaseAttributes, warriorGrowthAttributes);
        Collections.addAll(this.equipableItems, warriorWeaponTypes);
        Collections.addAll(this.equipableItems, warriorArmorTypes);
    }

    public Warrior() {
        super("Warrior", warriorBaseAttributes, warriorGrowthAttributes);
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
    public double getTotalPrimaryAttribute() {
        return getTotalAttributes().strength;
    }

    private double getDamageFromAttribute(double attribute) {
        return 1 + attribute / 100;
    }


}
