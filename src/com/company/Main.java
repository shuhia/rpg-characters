package com.company;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        System.out.println("Welcome to RPG Characters");
        Character warrior = new Warrior("Alex");
        Item bodyArmor = new Armor(ArmorType.CLOTH, "Skjorta", 1, Slot.BODY, new Attributes(10, 10, 10));
        Item weapon = new Weapon("Axe", 1, Slot.WEAPON, WeaponType.AXE, 10, 1);
        warrior.equip(bodyArmor);
        warrior.equip(weapon);
        warrior.equip(weapon);
        warrior.printStats();
        warrior.levelUp();
        warrior.printStats();
    }

    public static String askFor(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
        return input;
    }
}
