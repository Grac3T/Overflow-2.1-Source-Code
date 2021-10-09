// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.events.impl;

import us.overflow.utils.location.CustomLocation;
import org.bukkit.Location;
import us.overflow.base.user.User;
import org.bukkit.entity.Player;
import us.overflow.events.Cancellable;
import us.overflow.events.OverflowEvent;

public class PacketEvent extends OverflowEvent implements Cancellable
{
    private Player player;
    private Object packet;
    private boolean cancelled;
    private String type;
    private Direction direction;
    private User user;
    private long timeStamp;
    private Location to;
    private Location from;
    private CustomLocation newTo;
    private CustomLocation newFrom;
    
    public PacketEvent(final Player player, final Object packet, final String type, final Direction direction, final User user) {
        this.player = player;
        this.packet = packet;
        this.type = type;
        this.direction = direction;
        this.user = user;
        if (user.getOldTo() == null) {
            this.to = player.getLocation();
        }
        if (user.getOldFrom() == null) {
            this.from = player.getLocation();
        }
        if (user.newTo == null) {
            this.newTo = new CustomLocation(0.0, 0.0, 0.0, 0.0f, 0.0f);
        }
        if (user.newFrom == null) {
            this.newFrom = new CustomLocation(0.0, 0.0, 0.0, 0.0f, 0.0f);
        }
        this.to = user.getOldTo();
        this.from = user.getOldFrom();
        this.newTo = user.getNewTo();
        this.newFrom = user.getNewFrom();
        this.timeStamp = System.currentTimeMillis();
    }
    
    public PacketEvent(final Player player, final Object packet, final String type, final Direction direction) {
        this.player = player;
        this.packet = packet;
        this.type = type;
        this.direction = direction;
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
    
    public Direction getDirection() {
        return this.direction;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public long getTimeStamp() {
        return this.timeStamp;
    }
    
    public Location getTo() {
        return this.to;
    }
    
    public Location getFrom() {
        return this.from;
    }
    
    public CustomLocation getNewTo() {
        return this.newTo;
    }
    
    public CustomLocation getNewFrom() {
        return this.newFrom;
    }
    
    public void setPacket(final Object packet) {
        this.packet = packet;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public enum Direction
    {
        public static final Direction CLIENT;
        public static final Direction SERVER;
        
        public static Direction valueOf(final String name) {
            return Enum.valueOf(Direction.class, name);
        }
        
        static {
            Direction.CLIENT = new Direction("CLIENT", 0);
            Direction.SERVER = new Direction("SERVER", 1);
            Direction.$VALUES = new Direction[] { Direction.CLIENT, Direction.SERVER };
        }
    }
}
