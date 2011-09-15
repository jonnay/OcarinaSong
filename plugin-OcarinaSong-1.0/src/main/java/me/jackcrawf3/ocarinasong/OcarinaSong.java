
package me.jackcrawf3.ocarinasong;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;



import java.util.logging.Level;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Note;
import org.bukkit.Server;
import org.bukkit.block.BlockFace;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.Event;
import org.bukkit.block.NoteBlock;

import org.bukkit.plugin.java.JavaPlugin;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author Jackcrawf3
 */
public class OcarinaSong extends JavaPlugin {
    private OcarinaSongPlayerListener playerListener = new OcarinaSongPlayerListener(this);
    public Set<Player> musicians = new HashSet<Player>();
    public Server server;
    public NoteBlock noteblock;
    public Map<Player,List> PlayersNotes = new HashMap<Player,List>();
    public Plugin plugin = this;
    
    @Override
    public void onEnable()
    {  
        //Set up commands
        getCommand("ocarina").setExecutor(new OcarinaSongCommand(this,0));
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_INTERACT, playerListener, Priority.Highest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_TOGGLE_SNEAK, playerListener, Priority.Highest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_QUIT, playerListener, Priority.Highest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_RESPAWN, playerListener, Priority.Highest, this);
        getServer().getPluginManager().registerEvent(Event.Type.PLAYER_ITEM_HELD, playerListener, Priority.Highest, this);
        System.out.println("OcarinaSong has initialized!");
        return;
    }

   public boolean isPlaying(Player player) {
        return musicians.contains(player);
    }
    
    public void setPlaying(Player player, boolean enabled) {
        if (enabled) {
            musicians.add(player);
        } else {
            musicians.remove(player);
        }
        return;
    }

    public void hitUp(Player player, Location location) {
        byte musicnote = 0x14;
        /* B */
        PlayThatNote(player, location, musicnote);
        return;
    }

    public void hitLeft(Player player, Location location) {
        byte musicnote = 0x11;
        /* A */
        PlayThatNote(player, location, musicnote);
        return;
    }

    public void hitRight(Player player, Location location) {
        byte musicnote = 0x0F;
        /* G */
        PlayThatNote(player, location, musicnote);
        return;
    }

    public void hitDown(Player player, Location location) {
        byte musicnote = 0x0B;
        /* F */
        PlayThatNote(player, location, musicnote);
        return;
    }

    public void hitShift(Player player, Location location) {
        byte musicnote = 0x08;
        /* Other Note */
        PlayThatNote(player, location, musicnote);
        return;
    }
    
    public Note MakeNote(byte Bite)
    {
    Note Noddy = new Note(Bite);
    
    return Noddy;
    }
    
    public boolean SongCheck(Player player, List YourSong){
        List SongOfTime = new LinkedList();
        SongOfTime = new ArrayList();  
        byte musicnote;
        musicnote = 0x0F;
        SongOfTime.add(musicnote);
        musicnote = 0x08;
        SongOfTime.add(musicnote);
        musicnote = 0x0B;
        SongOfTime.add(musicnote);
        musicnote = 0x0F;
        SongOfTime.add(musicnote);
        musicnote = 0x08;
        SongOfTime.add(musicnote);
        musicnote = 0x0B;
        SongOfTime.add(musicnote);
        List SongOfStorms = new LinkedList();
        SongOfStorms = new ArrayList();  
        musicnote = 0x08;
        SongOfStorms.add(musicnote);
        musicnote = 0x0B;
        SongOfStorms.add(musicnote);
        musicnote = 0x14;
        SongOfStorms.add(musicnote);
        musicnote = 0x08;
        SongOfStorms.add(musicnote);
        musicnote = 0x0B;
        SongOfStorms.add(musicnote);
        musicnote = 0x14;
        SongOfStorms.add(musicnote);
        List SongOfHealing = new LinkedList();
        SongOfHealing = new ArrayList();    
        musicnote = 0x11;
        SongOfHealing.add(musicnote);
        musicnote = 0x0F;
        SongOfHealing.add(musicnote);
        musicnote = 0x0B;
        SongOfHealing.add(musicnote);
        musicnote = 0x11;
        SongOfHealing.add(musicnote);
        musicnote = 0x0F;
        SongOfHealing.add(musicnote);
        musicnote = 0x0B;
        SongOfHealing.add(musicnote);
        List ZeldasLullaby = new LinkedList();
        ZeldasLullaby = new ArrayList();    
        musicnote = 0x11;
        ZeldasLullaby.add(musicnote);
        musicnote = 0x14;
        ZeldasLullaby.add(musicnote);
        musicnote = 0x0F;
        ZeldasLullaby.add(musicnote);
        musicnote = 0x11;
        ZeldasLullaby.add(musicnote);
        musicnote = 0x14;
        ZeldasLullaby.add(musicnote);
        musicnote = 0x0F;
        ZeldasLullaby.add(musicnote);
        List SonataOfAwakening = new LinkedList();
        SonataOfAwakening = new ArrayList();    
        musicnote = 0x14;
        SonataOfAwakening.add(musicnote);
        musicnote = 0x11;
        SonataOfAwakening.add(musicnote);
        musicnote = 0x14;
        SonataOfAwakening.add(musicnote);
        musicnote = 0x11;
        SonataOfAwakening.add(musicnote);
        musicnote = 0x08;
        SonataOfAwakening.add(musicnote);
        musicnote = 0x0F;
        SonataOfAwakening.add(musicnote);
        musicnote = 0x08;
        SonataOfAwakening.add(musicnote);
        
       
        List LastSix = new LinkedList();
        LastSix = new ArrayList();   
        
        LastSix.add(YourSong.get(YourSong.size()-6));
        LastSix.add(YourSong.get(YourSong.size()-5));
        LastSix.add(YourSong.get(YourSong.size()-4));
        LastSix.add(YourSong.get(YourSong.size()-3));
        LastSix.add(YourSong.get(YourSong.size()-2));
        LastSix.add(YourSong.get(YourSong.size()-1));

        
        List LastSeven = new LinkedList();
        LastSeven = new ArrayList();   
        LastSeven.add(YourSong.get(YourSong.size()-7));
        LastSeven.add(YourSong.get(YourSong.size()-6));
        LastSeven.add(YourSong.get(YourSong.size()-5));
        LastSeven.add(YourSong.get(YourSong.size()-4));
        LastSeven.add(YourSong.get(YourSong.size()-3));
        LastSeven.add(YourSong.get(YourSong.size()-2));
        LastSeven.add(YourSong.get(YourSong.size()-1));
        
        
        
            
        if (SongOfTime.equals(LastSix)){
            player.sendMessage(ChatColor.AQUA + "Played the " + ChatColor.GRAY + "Song of Time" + ChatColor.AQUA + "!");
            this.playerListener.PlaySong("time", player);
            return true;
        }
        else if (SongOfStorms.equals(LastSix)){
            player.sendMessage(ChatColor.AQUA + "Played the " + ChatColor.GRAY + "Song of Storms" + ChatColor.AQUA + "!");
            this.playerListener.PlaySong("storms", player);
            return true;
        }
        else if (SongOfHealing.equals(LastSix)){
            player.sendMessage(ChatColor.AQUA + "Played the " + ChatColor.GRAY + "Song of Healing" + ChatColor.AQUA + "!");
            this.playerListener.PlaySong("healing", player);
            return true;
        }
        else if (ZeldasLullaby.equals(LastSix)){
            player.sendMessage(ChatColor.AQUA + "Played " + ChatColor.GRAY + "Zelda's Lullaby" + ChatColor.AQUA + "!");
            this.playerListener.PlaySong("zelda", player);
            return true;
        }
        
        /*if (SonataOfAwakening.equals(LastSeven)){
            player.sendMessage(ChatColor.AQUA + "Played " + ChatColor.GRAY + "Sonata of Awakening" + ChatColor.AQUA + "!");
            this.playerListener.PlaySong("awakening", player);
            return true;
        }*/
        
        
        return false;
    }

    public void PlayThatNote(Player player, Location location, Byte musicnote){
    for (Player thisplayer : Bukkit.getServer().getOnlinePlayers()) { //Send note to every player
        thisplayer.playNote(location, Instrument.PIANO, new Note(musicnote));
    }
    List IndivNotes = new LinkedList();
    IndivNotes = new ArrayList();     
    
    if (PlayersNotes.containsKey(player)){
       IndivNotes = PlayersNotes.get(player);
    }
     
        boolean success = false;
        IndivNotes.add(musicnote);
        
        if (IndivNotes.size()>=6){
            success = SongCheck(player,IndivNotes);
        }
        if (success==true)IndivNotes.clear();
        PlayersNotes.put(player, IndivNotes);
        return; 
    }   

    
    public void PlayThatNoteFreely(Player player, Location location, Byte musicnote){
        for (Player thisplayer : Bukkit.getServer().getOnlinePlayers()) { //Send note to every player
            thisplayer.playNote(location, Instrument.PIANO, new Note(musicnote));
        }
        return; 
    }   

    
    public void listSongs(CommandSender sender){
    
    sender.sendMessage(ChatColor.AQUA + "Songs you can Play:");
    sender.sendMessage(ChatColor.GRAY + "Song of Storms:" + ChatColor.YELLOW + " S V ^ S V ^");
    sender.sendMessage(ChatColor.GRAY + "Song of Time:" + ChatColor.YELLOW + " > S V > S V");
    sender.sendMessage(ChatColor.GRAY + "Song of Healing:" + ChatColor.YELLOW + " < > V < > V");
    sender.sendMessage(ChatColor.GRAY + "Zelda's Lullaby:" + ChatColor.YELLOW + " < ^ > < ^ >");
    return;
    }
    
    
    public void StartOcarina(Player player){
        playerListener.StartPlaying(player);
        return;
    }
    
    
    public void StopOcarina(Player player){
        playerListener.StopPlaying(player);
        return;
    }
    
    public void StopAllOcarina(){
        for (Player thisplayer : Bukkit.getServer().getOnlinePlayers()) { //Send note to every player
            if (isPlaying(thisplayer)){
                StopOcarina(thisplayer);
            }
        }
        return;
    }

    @Override
    public void onDisable()
    {
        StopAllOcarina();
        System.out.println("OcarinaSong has stopped!");
        return;
    }
}
