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

public class AimV extends Check
{
    public AimV(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && System.currentTimeMillis() - user.lastAttackPacket < 100L && Math.abs(e.getTo().getYaw() - e.getFrom().getYaw()) > 0.99 && Math.abs(e.getTo().getPitch() - e.getFrom().getPitch()) > 0.65) {
            final double diff = Math.abs(e.getTo().getYaw() - e.getFrom().getYaw());
            final double sigmoi = MathUtil.sigmoid(diff);
            final int ticks1 = user.killauraA2Ticks1;
            final int ticks2 = user.killauraA2Ticks2;
            final double round = MathUtil.round(sigmoi);
            final double normalFuckingValue = MathUtil.normalize(round, sigmoi, user.killauraA2LastValue);
            if (Double.isInfinite(Math.abs(normalFuckingValue)) && user.killauraA2Verbose.flag(5, 999L) && Math.abs(diff) > 0.0) {
                this.flag(user, new String[0]);
            }
            user.killauraA2LastValue = sigmoi;
            user.killauraA2Ticks1 = ticks1;
            user.killauraA2Ticks2 = ticks2;
        }
    }
}
