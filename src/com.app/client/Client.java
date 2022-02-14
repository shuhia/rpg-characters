package com.app.client;

import com.app.game.Attributes;
import com.app.game.Character;
import com.app.game.ISpecifications;
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
import java.util.LinkedList;
import java.util.Scanner;

public class Client implements IClient {
    static Scanner scanner = new Scanner(System.in);

    public static String askForInputOf(String message) {
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
        var restartGame = false;
        do {
            System.out.println("Welcome to RPG Characters");
            String characterType = askFor("character type", ISpecifications.characters.values());
            Character character = createCharacter(characterType);
            LinkedList<Item> inventory = new LinkedList<Item>();
            var isPlaying = true;
            while (isPlaying) {
                String actionType = askFor("action type", actions.values());
                switch (actionType) {
                    case "LEVEL_UP" -> {
                        character.levelUp();
                    }
                    case "CREATE_ITEM" -> {
                        character.addItem(createItem());
                    }
                    case "LIST_INVENTORY" -> {
                        character.printItems();
                    }
                    case "PRINT_STATS" -> {
                        character.printStats();
                    }
                    case "RESTART_GAME" -> {
                        isPlaying = false;
                        restartGame = true;
                    }
                    case "END_GAME" -> {
                        isPlaying = false;
                        restartGame = false;
                    }

                    default -> {
                        System.out.println("Invalid action");
                    }
                }
            }
        }
        while(restartGame);
    }

    private Item createItem() {
        String[] options = {"WEAPON", "ARMOR"};
        var itemType = askFor("Item type", options);
        Item item = null;
        if (itemType.equals("WEAPON")) {
            var type = askFor("type", WeaponType.values());
            var name = askFor("name");
            var requiredLevel = askForValue("required level");
            var slot = askFor("slot type", SlotType.values());
            var attackSpeedPerSecond = askForValue("attackspeed per second");
            var damage = askForValue("damage");
            item = new Weapon(name, requiredLevel, WeaponType.valueOf(type), SlotType.valueOf(slot), damage, attackSpeedPerSecond);
        } else if (itemType.equals("ARMOR")) {
            var type = askFor("type", ArmorType.values());
            var name = askFor("name");
            var requiredLevel = askForValue("required level");
            var slot = askFor("slot type", SlotType.values());
            var attributes = askForAttributes();
            item = new Armor(name, requiredLevel, ArmorType.valueOf(type), SlotType.valueOf(slot), attributes);
        } else return null;
        System.out.println("created item");
        item.printStats();
        return item;
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
                System.out.println("invalid type");
                return null;
        }

    }

    public Attributes askForAttributes() {
        var attributes = new Attributes();
        attributes.setStrength(askForValue("strength"));
        attributes.setDexterity(askForValue("dexterity"));
        attributes.setIntelligence(askForValue("intelligence"));
        return attributes;
    }

    public Integer askForValue(String something) {
        return askForValue(something, 0);
    }

    public Integer askForValue(String something, int min) {

        int number = -1;
        while (number < min) {
            System.out.println("Please enter a value for " + something);
            try {
                var string = scanner.nextLine();
                number = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number!");
            }
        }
        System.out.println("Set " + something + " to " + number);
        return number;
    }

    public String askFor(String something) {
        System.out.println("Please enter a " + something);
        var string = scanner.nextLine().trim();
        while (string.isBlank()) {
            System.out.println("Empty string is not allowed!");
            string = scanner.nextLine().trim();
        }
        return string;
    }

    public String askFor(String something, Enum[] options) {
        // Convert enum to string
        String[] strings = Arrays.stream(options).map(Enum::name).toArray(String[]::new);
        return askFor(something, strings);
    }

    @Override
    public String askFor(String something, String[] options) {
        int choice = -1;
        var string = "";
        String option = "";
        final int length = options.length;
        while (choice < 0 || choice >= length) {
            try {
                System.out.println("Please choose " + something + ".");
                printOptions(options);
                string = scanner.nextLine();
                choice = Integer.parseInt(string) - 1;

            } catch (Exception e) {
                if (e instanceof NumberFormatException) System.out.println("Please enter a number");
                else if (e instanceof ArrayIndexOutOfBoundsException)
                    System.out.println("Please enter a valid number from: 1 to " + length);
                else e.printStackTrace();
            }
        }
        option = options[choice];
        printSelectedOption(option);
        return option;
    }

    @Override
    public String printOptions(String[] options) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int index = 1; index <= options.length; index++) {
            stringBuilder.append(index).append(".").append(options[index - 1]).append("\n");
        }
        // Remove excess space
        var response = stringBuilder.toString().trim();
        System.out.println(response);

        return response;
    }

    @Override
    public String printSelectedOption(String option) {
        var response = "You choice: " + option;
        System.out.println(response);
        return response;
    }

}
