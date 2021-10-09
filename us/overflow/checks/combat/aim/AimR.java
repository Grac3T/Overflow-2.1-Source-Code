// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimR extends Check
{
    public AimR(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null && this.isPacketMovement(e.getType()) && System.currentTimeMillis() - user.lastAttackPacket < 500L) {
            final double pitchDiff = Math.abs(e.getTo().getPitch() - e.getFrom().getPitch());
            if (pitchDiff > 0.0 && MathUtil.preciseRound((double)e.getTo().getPitch(), 1) == e.getTo().getPitch() && user.aimSVerbose.flag(4, 999L)) {
                this.flag(user, new String[0]);
            }
        }
    }
}
