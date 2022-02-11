package com.company.game;

import static com.company.game.ICharacterSpecifications.*;


public class Rogue extends Character {

    public Rogue(String name) {
        super(name, rogueBaseAttributes, rogueGrowthAttributes, rogueWeaponTypes, rogueArmorTypes);
    }

    public Rogue() {
        super(Rogue.class.getSimpleName(), rogueBaseAttributes, rogueGrowthAttributes, rogueWeaponTypes, rogueArmorTypes);
    }

    @Override
    public double getTotalPrimaryAttribute() {
        return getTotalAttributes().dexterity;
    }

}
