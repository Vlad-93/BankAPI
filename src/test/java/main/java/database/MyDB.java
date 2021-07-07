package main.java.database;

import DAO.DAO;
import model.Client;
import java.sql.*;

public class MyDB implements DAO {
    Connection conn;

    public MyDB() {
        String url = "jdbc:mysql://localhost:3306/bank_api?useSSL=false&useUnicode=true&serverTimezone=UTC";
        String username = "root";
        String password = "root";
        System.out.println("Connecting to DB...");

        try {
            conn = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to DB!");
        } catch (SQLException e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }
    }


    public Client Authenticate(String login, String password) throws Exception {
        String sql = "SELECT * FROM bank_api.clients WHERE " +
                "login = '" + login + "' AND password = '" + password + "' ";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);

        if (result.next()) {
            return new Client(Integer.parseInt(result.getString("id")), result.getString("first_name"),
                    result.getString("second_name"), login, password);
        } else
            throw new Exception("Неверный логин или пароль");
    }

  /*  public void setMessage() throws SQLException {
        String sql = "INSERT INTO users.chatroom_1_9 ( user_id, message) VALUES ( ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        //statement.setString(1, "bill");
        statement.setString(1, "1");
        statement.setString(2, "Bill Gates");
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("A new user was inserted successfully!");
        }
    }

    public int getIdByUser(User user) throws Exception {
        String sql = "SELECT * FROM users.users WHERE " +
                "first_name = '" + user.getFirstName() + "' AND second_name = '" + user.getSecondName() + "' ";
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        System.out.println("SQL = " + sql);
        if (result.next()) {
            return Integer.parseInt(result.getString("id"));
        } else
            throw new Exception(" ");
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM users.users WHERE id = " + id;
        Statement statement = conn.createStatement();
        ResultSet result = statement.executeQuery(sql);
        if (result.next()) {
            return new User(result.getString("first_name"), result.getString("second_name"));
        } else
            return null;
    }*/


    public static void main(String[] args) throws Exception {
        new MyDB();
    }
}
