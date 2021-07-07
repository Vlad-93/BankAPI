package main.java.controller;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.util.regex.Pattern;

/**
 * Базовый обработчик запросов, распеределяет запросы между специализированными обработчиками
 */
public class BaseHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String URI = exchange.getRequestURI().toString();

        if (Pattern.matches("/clients/\\D+/cards/$", URI)) {
            CardsListHandler cardsListHandler = new CardsListHandler();
            cardsListHandler.handle(exchange);
        } else {
            CardHandler handler = new CardHandler();
            handler.handle(exchange);
        }
    }
}