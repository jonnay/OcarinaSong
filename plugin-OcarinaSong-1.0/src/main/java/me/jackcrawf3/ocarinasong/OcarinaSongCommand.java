
package me.jackcrawf3.ocarinasong;


import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;



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
        if(("ocarina".equals(command.getLabel()))) {
            if (sender.hasPermission("ocarina")){
                plugin.listSongs(sender);
            }
            else{sender.sendMessage(ChatColor.DARK_RED + "You're too unintelligent to know anything about Ocarinas");}
        }
    return true;
    }
}