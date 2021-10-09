// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.other.TimeUtils;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimD extends Check
{
    private double offset;
    
    public AimD(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.offset = Math.pow(2.0, 24.0);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null) {
            return;
        }
        if (System.currentTimeMillis() - e.getUser().lastJoin < 1000L) {
            return;
        }
        if (e.getType().equalsIgnoreCase("PacketPlayInPositionLook")) {
            final User user = e.getUser();
            if (user.getOldTo() == null || user.getOldFrom() == null || e.getTo() == null || e.getFrom() == null) {
                return;
            }
            if (user.isUsingOptifine() || System.currentTimeMillis() - user.lastLag < 1000L) {
                final User user2 = user;
                user2.aimDVerbose -= ((user.aimDVerbose > 0) ? 5 : 0);
                return;
            }
            if (System.currentTimeMillis() - user.lastAttackPacket < 1000L) {
                final float pitchDifference = Math.abs(user.getOldFrom().getPitch() - user.getOldTo().getPitch());
                if (user.getOldTo().getYaw() != user.getOldFrom().getYaw() && user.getOldTo().getPitch() != user.getOldFrom().getPitch() && Math.abs(user.getOldTo().getPitch() - user.getOldFrom().getPitch()) > 0.0 && Math.abs(user.getOldTo().getPitch()) != 90.0f) {
                    final int cancer = String.valueOf(MathUtil.gcd((long)(pitchDifference * this.offset), (long)(user.lastAimHYawDiff * this.offset))).length();
                    if (cancer < 6) {
                        final User user3 = user;
                        user3.aimDVerbose += ((user.aimDVerbose < 50) ? 1 : 0);
                    }
                    else {
                        final User user4 = user;
                        user4.aimDVerbose -= ((user.aimDVerbose > 0) ? 1 : 0);
                    }
                }
                if (user.aimDVerbose > 25) {
                    this.flag(user, new String[0]);
                }
                user.lastAimHYawDiff = pitchDifference;
            }
            else if (TimeUtils.secondsFromLong(user.lastAttackPacket) > 5L) {
                user.aimDVerbose = 0;
            }
        }
    }
}
