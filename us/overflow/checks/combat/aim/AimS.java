// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import java.util.Collection;
import java.util.Collections;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimS extends Check
{
    public AimS(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null) {
            if (user.isAllLagging()) {
                user.aimSSamples.clear();
                return;
            }
            if (user.aimS1Verbose > 7) {
                this.flag(user, new String[0]);
            }
            if (System.currentTimeMillis() - user.lastAttackPacket < 500L) {
                if (user.aimSSamples.size() <= 20) {
                    final double yaw = Math.abs(e.getTo().getYaw() - e.getFrom().getYaw());
                    if (user.aimSLastYaw != yaw) {
                        user.aimSSamples.add(yaw);
                    }
                    user.aimSLastYaw = yaw;
                }
                else {
                    final int badSamples = Collections.frequency(user.aimSSamples, 0.0);
                    final int samples = user.aimSSamples.size();
                    final int calc = Math.abs(samples - badSamples);
                    if (calc < 17 && badSamples < 15 && badSamples > calc) {
                        this.flag(user, new String[0]);
                        final User user2 = user;
                        user2.aimS1Verbose += 15;
                    }
                    else if (user.aimS1Verbose > 0) {
                        final User user3 = user;
                        --user3.aimS1Verbose;
                    }
                    user.aimSSamples.clear();
                }
            }
        }
    }
}
