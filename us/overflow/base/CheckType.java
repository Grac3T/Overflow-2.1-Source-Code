// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.base;

public enum CheckType
{
    public static final CheckType COMBAT;
    public static final CheckType MOVEMENT;
    public static final CheckType OTHER;
    
    public static CheckType valueOf(final String name) {
        return Enum.valueOf(CheckType.class, name);
    }
    
    static {
        CheckType.COMBAT = new CheckType("COMBAT", 0);
        CheckType.MOVEMENT = new CheckType("MOVEMENT", 1);
        CheckType.OTHER = new CheckType("OTHER", 2);
        CheckType.$VALUES = new CheckType[] { CheckType.COMBAT, CheckType.MOVEMENT, CheckType.OTHER };
    }
}
