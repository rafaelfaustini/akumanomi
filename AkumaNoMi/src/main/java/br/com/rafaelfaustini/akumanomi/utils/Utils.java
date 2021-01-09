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
}
