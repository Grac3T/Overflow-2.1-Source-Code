// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimL extends Check
{
    public AimL(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        final User user = e.getUser();
        if (user != null) {
            double avgPitchSpeed = user.avgPitchSpeed;
            double avgPitchDevation = user.avgPitchDevation;
            if (this.isPacketMovement(e.getType())) {
                if (TimeUtils.elapsed(user.lastAttackPacket) < 100L) {
                    if (user.isUsingOptifine()) {
                        user.killauraLVerbose.setVerbose(0);
                        return;
                    }
                    final double pitch = user.newTo.getPitch() - user.newFrom.getPitch();
                    final double yaw = user.newTo.getYaw() - user.newFrom.getYaw();
                    if (yaw > 3.0 && yaw < 90.0) {
                        avgPitchSpeed = (avgPitchSpeed * 14.0 + pitch) / 15.0;
                        final double deviation = Math.abs(pitch - avgPitchSpeed);
                        avgPitchDevation = (avgPitchDevation * 9.0 + deviation) / 10.0;
                        if (avgPitchDevation < 0.1 && user.killauraLVerbose.flag(10, 700L)) {
                            this.flag(user, new String[0]);
                        }
                    }
                }
                user.avgPitchSpeed = avgPitchSpeed;
                user.avgPitchDevation = avgPitchDevation;
            }
        }
    }
}
