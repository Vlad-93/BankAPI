package main.java.server;

import java.net.InetSocketAddress;
import com.sun.net.httpserver.HttpServer;
import database.MyDB;
import model.Client;
import server.handlers.*;

public class Server {
    public Server(){

    }

    public void start() throws Exception {
        MyDB database = new MyDB();
        Client currentClient = new Client();

        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/home", new StartPageHandler());

        server.createContext("/login", new LoginHandler(database, currentClient));

        server.createContext("/new_card", new NewCardHandler(database, currentClient));
        server.createContext("/cards_list", new ListOfCardsHandler(database, currentClient));
        server.createContext("/balance", new BalanceHandler(database, currentClient));

        server.setExecutor(null);
        server.start();
    }


}