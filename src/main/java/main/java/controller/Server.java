package main.java.controller;

import com.sun.net.httpserver.HttpServer;


import java.io.IOException;
import java.net.InetSocketAddress;

/**
 * Класс отвечающий за создание сервера
 */
public class Server {
    public static HttpServer server;

    /**
     * Запускает сервер
     */
    public static int startServer() throws IOException {

        server = HttpServer.create(new InetSocketAddress(8030), 0);

        BaseHandler baseHandler = new BaseHandler();
        StopApiHandler stopApiHandler = new StopApiHandler();



        server.createContext("/exit", stopApiHandler);
        server.createContext("/clients/", baseHandler);



        server.setExecutor(null);
        server.start();
        return 1;


    }

    /**
     * Завершает работу сервера
     */
    public static void stop() {
        try {
            server.stop(0);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}