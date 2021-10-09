// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedPlayerInfoData extends NMSObject
{
    private static String type;
    private static FieldAccessor<Enum> enumGamemodeAccessor;
    private static FieldAccessor<Object> profileAcessor;
    private static FieldAccessor<Integer> pingAcessor;
    private int ping;
    private WrappedEnumGameMode gameMode;
    private WrappedGameProfile gameProfile;
    private String username;
    
    public WrappedPlayerInfoData(final Object object, final Player player) {
        super(object, player);
        this.username = "";
    }
    
    public WrappedPlayerInfoData(final Object object) {
        super(object);
        this.username = "";
        this.ping = (int)this.fetch(WrappedPlayerInfoData.pingAcessor);
        this.gameProfile = new WrappedGameProfile(this.fetch(WrappedPlayerInfoData.profileAcessor));
        this.gameMode = WrappedEnumGameMode.fromObject((Enum)this.fetch(WrappedPlayerInfoData.enumGamemodeAccessor));
    }
    
    public WrappedPlayerInfoData(final WrappedGameProfile gameProfile, final WrappedEnumGameMode gameMode, final int ping) {
        this.username = "";
        this.ping = ping;
        this.gameProfile = gameProfile;
        this.gameMode = gameMode;
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        super.process(player, version);
        this.ping = (int)this.fetch(WrappedPlayerInfoData.pingAcessor);
        this.gameProfile = new WrappedGameProfile(this.fetch(WrappedPlayerInfoData.profileAcessor));
        this.gameMode = WrappedEnumGameMode.fromObject((Enum)this.fetch(WrappedPlayerInfoData.enumGamemodeAccessor));
        this.username = player.getName();
    }
    
    public WrappedPlayerInfoData() {
        this.username = "";
    }
    
    static {
        WrappedPlayerInfoData.type = NMSObject.Type.PLAYERINFODATA;
        WrappedPlayerInfoData.enumGamemodeAccessor = fetchField(WrappedPlayerInfoData.type, (Class)Enum.class, 0);
        WrappedPlayerInfoData.profileAcessor = fetchFieldByName(WrappedPlayerInfoData.type, "d", (Class)Object.class);
        WrappedPlayerInfoData.pingAcessor = fetchField(WrappedPlayerInfoData.type, (Class)Integer.class, 0);
    }
}
