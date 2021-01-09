package br.com.rafaelfaustini.akumanomi.dao;

import br.com.rafaelfaustini.akumanomi.AkumaNoMi;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnection {

    private Connection con = null;

    public Connection openConnection(){
        AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);
        try {

            Class.forName("org.sqlite.JDBC");

            File file = new File(plugin.getDataFolder(), "database.db");

            String URL = "jdbc:sqlite:" + file;
            con = DriverManager.getConnection(URL);
        } catch (Exception e){
            System.out.println("[AkumaNoMi] There was an error connecting to the SQLITE database");
            plugin.getPluginLoader().disablePlugin(plugin);
        }
        return con;
    }

    public void close() throws SQLException {
        if(con != null){
                con.close();
                con = null;
        }
    }
}
