package br.com.rafaelfaustini.akumanomi.utils;

import org.bukkit.ChatColor;

public class Utils {

    public static String MessageText(String str, String... stringVariables){
        if(str == null) return "";
        for(String stringVar : stringVariables){
            str = str.replaceFirst("%%", stringVar);
        }
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public static void TryException(Exception e){
        System.out.println("[AkumaNoMi] There was an error loading the plugin");
        if(Debug.getEnabled()){
            System.out.println(String.format("[AkumaNoMi] %s",e.getMessage()));
            e.printStackTrace();
        }
    }
}
