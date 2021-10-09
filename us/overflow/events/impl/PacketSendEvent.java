// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.events.impl;

import org.bukkit.entity.Player;
import us.overflow.events.Cancellable;
import us.overflow.events.OverflowEvent;

public class PacketSendEvent extends OverflowEvent implements Cancellable
{
    private Player player;
    private Object packet;
    private boolean cancelled;
    private String type;
    private long timeStamp;
    
    public PacketSendEvent(final Player player, final Object packet, final String type) {
        this.player = player;
        this.packet = packet;
        this.type = type;
        this.timeStamp = System.currentTimeMillis();
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Object getPacket() {
        return this.packet;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public String getType() {
        return this.type;
    }
    
    public long getTimeStamp() {
        return this.timeStamp;
    }
    
    public void setPacket(final Object packet) {
        this.packet = packet;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
