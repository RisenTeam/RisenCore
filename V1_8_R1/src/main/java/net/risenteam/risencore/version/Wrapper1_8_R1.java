package net.risenteam.risencore.version;

import net.minecraft.server.v1_8_R1.*;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class Wrapper1_8_R1 implements RisenWrapper {

    @Override
    public void sendTitle(Player player, String title, String subtitle) {
        PacketPlayOutTitle packetTitle = new PacketPlayOutTitle(EnumTitleAction.TITLE, ChatSerializer.a("{\"text\":\"" + title + "\"}"));
        PacketPlayOutTitle packetSubtitle = new PacketPlayOutTitle(EnumTitleAction.SUBTITLE, ChatSerializer.a("{\"text\":\"" + subtitle + "\"}"));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetTitle);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetSubtitle);
    }

    @Override
    public void clearTitle(Player player) {
        PacketPlayOutTitle packetClear = new PacketPlayOutTitle(EnumTitleAction.CLEAR, ChatSerializer.a(""));
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetClear);
    }

    @Override
    public void sendActionBar(Player player, String message) {
        PacketPlayOutChat packetActionBar = new PacketPlayOutChat(ChatSerializer.a("{\"text\":\"" + message + "\"}"), (byte) 2);
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packetActionBar);
    }
}
