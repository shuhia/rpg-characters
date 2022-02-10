package com.company.client;

import com.company.game.Character;
import com.company.game.*;
import com.company.game.CharacterSpecifications.characters;
import com.company.types.ArmorType;
import com.company.types.Slot;
import com.company.types.WeaponType;

import java.util.Arrays;
import java.util.Scanner;

public class Client {
    public Client() {
    }

    public static String askFor(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
        return input;
    }

    public void start() {

        try {

            run();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void run() throws Exception {

        System.out.println("Welcome to RPG Characters");
        System.out.println("Choose a character");
        Character c = getCharacter();
        while (true) {
            System.out.println("What do you want to do?");
            enum actions {
                LEVEL_UP,
                EQUIP_ITEM,
                ATTACK,
                PRINT_STATS,
                q,
            }

            String choice = getAlternative(Arrays.stream(actions.values()).map((value) -> value.name()).toArray()).toString();
            switch (choice) {
                case "LEVEL_UP": {
                    c.levelUp();
                    System.out.println(c.getLevel());
                    break;
                }
                case "PRINT_STATS": {
                    c.printStats();
                    break;
                }
                default: {
                    System.out.println("Fail");
                }
            }
        }
    }

    private Character getCharacter() {
        Object[] alternatives = Arrays.stream(characters.values()).map((value) -> value.name()).toArray();
        Object alternative = getAlternative(alternatives).toString();
        Character c = createCharacter(alternative);
        if (c == null) return getCharacter();
        else {
            return c;
        }
    }

    public Character createCharacter(Object type) throws IllegalArgumentException {
        System.out.println(type.toString());
        switch (type.toString()) {
            case "WARRIOR": {
                return new Warrior();
            }
            default:
                return null;
        }

    }

    public Object getAlternative(Object[] alternatives) {
        printAlternatives(alternatives);
        try {
            Scanner scan = new Scanner(System.in);
            String input = scan.next();
            int choice = Integer.parseInt(input) - 1;
            final Object alternative = alternatives[choice];
            System.out.println("Your choice: " + alternative.toString());
            return alternative;
        } catch (Exception e) {
            System.out.println("Please try again");
            return getAlternative(alternatives);
        }
    }

    public void printAlternatives(Object[] alternatives) {
        for (int i = 1; i <= alternatives.length; i++) {
            System.out.println(i + "." + alternatives[i - 1]);
        }
    }

    public void testGame() throws Exception {
        Character warrior = new Warrior("Warrior");
        Item bodyArmor = new Armor(ArmorType.PLATE, "Iron Chest Plate", 1, Slot.BODY, new Attributes(10, 10, 10));
        Item weapon = new Weapon("Axe", 1, WeaponType.AXE, 10, 1);
        warrior.equip(bodyArmor);
        warrior.equip(weapon);
        warrior.equip(weapon);
        warrior.printStats();
        warrior.levelUp();
        warrior.printStats();
    }


}
