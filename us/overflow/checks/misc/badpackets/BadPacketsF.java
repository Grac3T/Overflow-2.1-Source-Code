// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsF extends Check
{
    public BadPacketsF(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null && user.hasSwordInHand()) {
            if (e.getType().equalsIgnoreCase("PacketPlayInBlockDig")) {
                user.lastBadPacketsFBlockDig = System.currentTimeMillis();
                if (System.currentTimeMillis() - user.lastAttackPacket < 100L) {
                    final long dig = user.lastBadPacketsFBlockDig;
                    final long block = user.lastBadPacketsFBlockPlace;
                    if (dig - block > 1000L) {
                        if (user.badPacketsFVerbose.flag(12, 999L)) {
                            this.flag(user, new String[0]);
                        }
                    }
                    else {
                        user.badPacketsFVerbose.takeaway();
                    }
                }
            }
            if (e.getType().equalsIgnoreCase("PacketPlayInBlockPlace")) {
                user.lastBadPacketsFBlockPlace = System.currentTimeMillis();
            }
        }
    }
}
