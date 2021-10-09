// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api.packets.channelhandler;

import us.overflow.Overflow;
import net.minecraft.util.io.netty.channel.ChannelPromise;
import net.minecraft.util.io.netty.channel.ChannelHandlerContext;
import net.minecraft.util.io.netty.channel.ChannelDuplexHandler;
import us.overflow.tinyprotocol.reflection.Reflection;
import net.minecraft.util.io.netty.channel.ChannelHandler;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import us.overflow.utils.blockbox.ReflectionUtil;
import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import org.bukkit.entity.Player;
import java.util.WeakHashMap;
import net.minecraft.util.io.netty.channel.Channel;
import java.util.Map;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import us.overflow.tinyprotocol.reflection.FieldAccessor;

public class ChannelHandler1_7 extends ChannelHandlerAbstract
{
    static final FieldAccessor<GameProfile> getGameProfile;
    static final FieldAccessor<Integer> protocolId;
    static final FieldAccessor<Enum> protocolType;
    private Map<String, Channel> channelLookup;
    private Map<Channel, Integer> protocolLookup;
    
    public ChannelHandler1_7() {
        this.channelLookup = new WeakHashMap();
        this.protocolLookup = new WeakHashMap();
    }
    
    public void addChannel(final Player player) {
        final Channel channel = this.getChannel(player);
        this.addChannelHandlerExecutor.execute(this::lambda$addChannel$0);
    }
    
    public void removeChannel(final Player player) {
        final Channel channel = this.getChannel(player);
        this.removeChannelHandlerExecutor.execute(this::lambda$removeChannel$1);
    }
    
    private Channel getChannel(final Player player) {
        return (Channel)Reflections.getNMSClass("NetworkManager").getFirstFieldByType((Class)Channel.class).get(ChannelHandler1_7.networkManagerField.get(ChannelHandler1_7.playerConnectionField.get(ReflectionUtil.getEntityPlayer(player))));
    }
    
    public ProtocolVersion getProtocolVersion(final Player player) {
        final Channel channel = this.channelLookup.get(player.getName());
        if (channel == null) {
            this.channelLookup.put(player.getName(), this.getChannel(player));
        }
        return ProtocolVersion.getVersion((int)this.protocolLookup.getOrDefault(channel, -1));
    }
    
    public void sendPacket(final Player player, final Object packet) {
        this.getChannel(player).pipeline().writeAndFlush(packet);
    }
    
    static {
        ChannelHandler1_7.getGameProfile = Reflection.getField(ChannelHandler1_7.PACKET_LOGIN_IN_START, (Class)GameProfile.class, 0);
        ChannelHandler1_7.protocolId = Reflection.getField(ChannelHandler1_7.PACKET_SET_PROTOCOL, (Class)Integer.TYPE, 0);
        ChannelHandler1_7.protocolType = Reflection.getField(ChannelHandler1_7.PACKET_SET_PROTOCOL, (Class)Enum.class, 0);
    }
    
    private class ChannelHandler extends ChannelDuplexHandler
    {
        private final Player player;
        private final ChannelHandlerAbstract channelHandlerAbstract;
        
        ChannelHandler(final ChannelHandler1_7 this$0, final Player player, final ChannelHandlerAbstract channelHandlerAbstract) {
            this.this$0 = this$0;
            this.player = player;
            this.channelHandlerAbstract = channelHandlerAbstract;
        }
        
        public void write(final ChannelHandlerContext ctx, final Object msg, final ChannelPromise promise) throws Exception {
            final Object packet = Overflow.getInstance().getTinyProtocolHandler().onPacketOutAsync(this.player, msg);
            if (packet != null) {
                super.write(ctx, packet, promise);
            }
        }
        
        public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
            final Channel channel = ctx.channel();
            if (ChannelHandlerAbstract.PACKET_LOGIN_IN_START.isInstance(msg)) {
                final GameProfile profile = (GameProfile)ChannelHandler1_7.getGameProfile.get(msg);
                this.this$0.channelLookup.put(profile.getName(), channel);
            }
            else if (ChannelHandlerAbstract.PACKET_SET_PROTOCOL.isInstance(msg)) {
                final String protocol = ((Enum)ChannelHandler1_7.protocolType.get(msg)).name();
                if (protocol.equalsIgnoreCase("LOGIN")) {
                    this.this$0.protocolLookup.put(channel, ChannelHandler1_7.protocolId.get(msg));
                }
            }
            final Object packet = Overflow.getInstance().getTinyProtocolHandler().onPacketInAsync(this.player, msg);
            if (packet != null) {
                super.channelRead(ctx, packet);
            }
        }
    }
}
