package net.risenteam.risencore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class RisenPlayerCommand extends RisenCommand {

    public RisenPlayerCommand(String name, String... aliases) {
        super(name, aliases);
    }

    public RisenPlayerCommand(String name, String description, String usageMessage, String... aliases) {
        super(name, description, usageMessage, aliases);
    }

    public void onCommand(CommandSender sender, String label, String[] args){
        if(sender instanceof Player){
            this.onCommand((Player) sender, label, args);
        }else{
            sender.sendMessage("You must be a player to execute this command!");
        }
    }

    public abstract void onCommand(Player player, String label, String[] args);
}
