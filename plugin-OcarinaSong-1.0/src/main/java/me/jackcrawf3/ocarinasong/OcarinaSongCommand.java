
package me.jackcrawf3.ocarinasong;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



/**
 *
 * @author Jackcrawf3
 */
class OcarinaSongCommand implements CommandExecutor {
    private OcarinaSong plugin;
    private int cmd;
    private Player player;
    
    public OcarinaSongCommand(OcarinaSong plugin, int commandType) {
        this.plugin = plugin;
        cmd = commandType;
    }
    
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        player = (Player) sender;
        if("ocarina".equals(command.toString())) {
            if (player.hasPermission("jack.ocarina")){
                if (args.length==0){
                    plugin.listSongs(player);
                }
                else{
                    if (!player.hasPermission("jack.ocarina.time")){
                        if(args[1]=="time"){
                            Bukkit.getServer().getScheduler().scheduleAsyncDelayedTask(plugin, new SongOfTime((Player)sender),10);
                            player.sendMessage("Playing Song of Time!");
                        }
                    }
                }
            }   
        }
    return true;
    }
}