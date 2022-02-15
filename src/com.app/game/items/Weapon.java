package com.app.game.items;

import com.app.game.enums.SlotType;
import com.app.game.enums.WeaponType;
import com.app.game.exceptions.InvalidWeaponException;

public class Weapon extends Item {
    double damage;
    double attackSpeedPerSecond;

    public Weapon(String name, int requiredLevel, WeaponType type, SlotType slot, double damage, double attackSpeedPerSecond) {
        super(name, requiredLevel, slot, type);
        this.damage = damage;
        this.attackSpeedPerSecond = attackSpeedPerSecond;
    }

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

    @Override
    public void printStats() {
        super.printStats();
        System.out.println("damage: " + damage);
        System.out.println("attack speed" + attackSpeedPerSecond);
    }

    @Override
    public void throwInvalid(String message) throws Exception {
        throw new InvalidWeaponException("Weapon " + message);
    }
}
