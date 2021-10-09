// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.minecraft.vector;

import us.overflow.utils.minecraft.vector.util.Vector2f;

public class Vec2f extends Vector2f
{
    public Vec2f(final float x, final float y) {
        super(x, y);
    }
    
    public Vec2f() {
        super(0.0f, 0.0f);
    }
    
    public Vec2f add(final double x, final double y) {
        this.x += (float)x;
        this.y += (float)y;
        return this;
    }
    
    public Vec2f add(final float x, final float y) {
        this.x += x;
        this.y += y;
        return this;
    }
    
    public Vec2f add(final int x, final int y) {
        this.x += x;
        this.y += y;
        return this;
    }
    
    public Vec2f add(final Vec2f vec) {
        this.x += vec.x;
        this.y += vec.y;
        return this;
    }
    
    public Vec2f subtract(final double x, final double y) {
        this.x -= (float)x;
        this.y -= (float)y;
        return this;
    }
    
    public Vec2f subtract(final float x, final float y) {
        this.x -= x;
        this.y -= y;
        return this;
    }
    
    public Vec2f subtract(final Vec2f vec) {
        this.x -= vec.x;
        this.y -= vec.y;
        return this;
    }
    
    public Vec2f subtract(final int x, final int y) {
        this.x -= x;
        this.y -= y;
        return this;
    }
    
    public Vec2f multiply(final float number) {
        this.x *= this.x;
        this.y *= this.y;
        return this;
    }
    
    public Vec2f multiply(final Vec2f vec) {
        this.x *= vec.x;
        this.y *= vec.y;
        return this;
    }
    
    public Vec2f multiply(final float x, final float y) {
        this.x *= x;
        this.y *= y;
        return this;
    }
    
    public Vec2f set(final Vec2f vec) {
        this.x = vec.x;
        this.y = vec.y;
        return this;
    }
    
    public double squareDistanceTo(final Vec2f vec) {
        final double var2 = vec.x - this.x;
        final double var3 = vec.y - this.y;
        return var2 * var2 + var3 * var3;
    }
    
    public Vec2f divide(final float number) {
        this.x /= number;
        this.y /= number;
        return this;
    }
    
    public Vec2f clone() {
        return new Vec2f(this.x, this.y);
    }
    
    public static Vec2f diff(final Vec2f one, final Vec2f two) {
        return new Vec2f(two.x - one.x, two.y - one.y);
    }
}
