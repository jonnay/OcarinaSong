
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
        Player player = event.getPlayer();
        if (event.getBlock().getType()==Material.WALL_SIGN){
            if (event.getLine(1).equals("[Awaken]")||event.getLine(1).equals("[awaken]")||event.getLine(1).toLowerCase().equals("§b[awaken]".toLowerCase())){
                if (event.getPlayer().hasPermission("ocarina.awakening.sign")){
                    event.setLine(1, "§b[Awaken]");
                    event.getPlayer().sendMessage(ChatColor.AQUA+ "Created " + ChatColor.GRAY + "Awakening Detector" + ChatColor.AQUA + "!");
                }
                else{
                    event.getPlayer().sendMessage(ChatColor.BLUE + "You don't understand ancient Magicks enough!");
                    event.getPlayer().getWorld().dropItemNaturally(event.getBlock().getLocation(), new ItemStack(Material.SIGN,1));
                    event.getBlock().setType(Material.AIR);
                    event.setCancelled(true);
                }

            }
        }
        return;
    }

    
    
    
    
    }
    
    
    
    
    
    
    
    
 