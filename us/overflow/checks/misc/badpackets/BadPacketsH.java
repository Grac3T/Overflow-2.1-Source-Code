// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.tinyprotocol.packet.in.WrappedInTransactionPacket;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsH extends Check
{
    public BadPacketsH(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType())) {
            final long lastTransaction = System.currentTimeMillis() - user.lastBadPacketsITransaction;
            final long lastKeepAlive = System.currentTimeMillis() - user.lastBadPacketsKeepAlive;
            if (lastTransaction > lastKeepAlive) {
                final User user2 = user;
                user2.badPacketsJVerbose += ((user.badPacketsJVerbose < 100) ? 1 : 0);
                if (user.badPacketsJVerbose > 50) {}
            }
            else {
                user.badPacketsJVerbose = ((user.badPacketsJVerbose > 0) ? Math.max(user.badPacketsJVerbose - 1, 1) : 1);
            }
        }
        if (e.getType().equalsIgnoreCase("PacketPlayInKeepAlive")) {
            user.lastBadPacketsKeepAlive = System.currentTimeMillis();
        }
        if (e.getType().equalsIgnoreCase("PacketPlayInTransaction")) {
            final WrappedInTransactionPacket wrappedInTransactionPacket = new WrappedInTransactionPacket(e.getPacket(), e.getPlayer());
            final int id = wrappedInTransactionPacket.getAction();
            if (id == 420 || id == 421) {
                user.lastBadPacketsITransaction = System.currentTimeMillis();
            }
        }
    }
}
