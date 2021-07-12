package bankAPI;

import bankAPI.database.DBConnection;
import bankAPI.server.Server;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        DBConnection dbCon = DBConnection.getInstance();
        dbCon.createConnection();
        dbCon.dbInit();

        try {
            new Server().startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
