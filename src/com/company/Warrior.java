package com.company;

public class Warrior extends Character {

    protected Warrior(String name) {
        super(name, new Attributes(5, 2, 1), new Attributes(3, 2, 1));
        // Set warrior starting attributes
        // Set equip limitations
        this.equipableWeapons.add(WeaponType.AXE);
        this.equipableWeapons.add(WeaponType.HAMMER);
        this.equipableWeapons.add(WeaponType.SWORD);
    }


    @Override
    public double getTotalDamagePerSecond() {
        // • Character DPS = Weapon DPS * (1 + TotalMainPrimaryAttribute/100)
        double weaponsDamagePerSecond = 1;
        if (equipped.containsKey(Slot.WEAPON)) {
            weaponsDamagePerSecond = ((Weapon) equipped.get(Slot.WEAPON)).getDamagePerSecond();
        }
        var totalMainAttribute = getTotalAttributes().strength;
        var totalDamagePerSecond = weaponsDamagePerSecond * (1 + totalMainAttribute / 100);
        return totalDamagePerSecond;
    }
}
