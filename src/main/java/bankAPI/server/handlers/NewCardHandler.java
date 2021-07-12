package bankAPI.server.handlers;

import bankAPI.database.DBConnection;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NewCardHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        int responseCode;
        String response;

        String s = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8)).readLine();

        long accountId = Long.parseLong(s);
        if (createNewCard(accountId)) {
            response = "Карта создана";
            responseCode = 200;
        }
        else {
            response = "Ошибка создания карты";
            responseCode = 404;
        }

        exchange.sendResponseHeaders(responseCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
    }

    private boolean createNewCard(long accountID) {
        String query = "INSERT INTO Cards (ACCOUNT_ID) VALUES (?)";
        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setLong(1, accountID);
            return preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

}