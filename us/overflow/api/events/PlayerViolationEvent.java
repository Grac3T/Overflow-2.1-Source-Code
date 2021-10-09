// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class PlayerViolationEvent extends Event
{
    private static final HandlerList handlers;
    private Player p;
    private String checkName;
    private String checkType;
    private int violation;
    private boolean canceled;
    
    public PlayerViolationEvent(final Player p, final String checkName, final String checkType, final int violation) {
        this.p = p;
        this.checkName = checkName;
        this.checkType = checkType;
        this.violation = violation;
    }
    
    public Player getPlayer() {
        return this.p;
    }
    
    public String getCheckName() {
        return this.checkName;
    }
    
    public String getCheckType() {
        return this.checkType;
    }
    
    public int getViolation() {
        return this.violation;
    }
    
    public HandlerList getHandlers() {
        return PlayerViolationEvent.handlers;
    }
    
    public void setCanceled(final boolean canceled) {
        this.canceled = canceled;
    }
    
    public boolean isCanceled() {
        return this.canceled;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerViolationEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
