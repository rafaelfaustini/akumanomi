package br.com.rafaelfaustini.akumanomi.commands;

import br.com.rafaelfaustini.akumanomi.AkumaNoMi;
import br.com.rafaelfaustini.akumanomi.Esper;
import br.com.rafaelfaustini.akumanomi.controller.AkumaNoMiController;
import br.com.rafaelfaustini.akumanomi.controller.PlayerController;
import br.com.rafaelfaustini.akumanomi.model.AkumaNoMiModel;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;
import br.com.rafaelfaustini.akumanomi.utils.Debug;
import br.com.rafaelfaustini.akumanomi.utils.Utils;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.*;
import java.lang.*;


import static br.com.rafaelfaustini.akumanomi.utils.Utils.MessageText;
import static br.com.rafaelfaustini.akumanomi.utils.Utils.rollD;


public class MeraMeraNoMi implements CommandExecutor, Listener {
    private AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);
    private final String name = "Mera Mera No Mi";
    HashMap<UUID, Long> expired = new HashMap<UUID, Long>();
    List<String> skills = new ArrayList<String>();
    HashMap<UUID, Integer> currentSkill = new HashMap<UUID, Integer>();
    public MeraMeraNoMi(){
        try {
           skills = plugin.messagesConfig.getConfig().getStringList("meramera.skills");
        } catch (Exception e){
            Utils.TryException(e);
        }
    }
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

    public boolean isMeraMera(Player p){
        PlayerController playerControl = new PlayerController();
        PlayerModel player = playerControl.getByUUID(p.getUniqueId());
        boolean isMera = player.isEsper();
        if(isMera){
         isMera = player.getFruit().getName().equals(name);
        }
        return isMera;
    }

    private boolean isMeraMeraFruit(ItemStack item){
        try {
            String itemName = plugin.messagesConfig.getConfig().get("meramera.name").toString();
            boolean checkName =  ChatColor.stripColor(ChatColor.translateAlternateColorCodes('&', itemName)).equalsIgnoreCase(ChatColor.translateAlternateColorCodes('&',ChatColor.stripColor(item.getItemMeta().getDisplayName())));
            boolean checkColor = item.getItemMeta().getDisplayName().length() == (itemName).length();

            AkumaNoMiController akumanomiControl = new AkumaNoMiController();
            akumanomiControl.get(name);


            if(item.getType() == akumanomiControl.getFruit().getItem() && checkName && checkColor){
                return true;
            }
        } catch (Exception e){
            Utils.TryException(e);
        }
        return false;
    }

    private void set(Player player){
        PlayerController playerControl = new PlayerController();
        playerControl.setEsper(player.getUniqueId(), name);
    }

    private void passiveBuff(Player player){
        player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3));
    }

    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent e) {
        if(e.getEntity().getType() == EntityType.PLAYER){
            Player p = (Player) e.getEntity();
            if(isMeraMera(p)) {
                if(rollD(10)){
                    e.setCancelled(true); // Rolls D10
                    p.spawnParticle(Particle.FLAME, p.getLocation(), 5000);
                }
            }
        }
    }

    @EventHandler
    public void onItemConsume(PlayerItemConsumeEvent e) {
        try {
        Player p = e.getPlayer();
        if(isMeraMeraFruit(e.getItem())){
            PlayerController playerControl = new PlayerController();
            PlayerModel player = playerControl.getByUUID(p.getUniqueId());
            p.sendMessage(Boolean.toString(player.isEsper()));
            if(!player.isEsper()) {
                p.setFireTicks(50);
                p.getWorld().spawnParticle(Particle.SMOKE_LARGE, p.getLocation(), 100);
                p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 3));
                this.set(p);
                p.spawnParticle(Particle.FLAME, p.getLocation(), 500);
            } else {
                p.sendMessage(MessageText(plugin.messagesConfig.getConfig().getString("akumanomi.eatTwice")));
                p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 5));
                p.getWorld().createExplosion(p.getLocation(), 10);
            }
        }
        } catch (Exception ex){
            Utils.TryException(ex);
        }
    }

    private void fireBlastJump(Player p){
        p.spawnParticle(Particle.CRIT_MAGIC,p.getLocation(), 1000, .2, .2, .2);
        p.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 5));
        Vector direction = p.getLocation().getDirection();
        p.setVelocity(direction.multiply(2));
        p.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 100, 9));
        p.getWorld().createExplosion(p.getLocation(), 2);
        p.spawnParticle(Particle.LAVA,p.getLocation(), 1000, .2, .2, .2);

    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){
        try {
            Player p = e.getPlayer();
            Action action = e.getAction();
            Material powerItem = Material.BLAZE_ROD;

            if(p.getInventory().getItemInMainHand().getType().equals(powerItem)) {
                switch(action){
                    case RIGHT_CLICK_AIR:
                    case RIGHT_CLICK_BLOCK:
                        if(currentSkill.get(p.getUniqueId()) != null){
                            p.sendMessage(MessageText(skills.get((currentSkill.get(p.getUniqueId()) + 1) % skills.size())));
                            currentSkill.put(p.getUniqueId(), (currentSkill.get(p.getUniqueId()) + 1) % skills.size());
                        } else {
                            p.sendMessage(MessageText(skills.get(skills.size()-1)));
                            currentSkill.put(p.getUniqueId(), skills.size()-1);
                        }
                        break;
                    case LEFT_CLICK_AIR:
                    case LEFT_CLICK_BLOCK:
                        switch(currentSkill.get(p.getUniqueId())){
                            case 0:
                                break;
                            case 1:
                                if (expired.containsKey(p.getUniqueId()) && System.currentTimeMillis() < expired.get(p.getUniqueId())) {
                                    p.sendMessage(String.format(MessageText(plugin.messagesConfig.getConfig().get("akumanomi.cooldown").toString(),
                                            "%d"), (expired.get(p.getUniqueId()) - System.currentTimeMillis()) / 1000));
                                } else {
                                    if (isMeraMera(p)) {
                                        p.sendMessage(MessageText(plugin.messagesConfig.getConfig().get("meramera.onFireBlastJump").toString()));
                                        fireBlastJump(p);
                                        expired.put(p.getUniqueId(), System.currentTimeMillis() + 5000);
                                    }
                                }
                                break;
                        }
                }
            }
        } catch (Exception ex){
            Utils.TryException(ex);
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if(isMeraMera(p)){
            passiveBuff(p);
        }
    }

    @EventHandler
    public void onPlayerRespawnEvent(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        if(isMeraMera(p)){
            Bukkit.getScheduler().runTaskLater(plugin, task -> {
                passiveBuff(p);
            }, 5L);
        }
    }
}
