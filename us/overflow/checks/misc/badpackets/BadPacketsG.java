// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.tinyprotocol.packet.in.WrappedInTransactionPacket;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsG extends Check
{
    public BadPacketsG(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null && TimeUtils.secondsFromLong(user.lastJoin) > 2L && e.getType().equalsIgnoreCase("PacketPlayInTransaction")) {
            final WrappedInTransactionPacket wrappedInTransactionPacket = new WrappedInTransactionPacket(e.getPacket(), e.getPlayer());
            final int id = wrappedInTransactionPacket.getAction();
            if (id == 420 || id != 421) {}
        }
    }
}
