package br.com.rafaelfaustini.akumanomi.commands;

import br.com.rafaelfaustini.akumanomi.AkumaNoMi;
import br.com.rafaelfaustini.akumanomi.Esper;
import br.com.rafaelfaustini.akumanomi.controller.BountyController;
import br.com.rafaelfaustini.akumanomi.controller.PlayerController;
import br.com.rafaelfaustini.akumanomi.gui.TopGUI;
import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Arrays;
import java.util.List;

import static br.com.rafaelfaustini.akumanomi.utils.Utils.MessageText;

public class Bounty implements CommandExecutor, Listener {
    AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);
    private void getTop(Player player, String n){
        BountyController bountyControl = new BountyController();
        bountyControl.top(Integer.parseInt(n));
        player.sendMessage(String.format("TOP %s", n));
        for(BountyModel bounty : bountyControl.getBounties()){
            player.sendMessage(MessageText(bounty.toString()));
        }
        TopGUI gui = new TopGUI(9, bountyControl.getBounties());
        gui.openInventory(player);
    }
    private void setBounty(Player p, float amount){
        try{
            BountyController bountyControl = new BountyController();
            PlayerModel playerModel = new PlayerModel(p.getUniqueId().toString(), p.getDisplayName());
            BountyModel bounty = new BountyModel(playerModel, amount);
            bountyControl.getByUUID(p.getUniqueId());
            if(bountyControl.getBounty() != null){

                bountyControl.setBounty(bounty);
                bountyControl.update();
            } else {
                bountyControl.setBounty(bounty);
                bountyControl.insert();
            }
        } catch (Exception e){
            System.out.println(String.format("[AkumaNoMi] %s",e.getMessage()));
        } finally {
            p.sendMessage(MessageText(plugin.messagesConfig.getConfig().get("bounty.setSuccess").toString()));
        }

    }
    private void increaseBounty(Player assasin, Player victim){
        try {
            BountyController bountyControl = new BountyController();
            bountyControl.getByUUID(assasin.getUniqueId());
            BountyModel assasinBounty = bountyControl.getBounty();
            bountyControl.getByUUID(victim.getUniqueId());
            BountyModel victimBounty = bountyControl.getBounty();
            if(assasinBounty.getMoney() < 100000000f){
                Float bonus = (victimBounty.getMoney()*0.15f) + 1000f;
                assasinBounty.setMoney((assasinBounty.getMoney()) + bonus);
                bountyControl.setBounty(assasinBounty);
                bountyControl.update();
                assasin.sendMessage(bountyControl.getBounty().toString());
                assasin.sendMessage(String.format("Your bounty increased to %f",bonus));
                Float penality = (victimBounty.getMoney()*0.1f) + 1000f;
                if(victimBounty.getMoney() > 0) {
                    victimBounty.setMoney(assasinBounty.getMoney() - penality);
                    bountyControl.setBounty(victimBounty);
                    bountyControl.update();
                }
            }
        } catch (Exception e){
            System.out.println(String.format("[AkumaNoMi] %s",e.getMessage()));
        }
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);
        if(sender instanceof Player) {
            Player player = (Player) sender;

            if(args.length > 0){
                if(StringUtils.isNumeric(args[0])){
                    this.getTop(player, args[0]);
                    return true;
                }
                switch(args[0]){
                    case "set":
                        if(args.length > 1){
                            if(StringUtils.isNumeric(args[2])) {
                                setBounty(player, Float.parseFloat(args[2]));
                            }
                        } else {
                            player.sendMessage("/bounty set <player> <amount>");
                        }
                        return true;

                }
            } else {
            }
        }
        return false;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        PlayerController playerControl = new PlayerController();
        PlayerModel playerModel = new PlayerModel(p.getUniqueId().toString(), p.getDisplayName());
        playerControl.setPlayer(playerModel);
        if(playerControl.getByUUID() == null){
            playerControl.insert();
            BountyController bountyControl = new BountyController();
            BountyModel bounty = new BountyModel(playerModel, 0f);
            bountyControl.setBounty(bounty);
            bountyControl.insert();
        }
        if(!playerControl.isUpdated()){

            playerControl.update();
        }

    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent e){
        LivingEntity entity = e.getEntity();
        if(entity.getType() == EntityType.PLAYER){
            Player killed = (Player)entity;
           Player killer = entity.getKiller();
           if(killed != null && killer != null){
               increaseBounty(killer, killed);
               killed.sendMessage("You were killed");
               killer.sendMessage("You killed");
           }
        }
    }
}
