package com.engels.betterspectator;

import com.engels.betterspectator.commands.inventoryCommand;
import com.engels.betterspectator.commands.spectateCommand;
import com.engels.betterspectator.events.events;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BetterSpectator extends JavaPlugin {
    private static HashMap<UUID,User> users = new HashMap<UUID, User>();
    private static BetterSpectator instance;

    public static BetterSpectator getInstance() {
        return instance;
    }

    public static HashMap<UUID, User> getUsers() {
        return users;
    }



    public static void setUser(UUID id, User user) {
            BetterSpectator.users.put(id, user);
    }

    public static void setUsers(HashMap<UUID, User> users) {
        BetterSpectator.users = users;
    }

    @Override
    public void onEnable(){
        instance = this;
        spectateCommand SpectationBloc = new spectateCommand();
        inventoryCommand InventoryBloc = new inventoryCommand();
        getServer().getPluginManager().registerEvents(new events(), this);
        getCommand("vanish").setExecutor(SpectationBloc);
        getCommand("unvanish").setExecutor(SpectationBloc);
        getCommand("spec").setExecutor(SpectationBloc);
        getCommand("exit").setExecutor(SpectationBloc);
        getCommand("inv").setExecutor(InventoryBloc);
        getCommand("next").setExecutor(SpectationBloc);
        getCommand("prev").setExecutor(SpectationBloc);
        getCommand("promote").setExecutor(SpectationBloc);
        getCommand("demote").setExecutor(SpectationBloc);
        getCommand("showall").setExecutor(SpectationBloc);
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW+"BetterSpectator is on!");


    }

    @Override
    public void onDisable(){
        getServer().getConsoleSender().sendMessage(ChatColor.YELLOW+"BetterSpectator is off");
    }


}


