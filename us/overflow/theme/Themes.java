// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.theme;

public enum Themes
{
    public static final Themes OVERFLOW_2_0;
    public static final Themes KAURI;
    public static final Themes OVERFLOW_1_0;
    public static final Themes VERUS;
    public static final Themes IRIS;
    public static final Themes SECURITY;
    public static final Themes CUSTOM;
    
    public static Themes valueOf(final String name) {
        return Enum.valueOf(Themes.class, name);
    }
    
    static {
        Themes.OVERFLOW_2_0 = new Themes("OVERFLOW_2_0", 0);
        Themes.KAURI = new Themes("KAURI", 1);
        Themes.OVERFLOW_1_0 = new Themes("OVERFLOW_1_0", 2);
        Themes.VERUS = new Themes("VERUS", 3);
        Themes.IRIS = new Themes("IRIS", 4);
        Themes.SECURITY = new Themes("SECURITY", 5);
        Themes.CUSTOM = new Themes("CUSTOM", 6);
        Themes.$VALUES = new Themes[] { Themes.OVERFLOW_2_0, Themes.KAURI, Themes.OVERFLOW_1_0, Themes.VERUS, Themes.IRIS, Themes.SECURITY, Themes.CUSTOM };
    }
}
