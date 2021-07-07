package main.java.services;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.BankCard;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Service {

    public static String getListOfCardsById(int clientId){
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_api?useSSL=false&useUnicode=true&serverTimezone=UTC",
                "root", "root")){
            String sql = "SELECT * FROM bank_api.bank_cards WHERE cardholder_id = '" + clientId + "' ";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            System.out.println("SQL = " + sql);
            ArrayList<BankCard> list = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id");
                long number = rs.getLong("number");
                int cardHolderId = rs.getInt("cardholder_id");

                BankCard card = new BankCard(id, number, cardHolderId);
                list.add(card);
            }

            for (BankCard card : list)
                System.out.println(card);
            return writeListToJsonArray(list);
        } catch(SQLException | IOException e){
            e.printStackTrace();
        }
        return "";
    }

    public static String writeListToJsonArray(List list) throws IOException {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(out, list);
        final byte[] data = out.toByteArray();
        return new String(data);
    }

    public static void main(String[] args) {
        String s = getListOfCardsById(1);
        System.out.println(s);
    }

}
