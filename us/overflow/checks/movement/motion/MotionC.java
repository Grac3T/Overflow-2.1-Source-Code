// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.motion;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import org.bukkit.potion.PotionEffectType;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class MotionC extends Check
{
    public MotionC(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                if (System.currentTimeMillis() - user.lastTeleport < 1000L || System.currentTimeMillis() - user.lastRespawn < 1000L || user.teleportTicks < 10 || user.joinTicks < 10 || user.stairTicks > 0 || user.slabTicks > 0 || System.currentTimeMillis() - user.lastFullBlockMove > 155L || System.currentTimeMillis() - user.lastBlockJump < 1000L || user.generalCancel() || user.collidesHorizontally || user.fenceTicks > 0 || user.wallTicks > 0 || user.climbableTicks > 0 || user.slimeTicks > 0 || System.currentTimeMillis() - user.lastSlime < 1000L || user.jumpPad || TimeUtils.secondsFromLong(user.lastJumpPadUpdate) < 4L || user.stairTicks > 0 || user.explode || System.currentTimeMillis() - user.lastAttackByEntity < 1000L || System.currentTimeMillis() - user.lastBowDamage < 1000L || user.blockAboveTicks > 0 || user.halfBlockTicks > 0 || user.stairTicks > 0) {
                    return;
                }
                double max = 0.41999998688697815;
                if (user.getJumpPotionTicks() > 0) {
                    max += MathUtil.getPotionEffectLevel(user.getPlayer(), PotionEffectType.JUMP) * 0.1f;
                }
                if (!user.isClientGround() && user.isLastClientGround()) {
                    final double motionY = user.getNewTo().getY() - user.getNewFrom().getY();
                    if (System.currentTimeMillis() - user.getLastBlockJump() < 1000L && (motionY <= 0.404445 || motionY > 0.404444)) {
                        return;
                    }
                    final double motionYTrim = MathUtil.trim(8, motionY);
                    if (!user.collidesVertically && motionYTrim == -0.0784 && user.getNewTo().getY() < user.getNewFrom().getY()) {
                        return;
                    }
                    if (Math.abs(motionY) > 0.0 && (motionY > max || motionY < max)) {
                        this.flag(user, new String[0]);
                    }
                }
            }
        }
    }
}
