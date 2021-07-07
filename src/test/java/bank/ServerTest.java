//package bank;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLConnection;
//import org.codehaus.jackson.map.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class MainHandlerTest {
//
//
//    public class ServerTest {
//
//        public final ObjectMapper objectMapper = new ObjectMapper();
//
//        @BeforeEach
//        public void startServer() throws IOException {
//            new BaseServer().startServer();
//        }
//
//        @Test
//        public void mainHandlerTest() throws IOException {
//            HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8080/").openConnection();
//            connection.setDoOutput(true);
//            connection.setRequestMethod("POST");
//            try (OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream())){
//                MainPageRequest request = new MainPageRequest();
//                request.setUserName("vlad");
//                String body = objectToJson(request);
//                outputStreamWriter.write(body);
//                outputStreamWriter.flush();
//
//            } catch (Throwable e) {
//                System.out.println("Ошибка записи тела запроса");
//            }
//
//            try (InputStream inputStream =  connection.getInputStream()){
//                MainPageResponse response = (MainPageResponse) jsonToObject(inputStream, MainPageResponse.class);
//                assertTrue(response.getHelloText().contains("vlad"));
//            }
//        }
//
//        @Test
//        public void mainHandlerTestGet() throws IOException {
//            URLConnection connection = new URL("http://localhost:8080/").openConnection();
//            connection.getInputStream();
//        }
//
//        public String objectToJson(Object object) throws IOException {
//            return objectMapper.writeValueAsString(object);
//        }
//
//        public Object jsonToObject(InputStream inputStream, Class<?> clazz) throws IOException {
//            return objectMapper.readValue(inputStream, clazz);
//        }
//    }
//
//}
