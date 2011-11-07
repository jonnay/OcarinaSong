package me.jackcrawf3.ocarinasong;

import me.jackcrawf3.ocarinasong.misc.KeyCheck;
import me.jackcrawf3.ocarinasong.songs.SonataOfAwakening;
import me.jackcrawf3.ocarinasong.songs.SongOfStorms;
import me.jackcrawf3.ocarinasong.songs.SongOfHealing;
import me.jackcrawf3.ocarinasong.songs.EponasSong;
import me.jackcrawf3.ocarinasong.songs.SongOfTime;
import me.jackcrawf3.ocarinasong.songs.ZeldasLullaby;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.PigZombie;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.Zombie;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


/**
 *
 * @author Jackcrawf3
 */
public class OcarinaSongPlayerListener extends PlayerListener {
private final OcarinaSong plugin;
public Map<Player, Integer> YourId = new HashMap<Player, Integer>();
public Map<Player, Location> MusicBoxBlockLocation = new HashMap<Player, Location>();


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
        if ("time".equals(song)){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfTime(player, plugin, 0),4);
        }
        else if ("storms".equals(song)){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfStorms(player, plugin, 0),4);
        }
        else if ("healing".equals(song)){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SongOfHealing(player, plugin, 0),4);
            if (player.hasPermission("ocarina.healing")){
            }
        }
        
        else if ("zelda".equals(song)){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new ZeldasLullaby(player, plugin, 0),4);
            if (player.hasPermission("ocarina.zelda")){
            }
        }
        else if ("awakening".equals(song)){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new SonataOfAwakening(player, plugin, 0),4);
            if (player.hasPermission("ocarina.awakening")){
            }
        }
        else if ("epona".equals(song)){Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new EponasSong(player, plugin, 0),4);
            if (player.hasPermission("ocarina.epona.call")){
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
     
        if (Action.RIGHT_CLICK_BLOCK == event.getAction() || Action.LEFT_CLICK_BLOCK == event.getAction() && MusicBoxBlockLocation.containsKey(player) ){
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
                MusicBoxBlockLocation.put(player, player.getLocation().getBlock().getRelative(BlockFace.DOWN).getLocation());
                drawNoteblock(MusicBoxBlockLocation.get(player));
                
                YourId.put(player,Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new KeyCheck(player,plugin),0,10));
            }
    }
    
    public void drawNoteblock(Location loc){
        for (Player player : plugin.getServer().getOnlinePlayers()){
            byte bite = 0x00;
            player.sendBlockChange(loc,Material.NOTE_BLOCK,bite);
        }
    }
    public void undrawNoteblock(Location loc){
        for (Player player : plugin.getServer().getOnlinePlayers()){
            byte bite = 0x00;
            player.sendBlockChange(loc,loc.getBlock().getType(), bite);
        }
    }
    
    
    public void StopPlaying(Player player){
        plugin.setPlaying(player, false);
        player.sendMessage(ChatColor.DARK_PURPLE + "You stop playing your "+ ChatColor.AQUA + "ocarina" + ChatColor.DARK_PURPLE + "!");
        undrawNoteblock(MusicBoxBlockLocation.get(player));
        Bukkit.getServer().getScheduler().cancelTask(YourId.get(player));
        YourId.remove(player);
        MusicBoxBlockLocation.remove(player);
    }

    @Override
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        if (plugin.isSomeonesHorse(event.getRightClicked())){
            if (plugin.isPlayersVehicle(event.getRightClicked(), player)){
                if (event.getRightClicked().getPassenger() == player){
                    event.getRightClicked().eject();
                    player.sendMessage(ChatColor.AQUA + "You dismount your pet!");
                }
                else if (event.getRightClicked().getPassenger() == null){
                    event.getRightClicked().setPassenger(player);
                    player.sendMessage(ChatColor.AQUA + "You mount your pet!");
                }
                else{
                    if (player.hasPermission("ocarina.horses.dismountopponent")){
                        Random generator = new Random();
                        Integer  inty = generator.nextInt(20);
                        if (inty==3){
                            player.sendMessage(ChatColor.RED + "You have dismounted the Jockey!");
                            
                            if (event.getRightClicked().getPassenger() instanceof Player){
                                ((Player) event.getRightClicked()).sendMessage(ChatColor.RED + player.getName() + " has dismounted you from your pet!");
                            }
                            event.getRightClicked().setPassenger(null);
                        }
                        else{
                            player.sendMessage(ChatColor.RED + "You tug at the Jockey!");
                        }
                    }                
                }
                event.setCancelled(true);
            }
        }
        else if (event.getRightClicked() instanceof LivingEntity && !(event.getRightClicked() instanceof Player)){
            LivingEntity entity = (LivingEntity) event.getRightClicked();
            if (plugin.isTaming.contains(player)){
                event.setCancelled(true);
                if (player.getItemInHand().getType()==Material.APPLE){
                    if (entity instanceof Pig || entity instanceof Sheep || entity instanceof Cow || entity instanceof Chicken){
                        plugin.registerHorse(player, entity);
                        player.sendMessage(ChatColor.AQUA + "You tame the majestic creature!");
                        if (player.getItemInHand().getAmount()!=1)player.getItemInHand().setAmount(player.getItemInHand().getAmount()-1);
                        else player.getInventory().clear(player.getInventory().getHeldItemSlot());
                        plugin.isTaming.remove(player);
                    }
                    else{
                        player.sendMessage(ChatColor.RED + "You cannot tame this with an apple!");
                    }
                }
                else if (player.getItemInHand().getType()==Material.BONE){
                    if (entity instanceof Wolf || entity instanceof PigZombie || entity instanceof Ghast || entity instanceof Spider || entity instanceof Slime){
                        if (entity.getHealth() < 8){
                            plugin.registerHorse(player, entity);
                            player.sendMessage(ChatColor.AQUA + "You tame the wild beast!");
                            if (player.getItemInHand().getAmount()!=1)player.getItemInHand().setAmount(player.getItemInHand().getAmount()-1);
                            else player.getInventory().clear(player.getInventory().getHeldItemSlot());
                            plugin.isTaming.remove(player);
                        }
                        else{
                            player.sendMessage(ChatColor.RED + "It's too strong to be tamed!");
                        }
                    }
                    else{
                        player.sendMessage(ChatColor.RED + "You cannot tame this with a bone!");
                    }
                }
                else{
                    player.sendMessage(ChatColor.RED + "You cannot tame this with a " + player.getItemInHand().getType().toString());
                }
            }           
        }
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
    
    
    
    
    
    
    
    
 