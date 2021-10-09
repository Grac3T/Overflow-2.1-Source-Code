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

public class AimA extends Check
{
    private double offset;
    
    public AimA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
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
            final boolean work2 = Math.abs(user.getFrom().getYaw() - user.getTo().getYaw()) < 30.0f;
            final boolean work3 = System.currentTimeMillis() - user.lastAttackPacket < 500L || System.currentTimeMillis() - user.lastPlace < 155L;
            if (user.isUsingOptifine() || System.currentTimeMillis() - user.lastLag < 1000L || user.isLagging()) {
                final User user2 = user;
                user2.aimAVerbose -= ((user.aimAVerbose > 0) ? 5 : 0);
                return;
            }
            if (work3 && work2) {
                final float pitchDifference = Math.abs(user.getOldFrom().getPitch() - user.getOldTo().getPitch());
                if (user.getOldTo().getYaw() != user.getOldFrom().getYaw() && user.getOldTo().getPitch() != user.getOldFrom().getPitch()) {
                    if (Math.abs(user.getOldTo().getPitch() - user.getOldFrom().getPitch()) > 0.0 && Math.abs(user.getOldTo().getPitch()) != 90.0f && MathUtil.gcd((long)(pitchDifference * this.offset), (long)(user.lastAimAPitch * this.offset)) < 131072L) {
                        if (user.aimAVerbose < 20) {
                            final User user3 = user;
                            user3.aimAVerbose += 2;
                        }
                    }
                    else {
                        final User user4 = user;
                        user4.aimAVerbose -= ((user.aimAVerbose > 0) ? 1 : 0);
                    }
                }
                else {
                    final User user5 = user;
                    user5.aimAVerbose -= ((user.aimAVerbose > 0) ? 1 : 0);
                }
                if (user.aimAVerbose > 14) {
                    this.flag(user, new String[0]);
                }
                user.lastAimAPitch = pitchDifference;
            }
            else if (TimeUtils.secondsFromLong(user.lastAttackPacket) > 5L && TimeUtils.secondsFromLong(user.lastPlace) > 2L) {
                user.aimAVerbose = 0;
            }
        }
    }
}
