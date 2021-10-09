// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import org.bukkit.WorldType;
import us.overflow.tinyprotocol.packet.types.WrappedEnumDifficulty;
import us.overflow.tinyprotocol.packet.types.WrappedEnumGameMode;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutRespawnPacket extends NMSObject
{
    private static String packet;
    private static FieldAccessor<Enum> difficultyAcessor;
    private static FieldAccessor<Enum> gamemodeAccessor;
    private static WrappedClass worldTypeClass;
    private static FieldAccessor<Integer> dimensionAccesor;
    private static FieldAccessor<Object> dimensionManagerAcceessor;
    private static WrappedClass dimensionManagerClass;
    private int dimension;
    private WrappedEnumGameMode gamemode;
    private WrappedEnumDifficulty difficulty;
    private WorldType worldType;
    
    public WrappedOutRespawnPacket(final Object object, final Player player) {
        super(object, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_13)) {
            final Object dimensionManager = this.fetch((FieldAccessor)WrappedOutRespawnPacket.dimensionManagerAcceessor);
            this.dimension = (int)WrappedOutRespawnPacket.dimensionManagerClass.getFirstFieldByType((Class)Integer.TYPE).get(dimensionManager);
        }
        else {
            this.dimension = (int)this.fetch((FieldAccessor)WrappedOutRespawnPacket.dimensionAccesor);
        }
        this.gamemode = WrappedEnumGameMode.fromObject((Enum)this.fetch((FieldAccessor)WrappedOutRespawnPacket.gamemodeAccessor));
        this.difficulty = WrappedEnumDifficulty.fromObject((Enum)this.fetch((FieldAccessor)WrappedOutRespawnPacket.difficultyAcessor));
        this.worldType = WorldType.getByName((String)WrappedOutRespawnPacket.worldTypeClass.getFirstFieldByType((Class)String.class).get(this.getObject()));
    }
    
    public int getDimension() {
        return this.dimension;
    }
    
    public WrappedEnumGameMode getGamemode() {
        return this.gamemode;
    }
    
    public WrappedEnumDifficulty getDifficulty() {
        return this.difficulty;
    }
    
    public WorldType getWorldType() {
        return this.worldType;
    }
    
    static {
        WrappedOutRespawnPacket.packet = "PacketPlayOutRespawn";
        if (ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_13)) {
            WrappedOutRespawnPacket.dimensionManagerAcceessor = (FieldAccessor<Object>)fetchField(WrappedOutRespawnPacket.packet, (Class)Object.class, 0);
            WrappedOutRespawnPacket.dimensionManagerClass = Reflections.getNMSClass("DimensionManager");
        }
        else {
            WrappedOutRespawnPacket.dimensionAccesor = (FieldAccessor<Integer>)fetchField(WrappedOutRespawnPacket.packet, (Class)Integer.TYPE, 0);
        }
        WrappedOutRespawnPacket.difficultyAcessor = (FieldAccessor<Enum>)fetchField(WrappedOutRespawnPacket.packet, (Class)Enum.class, 0);
        WrappedOutRespawnPacket.gamemodeAccessor = (FieldAccessor<Enum>)fetchField(WrappedOutRespawnPacket.packet, (Class)Enum.class, 1);
        WrappedOutRespawnPacket.worldTypeClass = Reflections.getNMSClass("WorldType");
    }
}
