/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jackcrawf3.ocarinasong;

import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Note;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

/**
 *
 * @author Jackcrawf3
 */
public class SongOfTime implements Runnable {

    private Player player;
    private boolean finished = false;
    OcarinaSong plugin;
    private int currently = 0;
    static final byte song[] = {0x08,0x0B,0x14,0x01,0x08,0x0B,0x14,0x01,0x16,0x17,0x16,0x17,0x16,0x12,0x0F};
    public SongOfTime(Player player) {
    }

    public void run() {
        if (plugin.isPlaying(player)) {
            byte musicnote = 0x01;
            if (!player.hasPermission("jack.ocarina.time")) {
                return;
            }
            if (finished) {
                return;
            }

            if(currently<song.length)musicnote = song[currently];

            currently++;

            player.playNote(player.getLocation().getBlock().getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getRelative(BlockFace.DOWN).getLocation(), Instrument.PIANO, new Note(musicnote));

        }
        return;

    }
}
