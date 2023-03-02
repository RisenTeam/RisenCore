package net.risenteam.risencore.version;

import org.bukkit.entity.Player;

public interface RisenWrapper {

    void sendTitle(Player player, String title, String subtitle);
    void clearTitle(Player player);
    void sendActionBar(Player player, String message);

}
