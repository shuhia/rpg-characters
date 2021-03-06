package com.app.game.characters;

import com.app.game.Character;

import static com.app.game.ISpecifications.*;


public class Rogue extends Character implements RogueSpec {

    public Rogue(String name) {
        super(name, rogueBaseAttributes, rogueGrowthAttributes, rogueWeaponTypes, rogueArmorTypes);
    }

    public Rogue() {
        super(Rogue.class.getSimpleName(), rogueBaseAttributes, rogueGrowthAttributes, rogueWeaponTypes, rogueArmorTypes);
    }

    @Override
    public double getTotalPrimaryAttribute() {
        return getTotalAttributes().getDexterity();
    }

}
