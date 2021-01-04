package br.com.rafaelfaustini.akumanomi;

import br.com.rafaelfaustini.akumanomi.commands.MeraMeraNoMi;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.Arrays;

public final class AkumaNoMi extends JavaPlugin{

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new Esper(), this);
        getServer().getPluginManager().registerEvents(new MeraMeraNoMi(), this);
        getCommand("MeraMeraNoMi").setExecutor(new MeraMeraNoMi());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
