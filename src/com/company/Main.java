package com.company;

import com.company.client.Client;

public class Main {


    public static void main(String[] args) {
        Client client = new Client();
        try {
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
