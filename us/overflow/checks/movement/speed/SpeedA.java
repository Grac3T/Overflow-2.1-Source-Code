// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.speed;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import org.bukkit.potion.PotionEffectType;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class SpeedA extends Check
{
    public SpeedA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                if (System.currentTimeMillis() - user.lastServerVelocity < 1000L || System.currentTimeMillis() - user.lastBowDamage < 1000L || user.didSwitchGamemode || user.isJumpPad() || TimeUtils.secondsFromLong(user.lastJumpPadUpdate) < 3L || System.currentTimeMillis() - user.getLastAttackByEntity() < 1000L || user.generalCancel() || user.slimeTicks > 0 || user.didSwitchGamemode || user.stairTicks > 0 || user.slabTicks > 0) {
                    user.speedAVerbose = 0;
                    return;
                }
                float threshold = (float)((user.getAirTicks() > 0) ? ((user.getAirTicks() < 0) ? (0.4163 * Math.pow(0.984, user.getAirTicks())) : (0.4163 * Math.pow(0.984, 9.0))) : ((user.getGroundTicks() > 24) ? 0.291 : 0.375));
                if (user.slabTicks > 0 || user.stairTicks > 0) {
                    threshold += (float)0.3;
                }
                if (user.blockAboveTicks > 0 && user.iceTicks < 1) {
                    threshold += (float)0.4;
                }
                if (user.iceTicks > 0 && user.blockAboveTicks > 0) {
                    threshold += (float)1.1;
                }
                if (user.iceTicks > 0 || System.currentTimeMillis() - user.lastIce < 1000L || user.halfBlockTicks > 0) {
                    threshold += 1.5;
                }
                if (System.currentTimeMillis() - user.lastAttackPacket < 100L) {
                    threshold += (float)0.2;
                }
                if (System.currentTimeMillis() - user.lastIce < 1000L || System.currentTimeMillis() - user.getLastBlockJump() < 1000L) {
                    threshold += (float)0.4;
                }
                threshold += MathUtil.getPotionEffectLevel(user.getPlayer(), PotionEffectType.SPEED);
                if (user.getSpeedPotionTicks() > 0) {
                    user.speedAVerbose = 0;
                    return;
                }
                if (user.getMovementSpeed() > threshold) {
                    if (user.speedAVerbose++ > 1) {
                        this.flag(user, new String[0]);
                        user.speedAVerbose = 0;
                    }
                }
                else {
                    final User user2 = user;
                    user2.speedAVerbose -= ((user.speedAVerbose > 0) ? 1 : 0);
                }
            }
        }
    }
}
