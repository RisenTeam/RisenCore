package net.risenteam.risencore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;

public abstract class RisenCommand extends Command {

    protected RisenCommand(String name, String ... aliases) {
        super(name, "", "/" + name, new ArrayList<>(Arrays.asList(aliases)));
    }

    protected RisenCommand(String name, String description, String usageMessage, String ... aliases) {
        super(name, description, usageMessage, new ArrayList<>(Arrays.asList(aliases)));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        this.onCommand(sender, label, args);
        return false;
    }

    public abstract void onCommand(CommandSender sender, String label, String[] args);
}
