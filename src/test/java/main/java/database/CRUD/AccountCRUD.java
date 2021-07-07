package main.java.database.CRUD;

import util.Generator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountCRUD {


    public static void setBalance(int clientId, double balance){
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_api?useSSL=false&useUnicode=true&serverTimezone=UTC",
                "root", "root")){
            String sql = "UPDATE bank_api.accounts SET balance = ? WHERE (accoint_id = ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, balance);
            statement.setInt(2, clientId);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Balance updated successfully!");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setBalance(1, 7000);
    }
}
