package net.risenteam.risencore.commands;

import net.risenteam.risencore.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.SimplePluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;

public class CommandManager {

    private SimpleCommandMap commandRegistry;
    private JavaPlugin plugin;

    public CommandManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void init(){
        try{
            Logger.log("Retrieving command registry from plugin manager.");
            SimplePluginManager pluginManager = (SimplePluginManager) Bukkit.getPluginManager();
            Field commandMap = pluginManager.getClass().getDeclaredField("commandMap");
            Logger.log("Found commandMap field.");
            commandMap.setAccessible(true);
            commandRegistry = (SimpleCommandMap) commandMap.get(pluginManager);
            commandMap.setAccessible(false);
            Logger.success("Successfully retrieve command map.");
        }catch (Exception e){
            if (this.plugin.getConfig().getBoolean("error-stack-trace", false)) {
                e.printStackTrace();
            }
            Logger.fail("Failed to retrieve command map.");
            Logger.error(e.getMessage());
        }
    }

    public void register(RisenCommand command){
        commandRegistry.register("risen", command);
    }

}
