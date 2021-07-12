package bankAPI;

import bankAPI.database.DBConnection;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataBaseTest {

    @BeforeEach
    public void createDataBase() {
        DBConnection dbCon = DBConnection.getInstance();
        dbCon.createConnection();
        dbCon.dbInit();
    }

    @AfterEach
    public void close() {
        DBConnection dbCon = DBConnection.getInstance();
        dbCon.close();
    }

    @Test
    public void dbInitTest() throws SQLException {
        String query = "select * FROM Cards";
        DBConnection dbConnection = DBConnection.getInstance();
        ResultSet resultSet = dbConnection.getConnection().prepareStatement(query).executeQuery();

        resultSet.next();
        assertEquals(resultSet.getDouble(1), 1);
    }

}
