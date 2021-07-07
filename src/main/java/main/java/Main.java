package main.java;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import static controller.Server.startServer;
import static service.DBConnector.*;


public class Main
{

    public static void main(String[] args) throws IOException, SQLException {
        createConnection();
        dbInit();
        startServer();

    }}
//
//            JdbcConnectionPool cp = JdbcConnectionPool.create(
//                    "jdbc:h2:~/test", "sa", "sa");
//            for (int i = 0; i < args.length; i++) {
//                Connection conn = cp.getConnection();
//                conn.createStatement().execute(args[i]);
//                conn.close();
//            }
//            cp.dispose();
//        }  }



