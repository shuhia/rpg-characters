package com.app.client;

import com.app.game.Character;
import com.app.game.*;
import com.app.game.ICharacterSpecifications.characters;
import com.app.game.characters.Mage;
import com.app.game.characters.Ranger;
import com.app.game.characters.Rogue;
import com.app.game.characters.Warrior;
import com.app.game.items.Armor;
import com.app.game.items.Item;
import com.app.game.items.Weapon;
import com.app.types.ArmorType;
import com.app.types.SlotType;
import com.app.types.WeaponType;

import java.util.Arrays;
import java.util.Scanner;

// TODO: Add methods that can call methods in an object with string
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
        ItemFactory itemFactory = new ItemFactory();
        System.out.println("Welcome to RPG Characters");
        System.out.println("---------------------------------------");
        System.out.println("Choose a character");
        Character c = getCharacter();
        System.out.println("---------------------------------------");
        var isStarted = true;
        while (isStarted) {
            System.out.println("What do you want to do?");
            enum actions {
                LEVEL_UP,
                PRINT_STATS,
                CREATE_AXE,
                END_GAME,
            }
            String choice = getAlternative(Arrays.stream(actions.values()).map(Enum::name).toArray()).toString();
            switch (choice) {
                case "LEVEL_UP" -> {
                    c.levelUp();
                }
                case "PRINT_STATS" -> {
                    c.printStats();
                }
                case "CREATE_AXE" -> {
                    var axe = itemFactory.createItemOf("WEAPON");
                    axe.printStats();
                }
                case "END_GAME" -> {
                    isStarted = false;
                }
                default -> {
                    System.out.println("Invalid action");
                }
            }
            System.out.println("---------------------------------------");

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
            case "RANGER": {
                return new Ranger();
            }
            case "MAGE": {
                return new Mage();
            }
            case "ROGUE": {
                return new Rogue();
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
        Item bodyArmor = new Armor(ArmorType.PLATE, "Iron Chest Plate", 1, SlotType.BODY, new Attributes(10, 10, 10));
        Item weapon = new Weapon("Axe", 1, WeaponType.AXE, 10, 1);
        warrior.equip(bodyArmor);
        warrior.equip(weapon);
        warrior.equip(weapon);
        warrior.printStats();
        warrior.levelUp();
        warrior.printStats();
    }

}
