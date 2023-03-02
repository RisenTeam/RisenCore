package net.risenteam.risencore.commands.defaults;

import net.risenteam.risencore.RisenCore;
import net.risenteam.risencore.commands.RisenCommand;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;

public class CommandRisen extends RisenCommand {

    private final RisenCore core;

    public CommandRisen(RisenCore core) {
        super("risen", "Parent command of all Risen core's commands", null);
        this.core = core;
        addParameter(0, new String[]{"version", "Display the current core version"}, new String[]{"update", "Tells if the plugin has any update"});
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args[0].equalsIgnoreCase("version") ){
            PluginDescriptionFile pluginFile = core.getDescription();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[&f!&c] &oCurrently running on " + pluginFile.getName() + " V" + pluginFile.getVersion() + "."));
        }else if(args[0].equalsIgnoreCase("update")){
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[&f!&c] &oYou are running on the newest version."));
        }else {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c[&f!&c] &oCommand not found."));
        }
    }
}
