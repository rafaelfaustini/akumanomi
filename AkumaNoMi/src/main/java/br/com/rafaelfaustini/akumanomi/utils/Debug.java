package br.com.rafaelfaustini.akumanomi.utils;

import org.bukkit.configuration.Configuration;

public class Debug {
    private static Boolean enabled = false;

    public static void initialize(Configuration conf) {
        if(conf == null) enabled = true;
        enabled = conf.getBoolean("debug");
    }

    public static Boolean getEnabled() {
        return enabled;
    }
}
