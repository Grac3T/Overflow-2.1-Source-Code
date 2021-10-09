// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api.packets.channelhandler;

import us.overflow.tinyprotocol.reflection.Reflection;
import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedField;

public abstract class ChannelHandlerAbstract
{
    static final WrappedField networkManagerField;
    static final WrappedField playerConnectionField;
    static final Class<?> PACKET_SET_PROTOCOL;
    static final Class<?> PACKET_LOGIN_IN_START;
    final Executor addChannelHandlerExecutor;
    final Executor removeChannelHandlerExecutor;
    final String handlerKey;
    final String playerKey;
    
    ChannelHandlerAbstract() {
        this.addChannelHandlerExecutor = Executors.newSingleThreadExecutor();
        this.removeChannelHandlerExecutor = Executors.newSingleThreadExecutor();
        this.handlerKey = "packet_handler";
        this.playerKey = "atlas_player_handler";
    }
    
    public abstract void addChannel(final Player p0);
    
    public abstract void removeChannel(final Player p0);
    
    public abstract void sendPacket(final Player p0, final Object p1);
    
    public abstract ProtocolVersion getProtocolVersion(final Player p0);
    
    static {
        ChannelHandlerAbstract.networkManagerField = Reflections.getNMSClass("PlayerConnection").getFieldByName("networkManager");
        ChannelHandlerAbstract.playerConnectionField = Reflections.getNMSClass("EntityPlayer").getFieldByName("playerConnection");
        ChannelHandlerAbstract.PACKET_SET_PROTOCOL = Reflection.getMinecraftClass("PacketHandshakingInSetProtocol");
        ChannelHandlerAbstract.PACKET_LOGIN_IN_START = Reflection.getMinecraftClass("PacketLoginInStart");
    }
}
