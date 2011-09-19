/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jackcrawf3.ocarinasong;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Player;

/**
 *
 * @author Jackcrawf3
 */
public class SonataOfAwakening implements Runnable {

    private Player player;
    OcarinaSong plugin;
    private int currently;
    
    static final byte song[] = {0x14, 0x11, 0x14, 0x11, 0x00, 0x08, 0x0F, 0x00, 0x08};



    public SonataOfAwakening(Player thisplayer,OcarinaSong plugin, Integer counter){
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
            if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType()!= Material.NOTE_BLOCK){return;}
            
            if(currently<song.length)musicnote = song[currently];
            
            currently++;
            
            if (currently>song.length){
                if (player.hasPermission("ocarina.awakening"))Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SignCheck(player, plugin, "awakening"),0);
                return;
            }

            if (musicnote!= 0x00){plugin.PlayThatNoteFreely(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), musicnote);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SonataOfAwakening(player, plugin, currently),5);
            }
            else{
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SonataOfAwakening(player, plugin, currently),2);
            }
        }
        return;

    }
}
