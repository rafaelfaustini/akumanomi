package akumanomi.api.Internal;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.google.inject.Inject;

import akumanomi.AkumaNoMi;

public class ConnectionFactory {
    private AkumaNoMi plugin;

    @Inject
    public ConnectionFactory(AkumaNoMi plugin) {
        this.plugin = plugin;
    }


    public Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");

            File file = new File(plugin.getDataFolder(), "database.db");

            String URL = "jdbc:sqlite:" + file;
            Connection connection = DriverManager.getConnection(URL);
            connection.setAutoCommit(false);
            return connection;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            plugin.getPluginLoader().disablePlugin(plugin);
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            plugin.getPluginLoader().disablePlugin(plugin);
            return null;
        }
    }

}