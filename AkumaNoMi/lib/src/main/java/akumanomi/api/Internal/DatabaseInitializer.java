package akumanomi.api.Internal;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.google.inject.Inject;

import akumanomi.Helper.LoggerHelper;

public class DatabaseInitializer {

    private ConnectionFactory connectionFactory;

    @Inject
    public DatabaseInitializer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    private void createTableFruitType(Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS FRUIT_TYPE " +
                     "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                     " NAME           TEXT    NOT NULL)"; 
        stmt.executeUpdate(sql);    
        stmt.close();   
    }

    private void createTableFruit(Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS FRUIT " +
                     "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "KEY STRING     NOT NULL UNIQUE," +
                     "ACTIVE BIT     NOT NULL DEFAULT 1," +
                     "INACTIVATION_DATE TIMESTAMP," +
                     "FRUIT_TYPE_ID           INT    NOT NULL," +
                     "FOREIGN KEY(FRUIT_TYPE_ID) REFERENCES FRUIT_TYPE(ID))";
        stmt.executeUpdate(sql);    
        stmt.close();   
    }

    private void createTableEsper(Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();
        String sql = "CREATE TABLE IF NOT EXISTS ESPER " +
                     "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "PLAYER STRING     NOT NULL," +
                     "FRUIT_ID INTEGER     NOT NULL," +
                     "START_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                     "END_DATE TIMESTAMP DEFAULT NULL," +
                     " FOREIGN KEY(FRUIT_ID) REFERENCES FRUIT_TYPE(ID))"; 
        stmt.executeUpdate(sql);    
        stmt.close();   
    }

    private void populateFruitType(Connection conn) throws SQLException {
        Statement stmt = null;
        stmt = conn.createStatement();

        String  sql = "INSERT OR IGNORE INTO FRUIT_TYPE (ID,NAME) " +
            "VALUES (1, 'Mera Mera No Mi');"; 
        stmt.executeUpdate(sql);

        stmt.close();       
    }

    public void init() {
        try (Connection conn = connectionFactory.getConnection()) {
            try {
                createTableFruitType(conn);
                populateFruitType(conn);
                createTableFruit(conn);
                createTableEsper(conn);
                
            } catch (Exception e) {
                conn.rollback();
                LoggerHelper.error(e.getMessage());
            } finally {
                conn.commit();
            }
        } catch(Exception e) {
            
        }
    }
}
