package com.company.game;

import java.util.Collections;

import static com.company.game.ICharacterSpecifications.*;


public class Warrior extends Character {

    public Warrior(String name) {
        super(name, warriorBaseAttributes, warriorGrowthAttributes,warriorWeaponTypes, warriorArmorTypes);
    }

    public Warrior() {
        super(Warrior.class.getSimpleName(), warriorBaseAttributes, warriorGrowthAttributes,warriorWeaponTypes,warriorArmorTypes);
    }

    @Override
    public double getTotalPrimaryAttribute() {
        return getTotalAttributes().strength;
    }

}
