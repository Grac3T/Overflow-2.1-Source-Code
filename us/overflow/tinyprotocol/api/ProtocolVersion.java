// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api;

import org.bukkit.Bukkit;
import us.overflow.tinyprotocol.reflection.Reflection;

public enum ProtocolVersion
{
    public static final ProtocolVersion V1_7;
    public static final ProtocolVersion V1_7_10;
    public static final ProtocolVersion V1_8;
    public static final ProtocolVersion V1_8_5;
    public static final ProtocolVersion V1_8_9;
    public static final ProtocolVersion V1_9;
    public static final ProtocolVersion V1_9_1;
    public static final ProtocolVersion V1_9_2;
    public static final ProtocolVersion V1_9_4;
    public static final ProtocolVersion V1_10;
    public static final ProtocolVersion V1_10_2;
    public static final ProtocolVersion V1_11;
    public static final ProtocolVersion V1_12;
    public static final ProtocolVersion V1_12_1;
    public static final ProtocolVersion V1_12_2;
    public static final ProtocolVersion V1_13;
    public static final ProtocolVersion V1_13_1;
    public static final ProtocolVersion V1_13_2;
    public static final ProtocolVersion V1_14;
    public static final ProtocolVersion UNKNOWN;
    private static ProtocolVersion gameVersion;
    private int version;
    private String serverVersion;
    private static boolean paperSpigot;
    
    public static ProtocolVersion valueOf(final String name) {
        return Enum.valueOf(ProtocolVersion.class, name);
    }
    
    private static ProtocolVersion fetchGameVersion() {
        for (final ProtocolVersion version : values()) {
            if (version.getServerVersion() != null && version.getServerVersion().equals(Reflection.VERSION)) {
                return version;
            }
        }
        return ProtocolVersion.UNKNOWN;
    }
    
    public static ProtocolVersion getVersion(final int versionId) {
        for (final ProtocolVersion version : values()) {
            if (version.getVersion() == versionId) {
                return version;
            }
        }
        return ProtocolVersion.UNKNOWN;
    }
    
    public boolean isBelow(final ProtocolVersion version) {
        return this.getVersion() < version.getVersion();
    }
    
    public boolean isAbove(final ProtocolVersion version) {
        return this.getVersion() > version.getVersion();
    }
    
    public boolean isOrAbove(final ProtocolVersion version) {
        return this.getVersion() >= version.getVersion();
    }
    
    public int getVersion() {
        return this.version;
    }
    
    public String getServerVersion() {
        return this.serverVersion;
    }
    
    private ProtocolVersion(final int version, final String serverVersion) {
        this.version = version;
        this.serverVersion = serverVersion;
    }
    
    public static ProtocolVersion getGameVersion() {
        return ProtocolVersion.gameVersion;
    }
    
    public static boolean isPaperSpigot() {
        return ProtocolVersion.paperSpigot;
    }
    
    static {
        ProtocolVersion.V1_7 = new ProtocolVersion("V1_7", 0, 4, "v1_7_R3");
        ProtocolVersion.V1_7_10 = new ProtocolVersion("V1_7_10", 1, 5, "v1_7_R4");
        ProtocolVersion.V1_8 = new ProtocolVersion("V1_8", 2, 45, "v1_8_R1");
        ProtocolVersion.V1_8_5 = new ProtocolVersion("V1_8_5", 3, 46, "v1_8_R2");
        ProtocolVersion.V1_8_9 = new ProtocolVersion("V1_8_9", 4, 47, "v1_8_R3");
        ProtocolVersion.V1_9 = new ProtocolVersion("V1_9", 5, 107, "v1_9_R1");
        ProtocolVersion.V1_9_1 = new ProtocolVersion("V1_9_1", 6, 108, (String)null);
        ProtocolVersion.V1_9_2 = new ProtocolVersion("V1_9_2", 7, 109, "v1_9_R2");
        ProtocolVersion.V1_9_4 = new ProtocolVersion("V1_9_4", 8, 110, "v1_9_R2");
        ProtocolVersion.V1_10 = new ProtocolVersion("V1_10", 9, 210, "v1_10_R1");
        ProtocolVersion.V1_10_2 = new ProtocolVersion("V1_10_2", 10, 210, "v1_10_R1");
        ProtocolVersion.V1_11 = new ProtocolVersion("V1_11", 11, 316, "v1_11_R1");
        ProtocolVersion.V1_12 = new ProtocolVersion("V1_12", 12, 335, "v1_12_R1");
        ProtocolVersion.V1_12_1 = new ProtocolVersion("V1_12_1", 13, 338, (String)null);
        ProtocolVersion.V1_12_2 = new ProtocolVersion("V1_12_2", 14, 340, "v1_12_R1");
        ProtocolVersion.V1_13 = new ProtocolVersion("V1_13", 15, 350, "v1_13_R1");
        ProtocolVersion.V1_13_1 = new ProtocolVersion("V1_13_1", 16, 351, "v1_13_R2");
        ProtocolVersion.V1_13_2 = new ProtocolVersion("V1_13_2", 17, 352, "v1_13_R2");
        ProtocolVersion.V1_14 = new ProtocolVersion("V1_14", 18, 477, "v1_14_R1");
        ProtocolVersion.UNKNOWN = new ProtocolVersion("UNKNOWN", 19, -1, "UNKNOWN");
        ProtocolVersion.$VALUES = new ProtocolVersion[] { ProtocolVersion.V1_7, ProtocolVersion.V1_7_10, ProtocolVersion.V1_8, ProtocolVersion.V1_8_5, ProtocolVersion.V1_8_9, ProtocolVersion.V1_9, ProtocolVersion.V1_9_1, ProtocolVersion.V1_9_2, ProtocolVersion.V1_9_4, ProtocolVersion.V1_10, ProtocolVersion.V1_10_2, ProtocolVersion.V1_11, ProtocolVersion.V1_12, ProtocolVersion.V1_12_1, ProtocolVersion.V1_12_2, ProtocolVersion.V1_13, ProtocolVersion.V1_13_1, ProtocolVersion.V1_13_2, ProtocolVersion.V1_14, ProtocolVersion.UNKNOWN };
        ProtocolVersion.gameVersion = fetchGameVersion();
        ProtocolVersion.paperSpigot = Bukkit.getServer().getBukkitVersion().toLowerCase().contains("paper");
    }
}
