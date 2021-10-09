// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api.packets;

import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.EventPriority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import us.overflow.base.user.User;
import us.overflow.Overflow;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.api.packets.channelhandler.ChannelHandler1_7;
import us.overflow.tinyprotocol.api.packets.channelhandler.ChannelHandler1_8;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import us.overflow.tinyprotocol.api.packets.channelhandler.ChannelHandlerAbstract;
import org.bukkit.event.Listener;

public class ChannelInjector implements Listener
{
    private ChannelHandlerAbstract channel;
    
    public ChannelInjector() {
        this.channel = (ChannelHandlerAbstract)(ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_8) ? new ChannelHandler1_8() : new ChannelHandler1_7());
    }
    
    public void addChannel(final Player player) {
        Overflow.getInstance().getExecutorService().execute(this::lambda$addChannel$0);
    }
    
    public void removeChannel(final Player player) {
        final User user = Overflow.getInstance().getUserManager().getUser(player.getUniqueId());
        if (user != null) {
            Overflow.getInstance().getUserManager().removeUser(user);
        }
        this.channel.removeChannel(player);
    }
    
    @EventHandler(priority = EventPriority.LOWEST)
    public void onJoin(final PlayerJoinEvent event) {
        this.addChannel(event.getPlayer());
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent event) {
        this.removeChannel(event.getPlayer());
    }
    
    public ChannelHandlerAbstract getChannel() {
        return this.channel;
    }
}
