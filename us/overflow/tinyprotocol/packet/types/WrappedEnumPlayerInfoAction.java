// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

public enum WrappedEnumPlayerInfoAction
{
    public static final WrappedEnumPlayerInfoAction ADD_PLAYER;
    public static final WrappedEnumPlayerInfoAction UPDATE_GAME_MODE;
    public static final WrappedEnumPlayerInfoAction UPDATE_LATENCY;
    public static final WrappedEnumPlayerInfoAction UPDATE_DISPLAY_NAME;
    public static final WrappedEnumPlayerInfoAction REMOVE_PLAYER;
    public String legacyMethodName;
    
    public static WrappedEnumPlayerInfoAction valueOf(final String name) {
        return Enum.valueOf(WrappedEnumPlayerInfoAction.class, name);
    }
    
    private WrappedEnumPlayerInfoAction(final String legacyMethodName) {
        this.legacyMethodName = legacyMethodName;
    }
    
    static {
        WrappedEnumPlayerInfoAction.ADD_PLAYER = new WrappedEnumPlayerInfoAction("ADD_PLAYER", 0, "addPlayer");
        WrappedEnumPlayerInfoAction.UPDATE_GAME_MODE = new WrappedEnumPlayerInfoAction("UPDATE_GAME_MODE", 1, "updateGamemode");
        WrappedEnumPlayerInfoAction.UPDATE_LATENCY = new WrappedEnumPlayerInfoAction("UPDATE_LATENCY", 2, "updatePing");
        WrappedEnumPlayerInfoAction.UPDATE_DISPLAY_NAME = new WrappedEnumPlayerInfoAction("UPDATE_DISPLAY_NAME", 3, "updateDisplayName");
        WrappedEnumPlayerInfoAction.REMOVE_PLAYER = new WrappedEnumPlayerInfoAction("REMOVE_PLAYER", 4, "removePlayer");
        WrappedEnumPlayerInfoAction.$VALUES = new WrappedEnumPlayerInfoAction[] { WrappedEnumPlayerInfoAction.ADD_PLAYER, WrappedEnumPlayerInfoAction.UPDATE_GAME_MODE, WrappedEnumPlayerInfoAction.UPDATE_LATENCY, WrappedEnumPlayerInfoAction.UPDATE_DISPLAY_NAME, WrappedEnumPlayerInfoAction.REMOVE_PLAYER };
    }
}
