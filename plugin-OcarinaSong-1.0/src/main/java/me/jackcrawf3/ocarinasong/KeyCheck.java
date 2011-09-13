
package me.jackcrawf3.ocarinasong;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 *
 * @author Jackcrawf3
 */
public class KeyCheck implements Runnable {
    Player player;
    Location initialLocation;
    OcarinaSong plugin;
    public Server server;
    
    public KeyCheck(Player thisplayer,OcarinaSong plugin){
     this.plugin = plugin;
     player = thisplayer;
     initialLocation = player.getLocation().clone();
    }
        
    public void run() {
            Location currentLocation;
           currentLocation = player.getLocation();
           
       if (initialLocation.getBlock().getRelative(BlockFace.DOWN).getType()!=Material.NOTE_BLOCK){
            plugin.StopOcarina(player);
            return;
        }
           
           
        if (player.getLocation().getX()==initialLocation.getX()){
            if (player.getLocation().getZ()==initialLocation.getZ()){
                    initialLocation.setYaw(player.getLocation().getYaw());
                    initialLocation.setPitch(player.getLocation().getPitch());
                    
                    
                    
           
                    
                    return;
        }}

        //Score_Under's bosslike algorithm
        double angle = Math.atan2(-(currentLocation.getX() - initialLocation.getX()),currentLocation.getZ() - initialLocation.getZ())*180.0f/Math.PI;
        int facing = (int) Math.round(((angle - player.getLocation().getYaw()) / 45.0f) % 8.0f);
        if(facing<0)facing+=8;
        
        player.teleport(initialLocation); //Stand them on the noteblock again

        switch (facing) {
            case 0:
                plugin.hitUp(player,initialLocation.getBlock().getRelative(BlockFace.DOWN).getLocation());
                break;
            case 2:
                plugin.hitRight(player,initialLocation.getBlock().getRelative(BlockFace.DOWN).getLocation());
                break;
            case 4:
                plugin.hitDown(player,initialLocation.getBlock().getRelative(BlockFace.DOWN).getLocation());
                break;
            case 6:
                plugin.hitLeft(player,initialLocation.getBlock().getRelative(BlockFace.DOWN).getLocation());
                break;
        }

        return;
        
        
        
        
        
        
                 
                    }
            }
    
    
   
        
    

