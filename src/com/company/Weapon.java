package com.company;

public class Weapon extends Item{
    WeaponType type;
    double damage;
    double attackSpeedPerSecond;

    public Weapon(String name, int requiredLevel, Slot slot, WeaponType type, double damage, double attackSpeedPerSecond) {
        super(name,requiredLevel, slot);
        this.type = type;
        this.damage = damage;
        this.attackSpeedPerSecond = attackSpeedPerSecond;
    }

    public double getDamagePerSecond(){
        return damage*attackSpeedPerSecond;
    }
    public WeaponType getType(){
        return this.type;
    }

}
