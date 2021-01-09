package br.com.rafaelfaustini.akumanomi.controller;

import br.com.rafaelfaustini.akumanomi.dao.PlayerDAO;
import br.com.rafaelfaustini.akumanomi.dao.SqliteConnection;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;
import br.com.rafaelfaustini.akumanomi.utils.Debug;
import br.com.rafaelfaustini.akumanomi.utils.Utils;

import java.sql.Connection;
import java.util.UUID;

public class PlayerController {
    private PlayerModel player;

    public PlayerController(){
        this.player = new PlayerModel(UUID.randomUUID().toString(), "");
    }

    public PlayerModel getPlayer(){
        return player;
    }

    public void setPlayer(PlayerModel _player){
        this.player = _player;
    }

    public void insert(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            PlayerDAO dao = new PlayerDAO(con);
            dao.insert(player);
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
    public void update(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            PlayerDAO dao = new PlayerDAO(con);
            dao.update(player);
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
    public PlayerModel getByUUID(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            PlayerDAO dao = new PlayerDAO(con);
            PlayerModel result = dao.getByUUID(player.getUUID());
            con.close();
            return result;
        } catch (Exception e){
            Utils.TryException(e);
        }
        return null;
    }
    public boolean isPlayer(String name){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            PlayerDAO dao = new PlayerDAO(con);
            Boolean result = dao.isPlayer(name);
            con.close();
            return result;
        } catch (Exception e){
            Utils.TryException(e);
        }
        return false;
    }
    public boolean isUpdated(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            PlayerDAO dao = new PlayerDAO(con);
            PlayerModel obj = dao.getByUUID(player.getUUID());
            con.close();
            return player.equals(obj);
        } catch (Exception ex){
            Utils.TryException(ex);
        }
        return false;
    }
}
