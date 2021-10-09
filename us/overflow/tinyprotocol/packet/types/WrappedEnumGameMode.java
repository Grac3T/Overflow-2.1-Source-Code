// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;

public enum WrappedEnumGameMode
{
    public static final WrappedEnumGameMode NOT_SET;
    public static final WrappedEnumGameMode SURVIVAL;
    public static final WrappedEnumGameMode CREATIVE;
    public static final WrappedEnumGameMode ADVENTURE;
    public static final WrappedEnumGameMode SPECTATOR;
    int f;
    String g;
    
    public static WrappedEnumGameMode valueOf(final String name) {
        return Enum.valueOf(WrappedEnumGameMode.class, name);
    }
    
    private WrappedEnumGameMode(final int var3, final String var4) {
        this.f = var3;
        this.g = var4;
    }
    
    public int getId() {
        return this.f;
    }
    
    public String b() {
        return this.g;
    }
    
    public boolean c() {
        return this == WrappedEnumGameMode.ADVENTURE || this == WrappedEnumGameMode.SPECTATOR;
    }
    
    public boolean d() {
        return this == WrappedEnumGameMode.CREATIVE;
    }
    
    public boolean e() {
        return this == WrappedEnumGameMode.SURVIVAL || this == WrappedEnumGameMode.ADVENTURE;
    }
    
    public static WrappedEnumGameMode getById(final int var0) {
        for (final WrappedEnumGameMode var4 : values()) {
            if (var4.f == var0) {
                return var4;
            }
        }
        return WrappedEnumGameMode.SURVIVAL;
    }
    
    public static WrappedEnumGameMode getByName(final String name) {
        for (final WrappedEnumGameMode var : values()) {
            if (var.name().equals(name)) {
                return var;
            }
        }
        return WrappedEnumGameMode.SURVIVAL;
    }
    
    public Object getObject(final WrappedEnumGameMode gamemode) {
        return Reflections.getNMSClass("EnumGameMode").getEnum(gamemode.name());
    }
    
    public Object getObject() {
        return this.getObject(getById(this.f));
    }
    
    public static WrappedEnumGameMode fromObject(final Enum var) {
        return getByName(var.name());
    }
    
    static {
        WrappedEnumGameMode.NOT_SET = new WrappedEnumGameMode("NOT_SET", 0, -1, "");
        WrappedEnumGameMode.SURVIVAL = new WrappedEnumGameMode("SURVIVAL", 1, 0, "survival");
        WrappedEnumGameMode.CREATIVE = new WrappedEnumGameMode("CREATIVE", 2, 1, "creative");
        WrappedEnumGameMode.ADVENTURE = new WrappedEnumGameMode("ADVENTURE", 3, 2, "adventure");
        WrappedEnumGameMode.SPECTATOR = new WrappedEnumGameMode("SPECTATOR", 4, 3, "spectator");
        WrappedEnumGameMode.$VALUES = new WrappedEnumGameMode[] { WrappedEnumGameMode.NOT_SET, WrappedEnumGameMode.SURVIVAL, WrappedEnumGameMode.CREATIVE, WrappedEnumGameMode.ADVENTURE, WrappedEnumGameMode.SPECTATOR };
    }
}
