package me.jackcrawf3.ocarinasong;




import java.util.ArrayList;
import java.util.List;
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
    
    public SignCheck(Player thisplayer,OcarinaSong plugin,Integer radius){
     this.plugin = plugin;
     player = thisplayer;
     this.radius = radius;
    }
        
    public void run() {
        
        int playerX = (int)player.getLocation().getX();
        int playerY = (int)player.getLocation().getY();
        int playerZ = (int)player.getLocation().getZ();
        List<Sign> signs = new ArrayList<Sign>();
        World world = player.getWorld();
        player.sendMessage("SignCheck was called...");
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
            player.sendMessage("Checking the area for signs...");
            if (sign.getLine(1).equals("§7[Awaken]")){
                if (sign.getType()==Material.WALL_SIGN){
                    BlockFace facing;
                    facing = BlockFace.EAST_NORTH_EAST;
                    switch (sign.getBlock().getData()){
                        case 0x2:
                            facing=BlockFace.EAST;
                            player.sendMessage("It's facing east");
                            break;
                        case 0x3:
                            facing=BlockFace.WEST;
                            player.sendMessage("It's facing west");
                            break;
                        case 0x4:
                            facing=BlockFace.NORTH;
                            player.sendMessage("It's facing north");
                            break;
                        case 0x5:
                            facing=BlockFace.SOUTH;
                            player.sendMessage("It's facing south");
                            break;
                    }                                                       
                
                    if (facing==BlockFace.NORTH||facing==BlockFace.WEST||facing==BlockFace.SOUTH||facing==BlockFace.EAST){
                        Block theblock;
                        if (facing==BlockFace.NORTH)theblock=sign.getBlock().getRelative(BlockFace.SOUTH);
                        else if (facing==BlockFace.SOUTH)theblock=sign.getBlock().getRelative(BlockFace.NORTH);
                        else if (facing==BlockFace.WEST)theblock=sign.getBlock().getRelative(BlockFace.EAST);
                        else if (facing==BlockFace.EAST)theblock=sign.getBlock().getRelative(BlockFace.WEST);
                        else theblock = sign.getBlock().getRelative(BlockFace.DOWN);
                        
                        
                        if (theblock.getRelative(BlockFace.UP).getType().equals(Material.LEVER)|| theblock.getRelative(BlockFace.UP).getType().equals(Material.STONE_BUTTON)){
                            player.sendMessage("Well, it does have a button/lever on it...");
                            Button button = new Button();
                            Lever lever = new Lever();
                            if (theblock.getRelative(BlockFace.UP).getType().equals(Material.STONE_BUTTON)){button.setData(theblock.getRelative(BlockFace.UP).getData());
                            button.setPowered(true);
                            theblock.getRelative(BlockFace.SOUTH).setData(button.getData());
                            }
                            if (theblock.getRelative(BlockFace.UP).getType().equals(Material.LEVER)){lever.setData(theblock.getRelative(BlockFace.UP).getData());
                                lever.setPowered(!lever.isPowered());
                                theblock.getRelative(BlockFace.UP).setData(lever.getData());
                            }
                           
                        }
                        if (theblock.getRelative(BlockFace.NORTH).getType().equals(Material.LEVER)|| theblock.getRelative(BlockFace.NORTH).getType().equals(Material.STONE_BUTTON)){
                            player.sendMessage("Well, it does have a button/lever on it...");
                            Button button = new Button();
                            Lever lever = new Lever();
                            if (theblock.getRelative(BlockFace.NORTH).getType().equals(Material.STONE_BUTTON)){button.setData(theblock.getRelative(BlockFace.NORTH).getData());
                            button.setPowered(true);
                            theblock.getRelative(BlockFace.NORTH).setData(button.getData());
                            }
                            if (theblock.getRelative(BlockFace.NORTH).getType().equals(Material.LEVER)){lever.setData(theblock.getRelative(BlockFace.NORTH).getData());
                                lever.setPowered(!lever.isPowered());
                                theblock.getRelative(BlockFace.NORTH).setData(lever.getData());
                            }
                           
                        }
                        if (theblock.getRelative(BlockFace.EAST).getType().equals(Material.LEVER)|| theblock.getRelative(BlockFace.EAST).getType().equals(Material.STONE_BUTTON)){
                            player.sendMessage("Well, it does have a button/lever on it...");
                            Button button = new Button();
                            Lever lever = new Lever();
                            if (theblock.getRelative(BlockFace.EAST).getType().equals(Material.STONE_BUTTON)){button.setData(theblock.getRelative(BlockFace.EAST).getData());
                            button.setPowered(true);
                            theblock.getRelative(BlockFace.EAST).setData(button.getData());
                            }
                            if (theblock.getRelative(BlockFace.EAST).getType().equals(Material.LEVER)){lever.setData(theblock.getRelative(BlockFace.EAST).getData());
                                lever.setPowered(!lever.isPowered());
                                theblock.getRelative(BlockFace.EAST).setData(lever.getData());
                            }
                           
                        }
                        if (theblock.getRelative(BlockFace.WEST).getType().equals(Material.LEVER)|| theblock.getRelative(BlockFace.WEST).getType().equals(Material.STONE_BUTTON)){
                            player.sendMessage("Well, it does have a button/lever on it...");
                            Button button = new Button();
                            Lever lever = new Lever();
                            if (theblock.getRelative(BlockFace.WEST).getType().equals(Material.STONE_BUTTON)){button.setData(theblock.getRelative(BlockFace.WEST).getData());
                            button.setPowered(true);
                            theblock.getRelative(BlockFace.WEST).setData(button.getData());
                            }
                            if (theblock.getRelative(BlockFace.WEST).getType().equals(Material.LEVER)){lever.setData(theblock.getRelative(BlockFace.WEST).getData());
                                lever.setPowered(!lever.isPowered());
                                theblock.getRelative(BlockFace.WEST).setData(lever.getData());
                            }
                           
                        }
                        if (theblock.getRelative(BlockFace.SOUTH).getType().equals(Material.LEVER)|| theblock.getRelative(BlockFace.SOUTH).getType().equals(Material.STONE_BUTTON)){
                            player.sendMessage("Well, it does have a button/lever on it...");
                            Button button = new Button();
                            Lever lever = new Lever();
                            if (theblock.getRelative(BlockFace.SOUTH).getType().equals(Material.STONE_BUTTON)){button.setData(theblock.getRelative(BlockFace.SOUTH).getData());
                            button.setPowered(true);
                            theblock.getRelative(BlockFace.SOUTH).setData(button.getData());
                            }
                            if (theblock.getRelative(BlockFace.SOUTH).getType().equals(Material.LEVER)){lever.setData(theblock.getRelative(BlockFace.SOUTH).getData());
                                lever.setPowered(!lever.isPowered());
                                theblock.getRelative(BlockFace.SOUTH).setData(lever.getData());
                            }
                        }
                     }
                }
            }
        }
    }
}
    
    
   
        
    

