// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.minecraft;

public class MCSmoothing
{
    private float x;
    private float y;
    private float z;
    
    public MCSmoothing() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }
    
    public float smooth(float toSmooth, final float increment) {
        this.x += toSmooth;
        toSmooth = (this.x - this.y) * increment;
        this.z += (toSmooth - this.z) * 0.5f;
        if ((toSmooth > 0.0f && toSmooth > this.z) || (toSmooth < 0.0f && toSmooth < this.z)) {
            toSmooth = this.z;
        }
        this.y += toSmooth;
        return toSmooth;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getZ() {
        return this.z;
    }
    
    public float getY() {
        return this.y;
    }
    
    public void reset() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }
}
