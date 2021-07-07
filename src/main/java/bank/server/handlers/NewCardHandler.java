package bank.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

public class NewCardHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String URI = exchange.getRequestURI().toString();
        long cardId = getIdFromUri(URI);
        int responseCode;
        UserService service = new UserService();
        String response;

        String data = service.getBalance(cardId);
        response = data == null ? "No such card found" : data;

        responseCode = response.equals("No such card found") ? 404 : 200;

        exchange.sendResponseHeaders(responseCode, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.flush();
        os.close();
    }
}
