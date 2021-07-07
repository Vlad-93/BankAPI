package main.java.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class StartPageHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange t) throws IOException {

        String response = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=windows-1251\">\n" +
                "<h2>Стартовая страница</h2>\n" +
                "<!--<p>HTML links are defined with the a tag:</p>\n" +
                "-->\n" +
                "<p><a href=\"http://localhost:8000/new_card\">Выпуск новой карты по счету</a></p>\n" +
                "<p><a href=\"http://localhost:8000/cards_list\">Проcмотр списка карт</a></p>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes("Windows-1251"));
        os.close();
    }
}
