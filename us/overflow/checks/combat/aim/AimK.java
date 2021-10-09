// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimK extends Check
{
    public AimK(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null && this.isPacketMovement(e.getType()) && System.currentTimeMillis() - user.lastAttackPacket < 300L) {
            final double yaw = Math.abs(e.getTo().getYaw() - e.getFrom().getY());
            final double pitch = Math.abs(e.getTo().getPitch() - e.getFrom().getPitch());
            final int verbose1 = user.aimQVerbose;
            user.lastAimQPitch = pitch;
            user.lastAimQYaw = yaw;
            user.aimQVerbose = verbose1;
        }
    }
    
    public static double atanh(double a) {
        double mult;
        if (Double.doubleToRawLongBits(a) < 0L) {
            a = Math.abs(a);
            mult = -0.5;
        }
        else {
            mult = 0.5;
        }
        return mult * Math.log((1.0 + a) / (1.0 - a));
    }
}
