package net.risenteam.risencore.version;

import net.minecraft.server.v1_9_R1.IChatBaseComponent;
import net.minecraft.server.v1_9_R1.PacketPlayOutChat;
import net.minecraft.server.v1_9_R1.PacketPlayOutTitle;
import org.bukkit.craftbukkit.v1_9_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Wrapper1_9_R1 implements RisenWrapper {

    private JavaPlugin plugin;

    @Override
    public void setPlugin(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public JavaPlugin getPlugin() {
        return plugin;
    }


    @Override
    public void sendTitle(Player player, String title, String subtitle) {
        PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + title + "\"}"));
        PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + subtitle + "\"}"));

        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetTitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetSubtitle);
    }

    @Override
    public void clearTitle(Player player) {
        PacketPlayOutTitle packetClear = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.CLEAR, IChatBaseComponent.ChatSerializer.a(""));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetClear);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        PacketPlayOutChat packetActionBar = new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetActionBar);
    }

    @Override
    public void hidePlayer(Player player, Player target) {
        player.hidePlayer(target);
    }

    @Override
    public void showPlayer(Player player, Player target) {
        player.showPlayer(target);
    }

}
