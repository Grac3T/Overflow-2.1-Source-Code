// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimC extends Check
{
    public AimC(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null) {
            return;
        }
        if (e.getType().equalsIgnoreCase("PacketPlayInPositionLook")) {
            final User user = e.getUser();
            if (user.getOldTo() == null || user.getOldFrom() == null) {
                return;
            }
            if (System.currentTimeMillis() - user.lastAttackPacket < 1000L) {
                final double pitchDiff = e.getTo().getPitch() - e.getFrom().getPitch();
                if (System.currentTimeMillis() - user.lastLag > 1000L && !user.isUsingOptifine() && pitchDiff == user.lastAimCPitchDiff && Math.abs(e.getNewTo().getYaw() - e.getNewFrom().getYaw()) > 3.0 && Math.abs(e.getNewTo().getYaw() - e.getNewFrom().getYaw()) < 90.0 && user.lastEntityAttacked != null && user.lastEntityAttacked.getWorld().equals(user.getPlayer().getWorld()) && user.getPlayer().getLocation().distance(user.lastEntityAttacked.getLocation()) > 1.55) {
                    if (user.aimCVerbose++ > 3) {
                        this.flag(user, new String[0]);
                        user.aimCVerbose = 0;
                    }
                }
                else {
                    final User user2 = user;
                    user2.aimCVerbose -= ((user.aimCVerbose > 0) ? 1 : 0);
                }
                user.lastAimCPitchDiff = pitchDiff;
            }
        }
    }
}
