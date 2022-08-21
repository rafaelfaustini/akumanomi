package akumanomi.api.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import akumanomi.api.Repository.VO.FruitVO;

public class FruitRepository {
    public void createFruit(Connection connection, FruitVO fruit) throws SQLException {
        String sql = "INSERT INTO FRUIT(FRUIT_TYPE_ID, KEY) VALUES(?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, fruit.getTypeID());
        preparedStatement.setString(2, fruit.getKey());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public int getFruitTypeByKey(Connection connection, String key) throws SQLException {
        String sql = "SELECT FRUIT_TYPE_ID FROM FRUIT WHERE KEY = ? AND ACTIVE=1;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        int fruitTypeID = 0;
        if(rs.next()) {
            fruitTypeID = rs.getInt("FRUIT_TYPE_ID");
        }
        preparedStatement.close();
        return fruitTypeID;
    }

    public int getFruitIdByKey(Connection connection, String key) throws SQLException {
        String sql = "SELECT ID FROM FRUIT WHERE KEY = ? AND ACTIVE=1;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, key);
        ResultSet rs = preparedStatement.executeQuery();
        int fruitID = 0;
        if(rs.next()) {
            fruitID = rs.getInt("ID");
        }
        preparedStatement.close();
        return fruitID;
    }

    public void deactivateFruitByKey(Connection connection, String key) throws SQLException {
        String sql = "UPDATE FRUIT SET ACTIVE=0, INACTIVATION_DATE=CURRENT_TIMESTAMP WHERE KEY=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, key);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

    public void deactivateFruitByType(Connection connection, int fruitTypeID) throws SQLException {
        String sql = "UPDATE FRUIT SET ACTIVE=0, INACTIVATION_DATE=CURRENT_TIMESTAMP WHERE INACTIVATION_DATE IS NULL AND FRUIT_TYPE_ID=?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, fruitTypeID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }

}
