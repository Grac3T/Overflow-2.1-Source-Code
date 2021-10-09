// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import java.util.Iterator;
import java.util.EnumSet;
import net.minecraft.server.v1_8_R3.PacketPlayOutPosition;
import java.util.Set;

public enum WrappedEnumTeleportFlag
{
    public static final WrappedEnumTeleportFlag X;
    public static final WrappedEnumTeleportFlag Y;
    public static final WrappedEnumTeleportFlag Z;
    public static final WrappedEnumTeleportFlag Y_ROT;
    public static final WrappedEnumTeleportFlag X_ROT;
    private int f;
    
    public static WrappedEnumTeleportFlag valueOf(final String name) {
        return Enum.valueOf(WrappedEnumTeleportFlag.class, name);
    }
    
    private WrappedEnumTeleportFlag(final int var3) {
        this.f = var3;
    }
    
    private int a() {
        return 1 << this.f;
    }
    
    private boolean b(final int var1) {
        return (var1 & this.a()) == this.a();
    }
    
    public static Set<WrappedEnumTeleportFlag> a(final int var0) {
        final EnumSet var = EnumSet.noneOf(PacketPlayOutPosition.EnumPlayerTeleportFlags.class);
        for (final WrappedEnumTeleportFlag var5 : values()) {
            if (var5.b(var0)) {
                var.add(var5);
            }
        }
        return (Set<WrappedEnumTeleportFlag>)var;
    }
    
    public static int a(final Set<WrappedEnumTeleportFlag> var0) {
        int var = 0;
        for (final WrappedEnumTeleportFlag var3 : var0) {
            var |= var3.a();
        }
        return var;
    }
    
    public static WrappedEnumTeleportFlag fromObject(final Enum var) {
        return values()[var.ordinal()];
    }
    
    public Object getObject() {
        return Reflections.getNMSClass("EnumTeleportFlag").getEnum(this.name());
    }
    
    static {
        WrappedEnumTeleportFlag.X = new WrappedEnumTeleportFlag("X", 0, 0);
        WrappedEnumTeleportFlag.Y = new WrappedEnumTeleportFlag("Y", 1, 1);
        WrappedEnumTeleportFlag.Z = new WrappedEnumTeleportFlag("Z", 2, 2);
        WrappedEnumTeleportFlag.Y_ROT = new WrappedEnumTeleportFlag("Y_ROT", 3, 3);
        WrappedEnumTeleportFlag.X_ROT = new WrappedEnumTeleportFlag("X_ROT", 4, 4);
        WrappedEnumTeleportFlag.$VALUES = new WrappedEnumTeleportFlag[] { WrappedEnumTeleportFlag.X, WrappedEnumTeleportFlag.Y, WrappedEnumTeleportFlag.Z, WrappedEnumTeleportFlag.Y_ROT, WrappedEnumTeleportFlag.X_ROT };
    }
}
