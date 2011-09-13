package me.jackcrawf3.ocarinasong;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 *
 * @author Jackcrawf3
 */
public class SongOfHealing implements Runnable {

    private Player player;
    private boolean finished = false;
    OcarinaSong plugin;
    private int currently;
    Location first;
    
    static final byte song[] = {0x11, 0x00 ,0x0F, 0x00 ,0x0A,0x08,0x0A, 0x00, 0x00, 0x00, 0x00, 0x11, 0x00 ,0x0F, 0x00, 0x0B, 0x00, 0x11, 0x00 ,0x0F, 0x00, 0x0B, 0x00};



    public SongOfHealing(Player thisplayer,OcarinaSong plugin, Integer counter){
     this.plugin = plugin;
     player = thisplayer;
     currently = counter;
     first = player.getLocation().clone();
    }
    
        public void run() {
        
        if (plugin.isPlaying(player)) {
            byte musicnote;
            if (!player.hasPermission("jack.ocarina")) {
                return;
            }
            if (finished) {
                return;
            }
            if (player.getLocation().getBlock().getRelative(BlockFace.DOWN).getType()!= Material.NOTE_BLOCK)return;
            if (player.getLocation().getBlockX()!=first.getBlockX())return;
            if (player.getLocation().getBlockY()!=first.getBlockY())return;
            if (player.getLocation().getBlockZ()!=first.getBlockZ())return;
            

            
            if(currently<song.length){musicnote = song[currently];}else{
            musicnote = song[1];
            }

            currently++;
            
            if (currently>song.length){currently=0;
            player.setHealth(player.getHealth() + 2);}
            
            byte test;
            test = 0x01;
            if (musicnote== test){musicnote = 11; currently = 2;}

            if (musicnote!= 0x00){plugin.PlayThatNoteFreely(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation(), musicnote);
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfHealing(player, plugin, currently),4);
            }
            else{
            Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfHealing(player, plugin, currently),2);
            }
        }
        return;

    }
}
