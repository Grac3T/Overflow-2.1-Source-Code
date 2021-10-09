// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimP extends Check
{
    public AimP(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null) {
            if (user.isAllLagging()) {
                user.aimOYawStable = 30;
                user.aimOVerboseNigger = 0;
                user.aimOPartTick = 20;
                return;
            }
            if (this.isPacketMovement(e.getType()) && System.currentTimeMillis() - user.lastAttackPacket < 500L) {
                final double yawDiff = Math.abs(e.getNewTo().getYaw() - e.getNewFrom().getYaw());
                final double pitchDiff = Math.abs(e.getNewTo().getYaw() - e.getNewFrom().getYaw());
                if (yawDiff == 0.0 || pitchDiff < 0.11) {
                    if (user.aimOYawStable < 30) {
                        final User user2 = user;
                        ++user2.aimOYawStable;
                    }
                }
                else if (user.aimOYawStable > 0) {
                    final User user3 = user;
                    user3.aimOYawStable -= 3;
                }
                if (System.currentTimeMillis() - user.lastAimOSetTime1 < 100L && System.currentTimeMillis() - user.lastAimOSetTime2 < 120L) {
                    if (user.aimOVerboseNigger < 30) {
                        final User user4 = user;
                        ++user4.aimOVerboseNigger;
                    }
                }
                else if (user.aimOVerboseNigger > 0) {
                    final User user5 = user;
                    --user5.aimOVerboseNigger;
                }
                if (user.aimOVerboseNigger > 15) {
                    this.flag(user, new String[0]);
                }
                if (user.aimOSet) {
                    user.lastAimOSetTime1 = System.currentTimeMillis();
                }
                else {
                    user.lastAimOSetTime2 = System.currentTimeMillis();
                }
                if (user.aimOYawStable < 3) {
                    if (!user.aimOSet && yawDiff == 0.0) {
                        user.aimOSet = true;
                        final User user6 = user;
                        ++user6.aimOPartTick;
                    }
                    if (user.aimOSet && yawDiff > 0.0) {
                        user.aimOSet = false;
                        if (user.aimOPartTick > 0) {
                            final User user7 = user;
                            --user7.aimOPartTick;
                        }
                    }
                }
            }
        }
    }
}
