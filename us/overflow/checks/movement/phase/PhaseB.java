// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.phase;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class PhaseB extends Check
{
    public PhaseB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setDefaultBan(false);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getType().equalsIgnoreCase("PacketPlayInPosition") || e.getType().equalsIgnoreCase("PacketPlayInPositionLook") || e.getType().equalsIgnoreCase("PacketPlayInLook")) {
            final User user = e.getUser();
            if (user != null) {
                if (user.isJumpPad() || user.player.getAllowFlight() || user.player.isFlying()) {
                    return;
                }
                final double vertSpeed = Math.abs(e.getTo().getY() - e.getFrom().getY());
                if ((user.groundTicks > 0 || user.onGround) && vertSpeed > 0.99 && System.currentTimeMillis() - user.lastPhaseAFlag > 1000L && System.currentTimeMillis() - user.lastTeleport > 820L) {
                    this.flag(user, new String[0]);
                }
            }
        }
    }
}
