// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutHeldItemSlot extends NMSObject
{
    private static String packet;
    private FieldAccessor<Integer> slotField;
    private int slot;
    
    public WrappedOutHeldItemSlot(final Object object, final Player player) {
        super(object, player);
        this.slotField = (FieldAccessor<Integer>)fetchField(WrappedOutHeldItemSlot.packet, (Class)Integer.TYPE, 0);
    }
    
    public WrappedOutHeldItemSlot(final int slot) {
        this.slotField = (FieldAccessor<Integer>)fetchField(WrappedOutHeldItemSlot.packet, (Class)Integer.TYPE, 0);
        this.slot = slot;
        this.setObject(construct(WrappedOutHeldItemSlot.packet, (Object)slot));
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.slot = (int)this.fetch((FieldAccessor)this.slotField);
    }
    
    public Object getObject() {
        return super.getObject();
    }
    
    public FieldAccessor<Integer> getSlotField() {
        return this.slotField;
    }
    
    public int getSlot() {
        return this.slot;
    }
    
    static {
        WrappedOutHeldItemSlot.packet = "PacketPlayOutHeldItemSlot";
    }
}
