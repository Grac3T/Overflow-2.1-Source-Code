// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;

public enum WrappedEnumDifficulty
{
    public static final WrappedEnumDifficulty PEACEFUL;
    public static final WrappedEnumDifficulty EASY;
    public static final WrappedEnumDifficulty NORMAL;
    public static final WrappedEnumDifficulty HARD;
    private static final WrappedEnumDifficulty[] e;
    private final int f;
    private final String g;
    
    public static WrappedEnumDifficulty valueOf(final String name) {
        return Enum.valueOf(WrappedEnumDifficulty.class, name);
    }
    
    private WrappedEnumDifficulty(final int var3, final String var4) {
        this.f = var3;
        this.g = var4;
    }
    
    public int a() {
        return this.f;
    }
    
    public static WrappedEnumDifficulty getById(final int var0) {
        return WrappedEnumDifficulty.e[var0 % WrappedEnumDifficulty.e.length];
    }
    
    public String b() {
        return this.g;
    }
    
    public static WrappedEnumDifficulty getByName(final String name) {
        for (final WrappedEnumDifficulty var : values()) {
            if (var.name().equals(name)) {
                return var;
            }
        }
        return WrappedEnumDifficulty.PEACEFUL;
    }
    
    public Object getObject() {
        return Reflections.getNMSClass("EnumDifficulty").getEnum(this.name());
    }
    
    public static WrappedEnumDifficulty fromObject(final Enum var) {
        return getByName(var.name());
    }
    
    static {
        WrappedEnumDifficulty.PEACEFUL = new WrappedEnumDifficulty("PEACEFUL", 0, 0, "options.difficulty.peaceful");
        WrappedEnumDifficulty.EASY = new WrappedEnumDifficulty("EASY", 1, 1, "options.difficulty.easy");
        WrappedEnumDifficulty.NORMAL = new WrappedEnumDifficulty("NORMAL", 2, 2, "options.difficulty.normal");
        WrappedEnumDifficulty.HARD = new WrappedEnumDifficulty("HARD", 3, 3, "options.difficulty.hard");
        WrappedEnumDifficulty.$VALUES = new WrappedEnumDifficulty[] { WrappedEnumDifficulty.PEACEFUL, WrappedEnumDifficulty.EASY, WrappedEnumDifficulty.NORMAL, WrappedEnumDifficulty.HARD };
        WrappedEnumDifficulty.e = new WrappedEnumDifficulty[values().length];
        for (final WrappedEnumDifficulty var4 : values()) {
            WrappedEnumDifficulty.e[var4.f] = var4;
        }
    }
}
