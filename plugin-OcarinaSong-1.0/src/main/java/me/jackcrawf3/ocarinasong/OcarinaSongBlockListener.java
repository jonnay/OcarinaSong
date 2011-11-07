
package me.jackcrawf3.ocarinasong;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.inventory.ItemStack;




/**
 *
 * @author Jackcrawf3
 */
public class OcarinaSongBlockListener extends BlockListener {
private final OcarinaSong plugin;
public Server server;


    public OcarinaSongBlockListener(OcarinaSong plugin) {
        this.plugin = plugin;
    }

    @Override
    public void onSignChange(SignChangeEvent event) {
        if (event.getBlock().getType()==Material.WALL_SIGN){
            if (event.getLine(1).toLowerCase().equals("[awaken]".toLowerCase())||event.getLine(1).toLowerCase().equals("§b[awaken]".toLowerCase())){
                if (event.getPlayer().hasPermission("ocarina.awakening.sign")){
                    event.setLine(1, "§b[Awaken]");
                    event.getPlayer().sendMessage(ChatColor.AQUA+ "Created " + ChatColor.GRAY + "Awakening Detector" + ChatColor.AQUA + "!");
                }
                else{
                    event.getPlayer().sendMessage(ChatColor.BLUE + "You don't understand ancient magicks enough!");
                    event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SIGN,1));
                    event.getBlock().setType(Material.AIR);
                    event.setCancelled(true);
                }
            }       
            if (event.getLine(1).toLowerCase().equals("[zelda]".toLowerCase())||event.getLine(1).toLowerCase().equals("§b[zelda]".toLowerCase())){
                if (event.getPlayer().hasPermission("ocarina.zelda.sign")){
                    event.setLine(1, "§b[Zelda]");
                    event.getPlayer().sendMessage(ChatColor.AQUA+ "Created " + ChatColor.GRAY + "Zelda's Lullaby Detector" + ChatColor.AQUA + "!");
                }
                else{
                    event.getPlayer().sendMessage(ChatColor.BLUE + "You aren't trusted by the Hyrulean Royal Family... or the Ops!");
                    event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SIGN,1));
                    event.getBlock().setType(Material.AIR);
                    event.setCancelled(true);
                }
            }
            
            if (event.getLine(1).toLowerCase().equals("[time]".toLowerCase())||event.getLine(1).toLowerCase().equals("§b[time]".toLowerCase())){
                if (event.getPlayer().hasPermission("ocarina.time.sign")){
                    event.setLine(1, "§b[Time]");
                    event.getPlayer().sendMessage(ChatColor.AQUA+ "Created " + ChatColor.GRAY + "Song of Time Detector" + ChatColor.AQUA + "!");
                }
                else{
                    event.getPlayer().sendMessage(ChatColor.BLUE + "You don't well enough understand the art of watchmaking!");
                    event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SIGN,1));
                    event.getBlock().setType(Material.AIR);
                    event.setCancelled(true);
                }
            }
        }
        return;
    }

    
    
    
    
    }
    
    
    
    
    
    
    
    
 