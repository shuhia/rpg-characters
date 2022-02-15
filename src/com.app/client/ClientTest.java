package com.app.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {
    Client client = new Client();

    @Test
    void printOptions() {
        String[] options = {"option"};
        String expected = "1.option\n";
        String actual = client.printOptions(options);
        assertEquals(expected, actual);
    }

    @Test
    void printSelectedOption() {
        String option = "option";
        var expected = "You choice: " + option;
        assertEquals(expected, client.printSelectedOption(option));
    }
}