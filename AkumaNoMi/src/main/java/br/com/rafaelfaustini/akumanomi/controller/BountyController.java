package br.com.rafaelfaustini.akumanomi.controller;

import br.com.rafaelfaustini.akumanomi.dao.BountyDAO;
import br.com.rafaelfaustini.akumanomi.dao.SqliteConnection;
import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;
import br.com.rafaelfaustini.akumanomi.utils.Debug;
import br.com.rafaelfaustini.akumanomi.utils.Utils;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BountyController {
    private BountyModel bounty;
    private List<BountyModel> bounties = new ArrayList<BountyModel>();

    public BountyController(){

        this.bounty = new BountyModel(new PlayerModel(UUID.randomUUID().toString(), ""), 0f);
    }

    public BountyModel getBounty(){
        return bounty;
    }

    public List<BountyModel> getBounties(){
        return bounties;
    }

    public void setBounty(BountyModel _bounty){
        this.bounty = _bounty;
    }

    public void insert(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            dao.insert(bounty);
            con.close();
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
    public void update(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            dao.update(bounty);
            con.close();
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
    public void update(String name, Float money){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            dao.update(name, money);
            con.close();
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
    public void get(UUID uuid){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            this.bounty = dao.get(uuid);
            con.close();
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
    public void get(String name){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);

            this.bounty = dao.get(name);
            con.close();
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
    public void get(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            this.bounties = dao.get();
            con.close();
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
    public void top(int n){
        if(n > 50){
            n = 50;
        }
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            this.bounties = dao.top(n);
            con.close();
        } catch (Exception e){
            Utils.TryException(e);

        }
    }



}
