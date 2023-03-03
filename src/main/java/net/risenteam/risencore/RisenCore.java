package net.risenteam.risencore;

import net.risenteam.risencore.commands.CommandManager;
import net.risenteam.risencore.commands.defaults.CommandRisen;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class RisenCore extends RisenPlugin {

    private final CommandManager commandManager = new CommandManager(this);
    @Override
    public void onEnable() {
        commandManager.init();
        commandManager.register(new CommandRisen(this));
    }

}
