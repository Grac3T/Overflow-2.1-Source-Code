// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsB extends Check
{
    public BadPacketsB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null && this.isPacketMovement(e.getType())) {
            final double pitchDiff = Math.abs(e.getTo().getPitch() - e.getFrom().getPitch());
            if (pitchDiff >= 15.0) {
                user.lastBadPacketsBPos = System.currentTimeMillis();
            }
            final long last = System.currentTimeMillis() - user.lastBadPacketsBPos;
            if (user.isUsingOptifine()) {
                if (user.badPacketsOptifineTicks < 100) {
                    final User user2 = user;
                    ++user2.badPacketsOptifineTicks;
                }
            }
            else if (user.badPacketsOptifineTicks > 0) {
                final User user3 = user;
                --user3.badPacketsOptifineTicks;
            }
            if (user.badPacketsOptifineTicks > 20 || user.isLagging() || System.currentTimeMillis() - user.lastAttackPacket > 1000L) {
                user.lastBadPacketsBPos = System.currentTimeMillis();
                return;
            }
            if (System.currentTimeMillis() - user.lastAttackPacket < 1000L && last > 10000L && user.badPacketsBVerbose.flag(30, 999L)) {
                this.flag(user, new String[0]);
            }
            if (e.getType().equalsIgnoreCase("PacketPlayInPosition")) {
                user.lastBadPacketsBPos = System.currentTimeMillis();
            }
        }
    }
}
