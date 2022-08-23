package akumanomi.Helper;

import org.bukkit.ChatColor;

public class TextHelper {
    public static String coloredText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
