package bank;

import bank.database.DBConnection;
import bank.server.Server;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        DBConnection dbCon = new DBConnection();
        dbCon.createConnection();
        dbCon.dbInit();
        dbCon.close();

        try {
            new Server().startServer();
        } catch (IOException e) {
            System.out.println("Ошибка старта сервера");
        }

    }

}
