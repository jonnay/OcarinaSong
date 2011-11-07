/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.jackcrawf3.ocarinasong;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;
import org.bukkit.event.entity.EntityTargetEvent;

/**
 *
 * @author Jackcrawf3
 */
public class OcarinaSongEntityListener extends EntityListener{
    OcarinaSong plugin;
    
    
    public OcarinaSongEntityListener(OcarinaSong plugin){
        this.plugin = plugin;
    }
    
    @Override
    public void onEntityTarget(EntityTargetEvent event) {
        if (plugin.vehicleByEntity.containsKey(event.getEntity())){
            if (plugin.vehicleByEntity.get(event.getEntity()).equals(event.getTarget())){
                event.setCancelled(true);
            }
        }
    }

    @Override
    public void onEntityDeath(EntityDeathEvent event) {
        if (plugin.vehicleByEntity.containsKey(event.getEntity())){
            Player rider = plugin.vehicleByEntity.get(event.getEntity());
            rider.sendMessage(ChatColor.RED + "Your pet has died!");
            plugin.vehicleByEntity.remove(event.getEntity());
            plugin.vehicleByPlayer.remove(rider);
        }
    }
    
    
    
    
    
    
    
}
