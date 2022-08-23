package akumanomi.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;


public class WaterEnterEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private boolean isPlayerOnTopOfWater;
    private boolean isPlayerAtTheBottomOfWater;
    private boolean isPlayerAtTheMiddleOfWater;


    public WaterEnterEvent(Player player, boolean isPlayerOnTopOfWater, boolean isPlayerAtTheBottomOfWater,
            boolean isPlayerAtTheMiddleOfWater) {
        this.player = player;
        this.isPlayerOnTopOfWater = isPlayerOnTopOfWater;
        this.isPlayerAtTheBottomOfWater = isPlayerAtTheBottomOfWater;
        this.isPlayerAtTheMiddleOfWater = isPlayerAtTheMiddleOfWater;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isPlayerOnTopOfWater() {
        return isPlayerOnTopOfWater;
    }

    public boolean isPlayerAtTheBottomOfWater() {
        return isPlayerAtTheBottomOfWater;
    }

    public boolean isPlayerAtTheMiddleOfWater() {
        return isPlayerAtTheMiddleOfWater;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers; 
    }

     public static HandlerList getHandlerList() {
        return handlers;
     }

}