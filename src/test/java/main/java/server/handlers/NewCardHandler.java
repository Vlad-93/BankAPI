package main.java.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.CRUD.BankCardCRUD;
import database.MyDB;
import model.Client;

import java.io.IOException;
import java.io.OutputStream;

public class NewCardHandler implements HttpHandler {
    MyDB database;
    Client client;

    public NewCardHandler(MyDB db, Client client){
        database = db;
        this.client = client;
    }



    @Override
    public void handle(HttpExchange t) throws IOException {
        BankCardCRUD.create(client.getId());

        String response = "This is the response";
        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
