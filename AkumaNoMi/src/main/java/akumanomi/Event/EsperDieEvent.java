package akumanomi.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;


public class EsperDieEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;


    public EsperDieEvent(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers; 
    }

     public static HandlerList getHandlerList() {
        return handlers;
     }

}