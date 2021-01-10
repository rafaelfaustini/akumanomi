package br.com.rafaelfaustini.akumanomi.controller;

import br.com.rafaelfaustini.akumanomi.AkumaNoMi;
import br.com.rafaelfaustini.akumanomi.dao.AkumaNoMiDAO;
import br.com.rafaelfaustini.akumanomi.dao.BountyDAO;
import br.com.rafaelfaustini.akumanomi.dao.SqliteConnection;
import br.com.rafaelfaustini.akumanomi.model.AkumaNoMiModel;
import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;
import br.com.rafaelfaustini.akumanomi.utils.Utils;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AkumaNoMiController {
    private AkumaNoMiModel fruit;
    public void init(){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            AkumaNoMiDAO dao = new AkumaNoMiDAO(con);
            dao.init();
            con.close();
        } catch (Exception e){
            Utils.TryException(e);
        }
    }

    public AkumaNoMiModel getFruit() {
        return fruit;
    }

    public void get(String name){
        try {
            SqliteConnection sqliteconnection = new SqliteConnection();
            Connection con = sqliteconnection.openConnection();
            AkumaNoMiDAO dao = new AkumaNoMiDAO(con);
            fruit = dao.get(name);
            con.close();
        } catch (Exception e){
            Utils.TryException(e);
        }
    }

}
