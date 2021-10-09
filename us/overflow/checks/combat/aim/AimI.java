// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimI extends Check
{
    public AimI(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null && user.oldTo != null && user.oldFrom != null) {
            if (e.getTo() != e.getFrom()) {
                user.lastAimOMove = System.currentTimeMillis();
            }
            if (e.getNewTo().getPitch() != e.getNewFrom().getPitch()) {
                user.lastAimOPitchChange = System.currentTimeMillis();
            }
            if (e.getNewTo().getYaw() != e.getNewFrom().getYaw()) {
                user.lastAimOYawChange = System.currentTimeMillis();
            }
            final long lastMove = System.currentTimeMillis() - user.lastAimOMove;
            final long lastPitchChange = System.currentTimeMillis() - user.lastAimOPitchChange;
            final long lastYawChange = System.currentTimeMillis() - user.lastAimOYawChange;
            if (Math.abs(e.getTo().getPitch() - e.getFrom().getPitch()) > 1.2 && Math.abs(e.getTo().getYaw() - e.getFrom().getYaw()) > 1.05 && lastMove < 500L && lastPitchChange < 100L && lastYawChange < 100L && System.currentTimeMillis() - user.lastAttackPacket < 500L) {
                final User user2 = user;
                ++user2.aimOPredictionTicks;
            }
            else {
                user.aimOPredictionTicks = 0;
            }
            if (user.aimOPredictionTicks > 45) {}
        }
    }
}
