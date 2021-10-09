// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimT extends Check
{
    public AimT(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null) {
            if (user.isLagging() || TimeUtils.secondsFromLong(user.lastLag) < 4L) {
                user.aimTVerbose = 0;
                return;
            }
            if (System.currentTimeMillis() - user.lastAttackPacket > 1000L || System.currentTimeMillis() - user.lastFullBlockMove > 760L || e.getTo().getYaw() == e.getFrom().getYaw()) {
                user.aimTVerbose = 0;
                return;
            }
            if (Math.abs(e.getTo().getPitch() - e.getFrom().getPitch()) == 0.0) {
                if (user.aimTVerbose++ > 49) {
                    this.flag(user, new String[0]);
                }
            }
            else if (user.aimTVerbose > 0) {
                final User user2 = user;
                --user2.aimTVerbose;
            }
        }
    }
}
