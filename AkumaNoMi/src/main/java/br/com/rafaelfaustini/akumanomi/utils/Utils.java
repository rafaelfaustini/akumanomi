package br.com.rafaelfaustini.akumanomi.utils;

import org.bukkit.ChatColor;

import java.util.Random;

public class Utils {

    public static String MessageText(String str, String... stringVariables){
        if(str == null) return "";
        for(String stringVar : stringVariables){
            str = str.replaceFirst("%%", stringVar);
        }
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    public static boolean rollD(int number){
        Random r = new Random();
        int random = r.nextInt(number - 2) + 1;
        if(random == 5){
            return true;
        }
        return false;

    public static void TryException(Exception e){
        System.out.println("[AkumaNoMi] There was an error loading the plugin");
        if(Debug.getEnabled()){
            System.out.println(String.format("[AkumaNoMi] %s",e.getMessage()));
            e.printStackTrace();
        }
    }
}
