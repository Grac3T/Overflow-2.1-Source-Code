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

public class AimU extends Check
{
    public AimU(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType())) {
            if (user.isLagging()) {
                user.killauraKCount = 0;
                return;
            }
            if ((user.killauraAYawReset >= 5 || !user.onGround) && user.killauraKCount > 0) {
                user.killauraKCount = -5;
            }
            if ((e.getTo().getYaw() == e.getFrom().getYaw() || user.optifineSmoothing > 2) && user.killauraKCount > 0) {
                final User user2 = user;
                --user2.killauraKCount;
            }
            if (user.isUsingOptifine() && user.killauraKCount > 0) {
                final User user3 = user;
                user3.killauraKCount -= 3;
            }
            if (TimeUtils.elapsed(user.lastAttackPacket) <= 100L && TimeUtils.elapsed(user.lastFullBlockMove) <= 200L) {
                final float pitchDifference = Math.abs(e.getFrom().getYaw() - e.getTo().getYaw());
                final double offset = Math.pow(2.0, 24.0);
                if (MathUtil.gcd((long)(pitchDifference * offset), (long)(user.lastKillauraEGPitch * offset)) < 131072L) {
                    if (user.killauraKCount < 100) {
                        final User user4 = user;
                        ++user4.killauraKCount;
                    }
                }
                else if (user.killauraKCount > 0) {
                    final User user5 = user;
                    user5.killauraKCount -= 3;
                }
            }
            else if (user.killauraKCount > 0) {
                final User user6 = user;
                user6.killauraKCount -= 10;
            }
            if (user.killauraKCount > 10 && user.killauraKVerboseFix.flag(10, 999L)) {
                this.flag(user, new String[0]);
            }
        }
    }
}
