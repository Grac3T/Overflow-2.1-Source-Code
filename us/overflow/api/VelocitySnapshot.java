// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.api;

public class VelocitySnapshot
{
    public final double length;
    public final long timestamp;
    
    public VelocitySnapshot(final double length) {
        this.timestamp = System.currentTimeMillis();
        this.length = length;
    }
    
    public double getLength() {
        return this.length;
    }
    
    public long getTimestamp() {
        return this.timestamp;
    }
}
