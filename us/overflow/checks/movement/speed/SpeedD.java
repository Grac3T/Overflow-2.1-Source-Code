// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.speed;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class SpeedD extends Check
{
    public SpeedD(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                if (System.currentTimeMillis() - user.lastServerVelocity < 1000L || System.currentTimeMillis() - user.lastBowDamage < 1000L || System.currentTimeMillis() - user.getLastTeleport() < 1000L || System.currentTimeMillis() - user.getLastAttackByEntity() < 1000L || user.getBoatTicks() > 0 || user.getMountedTicks() > 0 || user.generalCancel() || user.didSwitchGamemode) {
                    return;
                }
                if (user.getHVelocityTicks() > 0.0 || user.getYVelocityTicks() > 0.0) {
                    return;
                }
                if (!user.isJumpPad() && TimeUtils.secondsFromLong(user.lastJumpPadUpdate) > 3L && user.isGAY() && user.iceTicks < 1 && System.currentTimeMillis() - user.lastIce > 1000L && user.blockAboveTicks < 2 && user.getMovementSpeed() >= 1.51) {
                    this.flag(user, new String[0]);
                }
            }
        }
    }
}
