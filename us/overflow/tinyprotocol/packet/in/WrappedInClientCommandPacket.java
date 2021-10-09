// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInClientCommandPacket extends NMSObject
{
    private static final String packet = "PacketPlayInClientCommand";
    private static FieldAccessor<Enum> fieldCommand;
    EnumClientCommand command;
    
    public WrappedInClientCommandPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.command = EnumClientCommand.values()[((Enum)this.fetch(WrappedInClientCommandPacket.fieldCommand)).ordinal()];
    }
    
    public EnumClientCommand getCommand() {
        return this.command;
    }
    
    static {
        WrappedInClientCommandPacket.fieldCommand = fetchField("PacketPlayInClientCommand", (Class)Enum.class, 0);
    }
    
    public enum EnumClientCommand
    {
        public static final EnumClientCommand PERFORM_RESPAWN;
        public static final EnumClientCommand REQUEST_STATS;
        public static final EnumClientCommand OPEN_INVENTORY_ACHIEVEMENT;
        
        public static EnumClientCommand valueOf(final String name) {
            return Enum.valueOf(EnumClientCommand.class, name);
        }
        
        static {
            EnumClientCommand.PERFORM_RESPAWN = new EnumClientCommand("PERFORM_RESPAWN", 0);
            EnumClientCommand.REQUEST_STATS = new EnumClientCommand("REQUEST_STATS", 1);
            EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT = new EnumClientCommand("OPEN_INVENTORY_ACHIEVEMENT", 2);
            EnumClientCommand.$VALUES = new EnumClientCommand[] { EnumClientCommand.PERFORM_RESPAWN, EnumClientCommand.REQUEST_STATS, EnumClientCommand.OPEN_INVENTORY_ACHIEVEMENT };
        }
    }
}
