package akumanomi.api.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import akumanomi.api.Repository.VO.EsperVO;

public class EsperRepository {

    public void createEsper(Connection connection, EsperVO esper) throws SQLException {
        String sql = "INSERT INTO ESPER(PLAYER, FRUIT_ID, START_DATE) VALUES(?, ?, CURRENT_TIMESTAMP)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, esper.getPlayer());
        preparedStatement.setInt(2, esper.getFruitId());
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public void removeEsper(Connection connection, String playerID) throws SQLException {
        String sql = "UPDATE ESPER SET END_DATE=CURRENT_TIMESTAMP WHERE PLAYER=? AND END_DATE IS NULL";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, playerID);
        preparedStatement.executeUpdate();
        preparedStatement.close();
    }
    public boolean checkEsper(Connection connection, String playerID) throws SQLException{
        String sql = "SELECT 1 FROM ESPER WHERE PLAYER=? AND END_DATE IS NULL;";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, playerID);
        ResultSet rs = preparedStatement.executeQuery();
        boolean isEsper = false;
        if(rs.next() == true) {
            isEsper = true;
        }
        preparedStatement.close();
        return isEsper;
    }
}
