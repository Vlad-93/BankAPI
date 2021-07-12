package bankAPI.server.handlers;

import bankAPI.database.DBConnection;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckBalanceHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String URI = exchange.getRequestURI().toString();
        long cardId = getIdFromUri(URI);
        int responseCode;
        String response;

        try {
            double balance = getCardBalance(cardId);
            responseCode = 200;
            response = String.format("{%f}", balance);
        } catch (IllegalArgumentException e) {
            responseCode = 404;
            response = e.getMessage();
        }

        exchange.sendResponseHeaders(responseCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
    }

    private double getCardBalance(long id) {
        String query = "SELECT * " +
                "FROM CARDS " +
                "WHERE ID = " + id;

        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet result = preparedStatement.executeQuery();

            result.next();

            return result.getDouble("balance");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new IllegalArgumentException("Карта не найдена");
    }

    private long getIdFromUri(String uri) {
        return Long.parseLong(uri.split("=")[1]);
    }
}
// curl -d '{"accounId": "1"}' -X POST http://localhost:8080/new_card