package me.jackcrawf3.ocarinasong;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Enderman;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Squid;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

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
            if (!player.hasPermission("ocarina")) {
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
            if (player.hasPermission("ocarina.healing")){
                 player.setHealth(player.getHealth()+2);
            List entities = player.getNearbyEntities(10,10,10);
                for (Object entity : entities){
                    Entity thisentity = (Entity)entity;
                    if ("org.bukkit.craftbukkit.entity.CraftWolf".equals(thisentity.getClass().getName())){
                        Wolf thiswolf = (Wolf)thisentity;
                        thiswolf.setHealth(thiswolf.getHealth() + 2);
                    }
                   if ("org.bukkit.craftbukkit.entity.CraftPlayer".equals(thisentity.getClass().getName())){
                        Player thisplayer = (Player)thisentity;
                        thisplayer.setHealth(thisplayer.getHealth() + 1);
                    }
                   if ("org.bukkit.craftbukkit.entity.CraftSquid".equals(thisentity.getClass().getName())){
                        Squid thismob = (Squid)thisentity;
                        thismob.setHealth(thismob.getHealth() + 2);
                    }
                   if ("org.bukkit.craftbukkit.entity.CraftPig".equals(thisentity.getClass().getName())){
                        Pig thismob = (Pig)thisentity;
                        thismob.setHealth(thismob.getHealth() + 2);
                    }
                  
                   if ("org.bukkit.craftbukkit.entity.CraftSheep".equals(thisentity.getClass().getName())){
                        Sheep thismob = (Sheep)thisentity;
                        thismob.setHealth(thismob.getHealth() + 2);
                        if (player.hasPermission("ocarina.healing.wool"))thismob.setSheared(false);
                    }
                   if (player.hasPermission("ocarina.healing.burn")){
                   if ("org.bukkit.craftbukkit.entity.CraftSkeleton".equals(thisentity.getClass().getName())){
                        Skeleton thismob = (Skeleton)thisentity;
                        thismob.damage(1);
                        thismob.setFireTicks(15);
                    }
                    if ("org.bukkit.craftbukkit.entity.CraftZombie".equals(thisentity.getClass().getName())){
                        Zombie thismob = (Zombie)thisentity;
                        thismob.damage(1);
                        thismob.setFireTicks(15);
                    }
                   if ("org.bukkit.craftbukkit.entity.CraftCreeper".equals(thisentity.getClass().getName())){
                        Creeper thismob = (Creeper)thisentity;
                        thismob.setPowered(false);
                        thismob.setFireTicks(15);
                        thismob.damage(1);
                    }
                   }
                  if ("org.bukkit.craftbukkit.entity.CraftEnderman".equals(thisentity.getClass().getName())){
                        Enderman thismob = (Enderman)thisentity;
                        thismob.getWorld().dropItemNaturally(thismob.getEyeLocation(),new ItemStack(Material.ENDER_PEARL,1));
                        thismob.getWorld().playEffect(thismob.getLocation(), Effect.EXTINGUISH, 1);
                        thismob.getWorld().playEffect(thismob.getLocation(), Effect.EXTINGUISH, 2);
                        thismob.getWorld().playEffect(thismob.getLocation(), Effect.EXTINGUISH, 3);
                        thismob.getWorld().playEffect(thismob.getLocation(), Effect.SMOKE, 4);
                        thismob.remove();
                    }
                   if (player.hasPermission("ocarina.healing.pig")){
                   if ("org.bukkit.craftbukkit.entity.CraftPigZombie".equals(thisentity.getClass().getName())){
                        PigZombie thismob = (PigZombie)thisentity;
                        thismob.setHealth(thismob.getHealth()+2);
                        Location thislocation = thismob.getLocation();
                        World thisworld = thismob.getWorld();
                        thismob.remove();
                        Pig newmob;
                        newmob = (Pig)thisworld.spawnCreature(thislocation, CreatureType.PIG);
                        newmob.setHealth(10);
                    }
                   }
                }
            }
            
            }
            
            byte test;
            test = 0x01;
            if (musicnote== test){musicnote = 0x11; currently = 2;}

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
