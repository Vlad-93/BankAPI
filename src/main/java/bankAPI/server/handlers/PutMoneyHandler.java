package bankAPI.server.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;

public class PutMoneyHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
//
//        String response = "{}";
//
//        StringBuilder sb = new StringBuilder();
//
//        try (InputStreamReader isr =  new InputStreamReader(httpExchange.getRequestBody(), "utf-8");
//            BufferedReader br = new BufferedReader(isr)) {
//            int b;
//            while ((b = br.read()) != -1) {
//                sb.append((char) b);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        ObjectMapper objectMapper = new ObjectMapper();
//        DTODepositRequest s = objectMapper.readValue(sb.toString(), DTODepositRequest.class);
//        System.out.println(s + " " + s.getDeposit());
//
//        httpExchange.sendResponseHeaders(200, response.length());
//        OutputStream os = httpExchange.getResponseBody();
//        os.write(response.getBytes());
//        os.flush();
//        os.close();
//
//
//        String URI = exchange.getRequestURI().toString();
//        long cardId = getIdFromUri(URI);
//        int responseCode;
//
//        String response;
//
//        String json= new BufferedReader(
//                new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))
//                .lines()
//                .collect(Collectors.joining("\n"));
//        String data = service.addMoneyToCard(json, cardId, true);
//        response = data == null ? "No such card found" : data;
//
//        responseCode = response.equals("No such card found") ? 404 : 200;
    }



}
