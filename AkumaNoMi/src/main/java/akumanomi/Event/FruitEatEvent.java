package akumanomi.Event;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;


public class FruitEatEvent extends Event {

    private static final HandlerList handlers = new HandlerList();

    private Player player;
    private int fruitTypeID;
    private String fruitKey;


    public FruitEatEvent(Player player, int fruitTypeID, String fruitKey) {
        this.player = player;
        this.fruitTypeID = fruitTypeID;
        this.fruitKey = fruitKey;
    }
    

    public int getFruitTypeID() {
        return fruitTypeID;
    }


    public String getFruitKey() {
        return fruitKey;
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