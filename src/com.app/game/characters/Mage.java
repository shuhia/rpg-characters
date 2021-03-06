package com.app.game.characters;

import com.app.game.Character;

import static com.app.game.ISpecifications.*;


public class Mage extends Character implements MageSpec{

    public Mage(String name) {
        super(name, mageBaseAttributes, mageGrowthAttributes, mageWeaponTypes, mageArmorTypes);
    }

    public Mage() {
        super(Mage.class.getSimpleName(), mageBaseAttributes, mageGrowthAttributes, mageWeaponTypes, mageArmorTypes);
    }

    @Override
    public double getTotalPrimaryAttribute() {
        return getTotalAttributes().getIntelligence();
    }

}
