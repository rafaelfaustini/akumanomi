package br.com.rafaelfaustini.akumanomi.utils;

import org.bukkit.ChatColor;

public class Utils {
    public static String MessageText(String str){
        if(str == null) return "";
        return ChatColor.translateAlternateColorCodes('&', str);
    }
}
