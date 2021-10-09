// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aimassist;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimAssistA extends Check
{
    private double offset;
    
    public AimAssistA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.offset = Math.pow(2.0, 24.0);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null && user.oldTo != null && user.oldFrom != null) {
            if (user.isLagging() || TimeUtils.elapsed(user.lastAttackPacket) > 1000L) {
                user.aimAssistsACount = 0;
                user.lastAimAssistGCD = 0L;
                return;
            }
            if (System.currentTimeMillis() - user.lastKillauraHBad < 1999L) {
                user.aimAssistsACount = 0;
                user.lastAimAssistGCD = 0L;
                return;
            }
            if (user.optifineSmoothing > 1 || user.optifineSmoothSens >= 3 || user.aimAssistAWork) {
                user.aimAssistsACount = 0;
                user.lastAimAssistGCD = 0L;
                return;
            }
            if (Math.abs(e.getTo().getPitch() - e.getFrom().getPitch()) == 0.0) {
                if (user.kys > 0) {
                    final User user2 = user;
                    --user2.kys;
                }
            }
            else if (user.kys < 30) {
                final User user3 = user;
                ++user3.kys;
            }
            if (user.kys < 7) {
                return;
            }
            user.lastFixerIDK = user.player.getLocation().getPitch();
            if (((user.killauraAYawReset > 2) ? (user.killauraAPitchReset * user.killauraAYawReset) : user.killauraAYawReset) > 3) {
                user.lastAimAssistACE = System.currentTimeMillis();
            }
            if (TimeUtils.elapsed(user.lastAimAssistACE) <= 100L && user.aimAssistsACount > 0) {
                final User user4 = user;
                --user4.aimAssistsACount;
            }
            if (TimeUtils.elapsed(user.lastFullBlockMove) > 500L) {
                if (user.aimAssistsACount > 0) {
                    final User user5 = user;
                    --user5.aimAssistsACount;
                }
            }
            else if (user.killauraAYawReset >= 5) {
                user.aimAssistsACount = 0;
            }
            if (TimeUtils.elapsed(user.lastAttackPacket) <= 999L) {
                final float pitch = MathUtil.getDistanceBetweenAngles(user.to.getPitch(), user.lastAimAssistAPitch);
                final long p1 = (long)(pitch * Math.pow(2.0, 24.0));
                final long p2 = (long)(user.lastAimAssistAPitch * Math.pow(2.0, 24.0));
                if (MathUtil.gcd(p1, p2) == user.lastAimAssistGCD) {
                    final User user6 = user;
                    user6.aimAssistsACount += 2;
                }
                else if (user.aimAssistsACount > 0) {
                    final User user7 = user;
                    user7.aimAssistsACount -= 2;
                }
                if (Math.abs(user.to.getPitch() - user.from.getPitch()) <= 0.0) {
                    if (user.killauraAYawReset > 1) {
                        user.aimAssistsACount = 0;
                        return;
                    }
                    if (user.deltaXZ < 0.2 && user.aimAssistsACount > 0) {
                        final User user8 = user;
                        --user8.aimAssistsACount;
                    }
                    if (user.aimAssistsACount >= 10 && user.aimAssistVerbose.flag(1, 999L)) {
                        this.flag(user, new String[0]);
                    }
                    user.lastAimAssistGCD = MathUtil.gcd(p1, p2);
                    user.lastAimAssistAYaw = user.to.getYaw();
                    user.lastAimAssistAPitch = user.to.getPitch();
                }
            }
        }
    }
}
