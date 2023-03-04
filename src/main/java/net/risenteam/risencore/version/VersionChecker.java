package net.risenteam.risencore.version;

import net.risenteam.risencore.utils.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.function.Consumer;

public class VersionChecker {

    private final JavaPlugin plugin;
    private final int resourceId;

    public VersionChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public void getVersion(final Consumer<String> consumer) {
        Bukkit.getScheduler().runTaskAsynchronously(this.plugin, () -> {
            try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
                if (scanner.hasNext()) {
                    consumer.accept(scanner.next());
                }
            } catch (IOException exception) {
                plugin.getLogger().info("Unable to check for updates: " + exception.getMessage());
            }
        });
    }

    public void logVersion(){
        getVersion(version -> {
            if (plugin.getDescription().getVersion().equalsIgnoreCase(version)) {
                Logger.log("You are running the latest version of " + plugin.getName());
            } else {
                Logger.warn("There is a new update available for " + plugin.getName() + " at https://www.spigotmc.org/resources/" + plugin.getName() + ". " + "Your version: " + plugin.getDescription().getVersion() + " New version: " + version);
            }
        });
    }
}