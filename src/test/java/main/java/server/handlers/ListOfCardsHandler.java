package main.java.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.CRUD.BankCardCRUD;
import database.MyDB;
import model.Client;
import services.Service;

import java.io.IOException;
import java.io.OutputStream;

public class ListOfCardsHandler implements HttpHandler {
    MyDB database;
    Client client;

    public ListOfCardsHandler(MyDB db, Client client){
        database = db;
        this.client = client;
    }



    @Override
    public void handle(HttpExchange t) throws IOException {
        String response = Service.getListOfCardsById(client.getId());

        t.sendResponseHeaders(200, response.length());
        OutputStream os = t.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
