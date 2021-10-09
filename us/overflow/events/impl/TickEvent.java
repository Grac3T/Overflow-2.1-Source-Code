// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.events.impl;

import us.overflow.events.OverflowEvent;

public class TickEvent extends OverflowEvent
{
    private int currentTick;
    
    public int getCurrentTick() {
        return this.currentTick;
    }
    
    public TickEvent(final int currentTick) {
        this.currentTick = currentTick;
    }
}
