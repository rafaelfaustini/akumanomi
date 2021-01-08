package br.com.rafaelfaustini.akumanomi.controller;

import br.com.rafaelfaustini.akumanomi.dao.BountyDAO;
import br.com.rafaelfaustini.akumanomi.dao.SqliteConnection;
import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;


import java.sql.Connection;
import java.util.List;
import java.util.UUID;

public class BountyController {
    private BountyModel bounty;
    private List<BountyModel> bounties;

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
            System.out.println("[AkumaNoMi] There was an error to insert the bounty");
            System.out.println(e.getMessage());

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
            System.out.println("[AkumaNoMi] There was an error to update the bounty");
            System.out.println(e.getMessage());
        }
    }
    public void getByUUID(UUID uuid){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            this.bounty = dao.getByUUID(uuid);
            con.close();
        } catch (Exception e){
            System.out.println("[AkumaNoMi] There was an error to retrieve the bounty");
            System.out.println(e.getMessage());

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
            System.out.println("[AkumaNoMi] There was an error to retrieve top");
            System.out.println(e.getMessage());

        }
    }



}
