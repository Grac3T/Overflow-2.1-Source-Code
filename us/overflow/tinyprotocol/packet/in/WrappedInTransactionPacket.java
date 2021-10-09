// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInTransactionPacket extends NMSObject
{
    private static final String packet = "PacketPlayInTransaction";
    private static FieldAccessor<Integer> fieldId;
    private static FieldAccessor<Short> fieldAction;
    private static FieldAccessor<Boolean> fieldAccepted;
    private int id;
    private short action;
    private boolean accept;
    
    public WrappedInTransactionPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.id = (int)this.fetch((FieldAccessor)WrappedInTransactionPacket.fieldId);
        this.action = (short)this.fetch((FieldAccessor)WrappedInTransactionPacket.fieldAction);
        this.accept = (boolean)this.fetch((FieldAccessor)WrappedInTransactionPacket.fieldAccepted);
    }
    
    public int getId() {
        return this.id;
    }
    
    public short getAction() {
        return this.action;
    }
    
    public boolean isAccept() {
        return this.accept;
    }
    
    static {
        WrappedInTransactionPacket.fieldId = (FieldAccessor<Integer>)fetchField("PacketPlayInTransaction", (Class)Integer.TYPE, 0);
        WrappedInTransactionPacket.fieldAction = (FieldAccessor<Short>)fetchField("PacketPlayInTransaction", (Class)Short.TYPE, 0);
        WrappedInTransactionPacket.fieldAccepted = (FieldAccessor<Boolean>)fetchField("PacketPlayInTransaction", (Class)Boolean.TYPE, 0);
    }
}
