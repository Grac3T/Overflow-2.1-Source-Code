// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.timer;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class TimerB extends Check
{
    public TimerB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final User user = event.getUser();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final long now = System.currentTimeMillis();
            final long lastFlying = user.lastTimerBFlying;
            if (user.joinTicks < 3 || user.teleportTicks < 3 || user.lagging) {
                return;
            }
            user.movingStats.add((double)(now - lastFlying));
            final double deviationMax = 7.07;
            final double deviationStats = user.movingStats.getStdDev(7.07);
            if (Double.isNaN(deviationStats) && deviationStats < 7.07) {
                if (++user.timerBVerbose > 7) {
                    this.flag(user, new String[0]);
                }
            }
            else {
                user.timerBVerbose = 0;
            }
            user.lastTimerBFlying = now;
        }
    }
}
