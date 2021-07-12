package bankAPI;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import bankAPI.database.DBConnection;
import bankAPI.model.Card;
import bankAPI.server.Server;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ServerTest {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void createDataBase() {
        DBConnection dbCon = DBConnection.getInstance();
        dbCon.createConnection();
        dbCon.dbInit();
    }

    @AfterEach
    public void close() {
        DBConnection dbCon = DBConnection.getInstance();
        dbCon.close();
    }

    @BeforeEach
    public void startServer() {
        try {
            new Server().startServer();
        } catch (IOException e) {
            System.out.println("Ошибка старта сервера");
            throw new RuntimeException();
        }
    }

    @Test
    public void checkBalanceTest() throws Exception {
        int cardID = 1;
        URL url = new URL("http://localhost:8080/check_balance?cardId=" + cardID);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        Scanner scan = new Scanner(new InputStreamReader(con.getInputStream()));
        String response = scan.nextLine().replace(',', '.')
                .replace('{', ' ').replace('}', ' ');
        scan.close();
        double balanceInServer = Double.parseDouble(response);

        Connection connection = DBConnection.getInstance().getConnection();
        String query = "SELECT BALANCE FROM CARDS WHERE ID = " + cardID;
        ResultSet resultSet = connection.prepareStatement(query).executeQuery();
        resultSet.next();
        double balanceInDB = resultSet.getDouble("Balance");

        assertEquals(balanceInDB, balanceInServer);
    }

//    @Test
//    public void checkCardList() throws Exception {
//        int accountID = 2;
//        URL url = new URL("http://localhost:8080/list_card?accountID=" + accountID);
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        con.setRequestMethod("GET");
//        con.setRequestProperty("Content-Type", "application/json");
//
//        Scanner scan = new Scanner(new InputStreamReader(con.getInputStream()));
//
//        String response = scan.nextLine().replace(',', '.')
//                .replace('{', ' ').replace('}', ' ');
//        scan.close();
//        double balanceInServer = Double.parseDouble(response);
//
//        Connection connection = DBConnection.getInstance().getConnection();
//        String query = "SELECT BALANCE FROM CARDS WHERE ID = " + accountID;
//        ResultSet resultSet = connection.prepareStatement(query).executeQuery();
//        resultSet.next();
//        double balanceInDB = resultSet.getDouble("Balance");
//
//        assertEquals(balanceInDB, balanceInServer);
//    }

    private String objectToJson(Object object) throws IOException {
        return objectMapper.writeValueAsString(object);
    }

    private Object jsonToObject(InputStream inputStream, Class<?> clazz) throws IOException {
        return objectMapper.readValue(inputStream, clazz);
    }

}
