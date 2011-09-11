
package me.jackcrawf3.ocarinasong;


import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.HashSet;
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

/**
 *
 * @author Jackcrawf3
 */
public class OcarinaSong extends JavaPlugin {
    private OcarinaSongPlayerListener playerListener = new OcarinaSongPlayerListener(this);
    public Set<Player> musicians = new HashSet<Player>();
    public Server server;
    public NoteBlock noteblock;
    public Map<Player,Map> PlayersNotes = new HashMap<Player,Map>();
    
    
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
    
    public void SongCheck(Player player, Map YourSong){
    Map<Integer, Note> SongOfTime = new HashMap<Integer, Note>();
    byte BiteNote;
    BiteNote = 0x0F;
    SongOfTime.put(1, MakeNote(BiteNote));
    BiteNote = 0x08;
    SongOfTime.put(2, MakeNote(BiteNote));
    BiteNote = 0x0B;
    SongOfTime.put(3, MakeNote(BiteNote));
    BiteNote = 0x0F;
    SongOfTime.put(4, MakeNote(BiteNote));
    BiteNote = 0x08;
    SongOfTime.put(5, MakeNote(BiteNote));
    BiteNote = 0x0B;
    SongOfTime.put(6, MakeNote(BiteNote));
    if (SongOfTime.equals(YourSong)){
    player.sendMessage("Played song of time!");
    
    }
    
    }
    
    
    public void PlayThatNote(Player player, Location location, Byte musicnote){
    for (Player thisplayer : Bukkit.getServer().getOnlinePlayers()) { //Send note to every player
        thisplayer.playNote(location, Instrument.PIANO, new Note(musicnote));
    }
    
    Map<Integer, Note> IndivNotes = PlayersNotes.get(player);
    if (IndivNotes.keySet().size()==6){
        SongCheck(player, IndivNotes);
        IndivNotes.clear();
    }
    else if (IndivNotes.keySet().size()==1){
    IndivNotes.put(1, new Note (musicnote));
    }
        else if (IndivNotes.keySet().size()==2){
    IndivNotes.put(2, new Note (musicnote));
    }
        else if (IndivNotes.keySet().size()==3){
    IndivNotes.put(3, new Note (musicnote));
    }
        else if (IndivNotes.keySet().size()==4){
    IndivNotes.put(4, new Note (musicnote));
    }
        else if (IndivNotes.keySet().size()==5){
    IndivNotes.put(5, new Note (musicnote));
    }

    PlayersNotes.put(player, IndivNotes);
    return; 
    }   

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void listSongs(Player player){
    
    player.sendMessage(ChatColor.AQUA + "Songs you can Play:");
    player.sendMessage(ChatColor.GRAY + "Song of Storms");
    
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
