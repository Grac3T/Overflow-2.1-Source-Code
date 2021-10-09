// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.speed;

import us.overflow.events.Listen;
import us.overflow.utils.location.CustomLocation;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import org.bukkit.potion.PotionEffectType;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class SpeedC extends Check
{
    public SpeedC(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                final CustomLocation to = user.newTo;
                final CustomLocation from = user.newFrom;
                if (user.generalCancel() || user.halfBlockTicks > 0 || user.isJumpPad() || user.snowTicks > 1 || user.generalCancel() || user.didSwitchGamemode) {
                    return;
                }
                final double x = to.getX() - from.getX();
                final double z = to.getZ() - from.getZ();
                double maxSpeed = 0.2873;
                if (user.iceTicks > 0) {
                    maxSpeed += 0.891800045967102;
                }
                if (user.slabTicks > 0 || user.stairTicks > 0 || System.currentTimeMillis() - user.lastBlockJump < 1000L) {
                    maxSpeed += 0.33966671926;
                }
                if (user.blockAboveTicks > 0) {
                    return;
                }
                maxSpeed += MathUtil.getPotionEffectLevel(user.getPlayer(), PotionEffectType.SPEED) * 0.2;
                if (user.speedPotionTicks > 0) {
                    return;
                }
                final double speed = Math.sqrt(x * x + z * z);
                if (speed > maxSpeed && user.getGroundTicks() > 19 && user.getAirTicks() < 1 && to.getY() - from.getY() == 0.0 && user.getSpeedCVerbose().flag(2, 920L)) {
                    this.flag(user, new String[0]);
                }
            }
        }
    }
}
