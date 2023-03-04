package net.risenteam.risencore.commands;

import net.risenteam.risencore.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class CommandManager {

    private final SimpleCommandMap commandRegistry;

    public CommandManager() {
        try{
            SimplePluginManager pluginManager = (SimplePluginManager) Bukkit.getPluginManager();
            Field commandMap = pluginManager.getClass().getDeclaredField("commandMap");
            commandMap.setAccessible(true);
            commandRegistry = (SimpleCommandMap) commandMap.get(pluginManager);
            commandMap.setAccessible(false);
            Logger.success("Successfully got command registry from plugin manager");
        }catch (NoSuchFieldException | IllegalAccessException exception) {
            throw new IllegalStateException("Failed to get command registry from plugin manager");
        }
    }

    public void registerCommand(RisenCommand command){
        commandRegistry.register("risen", command);
    }
}
