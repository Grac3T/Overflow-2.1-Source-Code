// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimE extends Check
{
    public AimE(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null && System.currentTimeMillis() - user.lastAttackPacket < 100L) {
            final double yawDelta = user.newTo.getYaw() - user.newFrom.getYaw();
            if (yawDelta == user.lastAimIYawDiff && yawDelta > 2.3) {
                if (user.aimEVerbose++ > 5) {
                    this.flag(user, new String[0]);
                    user.aimEVerbose = 0;
                }
            }
            else {
                final User user2 = user;
                user2.aimEVerbose -= ((user.aimEVerbose > 0) ? 1 : 0);
            }
            user.lastAimIYawDiff = yawDelta;
        }
    }
}
