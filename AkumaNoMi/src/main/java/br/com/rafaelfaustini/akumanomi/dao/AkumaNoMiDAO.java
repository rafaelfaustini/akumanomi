package br.com.rafaelfaustini.akumanomi.dao;
import br.com.rafaelfaustini.akumanomi.model.AkumaNoMiModel;
import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;
import br.com.rafaelfaustini.akumanomi.utils.Utils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class AkumaNoMiDAO {

    private Connection conexao;

    private void createTable(){
        try {
            String sql = "CREATE TABLE IF NOT EXISTS akumanomi (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT unique not null, item TEXT not null)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public void init(){
        this.createTable();
        this.insert("Mera Mera No Mi", "BEETROOT");
    }

    private void insert(String name, String item){
        try {
            String sql = "INSERT OR IGNORE INTO akumanomi (name, item) VALUES (?, ?)";
            PreparedStatement ps = conexao.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, item);

            ps.execute();
        } catch (SQLException e){
            Utils.TryException(e);
        }
    }

    public AkumaNoMiModel get(int _id) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT id, name, item FROM akumanomi where id=?";

        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setInt(1, _id);
        rs = ps.executeQuery();

        if(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String item = rs.getString(3);
            AkumaNoMiModel fruit = new AkumaNoMiModel(id, name, item);
            return fruit;
        }
        return null;
    }
    public AkumaNoMiModel get(String _name) throws SQLException{
        ResultSet rs = null;
        String sql = "SELECT id, name, item FROM akumanomi where name=?";

        PreparedStatement ps = this.conexao.prepareStatement(sql);
        ps.setString(1, _name);
        rs = ps.executeQuery();

        if(rs.next()){
            int id = rs.getInt(1);
            String name = rs.getString(2);
            String item = rs.getString(3);
            AkumaNoMiModel fruit = new AkumaNoMiModel(id, name, item);
            return fruit;
        }
        return null;
    }

    public AkumaNoMiDAO(Connection  _con){
        this.conexao = _con;
        this.createTable();
    }


}
