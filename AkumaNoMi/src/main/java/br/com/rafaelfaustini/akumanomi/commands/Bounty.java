package br.com.rafaelfaustini.akumanomi.commands;

import br.com.rafaelfaustini.akumanomi.AkumaNoMi;
import br.com.rafaelfaustini.akumanomi.Esper;
import br.com.rafaelfaustini.akumanomi.controller.BountyController;
import br.com.rafaelfaustini.akumanomi.controller.PlayerController;
import br.com.rafaelfaustini.akumanomi.gui.TopGUI;
import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.model.PlayerModel;
import org.apache.commons.lang.StringUtils;
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
        }
        if(!playerControl.isUpdated()){

            playerControl.update();
        }

    }
}
