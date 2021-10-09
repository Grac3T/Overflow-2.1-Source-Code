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

public class BadPacketsI extends Check
{
    public BadPacketsI(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType())) {
            final long lastTransaction = System.currentTimeMillis() - user.lastBadPacketsKTransaction;
            final long lastKeepAlive = System.currentTimeMillis() - user.lastBadPacketsKKeepAlive;
            final long diff = Math.abs(lastKeepAlive - lastTransaction);
            if (TimeUtils.secondsFromLong(user.lastJoin) > 10L && lastKeepAlive > lastTransaction && diff > 5000L) {
                final User user2 = user;
                user2.badPacketsKVerbose += ((user.badPacketsKVerbose < 20) ? 1 : 0);
            }
            else {
                user.badPacketsKVerbose = ((user.badPacketsKVerbose > 0) ? Math.max(user.badPacketsKVerbose - 1, 1) : 1);
            }
        }
        if (e.getType().equalsIgnoreCase("PacketPlayInKeepAlive")) {
            user.lastBadPacketsKKeepAlive = System.currentTimeMillis();
        }
        if (e.getType().equalsIgnoreCase("PacketPlayInTransaction")) {
            final WrappedInTransactionPacket wrappedInTransactionPacket = new WrappedInTransactionPacket(e.getPacket(), e.getPlayer());
            final int id = wrappedInTransactionPacket.getAction();
            if (id == 420 || id == 421) {
                user.lastBadPacketsKTransaction = System.currentTimeMillis();
            }
        }
    }
}
