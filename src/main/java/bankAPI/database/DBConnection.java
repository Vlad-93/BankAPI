package bankAPI.database;

import java.io.*;
import java.sql.*;
import java.util.Scanner;

public class DBConnection implements AutoCloseable {
    private Connection connection;

    private final String DB_URL = "jdbc:h2:mem:" ;
    private final String USER = "sa";
    private final String PASSWORD = "";
    private final String DB_Driver = "org.h2.Driver";

    private final String CREATE_DB_SCRIPT = "src/main/resources/Scr.sql";

    /**
     * выполняется подключение к БД, хранящейся в памяти
     */
    public void createConnection() {
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * создает и заполняет БД данными из скрипта
     */
    public void dbInit() {
        try (Scanner sc = new Scanner(new File(CREATE_DB_SCRIPT))) {
            StringBuilder sb = new StringBuilder();

            while(sc.hasNext())
                sb.append(sc.nextLine());

            PreparedStatement statement = connection.prepareStatement(sb.toString());
            statement.execute();
        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * закрывает подключение к БД
     */
    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private static DBConnection dbConnection;

    public static DBConnection getInstance() {
        if (dbConnection == null)
            dbConnection = new DBConnection();

        return dbConnection;
    }

    private DBConnection() {}
}
