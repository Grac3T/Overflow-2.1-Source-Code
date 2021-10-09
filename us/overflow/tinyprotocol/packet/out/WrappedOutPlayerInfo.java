// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import java.util.Iterator;
import us.overflow.tinyprotocol.packet.types.WrappedEnumGameMode;
import us.overflow.tinyprotocol.packet.types.WrappedGameProfile;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import us.overflow.utils.blockbox.ReflectionUtil;
import java.util.Arrays;
import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.types.WrappedEnumPlayerInfoAction;
import us.overflow.tinyprotocol.packet.types.WrappedPlayerInfoData;
import java.util.List;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutPlayerInfo extends NMSObject
{
    private static String packet;
    private static FieldAccessor<List> playerInfoListAccessor;
    private static FieldAccessor<Enum> actionAcessorEnum;
    private static FieldAccessor<Integer> actionAcessorInteger;
    private static FieldAccessor<Integer> gamemodeAccessor;
    private static FieldAccessor<Object> profileAcessor;
    private static FieldAccessor<Integer> pingAcessor;
    private List<WrappedPlayerInfoData> playerInfo;
    private WrappedEnumPlayerInfoAction action;
    
    public WrappedOutPlayerInfo(final Object object, final Player player) {
        super(object, player);
        this.playerInfo = new ArrayList<WrappedPlayerInfoData>();
    }
    
    public WrappedOutPlayerInfo(final WrappedEnumPlayerInfoAction action, final Player... players) {
        this.playerInfo = new ArrayList<WrappedPlayerInfoData>();
        if (ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_8)) {
            this.setPacket(WrappedOutPlayerInfo.packet, new Object[] { Reflections.getNMSClass("PacketPlayOutPlayerInfo.EnumPlayerInfoAction").getEnum(action.name()), Arrays.stream(players).map((Function<? super Player, ?>)ReflectionUtil::getEntityPlayer).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()) });
        }
        else {
            final WrappedClass outPlayerInfo = Reflections.getNMSClass(WrappedOutPlayerInfo.packet);
            final Object packet = outPlayerInfo.getConstructor().newInstance();
            outPlayerInfo.getMethod(action.legacyMethodName, new Class[] { ReflectionUtil.EntityPlayer }).invoke(packet, new Object[] { ReflectionUtil.getEntityPlayer(players[0]) });
            this.setObject(packet);
        }
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_8)) {
            WrappedOutPlayerInfo.playerInfoListAccessor = (FieldAccessor<List>)fetchField(WrappedOutPlayerInfo.packet, (Class)List.class, 0);
            WrappedOutPlayerInfo.actionAcessorEnum = (FieldAccessor<Enum>)fetchField(WrappedOutPlayerInfo.packet, (Class)Enum.class, 0);
            final List list = (List)this.fetch((FieldAccessor)WrappedOutPlayerInfo.playerInfoListAccessor);
            for (final Object object : list) {
                this.playerInfo.add(new WrappedPlayerInfoData(object));
            }
            this.action = WrappedEnumPlayerInfoAction.valueOf(((Enum)this.fetch((FieldAccessor)WrappedOutPlayerInfo.actionAcessorEnum)).name());
        }
        else {
            WrappedOutPlayerInfo.actionAcessorInteger = (FieldAccessor<Integer>)fetchField(WrappedOutPlayerInfo.packet, (Class)Integer.class, 5);
            WrappedOutPlayerInfo.profileAcessor = (FieldAccessor<Object>)fetchFieldByName(WrappedOutPlayerInfo.packet, "player", (Class)Object.class);
            WrappedOutPlayerInfo.gamemodeAccessor = (FieldAccessor<Integer>)fetchField(WrappedOutPlayerInfo.packet, (Class)Integer.class, 6);
            WrappedOutPlayerInfo.pingAcessor = (FieldAccessor<Integer>)fetchField(WrappedOutPlayerInfo.packet, (Class)Integer.class, 7);
            this.action = WrappedEnumPlayerInfoAction.values()[(int)this.fetch((FieldAccessor)WrappedOutPlayerInfo.actionAcessorInteger)];
            final WrappedGameProfile profile = new WrappedGameProfile(this.fetch((FieldAccessor)WrappedOutPlayerInfo.profileAcessor));
            final WrappedEnumGameMode gamemode = WrappedEnumGameMode.getById((int)this.fetch((FieldAccessor)WrappedOutPlayerInfo.gamemodeAccessor));
            final int ping = (int)this.fetch((FieldAccessor)WrappedOutPlayerInfo.pingAcessor);
            this.playerInfo.add(new WrappedPlayerInfoData(profile, gamemode, ping));
        }
    }
    
    static {
        WrappedOutPlayerInfo.packet = "PacketPlayOutPlayerInfo";
    }
}
