// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import java.util.HashMap;
import java.util.Random;
import java.util.Map;

public enum EnumDirection
{
    public static final EnumDirection DOWN;
    public static final EnumDirection UP;
    public static final EnumDirection NORTH;
    public static final EnumDirection SOUTH;
    public static final EnumDirection WEST;
    public static final EnumDirection EAST;
    private static final EnumDirection[] n;
    private static final EnumDirection[] o;
    private static final Map<String, EnumDirection> p;
    private final int h;
    private final int i;
    private final String j;
    private final EnumAxis k;
    private final EnumAxisDirection l;
    private final BaseBlockPosition m;
    
    public static EnumDirection valueOf(final String name) {
        return Enum.valueOf(EnumDirection.class, name);
    }
    
    private EnumDirection(final int order, final int offset, final String direction, final EnumAxisDirection axisDirection, final EnumAxis axis, final BaseBlockPosition offsetPosition) {
        this.i = offset;
        this.h = order;
        this.j = direction;
        this.k = axis;
        this.l = axisDirection;
        this.m = offsetPosition;
    }
    
    public static EnumDirection fromType1(final int var0) {
        return EnumDirection.n[MathHelper.a(var0 % EnumDirection.n.length)];
    }
    
    public static EnumDirection fromType2(final int var0) {
        return EnumDirection.o[MathHelper.a(var0 % EnumDirection.o.length)];
    }
    
    public static EnumDirection fromAngle(final double var0) {
        return fromType2(MathHelper.floor(var0 / 90.0 + 0.5) & 0x3);
    }
    
    public static EnumDirection a(final Random var0) {
        return values()[var0.nextInt(values().length)];
    }
    
    public static EnumDirection a(final EnumAxisDirection var0, final EnumAxis var1) {
        for (final EnumDirection var5 : values()) {
            if (var5.c() == var0 && var5.k() == var1) {
                return var5;
            }
        }
        throw new IllegalArgumentException("No such direction: " + var0 + " " + var1);
    }
    
    public int b() {
        return this.i;
    }
    
    public EnumAxisDirection c() {
        return this.l;
    }
    
    public EnumDirection opposite() {
        return fromType1(this.h);
    }
    
    public int getAdjacentX() {
        return (this.k == EnumAxis.X) ? this.l.a() : 0;
    }
    
    public int getAdjacentY() {
        return (this.k == EnumAxis.Y) ? this.l.a() : 0;
    }
    
    public int getAdjacentZ() {
        return (this.k == EnumAxis.Z) ? this.l.a() : 0;
    }
    
    public String j() {
        return this.j;
    }
    
    public EnumAxis k() {
        return this.k;
    }
    
    @Override
    public String toString() {
        return this.j;
    }
    
    public String getName() {
        return this.j;
    }
    
    static {
        EnumDirection.DOWN = new EnumDirection("DOWN", 0, 1, -1, "down", EnumAxisDirection.NEGATIVE, EnumAxis.Y, new BaseBlockPosition(0, -1, 0));
        EnumDirection.UP = new EnumDirection("UP", 1, 0, -1, "up", EnumAxisDirection.POSITIVE, EnumAxis.Y, new BaseBlockPosition(0, 1, 0));
        EnumDirection.NORTH = new EnumDirection("NORTH", 2, 3, 2, "north", EnumAxisDirection.NEGATIVE, EnumAxis.Z, new BaseBlockPosition(0, 0, -1));
        EnumDirection.SOUTH = new EnumDirection("SOUTH", 3, 2, 0, "south", EnumAxisDirection.POSITIVE, EnumAxis.Z, new BaseBlockPosition(0, 0, 1));
        EnumDirection.WEST = new EnumDirection("WEST", 4, 5, 1, "west", EnumAxisDirection.NEGATIVE, EnumAxis.X, new BaseBlockPosition(-1, 0, 0));
        EnumDirection.EAST = new EnumDirection("EAST", 5, 4, 3, "east", EnumAxisDirection.POSITIVE, EnumAxis.X, new BaseBlockPosition(1, 0, 0));
        EnumDirection.$VALUES = new EnumDirection[] { EnumDirection.DOWN, EnumDirection.UP, EnumDirection.NORTH, EnumDirection.SOUTH, EnumDirection.WEST, EnumDirection.EAST };
        EnumDirection.n = new EnumDirection[6];
        EnumDirection.o = new EnumDirection[4];
        EnumDirection.p = new HashMap();
        final EnumDirection[] values;
        final EnumDirection[] var0 = values = values();
        for (final EnumDirection var2 : values) {
            EnumDirection.n[var2.ordinal()] = var2;
            if (var2.k().c()) {
                EnumDirection.o[var2.i] = var2;
            }
            EnumDirection.p.put(var2.j().toLowerCase(), var2);
        }
    }
    
    public enum EnumDirectionLimit
    {
        public static final EnumDirectionLimit HORIZONTAL;
        public static final EnumDirectionLimit VERTICAL;
        
        public static EnumDirectionLimit valueOf(final String name) {
            return Enum.valueOf(EnumDirectionLimit.class, name);
        }
        
        public boolean a(final EnumDirection var1) {
            return var1 != null && var1.k().d() == this;
        }
        
        static {
            EnumDirectionLimit.HORIZONTAL = new EnumDirectionLimit("HORIZONTAL", 0);
            EnumDirectionLimit.VERTICAL = new EnumDirectionLimit("VERTICAL", 1);
            EnumDirectionLimit.$VALUES = new EnumDirectionLimit[] { EnumDirectionLimit.HORIZONTAL, EnumDirectionLimit.VERTICAL };
        }
    }
    
    public enum EnumAxisDirection
    {
        public static final EnumAxisDirection POSITIVE;
        public static final EnumAxisDirection NEGATIVE;
        private final int c;
        private final String d;
        
        public static EnumAxisDirection valueOf(final String name) {
            return Enum.valueOf(EnumAxisDirection.class, name);
        }
        
        private EnumAxisDirection(final int var3, final String var4) {
            this.c = var3;
            this.d = var4;
        }
        
        public int a() {
            return this.c;
        }
        
        @Override
        public String toString() {
            return this.d;
        }
        
        static {
            EnumAxisDirection.POSITIVE = new EnumAxisDirection("POSITIVE", 0, 1, "Towards positive");
            EnumAxisDirection.NEGATIVE = new EnumAxisDirection("NEGATIVE", 1, -1, "Towards negative");
            EnumAxisDirection.$VALUES = new EnumAxisDirection[] { EnumAxisDirection.POSITIVE, EnumAxisDirection.NEGATIVE };
        }
    }
    
    public enum EnumAxis
    {
        public static final EnumAxis X;
        public static final EnumAxis Y;
        public static final EnumAxis Z;
        private static final Map<String, EnumAxis> d;
        private final String e;
        private final EnumDirectionLimit f;
        
        public static EnumAxis valueOf(final String name) {
            return Enum.valueOf(EnumAxis.class, name);
        }
        
        private EnumAxis(final String var3, final EnumDirectionLimit var4) {
            this.e = var3;
            this.f = var4;
        }
        
        public String a() {
            return this.e;
        }
        
        public boolean b() {
            return this.f == EnumDirectionLimit.VERTICAL;
        }
        
        public boolean c() {
            return this.f == EnumDirectionLimit.HORIZONTAL;
        }
        
        @Override
        public String toString() {
            return this.e;
        }
        
        public boolean a(final EnumDirection var1) {
            return var1 != null && var1.k() == this;
        }
        
        public EnumDirectionLimit d() {
            return this.f;
        }
        
        public String getName() {
            return this.e;
        }
        
        static {
            EnumAxis.X = new EnumAxis("X", 0, "x", EnumDirectionLimit.HORIZONTAL);
            EnumAxis.Y = new EnumAxis("Y", 1, "y", EnumDirectionLimit.VERTICAL);
            EnumAxis.Z = new EnumAxis("Z", 2, "z", EnumDirectionLimit.HORIZONTAL);
            EnumAxis.$VALUES = new EnumAxis[] { EnumAxis.X, EnumAxis.Y, EnumAxis.Z };
            EnumAxis.d = new HashMap();
            for (final EnumAxis var4 : values()) {
                EnumAxis.d.put(var4.a().toLowerCase(), var4);
            }
        }
    }
}
