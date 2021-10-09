// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimO extends Check
{
    public AimO(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null && this.isPacketMovement(e.getType()) && !user.isUsingOptifine() && System.currentTimeMillis() - user.lastAttackPacket < 300L && System.currentTimeMillis() - user.lastFullBlockMove < 200L && e.getTo().getPitch() > 0.0 && Math.abs(e.getTo().getPitch() - e.getFrom().getPitch()) > 0.5) {
            final double pitchDelta = Math.abs(e.getTo().getPitch() - e.getFrom().getPitch());
            final double diffYaw = Math.abs(pitchDelta - user.aimOLastValue);
            if (diffYaw > 0.01 && diffYaw < 0.0999 && user.aimOVerbose.flag(3, 920L)) {
                this.flag(user, new String[0]);
            }
            user.aimOLastValue = pitchDelta;
        }
    }
}
