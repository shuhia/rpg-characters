package com.app.client;

public interface IClient {
    String askFor(String question, String[] options);

    String printOptions(String[] options);

    String printSelectedOption(String option);

    enum actions {
        LEVEL_UP,
        PRINT_STATS,
        CREATE_ITEM,
        LIST_INVENTORY,
        END_GAME,
    }
}
