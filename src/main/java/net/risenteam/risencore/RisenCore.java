package net.risenteam.risencore;

import net.risenteam.risencore.commands.CommandManager;
import net.risenteam.risencore.commands.defaults.CommandRisen;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RisenCore extends RisenPlugin {

    private final CommandManager commandManager = new CommandManager(this);

    @Override
    public void onEnable() {
        commandManager.init();
        commandManager.register(new CommandRisen(this));

        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            getWrapper().sendTitle(onlinePlayer, "1.0.0", "RISEN CORE");
            getWrapper().sendActionBar(onlinePlayer, "RISEN CORE - RUNNING ON 1.0.0");
        }
    }

}
