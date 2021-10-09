// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutTransaction extends NMSObject
{
    private static final String packet = "PacketPlayOutTransaction";
    private static FieldAccessor<Integer> fieldId;
    private static FieldAccessor<Short> fieldAction;
    private static FieldAccessor<Boolean> fieldAccepted;
    private int id;
    private short action;
    private boolean accept;
    
    public WrappedOutTransaction(final int id, final short action, final boolean accept) {
        this.setPacket("PacketPlayOutTransaction", new Object[] { id, action, accept });
    }
    
    public WrappedOutTransaction(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.id = (int)this.fetch(WrappedOutTransaction.fieldId);
        this.action = (short)this.fetch(WrappedOutTransaction.fieldAction);
        this.accept = (boolean)this.fetch(WrappedOutTransaction.fieldAccepted);
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
        WrappedOutTransaction.fieldId = fetchField("PacketPlayOutTransaction", (Class)Integer.TYPE, 0);
        WrappedOutTransaction.fieldAction = fetchField("PacketPlayOutTransaction", (Class)Short.TYPE, 0);
        WrappedOutTransaction.fieldAccepted = fetchField("PacketPlayOutTransaction", (Class)Boolean.TYPE, 0);
    }
}
