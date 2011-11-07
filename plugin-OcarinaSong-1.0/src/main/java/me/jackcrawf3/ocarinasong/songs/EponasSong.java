/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jackcrawf3.ocarinasong.songs;

import me.jackcrawf3.ocarinasong.OcNotes;
import me.jackcrawf3.ocarinasong.OcarinaSong;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.util.Vector;

/**
 *
 * @author Jackcrawf3
 */
public class EponasSong implements Runnable {

    private Player player;
    OcarinaSong plugin;
    private int currently;
    
    static final byte song[] = {OcNotes.UP.getByte(), OcNotes.REST.getByte(), OcNotes.LEFT.getByte(), OcNotes.RIGHT.getByte(), OcNotes.REST.getByte(), OcNotes.REST.getByte(), OcNotes.LEFT.getByte(), OcNotes.RIGHT.getByte()};



    public EponasSong(Player thisplayer,OcarinaSong plugin, Integer counter){
     this.plugin = plugin;
     player = thisplayer;
     currently = counter;
    }
    
        public void run() {
        
        if (plugin.isPlaying(player)) {
            byte musicnote = 0x01;
            if (!player.hasPermission("ocarina")) {
                return;
            }
            
            if(currently<song.length)musicnote = song[currently];
            
            currently++;
            
            if (currently>song.length){
                if (player.hasPermission("ocarina.epona.call")){
                    if (plugin.vehicleByPlayer.containsKey(player)){
                        LivingEntity livent = (LivingEntity) plugin.vehicleByPlayer.get(player);
                        if (player.getLocation().distance(livent.getLocation())<25f){
                            
                            //Failures at getting the damned creature just to walk to you.
                            //Vector towardsPlayer = new Vector();
                            //((org.bukkit.craftbukkit.entity.CraftCreature)livent).getHandle().setPathEntity(new net.minecraft.server.PathEntity(new net.minecraft.server.PathPoint[] {new net.minecraft.server.PathPoint((int)player.getLocation().getX(), (int)player.getLocation().getY(), (int)player.getLocation().getZ())}));
                            //livent.getVelocity().
                            //towardsPlayer = player.getEyeLocation().toVector().subtract(livent.getLocation().toVector()).multiply(.99f);
                            //livent.setVelocity(towardsPlayer);
                            livent.teleport(player.getLocation().add(1,0,1));
                            player.sendMessage(ChatColor.AQUA + "Your pet is coming!");
                        }
                        else{
                            livent.teleport(player.getLocation().add(1,0,1));
                            player.sendMessage(ChatColor.AQUA + "Your pet is coming!");
                        }
                    }
                }
                else{
                    player.sendMessage(ChatColor.RED + "You don't even know how these creatures work, do you?");
                }
                return;
            }

            if (musicnote!= 0x00){plugin.PlayThatNoteFreely(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), musicnote);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new EponasSong(player, plugin, currently),5);
            }
            else{
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new EponasSong(player, plugin, currently),2);
            }
        }
        return;

    }
}
