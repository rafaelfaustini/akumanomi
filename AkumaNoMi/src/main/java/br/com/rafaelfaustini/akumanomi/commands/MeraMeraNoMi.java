package br.com.rafaelfaustini.akumanomi.commands;

import br.com.rafaelfaustini.akumanomi.AkumaNoMi;
import br.com.rafaelfaustini.akumanomi.Esper;
import br.com.rafaelfaustini.akumanomi.utils.Debug;
import br.com.rafaelfaustini.akumanomi.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;


import static br.com.rafaelfaustini.akumanomi.utils.Utils.MessageText;


public class MeraMeraNoMi implements CommandExecutor, Listener {
    AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
                Player player = (Player) sender;
                if(sender instanceof Player) {
                    ItemStack fruit = new ItemStack(Material.BEETROOT);
                    ItemMeta fruitMeta = fruit.getItemMeta();
                    fruitMeta.setDisplayName(MessageText(plugin.messagesConfig.getConfig().get("meramera.name").toString()));
                    fruitMeta.setLore(Arrays.asList(ChatColor.translateAlternateColorCodes('&', plugin.messagesConfig.getConfig().get("meramera.description").toString())));
                    fruit.setItemMeta(fruitMeta);
                    player.getInventory().addItem(fruit);
                }
                return false;
    }

    private boolean isMeraMera(ItemStack item){
        try {
            String itemName = plugin.messagesConfig.getConfig().get("meramera.name").toString();
            boolean checkName =  ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', itemName)).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',ChatColor.stripColor(item.getItemMeta().getDisplayName())));
            boolean checkColor = item.getItemMeta().getDisplayName().length() == (itemName).length();

            if(item.getType() == Material.BEETROOT && checkName && checkColor){
                return true;
            }
        } catch (Exception e){
            Utils.TryException(e);
        }
        return false;
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        try {
            Player p = e.getPlayer();
            if(isMeraMera(e.getItem())){

                List<String> espers = plugin.espers.getConfig().getStringList("espers");
                if(!Esper.isEsper(p)) {
                    p.setFireTicks(50);
                    p.sendMessage(MessageText(plugin.messagesConfig.getConfig().get("meramera.onEat").toString()));
                    p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 100);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3));
                    Esper.setEsper(p);
                } else {
                    p.sendMessage(MessageText(plugin.messagesConfig.getConfig().getString("akumanomi.eatTwice")));
                    p.getWorld().createExplosion(p.getLocation(), 10);
                }
            }
        } catch (Exception ex){
            Utils.TryException(ex);
        }
    }
}
