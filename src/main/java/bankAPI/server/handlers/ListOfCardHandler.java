package bankAPI.server.handlers;

import bankAPI.database.DBConnection;
import bankAPI.model.Card;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListOfCardHandler implements HttpHandler {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        long accountID = getIdFromUri(exchange.getRequestURI().toString());
        List<Card> cards = getAllCards(accountID);
        String response = objectToJson(cards);

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
    }

    private List<Card> getAllCards(long accountID) {
        String query = "SELECT * " +
                "FROM CARDS " +
                "WHERE account_ID = " + accountID;

        DBConnection dbConnection = DBConnection.getInstance();
        Connection connection = dbConnection.getConnection();

        List<Card> cards = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                long ID = result.getLong(1);
                long number = result.getLong(2);
                //long accountID = result.getLong(3);
                double balance = result.getDouble(4);

                cards.add(new Card(ID, number, accountID, balance));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cards;
    }

    private String objectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            System.out.println("Ошибка записи тела ответа");
            throw new RuntimeException();
        }
    }

    private long getIdFromUri(String uri) {
        return Long.parseLong(uri.split("=")[1]);
    }
}
