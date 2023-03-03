package net.risenteam.risencore.version;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public interface RisenWrapper {

    void sendTitle(Player player, String title, String subtitle);

    default void broadcastTitle(String title, String subtitle){
        for(Player player : Bukkit.getOnlinePlayers()){
            sendTitle(player, title, subtitle);
        }
    }
    void clearTitle(Player player);
    void sendActionBar(Player player, String message);

    void hidePlayer(JavaPlugin plugin, Player player, Player target);
    void showPlayer(JavaPlugin plugin, Player player, Player target);

}
