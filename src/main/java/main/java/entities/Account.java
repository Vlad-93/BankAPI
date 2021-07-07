package main.java.entities;

import com.fasterxml.jackson.annotation.JsonAutoDetect;


import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static service.DBConnector.con;
import static service.DBConnector.rs;


@JsonAutoDetect
public class Account  {
    private final long id;
    private final String number;
    private final BigDecimal balance;
    private List<Card> cards;

    /**
     * Констуктор объекта "счет клиента
     * @param id уникальный идектификатор
     * @param number уникальный номер счёта
     * @param balance баланс в рублях
     */
    public Account(long id, String number, BigDecimal balance) {
        this.id = id;
        this.number = number;
        this.balance = balance;
    }

    /**
     * Геттеры
     */
    public long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    /**
     * @return список карт, прикрепелнных к этому счёту
     */

    public List<Card> getCards() {
        cards = new ArrayList<>();
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM CARD WHERE ACCOUNT_ID = ?");
            statement.setLong(1, this.id);
            rs = statement.executeQuery();
            while (rs.next()) {
                int cardId = rs.getInt("ID");
                String cardNumber = rs.getString("NUMBER");
                cards.add(new Card(cardId, cardNumber));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return cards;
    }

    /**
     * Проверяет, существует счёт с таким id в базе
     * @param id счета, наличие которого нужно проверить
     * @return true, если такой сч>т найден, иначе false
     */
    public static boolean ifExists(long id){
        try (PreparedStatement statement = con.prepareStatement("SELECT * FROM ACCOUNT WHERE ID = ?")) {
            statement.setLong(1, id);
            rs = statement.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return false;
        }
    }

    /**
     * Возвращает объект класса, найденный в базе по уникальному номеру
     * @param num номер аккаунта для поиска в базе
     * @return экзамепляр объекта с номером num
     */
    public static Account getAccount(String num) {
        Account account = null;
        try {
            PreparedStatement statement = con.prepareStatement("SELECT * FROM ACCOUNT WHERE NUMBER = ?");
            statement.setString(1, num);
            rs = statement.executeQuery();
            while (rs.next()) {
                int accId = rs.getInt("ID");
                String accNumber = rs.getString("NUMBER");
                BigDecimal balance = rs.getBigDecimal("BALANCE");
                account = new Account(accId, num, balance);
            }
            return account;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }
    }

    /**
     * Переопределение методов по умолчанию
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return getNumber().equals(account.getNumber()) && getBalance().equals(account.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getBalance());
    }

    @Override
    public String toString() {
        return "AccountImpl{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", balance=" + balance +
                ", cards=" + cards +
                '}';
    }
}