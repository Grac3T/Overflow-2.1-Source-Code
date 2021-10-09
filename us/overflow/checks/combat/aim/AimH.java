// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.Overflow;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimH extends Check
{
    public AimH(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null && user.oldTo != null && user.oldFrom != null && e.getTo().getPitch() != e.getFrom().getPitch() && System.currentTimeMillis() - user.lastAttackPacket < 300L && user.attacks > 5 && user.onGroundMixed) {
            if (user.lastEntityAttacked != null) {
                final User targetUser = Overflow.getInstance().getUserManager().getUser(user.lastEntityAttacked.getUniqueId());
                if (targetUser != null && (targetUser.nextToWall || targetUser.nextToWallTicks > 0 || System.currentTimeMillis() - user.lastFullBlockMove > 1000L)) {
                    return;
                }
            }
            final double diff = Math.abs(user.oldTo.getYaw() - user.oldFrom.getYaw());
            final double sigmoi = MathUtil.sigmoid(diff);
            final int ticks1 = user.killauraNTicks1;
            final int ticks2 = user.killauraNTicks2;
            final double round = MathUtil.round(sigmoi);
            final double normalFuckingValue = MathUtil.normalize(round, sigmoi, user.killauraNLast);
            if (Double.isInfinite(Math.abs(normalFuckingValue)) && user.killauraNVerbose.flag(50, 999L)) {
                this.flag(user, new String[0]);
            }
            user.killauraNLast = sigmoi;
            user.killauraNTicks1 = ticks1;
            user.killauraNTicks2 = ticks2;
        }
    }
}
