// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.events.impl;

import org.bukkit.entity.Player;
import us.overflow.events.Cancellable;
import us.overflow.events.OverflowEvent;

public class PlayerPhaseEvent extends OverflowEvent implements Cancellable
{
    private Player player;
    private boolean cancelled;
    
    public PlayerPhaseEvent(final Player player) {
        this.player = player;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
}
