// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils;

import us.overflow.Overflow;

public class TickTimer
{
    private int ticks;
    private int defaultPassed;
    
    public TickTimer(final int defaultPassed) {
        this.ticks = Overflow.getInstance().getCurrentTicks();
        this.defaultPassed = defaultPassed;
    }
    
    public void reset() {
        this.ticks = Overflow.getInstance().getCurrentTicks();
    }
    
    public boolean hasPassed() {
        return Overflow.getInstance().getCurrentTicks() - this.ticks > this.defaultPassed;
    }
    
    public boolean hasPassed(final int amount) {
        return Overflow.getInstance().getCurrentTicks() - this.ticks > amount;
    }
    
    public boolean hasNotPassed() {
        return Overflow.getInstance().getCurrentTicks() - this.ticks <= this.defaultPassed;
    }
    
    public boolean hasNotPassed(final int amount) {
        return Overflow.getInstance().getCurrentTicks() - this.ticks <= amount;
    }
    
    public int getPassed() {
        return Overflow.getInstance().getCurrentTicks() - this.ticks;
    }
}
