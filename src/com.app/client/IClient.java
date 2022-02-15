package com.app.client;

public interface IClient {
    String askFor(String question, String[] options);

    String printOptions(String[] options);

    String printSelectedOption(String option);

    enum actions {
        LEVEL_UP,
        PRINT_STATS,
        CREATE_ITEM,
        CREATE_ITEM_WITH_FACTORY,
        LIST_INVENTORY,
        RESTART_GAME,
        END_GAME,
    }
}
