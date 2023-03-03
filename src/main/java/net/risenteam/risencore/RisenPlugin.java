package net.risenteam.risencore;

import com.google.gson.Gson;
import fr.minuskube.inv.InventoryManager;
import net.risenteam.risencore.utils.Logger;
import net.risenteam.risencore.version.RisenWrapper;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class RisenPlugin extends JavaPlugin {

    private boolean managerInitialized = false;

    private final Gson gson = new Gson();
    private final InventoryManager inventoryManager = new InventoryManager(this);
    private RisenWrapper wrapper = find();

    public Gson getGson() {
        return gson;
    }

    public InventoryManager getInventoryManager() {
        if(!managerInitialized){
            managerInitialized = true;
            inventoryManager.init();
        }
        
        return inventoryManager;
    }

    public RisenWrapper getWrapper() {
        if(wrapper.getPlugin() == null) wrapper.setPlugin(this);
        return wrapper;
    }

    private RisenWrapper find(){
        final String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);

        try{
            return (RisenWrapper) Class.forName(getClass().getPackage().getName() + ".version.Wrapper" + version).newInstance();
        }catch (IllegalAccessException | InstantiationException exception) {
            Logger.fail("Failed to instantiate version wrapper for version " + version);
            Logger.error(exception.getMessage());
            Logger.warn("Disabling RisenCore.");
            Bukkit.getPluginManager().disablePlugin(this);
            throw new IllegalStateException("Failed to instantiate version wrapper for version " + version);
        } catch (ClassNotFoundException exception) {
            Logger.error("RisenCore does not support server version " + version);
            Logger.error("Wrapper not found : " + exception.getMessage());
            Logger.warn("Disabling RisenCore.");
            Bukkit.getPluginManager().disablePlugin(this);
            throw new IllegalStateException("RisenCore does not support server version " + version);
        }
    }
}
