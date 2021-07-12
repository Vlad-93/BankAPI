package bankAPI.server;

import bankAPI.server.handlers.*;

import com.sun.net.httpserver.HttpServer;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Server {

    public void startServer() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);
        server.createContext("/new_card", new NewCardHandler());
        server.createContext("/list_card", new ListOfCardHandler());
        server.createContext("/put_money", new PutMoneyHandler());
        server.createContext("/check_balance", new CheckBalanceHandler());

        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        server.setExecutor(threadPoolExecutor);
        server.start();
    }
}
