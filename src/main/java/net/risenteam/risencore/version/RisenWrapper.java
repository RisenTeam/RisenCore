package net.risenteam.risencore.version;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface RisenWrapper {

    void setPlugin(JavaPlugin plugin);

    JavaPlugin getPlugin();

    void sendTitle(Player player, String title, String subtitle);

    default void broadcastTitle(String title, String subtitle) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendTitle(player, title, subtitle);
        }
    }

    void clearTitle(Player player);

    default void broadcastClearTitle() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            clearTitle(player);
        }
    }

    void sendActionBar(Player player, String message);

    default void broadcastActionBar(String message) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendActionBar(player, message);
        }
    }

    void hidePlayer(Player player, Player target);

    void showPlayer(Player player, Player target);

}
