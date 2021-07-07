package main.java.server.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import database.MyDB;
import model.Client;
import model.DTODepositRequest;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BalanceHandler implements HttpHandler {

    MyDB database;
    Client client;

    public BalanceHandler(MyDB db, Client client){
        database = db;
        this.client = client;
    }


    @Override
    public void handle(HttpExchange httpExchange) throws IOException {


        String response = "{}";

        System.out.println(httpExchange.getRequestMethod());
        if("POST".equals(httpExchange.getRequestMethod())){
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Headers", "*");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Credentials", "true");
            httpExchange.getResponseHeaders().add("Access-Control-Allow-Credentials-Header", "*");
            httpExchange.getResponseHeaders().add("Content-Type", "application/json");
            StringBuilder buf = new StringBuilder(512);
            try {
                InputStreamReader isr =  new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                int b;
                while ((b = br.read()) != -1) {
                    buf.append((char) b);
                }
                br.close();
                isr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            ObjectMapper objectMapper = new ObjectMapper();
            DTODepositRequest s = objectMapper.readValue(buf.toString(), DTODepositRequest.class);
            System.out.println(s + " " + s.getDeposit());
        } else if ("GET".equals(httpExchange.getRequestMethod())){
            response = "<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "    <title>\n" +
                    "        Add balance\n" +
                    "    </title>\n" +
                    "</head>\n" +
                    "<p>\n" +
                    "    <label for=\"name\">Add money</label><br>\n" +
                    "    <input type=\"number\" id=\"sum\" placeholder=\"Enter sum\">\n" +
                    "    <button onclick=\"sendJSON()\">Add</button>\n" +
                    "<p class=\"result\"></p>\n" +
                    "<script>" +
                    "function sendJSON(){\n" +
                    "    let deposit = document.querySelector('#sum');\n" +
                    "    fetch( 'http://localhost:8000/balance',\n" +
                    "        {\n" +
                    "            method: \"POST\",\n" +
                    "            headers: { 'Content-Type': 'application/json;charset=utf-8' },\n" +
                    "            body: JSON.stringify({deposit: deposit.value})\n" +
                    "        })\n" +
                    "        .then(response => response.json())\n" +
                    "        .then(data => {\n" +
                    "            console.log(data);\n" +
                    "        })\n" +
                    "        .catch(e => console.log(e));\n" +
                    "}" +
                    "</script>\n" +
                    "</html>";




            // new String(Files.readAllBytes(  Paths.get("src/main/resources/index.html")), StandardCharsets.UTF_8);
        }


        httpExchange.sendResponseHeaders(200, response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes("Windows-1251"));
        os.close();
    }
}
