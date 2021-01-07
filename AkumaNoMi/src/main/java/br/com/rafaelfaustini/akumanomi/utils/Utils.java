package br.com.rafaelfaustini.akumanomi.utils;

import org.bukkit.ChatColor;

import java.util.Random;

public class Utils {
    public static String MessageText(String str){
        if(str == null) return "";
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    public static boolean rollD(int number){
        Random r = new Random();
        int random = r.nextInt(number - 2) + 1;
        if(random == 5){
            return true;
        }
        return false;
    }
}
