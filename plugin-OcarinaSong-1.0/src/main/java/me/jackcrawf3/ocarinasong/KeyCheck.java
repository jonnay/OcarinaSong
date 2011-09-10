/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
                int distanceX;
        int distanceZ;
        /*
        distanceX = Math.abs((int)(currentLocation.getBlockX()-initialLocation.getBlockX()));
        distanceZ = Math.abs((int)(currentLocation.getBlockZ()-initialLocation.getBlockZ()));
        
        if (distanceX ==0 && distanceZ==0){                    
            initialLocation.setYaw(player.getLocation().getYaw());
            initialLocation.setPitch(player.getLocation().getPitch());
        return;}

        /*angle = (int)Math.atan2(currentLocation.getZ()-initialLocation.getZ(), currentLocation.getX()-initialLocation.getX());
        pi = Math.PI;
        player.sendMessage(Integer.toString(angle));
                player.sendMessage(Float.toString(player.getLocation().getYaw()));
        facing = (int)(((angle-player.getLocation().getYaw())*4/pi+16)%8);
        player.sendMessage(Integer.toString(facing));*/
                      //  player.teleport(initialLocation);
        
        
            
        /*
        
        
        int yaw = (int)Math.abs(player.getLocation().getYaw());
                    //West
        if ((yaw>314) |(yaw<45)){
            if (distanceX > distanceZ){
                if (currentLocation.getX() > initialLocation.getX()){
                plugin.hitLeft(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
                else{
                plugin.hitRight(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
            }
            else
            {
                if (currentLocation.getZ() > initialLocation.getZ()){
                plugin.hitUp(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
                else{
                plugin.hitDown(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
            }
        }
                    //South
        else if ((yaw>44) && yaw<135){
            if (distanceX > distanceZ){
                if (currentLocation.getX() > initialLocation.getX()){
         
                plugin.hitUp(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
                else{
                plugin.hitDown(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
            }
            else
            {
                if (currentLocation.getZ() > initialLocation.getZ()){
                plugin.hitRight(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
                else{
                plugin.hitLeft(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }   
            }
        }
                    //East
        else if (yaw>134 && yaw< 225){
            if (distanceX > distanceZ){
                if (currentLocation.getX() > initialLocation.getX()){
                plugin.hitRight(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
                else{
                plugin.hitLeft(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
            }   
            else
            {
                if (currentLocation.getZ() > initialLocation.getZ()){
                plugin.hitDown(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
                else{
                plugin.hitUp(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                } 
            }
        }
                    //North
        else if (yaw>224 && yaw<315){
            if (distanceX > distanceZ){
                if (currentLocation.getX() > initialLocation.getX()){
                plugin.hitDown(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
                else{
                plugin.hitUp(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
            }
            else
            {
                if (currentLocation.getZ() > initialLocation.getZ()){
                plugin.hitLeft(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
                else{
                plugin.hitRight(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                }
            }  
        }
            */
        
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
        
        
        
        
        /*if (facing==0)plugin.hitUp(player);
        if (facing==2)plugin.hitLeft(player);
        if (facing==4)plugin.hitDown(player);
        if (facing==6)plugin.hitRight(player);*/
        
        
        
        
                 
                    }
            }
    
    
   
        
    

