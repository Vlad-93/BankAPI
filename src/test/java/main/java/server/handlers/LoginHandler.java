package main.java.server.handlers;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.MyDB;
import model.Client;

import java.io.IOException;
import java.io.OutputStream;

public class LoginHandler implements HttpHandler {
    MyDB db;
    Client client;

    public LoginHandler(MyDB db, Client client){
        this.db = db;
        this.client = client;
    }

    @Override
    public void handle(HttpExchange t) throws IOException {
        String[] params = t.getRequestURI().getQuery().split("&");
        String login = params[0].substring(6);
        String password = params[1].substring(9);
        String response;
        try {
            Client temp = db.Authenticate(login, password);
            client.setFirstName(temp.getFirstName());
            client.setSecondName(temp.getSecondName());
            client.setId(temp.getId());
            client.setPassword(password);
            client.setLogin(login);

            response = login + " " + password;
            t.getResponseHeaders().set("Location", "home");
            t.sendResponseHeaders(302,response.length());

        } catch (Exception e) {
            response = "Неверный логин или пароль";
            t.sendResponseHeaders(200, response.length());
        }

        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
