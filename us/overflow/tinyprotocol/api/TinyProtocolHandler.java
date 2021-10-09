// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api;

import java.util.WeakHashMap;
import us.overflow.base.user.User;
import us.overflow.events.OverflowEvent;
import us.overflow.events.impl.PacketEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.event.Listener;
import us.overflow.Overflow;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import java.util.Map;
import us.overflow.tinyprotocol.api.packets.ChannelInjector;

public class TinyProtocolHandler
{
    private static ChannelInjector instance;
    public boolean paused;
    private static Map<Player, ProtocolVersion> versionCache;
    private boolean didPosition;
    
    public TinyProtocolHandler() {
        this.paused = false;
        this.didPosition = false;
        TinyProtocolHandler.instance = new ChannelInjector();
        Bukkit.getPluginManager().registerEvents((Listener)TinyProtocolHandler.instance, (Plugin)Overflow.getInstance());
    }
    
    public static void sendPacket(final Player player, final Object packet) {
        TinyProtocolHandler.instance.getChannel().sendPacket(player, packet);
    }
    
    public static ProtocolVersion getProtocolVersion(final Player player) {
        return TinyProtocolHandler.instance.getChannel().getProtocolVersion(player);
    }
    
    public Object onPacketOutAsync(final Player sender, final Object packet) {
        if (!this.paused) {
            final String name = packet.getClass().getName();
            final int index = name.lastIndexOf(".");
            final String packetName = name.substring(index + 1);
            final User user = Overflow.getInstance().getUserManager().getUser(sender.getUniqueId());
            PacketEvent event = new PacketEvent(sender, packet, packetName, PacketEvent.Direction.SERVER);
            if (user != null && System.currentTimeMillis() - user.lastJoin > 500L) {
                event = new PacketEvent(sender, packet, packetName, PacketEvent.Direction.SERVER, user);
            }
            Overflow.getInstance().getEventManager().callEvent(event);
            return event.isCancelled() ? null : event.getPacket();
        }
        return packet;
    }
    
    public Object onPacketInAsync(final Player sender, final Object packet) {
        if (!this.paused) {
            final String name = packet.getClass().getName();
            final int index = name.lastIndexOf(".");
            final String packetName = name.substring(index + 1).replace("PacketPlayInUseItem", "PacketPlayInBlockPlace").replace("PacketPlayInFlying$PacketPlayInLook", "PacketPlayInLook").replace("PacketPlayInFlying$PacketPlayInPosition", "PacketPlayInPosition").replace("PacketPlayInFlying$PacketPlayInPositionLook", "PacketPlayInPositionLook");
            final User user = Overflow.getInstance().getUserManager().getUser(sender.getUniqueId());
            if (packetName.equalsIgnoreCase("PacketPlayInCustomPayload")) {
                user.setClientBrand("vanilla");
            }
            PacketEvent event = new PacketEvent(sender, packet, packetName, PacketEvent.Direction.CLIENT);
            if (user != null && System.currentTimeMillis() - user.lastJoin > 500L) {
                event = new PacketEvent(sender, packet, packetName, PacketEvent.Direction.CLIENT, user);
            }
            Overflow.getInstance().getEventManager().callEvent(event);
            return event.isCancelled() ? null : event.getPacket();
        }
        return packet;
    }
    
    public static ChannelInjector getInstance() {
        return TinyProtocolHandler.instance;
    }
    
    static {
        TinyProtocolHandler.versionCache = new WeakHashMap();
    }
}
