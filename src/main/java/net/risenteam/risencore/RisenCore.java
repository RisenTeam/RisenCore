package net.risenteam.risencore;

import net.risenteam.risencore.commands.CommandManager;
import net.risenteam.risencore.commands.defaults.CommandRisen;
import org.bukkit.Bukkit;

public class RisenCore extends RisenPlugin {

    private final CommandManager commandManager = new CommandManager(this);
    @Override
    public void onEnable() {
        commandManager.init();
        commandManager.register(new CommandRisen(this)); // Register the /risen command
    }

}
