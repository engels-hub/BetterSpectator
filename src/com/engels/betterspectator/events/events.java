package com.engels.betterspectator.events;
import com.engels.betterspectator.BetterSpectator;
import com.engels.betterspectator.TitleAPI;
import com.engels.betterspectator.User;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import java.util.ArrayList;
import java.util.Objects;

import static org.bukkit.Bukkit.getServer;

public class events implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event){

        Player player= event.getPlayer();

        //TitleAPI.sendTitle(player, 0, 5, 0, "Yo", player.getName());
        TitleAPI.sendTabTitle(player, "Yo", player.getName());

        if(BetterSpectator.getUsers().containsKey(player.getUniqueId())) return;

        User newUser= new User( getServer().getOnlinePlayers().size(), player.getName(), player.isOp(),new ArrayList<String>(), null, null, 0, 0,0,null);


        BetterSpectator.setUser(player.getUniqueId(),newUser);

        for (User user : BetterSpectator.getUsers().values()){
            player.sendMessage(user.getName());
        }

        if(player.isOp()){
            player.setAllowFlight(true);
        }


    }

    @EventHandler
    public static void onPlayerDeath(PlayerDeathEvent event){

        Player player= event.getEntity();

        BetterSpectator.setUser(player.getUniqueId(),BetterSpectator.getUsers().get(player.getUniqueId()).incrementDeathCount());

    }

    @EventHandler
    public static void onPlayerMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        //check if this player is being watched

        for (User p : BetterSpectator.getUsers().values()) {
            if(p.isCanSpectate())continue;

            if(!Objects.equals(p.getName(), player.getName()))continue; //if not, continue through loop

            if(p.getSpectated().size()==0)return;
            Location playerLocation=player.getEyeLocation();

            double multiplier = 0;
            Location newLocation = geometryMagic(multiplier, playerLocation);

            while(true) {

                //some geometry magic
                multiplier=multiplier+0.25;
                newLocation = geometryMagic(multiplier, playerLocation);
                if(newLocation.getBlock().getType() != Material.AIR)break;
                if(multiplier >= 1.25)break;
            }
            newLocation = geometryMagic(multiplier-1, playerLocation);
            if (p.getSpectated().size() == 0) return;
            for (String spectator : p.getSpectated()) {
                Bukkit.getPlayer(spectator).teleport(newLocation); //badabing badaboom
            }
        }

    }

    static Location geometryMagic(double multiplier, Location location){

        //we need to place player directly behind target's sight vector start
        //coordinates are normalised vector's coord times multiplier
        //It is changed if final coords of this func are not reachable; otherwise defaults to two blocks (as in 3rd person view)
        //Coordinates are being computed by subtracting normalised vector coords times multiplier from oldLocation coords.
        Vector sightVectorNormalised = location.getDirection().normalize().multiply(multiplier);

        location.setX(location.getX()-sightVectorNormalised.getX());
        location.setY(location.getY()-sightVectorNormalised.getY()-0.25);
        location.setZ(location.getZ()-sightVectorNormalised.getZ());
        return location;
    }



    @EventHandler
    public static void onInventoryClick(InventoryClickEvent event){
        Player target = (Player) event.getWhoClicked();
        for (User p : BetterSpectator.getUsers().values()) {
            if(p.isCanSpectate())continue;
            if(!Objects.equals(p.getName(), target.getName()))continue; //if not, continue through loop
            for(String pl : p.getSpectated()){
                copyInventory(Bukkit.getPlayer(pl),target);
            }

        }
    }

    @EventHandler
    public static void onInventoryOpen(InventoryOpenEvent event){

        Player target = (Player) event.getPlayer();
        for (User p : BetterSpectator.getUsers().values()) {
            if(p.isCanSpectate())continue;
            if(!Objects.equals(p.getName(), target.getName()))continue; //if not, continue through loop
            for(String pl : p.getSpectated()){
                copyInventory(Bukkit.getPlayer(pl),target);
            }

        }
    }

    @EventHandler
    public static void onInventoryClose(InventoryCloseEvent event){
        Player target = (Player) event.getPlayer();
        for (User p : BetterSpectator.getUsers().values()) {
            if(p.isCanSpectate())continue;
            if(!Objects.equals(p.getName(), target.getName()))continue; //if not, continue through loop
            for(String pl : p.getSpectated()){
                copyInventory(Bukkit.getPlayer(pl),target);
            }

        }
    }

    @EventHandler
    public static void onInventoryDrag(InventoryDragEvent event){
        Player target = (Player) event.getWhoClicked();

        for (User p : BetterSpectator.getUsers().values()) {
            if(p.isCanSpectate())continue;
            if(!Objects.equals(p.getName(), target.getName()))continue; //if not, continue through loop
            for(String pl : p.getSpectated()){
                copyInventory(Bukkit.getPlayer(pl),target);
            }

        }
    }

    @EventHandler
    public static void onHandChange(PlayerItemHeldEvent event){
        Player target = event.getPlayer();
        int hand = event.getNewSlot();

        for (User p : BetterSpectator.getUsers().values()) {
            if(p.isCanSpectate())continue;
            if(!Objects.equals(p.getName(), target.getName()))continue; //if not, continue through loop
            for(String pl : p.getSpectated()){
                copyInventory(Bukkit.getPlayer(pl),target);
                Bukkit.getPlayer(pl).getInventory().setHeldItemSlot(hand);
            }

        }
    }

    public static void copyInventory(Player player, Player target){
        ItemStack[] inventoryContent = target.getInventory().getContents();
        int index=0;
        for (ItemStack i : inventoryContent) {
            player.getInventory().setItem(index,i);
            index++;
        }
    }


    @EventHandler
    public static void onDamage(EntityDamageEvent event){
        if (event.getEntity().isOp()){
            event.setCancelled(true);
        }
    }
}
