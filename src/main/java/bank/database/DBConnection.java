package bank.database;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.*;

public class DBConnection implements AutoCloseable {
    private Connection connection;

    private final String DB_URL = "jdbc:h2:tcp://localhost/~/test"; //private final String DB_URL = "jdbc:h2:mem:" ;
    private final String USER = "sa";
    private final String PASSWORD = "";
    private final String DB_Driver = "org.h2.Driver";

    private final String CLIENTS_RESOURCE_FILE = "src/main/resourses/clients.txt";
    private final String ACCOUNTS_RESOURCE_FILE = "src/main/resourses/bankAccounts.txt";
    private final String CARDS_RESOURCE_FILE = "src/main/resourses/cards.txt";

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
     * заполняет БД данными из файлов
     */
    public void dbInit() {
        try {
            clientsInit();
            accountsInit();
            cardsInit();
        } catch (SQLException | IOException e) {
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

    private void clientsInit() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(CLIENTS_RESOURCE_FILE));
        PreparedStatement statement = connection.prepareStatement("INSERT INTO CLIENT (ID, FIRSTNAME, LASTNAME) VALUES (?, ?, ?); ");
//        statement.setString(1,"12");
        while (reader.ready()) {
            String[] clientParams = reader.readLine().split(";");
            statement.setLong(1, Long.parseLong(clientParams[0]));
            statement.setString(2, clientParams[1]);
            statement.setString(3, clientParams[2]);
            statement.executeUpdate();
        }
    }

    private void accountsInit() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(ACCOUNTS_RESOURCE_FILE));
        PreparedStatement statement = connection.prepareStatement("INSERT INTO BANKACCOUNT (ACCOUNTNUMBER, CLIENT_ID) VALUES (?, ?);");
        while (reader.ready()) {
            String[] accountParams = reader.readLine().split(";");
            statement.setLong(1, Long.parseLong(accountParams[0]));
            statement.setLong(2, Long.parseLong(accountParams[1]));
            statement.executeUpdate();
        }
    }

    private void cardsInit() throws IOException, SQLException {
        BufferedReader reader = new BufferedReader(new FileReader(CARDS_RESOURCE_FILE));
        PreparedStatement statement = connection.prepareStatement("INSERT INTO CARD(ID, ACCOUNT_ID, BALANCE) VALUES (?, ?, ?);");
        while (reader.ready()) {
            String[] cardParams = reader.readLine().split(";");
            statement.setLong(1, Long.parseLong(cardParams[0]));
            statement.setLong(2,  Long.parseLong(cardParams[1]));
            statement.setDouble(2,  Double.parseDouble(cardParams[1]));
            statement.executeUpdate();
        }
    }

}
