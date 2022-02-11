package com.company.game;

import static com.company.game.ICharacterSpecifications.*;


public class Ranger extends Character {

    public Ranger(String name) {
        super(name, rangerBaseAttributes, rangerGrowthAttributes, rangerWeaponTypes, rangerArmorTypes);
    }

    public Ranger() {
        super(Ranger.class.getSimpleName(), rangerBaseAttributes, rangerBaseAttributes, rangerWeaponTypes, rangerArmorTypes);
    }

    @Override
    public double getTotalPrimaryAttribute() {
        return getTotalAttributes().dexterity;
    }

}
