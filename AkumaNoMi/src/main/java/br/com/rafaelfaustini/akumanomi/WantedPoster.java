package br.com.rafaelfaustini.akumanomi;

import br.com.rafaelfaustini.akumanomi.controller.BountyController;
import br.com.rafaelfaustini.akumanomi.model.BountyModel;
import br.com.rafaelfaustini.akumanomi.utils.Utils;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.util.ArrayList;
import java.util.List;

import static br.com.rafaelfaustini.akumanomi.utils.Utils.MessageText;

public class WantedPoster implements Listener {
    private List<BountyModel> bounties = new ArrayList<BountyModel>();
    private AkumaNoMi plugin = AkumaNoMi.getPlugin(AkumaNoMi.class);

    private List<String> bountiesToBook(List<BountyModel> _bounties){
        List<String> list = new ArrayList<String>();
        try {
            for (BountyModel bounty:_bounties) {
                String title = MessageText(plugin.messagesConfig.getConfig().get("bounty.wantedPoster.title").toString());
                String subTitle = MessageText(plugin.messagesConfig.getConfig().get("bounty.wantedPoster.subTitle").toString());
                String wantedPlayerName = MessageText(plugin.messagesConfig.getConfig().get("bounty.wantedPoster.wantedPlayerName").toString(), bounty.getPlayer().getNickname().toString());
                String wantedReward = MessageText(plugin.messagesConfig.getConfig().get("bounty.wantedPoster.wantedReward").toString(), String.format("%.2f",bounty.getMoney()));
                list.add(String.format("\n%s\n\n%s\n\n%s\n\n%s", title, subTitle, wantedPlayerName, wantedReward));
            }
        } catch (Exception e) {
            Utils.TryException(e);
        }
        return list;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        try {
            Player p = e.getPlayer();
            boolean isRightClick = e.getAction() == Action.RIGHT_CLICK_AIR;

            ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta =  (BookMeta) book.getItemMeta();
            String bookTitle = MessageText(plugin.messagesConfig.getConfig().get("bounty.wantedPoster.bookTitle").toString());
            meta.setTitle(bookTitle);
            if(p.getInventory().getItemInMainHand().getType() == Material.WRITTEN_BOOK) {
                BookMeta currentMeta = (BookMeta) p.getInventory().getItemInMainHand().getItemMeta();
                boolean isWantedPoster = currentMeta.getTitle().equals(meta.getTitle());
                if (isRightClick && isWantedPoster) {
                    String bookAuthor = MessageText(plugin.messagesConfig.getConfig().get("bounty.wantedPoster.bookAuthor").toString());
                    meta.setAuthor(bookAuthor);
                    p.getInventory();
                    if (bounties.size() == 0) {
                        BountyController bountyController = new BountyController();
                        bountyController.get();
                        bounties = bountyController.getBounties();
                    }
                    List<String> pages = bountiesToBook(bounties);
                    meta.setPages(pages);
                    book.setItemMeta(meta);
                    p.openBook(book);
                    e.setCancelled(true);
                }
            }
        } catch (Exception ex){
            Utils.TryException(ex);
        }
    }

}
