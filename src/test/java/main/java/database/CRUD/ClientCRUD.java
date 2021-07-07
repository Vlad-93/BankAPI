package main.java.database.CRUD;

import model.Client;

import java.sql.*;

public class ClientCRUD {



    // добавить создание счёта
    public static void create(String firstName, String secondName, String login, String password) {
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_api?useSSL=false&useUnicode=true&serverTimezone=UTC",
                "root", "root")){
            String sql = "INSERT INTO `bank_api`.`clients` " +
                    "(`first_name`, `second_name`, `login`, `password`) VALUES (?, ?, ?, ?);";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, firstName);
            statement.setString(2, secondName);
            statement.setString(3, login);
            statement.setString(4, password);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new user was inserted successfully!");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static int getIdByLoginAndPassword(String login, String password){
        try(Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/bank_api?useSSL=false&useUnicode=true&serverTimezone=UTC",
                "root", "root")){
            String sql = "SELECT * FROM bank_api.clients WHERE " +
                    "login = '" + login + "' AND password = '" + password + "' ";
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()) {
                return Integer.parseInt(result.getString("id"));
            } else
                throw new Exception(" ");

        } catch(Exception e){
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(ClientCRUD.getIdByLoginAndPassword("1", "1"));
    }

}
