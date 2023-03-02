package net.risenteam.risencore.commands;

import net.risenteam.risencore.utils.Logger;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public abstract class RisenCommand extends Command {

    private final List<Parameters> parameters = new ArrayList<>();

    public RisenCommand(String name) {
        super(name);
    }

    public RisenCommand(String name, String description, String usageMessage, String ... aliases) {
        super(name, description, usageMessage, Arrays.asList(aliases));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(args.length == 0 && !parameters.isEmpty()){
            sender.sendMessage("    &c&lHelp Menu");
            for (Parameters parameter : parameters) {
                if(parameter.getPosition() == 0){
                    for (Map.Entry<String, String> entry : parameter.getValues().entrySet()) {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&f/&c" + entry.getKey() + " &7&o&l- &c" + entry.getValue()));
                    }
                }
            }
            return false;
        }
        execute(sender, args);
        return true;
    }

    public abstract void execute(CommandSender sender, String[] args);

    @Override
    public List<String> tabComplete(CommandSender sender, String alias, String[] args) throws IllegalArgumentException {
        List<String> values = new ArrayList<>();

        for (Parameters parameter : parameters) {
            if (parameter.getPosition() == args.length - 1) {
                values.addAll(parameter.tabComplete(args));
            }
        }

        return values;
    }

    public void addParameter(int index, String ... values){
        this.parameters.add(new Parameters(index, values));
    }

    public Parameters addParameter(int index, String[] ... values){
        Parameters parameter = new Parameters(index);
        for (String[] value : values) {
            if(value.length != 2){
                Logger.error("Please provide a pair parameter (value & description)");
                continue;
            }

            parameter.addValue(value[0], value[1]);
        }

        this.parameters.add(parameter);
        return parameter;
    }

}
