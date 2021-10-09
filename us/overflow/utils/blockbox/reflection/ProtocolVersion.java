// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.reflection;

public enum ProtocolVersion
{
    V1_7(4, "v1_7_R3"), 
    V1_7_10(5, "v1_7_R4"), 
    V1_8(45, "v1_8_R1"), 
    V1_8_5(46, "v1_8_R2"), 
    V1_8_9(47, "v1_8_R3"), 
    V1_9(107, "v1_9_R1"), 
    V1_9_1(108, (String)null), 
    V1_9_2(109, "v1_9_R2"), 
    V1_9_4(110, "v1_9_R2"), 
    V1_10(210, "v1_10_R1"), 
    V1_10_2(210, "v1_10_R1"), 
    V1_11(316, "v1_11_R1"), 
    V1_12(335, "v1_12_R1"), 
    V1_12_1(338, (String)null), 
    V1_12_2(340, "v1_12_R1"), 
    V1_13(350, "v1_13_R1"), 
    V1_13_1(351, "v1_13_R2"), 
    V1_13_2(352, "v1_13_R2"), 
    V1_14(477, "v1_14_R1"), 
    UNKNOWN(-1, "UNKNOWN");
    
    private static ProtocolVersion gameVersion;
    private int version;
    private String serverVersion;
    
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
    
    static {
        ProtocolVersion.gameVersion = fetchGameVersion();
    }
}
