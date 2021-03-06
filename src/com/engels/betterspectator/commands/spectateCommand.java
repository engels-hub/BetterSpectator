package com.engels.betterspectator.commands;

import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.engels.betterspectator.BetterSpectator;
import com.engels.betterspectator.TitleAPI;
import com.engels.betterspectator.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.*;

import static java.util.Objects.*;
import static org.bukkit.Bukkit.getServer;

public class spectateCommand implements CommandExecutor {




    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        HashMap<UUID,User> users = BetterSpectator.getUsers();
        if(!(sender instanceof Player)) {
            sender.sendMessage("Must be sent by a player.");
            return true;
        }

        Player player = (Player) sender;
        if(!(player.getName().equalsIgnoreCase( "ssunwinds") || player.getName().equalsIgnoreCase( "enge1s") || player.getName().equalsIgnoreCase("ProxyBeer") || player.getName().equalsIgnoreCase( "RagingBones03") || player.getName().equalsIgnoreCase( "mangalis"))) {
            ((Player) sender).sendMessage(ChatColor.RED+"Neesi OP");
            return true;
        }
        //boolean canSpectate=users.get(player.getUniqueId()).isCanSpectate();
/*
        if((cmd.getName().equalsIgnoreCase("promote")) && player.isOp()) {

            getServer().getConsoleSender().sendMessage(ChatColor.YELLOW+"BetterSpectator is on!");
            if(args[0] == null)
                return true;
            String playerName=args[0];
            if(Bukkit.getPlayer(playerName) == null)
                return true;
            BetterSpectator.setUser(player.getUniqueId(),BetterSpectator.getUsers().get(player.getUniqueId()).setCanSpectate(true));
        }
        if((cmd.getName().equalsIgnoreCase("demote")) && player.isOp()) {

            getServer().getConsoleSender().sendMessage(ChatColor.YELLOW+"BetterSpectator is on!");
            if(args[0] == null)
                return true;
            String playerName=args[0];
            if(Bukkit.getPlayer(playerName) == null)
                return true;
            BetterSpectator.setUser(player.getUniqueId(),BetterSpectator.getUsers().get(player.getUniqueId()).setCanSpectate(false));
        }

        if(!canSpectate) {
            ((Player) sender).chat(ChatColor.RED+"Neesi OP");
            return true;
        }
*/


        if((cmd.getName().equalsIgnoreCase("vanish"))){
            for (User p : users.values())
            {
                if(!p.isCanSpectate() && (getServer().getPlayer(p.getName()) != null)){
                    Bukkit.getPlayer(p.getName()).hidePlayer(player);
                    player.spigot().setCollidesWithEntities(false);
                }
            }
            return true;
        }
        if((cmd.getName().equalsIgnoreCase("unvanish"))){
            for (User p : users.values()) {
                if (getServer().getPlayer(p.getName()) != null) {
                    Bukkit.getPlayer(p.getName()).showPlayer(player);
                    player.spigot().setCollidesWithEntities(true);
                }
            }
        }



        if((cmd.getName().equalsIgnoreCase("spec"))) {
            player.performCommand("exit ");
        if(args[0] == null)
            return true;
        String TargetName=args[0];
        Player target= getServer().getPlayer(TargetName);

        if (getServer().getPlayer(TargetName) == null)
            return true;
        if(Objects.equals(player.getName(), TargetName))
            return true;
            player.performCommand("vanish");

            player.setAllowFlight(true);
            User modified=users.get(target.getUniqueId());
            modified.getSpectated().add(player.getName());
            BetterSpectator.setUser(target.getUniqueId(), modified);
            modified=users.get(player.getUniqueId());
            modified.setSpectateTarget(target.getName());
            modified.setClip(false);
            BetterSpectator.setUser(player.getUniqueId(), modified);

            //teleport player to target
            Location targetLocation=target.getEyeLocation();
            targetLocation.setY(targetLocation.getY()+2);
            player.teleport(targetLocation);

            //score
            Integer id = Bukkit.getScheduler().runTaskTimer(BetterSpectator.getInstance(), () -> {
                int k = BetterSpectator.getUsers().get(target.getUniqueId()).getKillCount();
                int d = BetterSpectator.getUsers().get(target.getUniqueId()).getDeathCount();
                //player.sendMessage("Spectating "+target.getName()+" ??? "+String.valueOf(target.getHealth())+" K/D "+k+"/"+d+" ServerCPS "+ cps);
                ActionBarAPI.sendActionBar(player,"Spectating "+target.getName()+" ??? "+String.valueOf(target.getHealth())+" K/D "+k+"/"+d);
            }, 0L, 20L).getTaskId();
            modified.setRunnableID(id);
            BetterSpectator.setUser(player.getUniqueId(), modified);
            player.performCommand("inv "+target.getName());
        }

        if((cmd.getName().equalsIgnoreCase("clip"))) {
            player.performCommand("exit ");

            if(args[0] == null)
                return true;
            String TargetName=args[0];
            Player target= getServer().getPlayer(TargetName);

            if (getServer().getPlayer(TargetName) == null)
                return true;
            if(Objects.equals(player.getName(), TargetName))
                return true;
            player.performCommand("vanish");

            player.setAllowFlight(true);
            User modified=users.get(target.getUniqueId());
            modified.getSpectated().add(player.getName());
            BetterSpectator.setUser(target.getUniqueId(), modified);
            modified=users.get(player.getUniqueId());
            modified.setSpectateTarget(target.getName());
            modified.setClip(true);
            BetterSpectator.setUser(player.getUniqueId(), modified);


            //teleport player to target
            Location targetLocation=target.getEyeLocation();
            targetLocation.setY(targetLocation.getY()+2);
            player.teleport(targetLocation);

            //score
            Integer id = Bukkit.getScheduler().runTaskTimer(BetterSpectator.getInstance(), () -> {
                int k = BetterSpectator.getUsers().get(target.getUniqueId()).getKillCount();
                int d = BetterSpectator.getUsers().get(target.getUniqueId()).getDeathCount();
                //player.sendMessage("Spectating "+target.getName()+" ??? "+String.valueOf(target.getHealth())+" K/D "+k+"/"+d+" ServerCPS "+ cps);
                ActionBarAPI.sendActionBar(player,"Spectating "+target.getName()+" ??? "+String.valueOf(target.getHealth())+" K/D "+k+"/"+d);
            }, 0L, 20L).getTaskId();
            modified.setRunnableID(id);
            BetterSpectator.setUser(player.getUniqueId(), modified);
            player.performCommand("inv "+target.getName());
        }

        if((cmd.getName().equalsIgnoreCase("exit"))) {
            //remove player from target spectated

            Player target;
            player.performCommand("unvanish");
            //player.setAllowFlight(false);
            if(BetterSpectator.getUsers().get(player.getUniqueId()).getSpectateTarget() == null) return true;
            target = Bukkit.getPlayer(BetterSpectator.getUsers().get(player.getUniqueId()).getSpectateTarget());
            BetterSpectator.getUsers().get(player.getUniqueId()).setSpectateTarget(null);
            Bukkit.getScheduler().cancelTask(BetterSpectator.getUsers().get(player.getUniqueId()).getRunnableID());
            for (User t : users.values()) {
                if(Objects.equals(t.getName(), target.getName())){
                    for(int i = 0; i< t.getSpectated().size(); i++){
                        if(Objects.equals(player.getName(), t.getSpectated().get(i))){
                            t.getSpectated().remove(i);
                            return true;
                        }
                    }
                }
            }

            User t=users.get(target.getUniqueId());
            for(int i = 0; i< t.getSpectated().size(); i++){
                if(Objects.equals(player.getName(), t.getSpectated().get(i))){
                    t.getSpectated().remove(i);
                    BetterSpectator.setUser(target.getUniqueId(),t);
                    return true;
                }
            }

        }

        if((cmd.getName().equalsIgnoreCase("next"))) {
            shiftSpectation(player, users, 1);
        }
        if((cmd.getName().equalsIgnoreCase("prev"))) {
            shiftSpectation(player, users, -1);
        }
        if((cmd.getName().equalsIgnoreCase("showall"))) {
            for (User user : BetterSpectator.getUsers().values()) {
                player.sendMessage(user.isCanSpectate() +" "+ user.getName());
            }
        }
        if((cmd.getName().equalsIgnoreCase("wipe"))) {
            if(args[0] == null)
                return true;
            String TargetName=args[0];
            Player target= getServer().getPlayer(TargetName);

            if (getServer().getPlayer(TargetName) == null)
                return true;
            if(Objects.equals(player.getName(), TargetName))
                return true;

            BetterSpectator.setUser(target.getUniqueId(),BetterSpectator.getUsers().get(target.getUniqueId()).setKillCount(0).setDeathCount(0));

        }


        return true;
    }

    public void shiftSpectation(Player player, HashMap<UUID,User> users, int  shifter){
        HashMap<UUID,User> players=new HashMap<>();
        String targetName = null;

        //create lists for OP and players
        for (Map.Entry<UUID, User> userEntry : users.entrySet()) {
            //!userEntry.getValue().isCanSpectate()

            if(!(player.getName().equalsIgnoreCase( "ssunwinds") || player.getName().equalsIgnoreCase( "enge1s") || player.getName().equalsIgnoreCase("ProxyBeer") || player.getName().equalsIgnoreCase( "RagingBones03") || player.getName().equalsIgnoreCase( "mangalis"))) {
                players.put(userEntry.getKey(), userEntry.getValue());      //every player
            }else if(userEntry.getKey() == player.getUniqueId()){   //spectator
                if(!isNull(BetterSpectator.getUsers().get(player.getUniqueId()).getRunnableID()))
                    Bukkit.getScheduler().cancelTask(BetterSpectator.getUsers().get(player.getUniqueId()).getRunnableID());
                TitleAPI.sendTabTitle(player, "", "");
                targetName = userEntry.getValue().getSpectateTarget();   //get targetName from spectator
            }

        }
        if(isNull(targetName)) return;
        ArrayList<User> playerList= new ArrayList<User> (players.values());
        int targetIndex=playerList.indexOf(users.get(Bukkit.getPlayer(targetName).getUniqueId()));

        if(targetIndex!=-1){
            Collections.rotate(playerList, shifter);//shift player array by one: [a, b, c] -> [c, a, b]
            player.performCommand("exit");    //                               ^            ^    targetIndex didn't change, but corresponding player did
            player.performCommand("spec " + playerList.get(targetIndex).getName());   //spectate  next player
        }
    }
}











