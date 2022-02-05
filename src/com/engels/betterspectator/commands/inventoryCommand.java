package com.engels.betterspectator.commands;
import com.connorlinfoot.actionbarapi.ActionBarAPI;
import com.engels.betterspectator.BetterSpectator;
import com.engels.betterspectator.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class inventoryCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage("Must be sent by a player.");
            return true;
        }

        Player player = (Player) sender;

        if(!BetterSpectator.getUsers().get(player.getUniqueId()).isCanSpectate()) {
            ((Player) sender).chat(ChatColor.RED+"Neesi OP");
            return true;
        }

        if(cmd.getName().equalsIgnoreCase("inv")) {
            if(args[0] == null)
                return true;
            String TargetName=args[0];
            Player target=Bukkit.getServer().getPlayer(TargetName);
            ItemStack[] inventoryContent = target.getInventory().getContents();
            int index=0;
            for (ItemStack i : inventoryContent) {
                player.getInventory().setItem(index,i);
                index++;
            }


        }

        return true;
    }
}
