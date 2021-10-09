// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.timer;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class TimerA extends Check
{
    public TimerA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final User user = event.getUser();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final long now = System.currentTimeMillis();
            final long lastFlying = user.lastTimerAFlying;
            if (user.isLagging() || System.currentTimeMillis() - user.lastJoin < 1000L || System.currentTimeMillis() - user.lastTeleport < 1000L) {
                return;
            }
            user.timerADeque.add(now - lastFlying);
            if (user.timerADeque.size() == 40) {
                final double averagePacketDiff = user.timerADeque.stream().mapToDouble(d -> d).average().orElse(0.0);
                final double timerSpeed = 50.0 / averagePacketDiff;
                if (timerSpeed > 1.01) {
                    if (++user.timerAVerbose > 3) {
                        this.flag(user, new String[0]);
                    }
                }
                else {
                    user.timerAVerbose = Math.max(user.timerAVerbose - 1, 0);
                }
                user.timerADeque.clear();
            }
            user.lastTimerAFlying = now;
        }
    }
}
