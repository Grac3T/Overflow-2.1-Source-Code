// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;

public enum WrappedEnumAnimation
{
    NONE, 
    EAT, 
    DRINK, 
    BLOCK, 
    BOW, 
    SPEAR, 
    CROSSBOW;
    
    private static WrappedClass enumAnimation;
    
    public static WrappedEnumAnimation fromNMS(final Object vanillaObject) {
        final Enum vanilla = (Enum)vanillaObject;
        return valueOf(vanilla.name());
    }
    
    public Enum toVanilla() {
        return WrappedEnumAnimation.enumAnimation.getEnum(this.name());
    }
    
    static {
        WrappedEnumAnimation.enumAnimation = Reflections.getNMSClass("EnumAnimation");
    }
}
