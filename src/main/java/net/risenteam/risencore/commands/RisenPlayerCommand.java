package net.risenteam.risencore.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public abstract class RisenPlayerCommand extends RisenCommand {

    public RisenPlayerCommand(String name) {
        super(name);
    }

    public RisenPlayerCommand(String name, String description, String usageMessage, String... aliases) {
        super(name, description, usageMessage, aliases);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof Player){
            this.execute((Player) sender, args);
            return;
        }

        sender.sendMessage(ChatColor.RED + "This command requires a player to work.");
    }

    public abstract void execute(Player player, String[] args);
}
