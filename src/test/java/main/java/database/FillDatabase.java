package main.java.database;

import database.CRUD.ClientCRUD;

public class FillDatabase {
    public static void main(String[] args) {
        ClientCRUD.create("qrer", "sfc", "123", "123");
    }
}
