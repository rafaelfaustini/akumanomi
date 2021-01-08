package br.com.rafaelfaustini.akumanomi.dao;

import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BountyDAO {

    private Connection conexao;

    private void createTable(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS bounty ( uuid text, money real, FOREIGN KEY (uuid) references player(uuid) )";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public BountyDAO(Connection  _con){
        this.conexao = _con;
        this.createTable();
    }

    public List<BountyModel> top(int _n) throws SQLException{
        List<BountyModel> bounties = new ArrayList<BountyModel>();
            ResultSet rs = null;
            String sql = "SELECT uuid, money from bounty ORDER BY MONEY DESC LIMIT ?";
            PreparedStatement ps = conexao.prepareStatement(sql);

            ps.setInt(1, _n);
            rs = ps.executeQuery();

            while(rs.next()){
                String uuid = rs.getString(1);
                Float money = rs.getFloat(2);
                PlayerDAO dao = new PlayerDAO(conexao);
                PlayerModel p = dao.getByUUID(UUID.fromString(uuid));

                BountyModel b = new BountyModel(p, money);

                bounties.add(b);

            }
        return bounties;
    }

    public BountyModel getByUUID(UUID _uuid) throws SQLException{
        ResultSet rs = null;
        String uuidStr = _uuid.toString();
        String sql = "SELECT uuid, money FROM bounty where uuid=?";

        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setString(1, uuidStr);
        rs = ps.executeQuery();

        if(rs.next()){
            String uuid = rs.getString(1);
            Float money = rs.getFloat(2);
            PlayerDAO dao = new PlayerDAO(conexao);
            PlayerModel p = dao.getByUUID(UUID.fromString(uuid));
            BountyModel b = new BountyModel(p, money);
            return b;
        }
        return null;
    }


    public void insert(BountyModel b) throws SQLException{
        String sql = "insert into bounty (uuid, money) values (?, ?)";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ps.setString(1, b.getPlayer().getUUID().toString());
        ps.setFloat(2, b.getMoney());

        ps.execute();
    }

    public Boolean update(BountyModel b) throws SQLException{
        boolean rs = false;

        String sql = "UPDATE bounty set money=? where uuid=?";
        PreparedStatement ps = this.conexao.prepareStatement(sql);

        ps.setFloat(1, b.getMoney());
        ps.setString(2, b.getPlayer().getUUID().toString());

        rs = ps.execute();

        return rs;
    }


}
