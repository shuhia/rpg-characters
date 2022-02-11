package com.app.game.items;

import com.app.types.SlotType;
import com.app.types.WeaponType;

public class Weapon extends Item {
    double damage;
    double attackSpeedPerSecond;

    public Weapon(String name, int requiredLevel, WeaponType type, double damage, double attackSpeedPerSecond) {
        super(name, requiredLevel, SlotType.WEAPON, type);
        this.damage = damage;
        this.attackSpeedPerSecond = attackSpeedPerSecond;
    }

    public double getDamagePerSecond() {
        return damage * attackSpeedPerSecond;
    }

    public Enum<WeaponType> getType() {
        return type;
    }

}
