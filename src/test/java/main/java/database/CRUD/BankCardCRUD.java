package main.java.database.CRUD;

import model.Client;
import util.Generator;

import java.sql.*;

public class BankCardCRUD {


    public static void create(int clientId){
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_api?useSSL=false&useUnicode=true&serverTimezone=UTC",
                "root", "root")){
            String sql = "INSERT INTO bank_api.bank_cards " +
                    "( `number`, `cardholder_id`) VALUES (?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            long cardNumber = Generator.getNextCardNumber();
            statement.setLong(1, cardNumber);
            statement.setInt(2, clientId);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new card " + cardNumber + " was created successfully!");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

}
