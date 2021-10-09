// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimN extends Check
{
    public AimN(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null && user.oldTo != null && user.oldFrom != null && e.getTo().getPitch() != e.getFrom().getPitch() && System.currentTimeMillis() - user.lastAttackPacket < 300L && user.attacks > 5) {
            final double yawDelta = Math.abs(e.getTo().getYaw() - e.getFrom().getYaw());
            final double diffYaw = Math.abs(yawDelta - user.lastAimNValue);
            if (user.isUsingOptifine()) {
                if (user.aimNOptifineTicks < 100) {
                    final User user2 = user;
                    ++user2.aimNOptifineTicks;
                }
            }
            else if (user.aimNOptifineTicks > 0) {
                final User user3 = user;
                --user3.aimNOptifineTicks;
            }
            if (user.aimNOptifineTicks > 0) {
                return;
            }
            if (diffYaw > 0.055 && diffYaw < 0.0999 && user.aimNVerbose.flag(8, 920L)) {
                this.flag(user, new String[0]);
            }
            user.lastAimNValue = yawDelta;
        }
    }
}
