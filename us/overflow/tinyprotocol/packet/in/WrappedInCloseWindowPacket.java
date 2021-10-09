// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInCloseWindowPacket extends NMSObject
{
    private static final String packet = "PacketPlayInCloseWindow";
    private static FieldAccessor<Integer> fieldId;
    private int id;
    
    public WrappedInCloseWindowPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.id = (int)this.fetch((FieldAccessor)WrappedInCloseWindowPacket.fieldId);
    }
    
    public int getId() {
        return this.id;
    }
    
    static {
        WrappedInCloseWindowPacket.fieldId = (FieldAccessor<Integer>)fetchField("PacketPlayInCloseWindow", (Class)Integer.TYPE, 0);
    }
}
