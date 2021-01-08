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
        } catch (Exception e){
            System.out.println("[AkumaNoMi] There was an error connecting to insert the bounty");

        }
    }
    public void update(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            dao.update(bounty);
        } catch (Exception e){
            System.out.println("[AkumaNoMi] There was an error connecting to update the bounty");

        }
    }
    public void getByUUID(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            BountyDAO dao = new BountyDAO(con);
            this.bounty = dao.getByUUID(bounty.getPlayer().getUUID());
        } catch (Exception e){
            System.out.println("[AkumaNoMi] There was an error connecting to retrieve the bounty");
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
        } catch (Exception e){
            System.out.println("[AkumaNoMi] There was an error to retrieve top");
        }
    }



}
