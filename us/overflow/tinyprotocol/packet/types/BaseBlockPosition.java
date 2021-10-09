// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedConstructor;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class BaseBlockPosition extends NMSObject
{
    public static final BaseBlockPosition ZERO;
    private static FieldAccessor<Integer> fieldX;
    private static FieldAccessor<Integer> fieldY;
    private static FieldAccessor<Integer> fieldZ;
    private static WrappedClass baseBlockPositionClass;
    private static WrappedClass blockPositionClass;
    private static WrappedConstructor blockPosConstructor;
    private static WrappedConstructor baseBlockPosConstructor;
    private int a;
    private int c;
    private int d;
    
    public BaseBlockPosition(final Object obj) {
        this.setObject(obj);
        this.a = (int)this.fetch(BaseBlockPosition.fieldX);
        this.c = (int)this.fetch(BaseBlockPosition.fieldY);
        this.d = (int)this.fetch(BaseBlockPosition.fieldZ);
    }
    
    public BaseBlockPosition(final int var1, final int var2, final int var3) {
        this.a = var1;
        this.c = var2;
        this.d = var3;
    }
    
    public BaseBlockPosition(final double var1, final double var3, final double var5) {
        this(MathHelper.floor(var1), MathHelper.floor(var3), MathHelper.floor(var5));
    }
    
    public boolean equals(final Object var1) {
        if (this == var1) {
            return true;
        }
        if (!(var1 instanceof BaseBlockPosition)) {
            return false;
        }
        final BaseBlockPosition var2 = (BaseBlockPosition)var1;
        return this.getX() == var2.getX() && this.getY() == var2.getY() && this.getZ() == var2.getZ();
    }
    
    public int hashCode() {
        return (this.getY() + this.getZ() * 31) * 31 + this.getX();
    }
    
    public int g(final BaseBlockPosition var1) {
        if (this.getY() == var1.getY()) {
            return (this.getZ() == var1.getZ()) ? (this.getX() - var1.getX()) : (this.getZ() - var1.getZ());
        }
        return this.getY() - var1.getY();
    }
    
    public int getX() {
        return this.a;
    }
    
    public int getY() {
        return this.c;
    }
    
    public int getZ() {
        return this.d;
    }
    
    public BaseBlockPosition d(final BaseBlockPosition var1) {
        return new BaseBlockPosition(this.getY() * var1.getZ() - this.getZ() * var1.getY(), this.getZ() * var1.getX() - this.getX() * var1.getZ(), this.getX() * var1.getY() - this.getY() * var1.getX());
    }
    
    public double c(final double var1, final double var3, final double var5) {
        final double var6 = this.getX() - var1;
        final double var7 = this.getY() - var3;
        final double var8 = this.getZ() - var5;
        return var6 * var6 + var7 * var7 + var8 * var8;
    }
    
    public double d(final double var1, final double var3, final double var5) {
        final double var6 = this.getX() + 0.5 - var1;
        final double var7 = this.getY() + 0.5 - var3;
        final double var8 = this.getZ() + 0.5 - var5;
        return var6 * var6 + var7 * var7 + var8 * var8;
    }
    
    public double i(final BaseBlockPosition var1) {
        return this.c(var1.getX(), var1.getY(), var1.getZ());
    }
    
    public Object getAsBaseBlockPosition() {
        return BaseBlockPosition.baseBlockPosConstructor.newInstance(new Object[] { this.getX(), this.getY(), this.getZ() });
    }
    
    public Object getAsBlockPosition() {
        return BaseBlockPosition.blockPosConstructor.newInstance(new Object[] { this.getX(), this.getY(), this.getZ() });
    }
    
    static {
        BaseBlockPosition.ZERO = new BaseBlockPosition(0, 0, 0);
        if (ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_7_10)) {
            BaseBlockPosition.fieldX = fetchField("BaseBlockPosition", (Class)Integer.TYPE, 0);
            BaseBlockPosition.fieldY = fetchField("BaseBlockPosition", (Class)Integer.TYPE, 1);
            BaseBlockPosition.fieldZ = fetchField("BaseBlockPosition", (Class)Integer.TYPE, 2);
            BaseBlockPosition.baseBlockPositionClass = Reflections.getNMSClass("BaseBlockPosition");
            BaseBlockPosition.blockPositionClass = Reflections.getNMSClass("BlockPosition");
            BaseBlockPosition.blockPosConstructor = BaseBlockPosition.blockPositionClass.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
            BaseBlockPosition.baseBlockPosConstructor = BaseBlockPosition.baseBlockPositionClass.getConstructor(new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
        }
    }
}
