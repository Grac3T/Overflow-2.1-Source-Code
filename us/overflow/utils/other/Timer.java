// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.other;

public class Timer
{
    private long lastTime;
    
    public Timer() {
        this.reset();
    }
    
    public void reset() {
        this.lastTime = System.currentTimeMillis();
    }
    
    public boolean hasReached(final double miliseconds) {
        return System.currentTimeMillis() - this.lastTime >= miliseconds;
    }
    
    public long getLastTime() {
        return this.lastTime;
    }
}
