// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInHeldItemSlotPacket extends NMSObject
{
    private static final String packet = "PacketPlayInHeldItemSlot";
    private static FieldAccessor<Integer> fieldHeldSlot;
    private int slot;
    
    public WrappedInHeldItemSlotPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.slot = (int)this.fetch((FieldAccessor)WrappedInHeldItemSlotPacket.fieldHeldSlot);
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    static {
        WrappedInHeldItemSlotPacket.fieldHeldSlot = (FieldAccessor<Integer>)fetchField("PacketPlayInHeldItemSlot", (Class)Integer.TYPE, 0);
    }
}
