package br.com.rafaelfaustini.akumanomi.dao;

import br.com.rafaelfaustini.akumanomi.model.PlayerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerDAO {

    private Connection conexao;

    private void createTable(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS `player` (`uuid` TEXT PRIMARY KEY, `nickname` TEXT)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public PlayerDAO(Connection  _con){
        this.conexao = _con;
        this.createTable();
    }

    public PlayerModel getByUUID(UUID _uuid) throws SQLException{
            ResultSet rs = null;
            String uuidStr = _uuid.toString();
            String sql = "SELECT uuid, nickname FROM player where uuid=?";

            PreparedStatement ps = this.conexao.prepareStatement(sql);
            ps.setString(1, uuidStr);
            rs = ps.executeQuery();

            if(rs.next()){
                String uuid = rs.getString(1);
                String nickname = rs.getString(2);
                PlayerModel p = new PlayerModel(uuid, nickname);
                return p;
            }
        return null;
    }

    public void insert(PlayerModel p) throws SQLException{
        String sql = "insert into player (uuid, nickname) values (?, ?)";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ps.setString(1, p.getUUID().toString());
        ps.setString(2, p.getNickname());

        ps.execute();
    }

    public Boolean update(PlayerModel p) throws SQLException{
        boolean rs = false;

        String sql = "UPDATE player set nickname=? where uuid=?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ps.setString(1, p.getNickname());
        ps.setString(2, p.getUUID().toString());

        rs = ps.execute();

        return rs;
    }

}
