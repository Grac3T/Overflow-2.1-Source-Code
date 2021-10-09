// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.motion;

import us.overflow.events.Listen;
import org.bukkit.Location;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class MotionA extends Check
{
    public MotionA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final User user = event.getUser();
            if (user.getHVelocityTicks() > 0.0 || user.getYVelocityTicks() > 0.0) {
                return;
            }
            if (user.didSwitchGamemode || user.generalCancel() || !user.movementChecksOK) {
                return;
            }
            if (!user.jumpPad && !user.onGround && user.waterTicks < 2 && user.webTicks < 2) {
                final Location from = user.from;
                final Location to = user.to;
                final double horizontalDistance = MathUtil.vectorDistance(from, to);
                if (horizontalDistance > 0.005) {
                    double estimated = user.lastMotionAHorizontal * 0.91 + 0.2;
                    if (user.sprinting) {
                        estimated += 0.0063;
                    }
                    if (horizontalDistance - estimated > 1.0E-12 && horizontalDistance > 0.1 && estimated > 0.075) {
                        if (++user.motionAVerbose > 5) {
                            this.flag(user, new String[0]);
                        }
                    }
                    else {
                        user.motionAVerbose = 0;
                    }
                    user.lastMotionAHorizontal = horizontalDistance;
                }
            }
        }
    }
}
