// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInEntityActionPacket extends NMSObject
{
    private static final String packet = "PacketPlayInEntityAction";
    private static FieldAccessor<Integer> fieldAction1_7;
    private static FieldAccessor<Enum> fieldAction1_8;
    private EnumPlayerAction action;
    
    public WrappedInEntityActionPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            WrappedInEntityActionPacket.fieldAction1_7 = fetchField("PacketPlayInEntityAction", (Class)Integer.TYPE, 0);
            this.action = EnumPlayerAction.values()[Math.min(8, (int)this.fetch(WrappedInEntityActionPacket.fieldAction1_7))];
        }
        else {
            WrappedInEntityActionPacket.fieldAction1_8 = fetchField("PacketPlayInEntityAction", (Class)Enum.class, 0);
            this.action = EnumPlayerAction.values()[((Enum)this.fetch(WrappedInEntityActionPacket.fieldAction1_8)).ordinal()];
        }
    }
    
    public EnumPlayerAction getAction() {
        return this.action;
    }
    
    public enum EnumPlayerAction
    {
        public static final EnumPlayerAction START_SNEAKING;
        public static final EnumPlayerAction STOP_SNEAKING;
        public static final EnumPlayerAction STOP_SLEEPING;
        public static final EnumPlayerAction START_SPRINTING;
        public static final EnumPlayerAction STOP_SPRINTING;
        public static final EnumPlayerAction START_RIDING_JUMP;
        public static final EnumPlayerAction STOP_RIDING_JUMP;
        public static final EnumPlayerAction OPEN_INVENTORY;
        public static final EnumPlayerAction START_FALL_FLYING;
        
        public static EnumPlayerAction valueOf(final String name) {
            return Enum.valueOf(EnumPlayerAction.class, name);
        }
        
        static {
            EnumPlayerAction.START_SNEAKING = new EnumPlayerAction("START_SNEAKING", 0);
            EnumPlayerAction.STOP_SNEAKING = new EnumPlayerAction("STOP_SNEAKING", 1);
            EnumPlayerAction.STOP_SLEEPING = new EnumPlayerAction("STOP_SLEEPING", 2);
            EnumPlayerAction.START_SPRINTING = new EnumPlayerAction("START_SPRINTING", 3);
            EnumPlayerAction.STOP_SPRINTING = new EnumPlayerAction("STOP_SPRINTING", 4);
            EnumPlayerAction.START_RIDING_JUMP = new EnumPlayerAction("START_RIDING_JUMP", 5);
            EnumPlayerAction.STOP_RIDING_JUMP = new EnumPlayerAction("STOP_RIDING_JUMP", 6);
            EnumPlayerAction.OPEN_INVENTORY = new EnumPlayerAction("OPEN_INVENTORY", 7);
            EnumPlayerAction.START_FALL_FLYING = new EnumPlayerAction("START_FALL_FLYING", 8);
            EnumPlayerAction.$VALUES = new EnumPlayerAction[] { EnumPlayerAction.START_SNEAKING, EnumPlayerAction.STOP_SNEAKING, EnumPlayerAction.STOP_SLEEPING, EnumPlayerAction.START_SPRINTING, EnumPlayerAction.STOP_SPRINTING, EnumPlayerAction.START_RIDING_JUMP, EnumPlayerAction.STOP_RIDING_JUMP, EnumPlayerAction.OPEN_INVENTORY, EnumPlayerAction.START_FALL_FLYING };
        }
    }
}
