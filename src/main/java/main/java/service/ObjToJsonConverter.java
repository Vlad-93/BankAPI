package main.java.service;


//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Card;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс, отвечающий за сериализацию  объектов в json и обратно
 */
public class ObjToJsonConverter {
    /**
     * @param list список с объектами, которые подлежат сериализации
     * @return строка формата json с сериализованными объектами
     */
    public static String convertListToJsonString(List<Object> list) {
        if (list.size() == 0)
            return null;
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();
        try {
            mapper.writeValue(writer, list);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    /**
     * @param map SingletonMap в формате ("sum" = 00.00)
     * @return json строка
     */
    public static String convertCardBalanceToJson(Map<String, BigDecimal> map) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();

        try {
            mapper.writeValue(writer, map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }

    /**
     * @param jsonString json строка
     * @return карта
     */
    public static Map<String, String> covertJsonToMap (String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, String> map = new HashMap<>();

        try {
            map = mapper.readValue(jsonString, new TypeReference<Map<String, String>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * Запись информации по одной карте в json
     * @param card - экзамепляр класса
     * @return строка в формате json
     */
    public static String convertCardToJson(Card card) {
        ObjectMapper mapper = new ObjectMapper();
        StringWriter writer = new StringWriter();

        try {
            mapper.writeValue(writer, card);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toString();
    }


}
