package main.java.DAO;

import model.Client;

public interface DAO {

    Client Authenticate(String login, String password) throws Exception;


}
