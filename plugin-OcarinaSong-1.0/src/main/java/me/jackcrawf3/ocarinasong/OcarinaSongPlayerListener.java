
package me.jackcrawf3.ocarinasong;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;


/**
 *
 * @author Jackcrawf3
 */
public class OcarinaSongPlayerListener extends PlayerListener {
private final OcarinaSong plugin;
public Map<Player, Integer> YourId = new HashMap<Player, Integer>();
public Map<Player, Location> MusicBoxBlockLocation = new HashMap<Player, Location>();
public Map<Player, Material> WhatWasItBefore = new HashMap<Player, Material>();
public Server server;


    public OcarinaSongPlayerListener(OcarinaSong plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onItemHeldChange(PlayerItemHeldEvent event) {
        int newslot = event.getNewSlot();
        if (event.getPlayer().getInventory().getItem(newslot).getType()!= Material.CLAY_BRICK){
            if (plugin.isPlaying(event.getPlayer())){
                plugin.StopOcarina(event.getPlayer());
            }
        }
    }

    
    
    
    public void PlaySong(String song, Player player){
        if (song=="time"){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfTime(player, plugin, 0),4);
                if (player.hasPermission("ocarina.time")){
                    plugin.getServer().broadcastMessage(ChatColor.GREEN + player.getName() + " has changed the time of day!");
                    player.getWorld().setTime(player.getWorld().getTime()+12000);
                 }
        }
        else if (song=="storms"){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfStorms(player, plugin, 0),4);
            if (player.hasPermission("ocarina.storms")){
                plugin.getServer().broadcastMessage(ChatColor.GREEN + player.getName() + " has changed the weather!");
                if(player.getWorld().hasStorm()==true){ player.getWorld().setStorm(false);}
                else{ player.getWorld().setStorm(true);}
            }
        }
        else if (song=="healing"){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfHealing(player, plugin, 0),4);
            if (player.hasPermission("ocarina.healing")){
            }
        }
        
        else if (song=="zelda"){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new ZeldasLullaby(player, plugin, 0),4);
            if (player.hasPermission("ocarina.zelda")){
            }
        }
        else if (song=="awakening"){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfHealing(player, plugin, 0),4);
            if (player.hasPermission("ocarina.awakening")){
            }
        }
    
        
        
        
        return;
        
    }
    

    

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        if (Action.LEFT_CLICK_BLOCK == event.getAction()){           
            if (event.getClickedBlock().equals(player.getLocation().getBlock().getRelative(BlockFace.DOWN))){
                if (plugin.isPlaying(player)){
                event.setCancelled(true);
                return;
                }
            }
        }
        if (Action.RIGHT_CLICK_BLOCK != event.getAction() && Action.RIGHT_CLICK_AIR != event.getAction()) return;
     
        if (Action.RIGHT_CLICK_BLOCK == event.getAction() && event.getClickedBlock().getLocation().equals(MusicBoxBlockLocation.get(player))) {
            if (plugin.isPlaying(player)) {
                event.setCancelled(true);
                StopPlaying(player);
                return;
            }
        }
        
        
        
        
        
        
        
        
        
        if (Action.RIGHT_CLICK_AIR == event.getAction() || Action.RIGHT_CLICK_BLOCK == event.getAction()){
            if (event.getMaterial() == Material.CLAY_BRICK){
                if (!player.hasPermission("ocarina")){
                    player.sendMessage(ChatColor.RED + "You're not skilled enough to play the Ocarina!");
                return;
                }
                if (!plugin.isPlaying(player)){
                    StartPlaying(player);
                }
                else{
                    StopPlaying(player);
                } 



            }
        }
    return;
    }

    @Override
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        if (plugin.isPlaying(event.getPlayer())){
        plugin.StopOcarina(event.getPlayer());
        }
    }
    
    
    
    public void StartPlaying(Player player){
            if (player.hasPermission("ocarina")){
                if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).isLiquid() || (player.getLocation().getBlock().getRelative(BlockFace.DOWN).isEmpty()))return;
                plugin.setPlaying(player, true);
                player.sendMessage(ChatColor.DARK_PURPLE + "You start playing your " + ChatColor.AQUA + "ocarina" + ChatColor.DARK_PURPLE + "!");
                WhatWasItBefore.put(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType());
                MusicBoxBlockLocation.put(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                MusicBoxBlockLocation.get(player).getBlock().setType(Material.NOTE_BLOCK);
                
                YourId.put(player,Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new KeyCheck(player,plugin),0,10));
            }
    }
    
    
    public void StopPlaying(Player player){
    
                plugin.setPlaying(player, false);
                player.sendMessage(ChatColor.DARK_PURPLE + "You stop playing your "+ ChatColor.AQUA + "ocarina" + ChatColor.DARK_PURPLE + "!");
                if (WhatWasItBefore.get(player)!= Material.NOTE_BLOCK)MusicBoxBlockLocation.get(player).getBlock().setType(WhatWasItBefore.get(player));
                Bukkit.getServer().getScheduler().cancelTask(YourId.get(player));
                YourId.remove(player);
                MusicBoxBlockLocation.remove(player);
                WhatWasItBefore.remove(player);
    }
    
    
    

    @Override
    public void onPlayerToggleSneak(PlayerToggleSneakEvent event) {
            Player player = event.getPlayer();
            if (!player.hasPermission("ocarina")) {
                return;
            }
            if (plugin.isPlaying(player)){   
                         
            if (event.isSneaking()){
               
                byte musicnote = 0x0A;
               plugin.hitShift(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                        
                }
            }
            return;
            }

    @Override
    public void onPlayerQuit(PlayerQuitEvent event) {
        if (plugin.isPlaying(event.getPlayer())){
            StopPlaying(event.getPlayer());
        }
        return;
    }
    
    
    
    
    
    
    
    }
    
    
    
    
    
    
    
    
 