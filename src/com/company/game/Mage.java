package com.company.game;

import static com.company.game.ICharacterSpecifications.*;


public class Mage extends Character {

    public Mage(String name) {
        super(name, mageBaseAttributes, mageGrowthAttributes, mageWeaponTypes, mageArmorTypes);
    }

    public Mage() {
        super(Mage.class.getSimpleName(), mageBaseAttributes, mageGrowthAttributes, mageWeaponTypes, mageArmorTypes);
    }

    @Override
    public double getTotalPrimaryAttribute() {
        return getTotalAttributes().intelligence;
    }

}
