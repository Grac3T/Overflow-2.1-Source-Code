// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class Vec3D extends NMSObject
{
    private static FieldAccessor<Double> fieldX;
    private static FieldAccessor<Double> fieldY;
    private static FieldAccessor<Double> fieldZ;
    public final double a;
    public final double b;
    public final double c;
    
    public Vec3D(final Object obj) {
        this.setObject(obj);
        this.a = (double)this.fetch((FieldAccessor)Vec3D.fieldX);
        this.b = (double)this.fetch((FieldAccessor)Vec3D.fieldY);
        this.c = (double)this.fetch((FieldAccessor)Vec3D.fieldZ);
    }
    
    public Vec3D(double var1, double var3, double var5) {
        if (var1 == -0.0) {
            var1 = 0.0;
        }
        if (var3 == -0.0) {
            var3 = 0.0;
        }
        if (var5 == -0.0) {
            var5 = 0.0;
        }
        this.a = var1;
        this.b = var3;
        this.c = var5;
    }
    
    public Vec3D(final BaseBlockPosition var1) {
        this(var1.getX(), var1.getY(), var1.getZ());
    }
    
    public Vec3D a() {
        final double var1 = MathHelper.sqrt(this.a * this.a + this.b * this.b + this.c * this.c);
        return (var1 < 1.0E-4) ? new Vec3D(0.0, 0.0, 0.0) : new Vec3D(this.a / var1, this.b / var1, this.c / var1);
    }
    
    public double b(final Vec3D var1) {
        return this.a * var1.a + this.b * var1.b + this.c * var1.c;
    }
    
    public Vec3D d(final Vec3D var1) {
        return this.a(var1.a, var1.b, var1.c);
    }
    
    public Vec3D a(final double var1, final double var3, final double var5) {
        return this.add(-var1, -var3, -var5);
    }
    
    public Vec3D e(final Vec3D var1) {
        return this.add(var1.a, var1.b, var1.c);
    }
    
    public Vec3D add(final double var1, final double var3, final double var5) {
        return new Vec3D(this.a + var1, this.b + var3, this.c + var5);
    }
    
    public double distanceSquared(final Vec3D var1) {
        final double var2 = var1.a - this.a;
        final double var3 = var1.b - this.b;
        final double var4 = var1.c - this.c;
        return var2 * var2 + var3 * var3 + var4 * var4;
    }
    
    public double b() {
        return MathHelper.sqrt(this.a * this.a + this.b * this.b + this.c * this.c);
    }
    
    public Vec3D a(final Vec3D var1, final double var2) {
        final double var3 = var1.a - this.a;
        final double var4 = var1.b - this.b;
        final double var5 = var1.c - this.c;
        if (var3 * var3 < 1.0000000116860974E-7) {
            return null;
        }
        final double var6 = (var2 - this.a) / var3;
        return (var6 >= 0.0 && var6 <= 1.0) ? new Vec3D(this.a + var3 * var6, this.b + var4 * var6, this.c + var5 * var6) : null;
    }
    
    public Vec3D b(final Vec3D var1, final double var2) {
        final double var3 = var1.a - this.a;
        final double var4 = var1.b - this.b;
        final double var5 = var1.c - this.c;
        if (var4 * var4 < 1.0000000116860974E-7) {
            return null;
        }
        final double var6 = (var2 - this.b) / var4;
        return (var6 >= 0.0 && var6 <= 1.0) ? new Vec3D(this.a + var3 * var6, this.b + var4 * var6, this.c + var5 * var6) : null;
    }
    
    public Vec3D c(final Vec3D var1, final double var2) {
        final double var3 = var1.a - this.a;
        final double var4 = var1.b - this.b;
        final double var5 = var1.c - this.c;
        if (var5 * var5 < 1.0000000116860974E-7) {
            return null;
        }
        final double var6 = (var2 - this.c) / var5;
        return (var6 >= 0.0 && var6 <= 1.0) ? new Vec3D(this.a + var3 * var6, this.b + var4 * var6, this.c + var5 * var6) : null;
    }
    
    public String toString() {
        return "(" + this.a + ", " + this.b + ", " + this.c + ")";
    }
    
    public Vec3D a(final float var1) {
        final float var2 = MathHelper.cos(var1);
        final float var3 = MathHelper.sin(var1);
        final double var4 = this.a;
        final double var5 = this.b * var2 + this.c * var3;
        final double var6 = this.c * var2 - this.b * var3;
        return new Vec3D(var4, var5, var6);
    }
    
    public Vec3D b(final float var1) {
        final float var2 = MathHelper.cos(var1);
        final float var3 = MathHelper.sin(var1);
        final double var4 = this.a * var2 + this.c * var3;
        final double var5 = this.b;
        final double var6 = this.c * var2 - this.a * var3;
        return new Vec3D(var4, var5, var6);
    }
    
    static {
        Vec3D.fieldX = (FieldAccessor<Double>)fetchField("Vec3D", (Class)Double.TYPE, 0);
        Vec3D.fieldY = (FieldAccessor<Double>)fetchField("Vec3D", (Class)Double.TYPE, 1);
        Vec3D.fieldZ = (FieldAccessor<Double>)fetchField("Vec3D", (Class)Double.TYPE, 2);
    }
}
