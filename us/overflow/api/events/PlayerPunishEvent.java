// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.api.events;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Event;

public class PlayerPunishEvent extends Event
{
    private static final HandlerList handlers;
    private Player p;
    private String checkName;
    private String checkType;
    private boolean canceled;
    
    public PlayerPunishEvent(final Player p, final String checkName, final String checkType) {
        this.p = p;
        this.checkName = checkName;
        this.checkType = checkType;
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
    
    public HandlerList getHandlers() {
        return PlayerPunishEvent.handlers;
    }
    
    public void setCanceled(final boolean canceled) {
        this.canceled = canceled;
    }
    
    public boolean isCanceled() {
        return this.canceled;
    }
    
    public static HandlerList getHandlerList() {
        return PlayerPunishEvent.handlers;
    }
    
    static {
        handlers = new HandlerList();
    }
}
