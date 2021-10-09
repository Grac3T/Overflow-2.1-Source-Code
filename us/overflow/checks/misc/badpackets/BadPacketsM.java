// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.tinyprotocol.packet.in.WrappedInHeldItemSlotPacket;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsM extends Check
{
    public BadPacketsM(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getType().equalsIgnoreCase("PacketPlayInHeldItemSlot")) {
            final User user = e.getUser();
            final WrappedInHeldItemSlotPacket wrappedInHeldItemSlotPacket = new WrappedInHeldItemSlotPacket(e.getPacket(), e.getPlayer());
            final int slot = wrappedInHeldItemSlotPacket.getSlot();
            if (slot < 0) {
                this.flag(user, new String[0]);
            }
        }
    }
}
