// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimW extends Check
{
    public AimW(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setExperimental(true);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null) {
            if (e.getType().equalsIgnoreCase("PacketPlayInLook")) {
                user.lastAimAssistHLook = System.currentTimeMillis();
            }
            if (e.getType().equalsIgnoreCase("PacketPlayInPositionLook") || e.getType().equalsIgnoreCase("PacketPlayInPosition")) {
                user.lastAimAssistHPostion = System.currentTimeMillis();
            }
            if (this.isPacketMovement(e.getType()) && user.cancelTicks < 3 && TimeUtils.elapsed(user.lastFullBlockMove) < 1000L && System.currentTimeMillis() - user.lastAimAssistHPostion < 100L && System.currentTimeMillis() - user.lastAttackPacket < 500L) {
                if (user.cancelTicks < 1) {
                    final float pitch = MathUtil.getDistanceBetweenAngles(e.getTo().getPitch(), user.lastAimAssistHPitch);
                    final float yaw = MathUtil.getDistanceBetweenAngles(e.getFrom().getYaw(), user.lastAimAssistHYaw);
                    final long p1 = (long)(pitch * Math.pow(2.0, 24.0));
                    final long p2 = (long)(user.lastAimAssistHPitch * Math.pow(2.0, 24.0));
                    final long y1 = (long)(yaw * Math.pow(2.0, 24.0));
                    final long y2 = (long)(user.lastAimAssistHYaw * Math.pow(2.0, 24.0));
                    final long gcdPitch = MathUtil.gcd(p1, p2);
                    final long gcdYaw = MathUtil.gcd(y1, y2);
                    if (Math.abs(gcdPitch - user.lastAimAssistHGCDPitch) == user.lastAimAssistHVal1 && Math.abs(gcdPitch - user.lastAimAssistHGCDPitch) < 50000.0) {
                        final User user2 = user;
                        user2.aimAssistHVerbose1 += 7;
                    }
                    else if (user.aimAssistHVerbose1 > 0) {
                        final User user3 = user;
                        --user3.aimAssistHVerbose1;
                    }
                    if (Math.abs(e.getTo().getPitch() - e.getFrom().getPitch()) < 0.003 || (Math.abs(e.getTo().getYaw() - e.getFrom().getYaw()) < 0.2 && (e.getTo().getYaw() == e.getFrom().getYaw() || e.getTo().getPitch() == e.getFrom().getPitch()))) {
                        if (user.aimAssistHPitchSame < 15) {
                            final User user4 = user;
                            ++user4.aimAssistHPitchSame;
                        }
                    }
                    else if (user.aimAssistHPitchSame > 0) {
                        final User user5 = user;
                        --user5.aimAssistHPitchSame;
                    }
                    if (user.aimAssistHPitchSame > 3) {
                        user.aimAssistHVerbose1 = 0;
                    }
                    if (System.currentTimeMillis() - user.lastAimAssistHLook < 1000L && (user.optifineSmoothing > 0 || user.optifineSmoothingFix > 2) && user.aimAssistHPitchSame > 0) {
                        final User user6 = user;
                        user6.aimAssistHPitchSame -= 7;
                    }
                    user.lastAimAssistHVal1 = Math.abs(gcdPitch - user.lastAimAssistHGCDPitch);
                    user.lastAimAssistHGCDYaw = gcdYaw;
                    user.lastAimAssistHGCDPitch = (double)gcdPitch;
                    user.lastAimAssistHYaw = e.getTo().getYaw();
                    user.lastAimAssistHPitch = e.getTo().getPitch();
                }
                else {
                    if (user.aimAssistHPitchSame > 0) {
                        final User user7 = user;
                        --user7.aimAssistHPitchSame;
                    }
                    if (user.aimAssistHVerbose1 > 0) {
                        final User user8 = user;
                        --user8.aimAssistHVerbose1;
                    }
                }
                if (user.aimAssistHVerbose1 > 0 && user.aimAssistHVerbose1 > user.aimAssistHVerbose1Last && user.aimAssistHVerbose2.flag(10, 1000L)) {
                    this.flag(user, new String[0]);
                }
                user.aimAssistHVerbose1Last = user.aimAssistHVerbose1;
                if (user.aimAssistHVerbose2.getVerbose() > 1) {
                    final User user9 = user;
                    ++user9.aimAssistHCounter;
                }
                else {
                    user.aimAssistHCounter = 0;
                }
            }
        }
    }
}
