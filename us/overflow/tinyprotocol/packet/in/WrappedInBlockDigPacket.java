// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.types.EnumDirection;
import us.overflow.tinyprotocol.packet.types.BaseBlockPosition;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInBlockDigPacket extends NMSObject
{
    private static final String packet = "PacketPlayInBlockDig";
    private static FieldAccessor<Object> fieldBlockPosition;
    private static FieldAccessor<Integer> fieldPosX;
    private static FieldAccessor<Integer> fieldPosY;
    private static FieldAccessor<Integer> fieldPosZ;
    private static FieldAccessor<Object> fieldDirection;
    private static FieldAccessor<Object> fieldDigType;
    private static FieldAccessor<Integer> face;
    private static FieldAccessor<Integer> intAction;
    private BaseBlockPosition position;
    private EnumDirection direction;
    private EnumPlayerDigType action;
    
    public WrappedInBlockDigPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            WrappedInBlockDigPacket.fieldPosX = fetchField("PacketPlayInBlockDig", (Class)Integer.TYPE, 0);
            WrappedInBlockDigPacket.fieldPosY = fetchField("PacketPlayInBlockDig", (Class)Integer.TYPE, 1);
            WrappedInBlockDigPacket.fieldPosZ = fetchField("PacketPlayInBlockDig", (Class)Integer.TYPE, 2);
            WrappedInBlockDigPacket.face = fetchField("PacketPlayInBlockDig", (Class)Integer.TYPE, 3);
            WrappedInBlockDigPacket.intAction = fetchField("PacketPlayInBlockDig", (Class)Integer.TYPE, 4);
            this.position = new BaseBlockPosition((int)this.fetch(WrappedInBlockDigPacket.fieldPosX), (int)this.fetch(WrappedInBlockDigPacket.fieldPosY), (int)this.fetch(WrappedInBlockDigPacket.fieldPosZ));
            this.direction = EnumDirection.values()[Math.min((int)this.fetch(WrappedInBlockDigPacket.face), 5)];
            this.action = EnumPlayerDigType.values()[(int)this.fetch(WrappedInBlockDigPacket.intAction)];
        }
        else {
            WrappedInBlockDigPacket.fieldBlockPosition = fetchField("PacketPlayInBlockDig", (Class)Object.class, 0);
            WrappedInBlockDigPacket.fieldDirection = fetchField("PacketPlayInBlockDig", (Class)Object.class, 1);
            WrappedInBlockDigPacket.fieldDigType = fetchField("PacketPlayInBlockDig", (Class)Object.class, 2);
            this.position = new BaseBlockPosition(this.fetch(WrappedInBlockDigPacket.fieldBlockPosition));
            this.direction = EnumDirection.values()[((Enum)this.fetch(WrappedInBlockDigPacket.fieldDirection)).ordinal()];
            this.action = EnumPlayerDigType.values()[((Enum)this.fetch(WrappedInBlockDigPacket.fieldDigType)).ordinal()];
        }
    }
    
    public BaseBlockPosition getPosition() {
        return this.position;
    }
    
    public EnumDirection getDirection() {
        return this.direction;
    }
    
    public EnumPlayerDigType getAction() {
        return this.action;
    }
    
    public enum EnumPlayerDigType
    {
        public static final EnumPlayerDigType START_DESTROY_BLOCK;
        public static final EnumPlayerDigType ABORT_DESTROY_BLOCK;
        public static final EnumPlayerDigType STOP_DESTROY_BLOCK;
        public static final EnumPlayerDigType DROP_ALL_ITEMS;
        public static final EnumPlayerDigType DROP_ITEM;
        public static final EnumPlayerDigType RELEASE_USE_ITEM;
        public static final EnumPlayerDigType SWAP_HELD_ITEMS;
        
        public static EnumPlayerDigType valueOf(final String name) {
            return Enum.valueOf(EnumPlayerDigType.class, name);
        }
        
        static {
            EnumPlayerDigType.START_DESTROY_BLOCK = new EnumPlayerDigType("START_DESTROY_BLOCK", 0);
            EnumPlayerDigType.ABORT_DESTROY_BLOCK = new EnumPlayerDigType("ABORT_DESTROY_BLOCK", 1);
            EnumPlayerDigType.STOP_DESTROY_BLOCK = new EnumPlayerDigType("STOP_DESTROY_BLOCK", 2);
            EnumPlayerDigType.DROP_ALL_ITEMS = new EnumPlayerDigType("DROP_ALL_ITEMS", 3);
            EnumPlayerDigType.DROP_ITEM = new EnumPlayerDigType("DROP_ITEM", 4);
            EnumPlayerDigType.RELEASE_USE_ITEM = new EnumPlayerDigType("RELEASE_USE_ITEM", 5);
            EnumPlayerDigType.SWAP_HELD_ITEMS = new EnumPlayerDigType("SWAP_HELD_ITEMS", 6);
            EnumPlayerDigType.$VALUES = new EnumPlayerDigType[] { EnumPlayerDigType.START_DESTROY_BLOCK, EnumPlayerDigType.ABORT_DESTROY_BLOCK, EnumPlayerDigType.STOP_DESTROY_BLOCK, EnumPlayerDigType.DROP_ALL_ITEMS, EnumPlayerDigType.DROP_ITEM, EnumPlayerDigType.RELEASE_USE_ITEM, EnumPlayerDigType.SWAP_HELD_ITEMS };
        }
    }
}
