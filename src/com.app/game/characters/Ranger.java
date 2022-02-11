package com.app.game.characters;

import com.app.game.Character;

import static com.app.game.ICharacterSpecifications.*;


public class Ranger extends Character {

    public Ranger(String name) {
        super(name, rangerBaseAttributes, rangerGrowthAttributes, rangerWeaponTypes, rangerArmorTypes);
    }

    public Ranger() {
        super(Ranger.class.getSimpleName(), rangerBaseAttributes, rangerBaseAttributes, rangerWeaponTypes, rangerArmorTypes);
    }

    @Override
    public double getTotalPrimaryAttribute() {
        return getTotalAttributes().getDexterity();
    }

}
