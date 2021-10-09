// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimX extends Check
{
    public AimX(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getType().equalsIgnoreCase("PacketPlayInPositionLook")) {
            final User user = e.getUser();
            if (System.currentTimeMillis() - user.lastAttackPacket < 1000L) {
                final double pitchDiff = e.getTo().getPitch() - e.getFrom().getPitch();
                if (!user.isLagging() && !user.isUsingOptifine() && pitchDiff == user.lastAimDPitch && Math.abs(e.getTo().getYaw() - e.getFrom().getYaw()) > 3.0 && Math.abs(e.getTo().getYaw() - e.getFrom().getYaw()) < 90.0 && user.lastEntityAttacked != null && user.lastEntityAttacked.getWorld().equals(user.getPlayer().getWorld()) && user.getPlayer().getLocation().distance(user.lastEntityAttacked.getLocation()) > 1.55 && user.aimD111Verbose.flag(20, 920L)) {
                    this.flag(user, new String[0]);
                }
                user.lastAimDPitch = pitchDiff;
            }
        }
    }
}
