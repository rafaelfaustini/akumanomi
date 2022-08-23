package akumanomi.Listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import akumanomi.Event.WaterEnterEvent;

public class MoveListener implements Listener {
    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        checkPlayerOnWaterEvent(player);
    }

    private void checkPlayerOnWaterEvent(Player player) {
        Location playerLocation = player.getLocation();
        boolean isPlayerOnTopOfWater = isLocationOnTopOfWater(playerLocation);
        boolean isPlayerAtTheBottomOfWater = isLocationAtTheBottomOfWater(playerLocation);
        boolean isPlayerMiddleOfWater = isLocationMiddleOfWater(playerLocation);
        if(!isLocationOnWater(isPlayerOnTopOfWater, isPlayerAtTheBottomOfWater, isPlayerMiddleOfWater)) {
            return;
        }
        Bukkit.getServer().getPluginManager().callEvent(new WaterEnterEvent(player, isPlayerOnTopOfWater, isPlayerAtTheBottomOfWater, isPlayerMiddleOfWater));
    }


    private boolean isBlockWater(Block block) {
        return block.getType() == Material.WATER;
    }


    private Block getLocationTopBlock(Location location) {
        location = location.clone();
        Location topBlockLocation = location.add(0, 1, 0);
        Block topBlock = topBlockLocation.getBlock();
        return topBlock;
    }

    private Block getLocationBottomBlock(Location location) {
        location = location.clone();
        Location bottomBlockLocation = location.subtract(0, 1, 0);
        Block bottomBlock = bottomBlockLocation.getBlock();
        return bottomBlock;
    }

    private boolean isLocationTopBlockWater(Location location) {
        Block topBlock = getLocationTopBlock(location);
        return isBlockWater(topBlock);
    }

    private boolean isLocationBlockWater(Location location) {
        Block block = location.getBlock();
        return isBlockWater(block);
    }

    private boolean isLocationBottomBlockWater(Location location) {
        Block bottomBlock = getLocationBottomBlock(location);
        return isBlockWater(bottomBlock);
    }

    private boolean isLocationAtTheBottomOfWater(Location location){
        boolean isBottomBlockWater = isLocationBottomBlockWater(location);
        boolean isTopBlockWater = isLocationTopBlockWater(location);

        return !isBottomBlockWater && isTopBlockWater;
    }


    private boolean isLocationOnTopOfWater(Location location) {
        boolean isBlockWater = isLocationBlockWater(location);

        boolean isTopBlockWater = isLocationTopBlockWater(location);

        return isBlockWater && !isTopBlockWater; 
    }

    private boolean isLocationMiddleOfWater(Location location) {
        boolean isBottomBlockWater = isLocationBottomBlockWater(location);

        boolean isTopBlockWater = isLocationTopBlockWater(location);

        return isBottomBlockWater && isTopBlockWater;
    }

    private boolean isLocationOnWater(boolean isLocationAtTheBottomOfWater, boolean isLocationOnTopOfWater, boolean isLocationMiddleOfWater) {
        return isLocationAtTheBottomOfWater || isLocationOnTopOfWater || isLocationMiddleOfWater;
    }




}
