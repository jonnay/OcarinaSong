package me.jackcrawf3.ocarinasong.misc;




import java.util.ArrayList;
import java.util.List;
import me.jackcrawf3.ocarinasong.OcarinaSong;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.material.Button;
import org.bukkit.material.Lever;

/**
 *
 * @author Jackcrawf3
 */
public class SignCheck implements Runnable {
    Player player;
    OcarinaSong plugin;
    public Server server;
    int radius;
    String song;
    
    public SignCheck(Player player,OcarinaSong plugin,String song){
     this.plugin = plugin;
     this.player = player;
     this.radius = 30;
     this.song = song;
    }
        
    public void run() {
        
        int playerX = (int)player.getLocation().getX();
        int playerY = (int)player.getLocation().getY();
        int playerZ = (int)player.getLocation().getZ();
        List<Sign> signs = new ArrayList<Sign>();
        World world = player.getWorld();
        for(int x = (int)playerX-radius;x<playerX+radius;x ++){
            for(int z = (int)playerZ-radius;z<playerZ+radius;z ++){
                for(int y = (int)playerY-radius;y<playerY+radius;y ++){
                    BlockState bs = world.getBlockAt(x,y,z).getState();
                    if(!(bs.getType() == Material.WALL_SIGN))continue;
                    signs.add((Sign)bs);
                }
            }
        }
        for (Sign sign : signs){
            if ((sign.getLine(1).equals("§b[Awaken]")&&"awakening".equals(song))||(sign.getLine(1).equals("§b[Time]")&&"time".equals(song))||(sign.getLine(1).equals("§b[Zelda]"))&&"zelda".equals(song)){
                
                int MaxDistance = 10;
                try{
                    if (Integer.parseInt(sign.getLine(3))>0)MaxDistance = Integer.parseInt(sign.getLine(3));
                }
                catch(java.lang.NumberFormatException err){
                            MaxDistance = 10;
                }
                Location loc = new Location(player.getWorld(),sign.getX(),sign.getY(),sign.getZ(),0,0);
                if (player.getLocation().distance(loc)<MaxDistance) {
                    if (sign.getType()==Material.WALL_SIGN){
                        BlockFace facing;
                        facing = BlockFace.EAST_NORTH_EAST;
                        switch (sign.getBlock().getData()){
                            case 0x2:
                                facing=BlockFace.EAST;
                                break;
                            case 0x3:
                                facing=BlockFace.WEST;
                                break;
                            case 0x4:
                                facing=BlockFace.NORTH;
                                break;
                            case 0x5:
                                facing=BlockFace.SOUTH;
                                break;
                        }                                                      
                        if (facing==BlockFace.NORTH||facing==BlockFace.WEST||facing==BlockFace.SOUTH||facing==BlockFace.EAST){
                            Block theblock;
                            if (facing==BlockFace.NORTH)theblock=sign.getBlock().getRelative(BlockFace.SOUTH);
                            else if (facing==BlockFace.SOUTH)theblock=sign.getBlock().getRelative(BlockFace.NORTH);
                            else if (facing==BlockFace.WEST)theblock=sign.getBlock().getRelative(BlockFace.EAST);
                            else if (facing==BlockFace.EAST)theblock=sign.getBlock().getRelative(BlockFace.WEST);
                            else theblock = sign.getBlock().getRelative(BlockFace.DOWN);
                            int ticks = 20;
                            try{
                            if (Integer.parseInt(sign.getLine(2))>0)ticks = Integer.parseInt(sign.getLine(2))*20;
                            }
                            catch(java.lang.NumberFormatException err){
                                ticks = 20;
                            }
                            plugin.checkForButtons(theblock, BlockFace.UP, ticks);
                            plugin.checkForButtons(theblock, BlockFace.NORTH, ticks);
                            plugin.checkForButtons(theblock, BlockFace.SOUTH, ticks);
                            plugin.checkForButtons(theblock, BlockFace.EAST, ticks);
                            plugin.checkForButtons(theblock, BlockFace.WEST, ticks);
                        }
                    }
                }
            }
        }
    }
}
    
    
   
        
    

