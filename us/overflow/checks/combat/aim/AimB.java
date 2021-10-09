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

public class AimB extends Check
{
    public AimB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
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
            if (System.currentTimeMillis() - user.lastAttackPacket < 1000L && System.currentTimeMillis() - user.lastLag > 1000L && user.lastEntityAttacked != null && user.lastEntityAttacked.getWorld().equals(user.getPlayer().getWorld()) && user.getPlayer().getLocation().distance(user.lastEntityAttacked.getLocation()) > 1.55) {
                if (Math.abs(e.getTo().getPitch()) == 90.0) {
                    return;
                }
                final double yaw = e.getTo().getYaw();
                final double pitch = e.getTo().getPitch();
                final double roundYaw1 = (double)Math.round(yaw);
                final double roundPitch1 = (double)Math.round(pitch);
                final double roundYaw2 = MathUtil.preciseRound(yaw, 1);
                final double roundPitch2 = MathUtil.preciseRound(pitch, 1);
                if ((yaw == roundYaw1 || roundPitch1 == pitch || roundYaw2 == yaw || roundPitch2 == pitch) && user.getAimBVerbose().flag(3, 999L)) {
                    this.flag(user, new String[0]);
                }
            }
        }
    }
}
