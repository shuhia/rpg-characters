package com.app.client;

import com.app.game.Attributes;
import com.app.game.Character;
import com.app.game.ISpecifications;
import com.app.game.ItemFactory;
import com.app.game.characters.Mage;
import com.app.game.characters.Ranger;
import com.app.game.characters.Rogue;
import com.app.game.characters.Warrior;
import com.app.game.enums.ArmorType;
import com.app.game.enums.SlotType;
import com.app.game.enums.WeaponType;
import com.app.game.items.Armor;
import com.app.game.items.Item;
import com.app.game.items.Weapon;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

/***
 * This class is used for interacting with rpg characters classes in the Game folder
 */
public class Client implements IClient {
    static Scanner scanner = new Scanner(System.in);

    public static String askForInputOf(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(input);
        return input;
    }

    /***
     * Starts the game
     */
    public void start() {

        try {
            run();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /***
     * Runs the game
     * @throws Exception
     */
    public void run() throws Exception {
        var restartGame = false;
        do {
            var itemFactory = new ItemFactory();
            // Welcome user
            System.out.println("Welcome to RPG Characters");
            // Ask for a character type
            String characterType = askFor("character type", ISpecifications.characters.values());
            // Visit the character shop to get a character object of type
            Character character = visitCharacterShop(characterType);
            var isPlaying = true;
            while (isPlaying) {
                // Ask user for action type
                // actions.values from IClient values
                String actionType = askFor("action type", actions.values());
                switch (actionType) {
                    case "LEVEL_UP" -> {
                        character.levelUp();
                    }
                    case "CREATE_ITEM" -> {
                        character.addItem(visitItemShop());
                    }
                    case "LIST_INVENTORY" -> {
                        character.printItems();
                    }
                    case "CREATE_ITEM_WITH_FACTORY" -> {
                        var item = itemFactory.createItemOf(askFor("item type", new String[]{"WEAPON", "ARMOR"}));
                        item.printStats();
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
        while (restartGame);
    }

    /***
     * Asks visitor for weaponType and redirects user to correct section
     * @return
     */
    private Item visitItemShop() {
        // Ask visitor for item type
        String[] options = {"WEAPON", "ARMOR"};
        var itemType = askFor("Item type", options);
        Item item;
        switch (itemType) {
            case "WEAPON" -> {
                item = visitWeaponSection();
            }
            case "ARMOR" -> {
                item = visitArmorSection();
            }
            default -> {
                return null;
            }
        }
        System.out.println("Received a new item with following spec:");
        item.printStats();
        return item;
    }

    /***
     * Asks visitor for armor specifications
     * @return a piece of armor
     */
    private Item visitArmorSection() {
        Item item;
        var type = askFor("type", ArmorType.values());
        var name = askFor("name");
        var requiredLevel = askForValue("required level");
        var slot = askFor("slot type", SlotType.values());
        var attributes = askForAttributes();
        item = new Armor(name, requiredLevel, ArmorType.valueOf(type), SlotType.valueOf(slot), attributes);
        return item;
    }

    /***
     * Asks visitor for weapon specifications
     * @return
     */
    private Item visitWeaponSection() {
        Item item;
        var type = askFor("type", WeaponType.values());
        var name = askFor("name");
        var requiredLevel = askForValue("required level");
        var slot = askFor("slot type", new Enum[]{SlotType.WEAPON});
        var attackSpeedPerSecond = askForValue("attackspeed per second");
        var damage = askForValue("damage");
        item = new Weapon(name, requiredLevel, WeaponType.valueOf(type), SlotType.valueOf(slot), damage, attackSpeedPerSecond);
        return item;
    }

    /***
     * Used to get a character from the store based on characterType
     * @param type - type of character
     * @return - returns a character
     * @throws IllegalArgumentException
     */
    public Character visitCharacterShop(Object type) throws IllegalArgumentException {
        System.out.println(type.toString());
        switch (type.toString()) {
            case "WARRIOR" -> {
                return new Warrior();
            }
            case "RANGER" -> {
                return new Ranger();
            }
            case "MAGE" -> {
                return new Mage();
            }
            case "ROGUE" -> {
                return new Rogue();
            }
            default -> {
                System.out.println("invalid type");
                return null;
            }
        }

    }

    /***
     * Asks for what values the user want to set for each attribute.
     * @return attributes
     */
    public Attributes askForAttributes() {
        var attributes = new Attributes();
        attributes.setStrength(askForValue("strength"));
        attributes.setDexterity(askForValue("dexterity"));
        attributes.setIntelligence(askForValue("intelligence"));
        return attributes;
    }

    /***
     * Asks user for a value
     * @param something - name of the value
     * @return a value
     */
    public Integer askForValue(String something) {
        return askForValue(something, 0);
    }
    /***
     * Asks user for a value
     * @param something - name of the value
     * @param min - minimum value
     * @return a value
     */
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

    /***
     * Ask for a value that is text based. For example: askFor(name) returns a name.
     * @param something - anything in text
     * @return text
     */
    public String askFor(String something) {
        System.out.println("Please enter a " + something);
        var string = scanner.nextLine().trim();
        while (string.isBlank()) {
            System.out.println("Empty string is not allowed!");
            string = scanner.nextLine().trim();
        }
        return string;
    }

    /***
     * Asks user to select one value out of many values based on options provided.
     * @param something - the name of the value you are asking for
     * @param options - name of the values
     * @return name of a value
     */
    public String askFor(String something, Enum<?>[] options) {
        // Convert enum to string
        String[] strings = Arrays.stream(options).map(Enum::name).toArray(String[]::new);
        return askFor(something, strings);
    }

    /***
     * Asks user to select one value out of many values based on options provided.
     * @param something - the name of the value you are asking for
     * @param options - name of the values
     * @return name of a value
     */
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

    /***
     * Print options in the format of a ordered list. For example: 1.option, 2.option, ....
     * @param options - options
     * @return ordered list in the format of a string
     */
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

    /***
     * Print the selected option surrounded with some clarifications
     * @param option
     * @return
     */
    @Override
    public String printSelectedOption(String option) {
        var response = "You choice: " + option;
        System.out.println(response);
        return response;
    }

}
