// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.motion;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.other.TimeUtils;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class MotionB extends Check
{
    public MotionB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setExperimental(true);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                if (user.blockAboveTicks > 0 || user.getJumpPotionTicks() > 0) {
                    user.stableMotionACount = 0;
                    user.stableMotionADecrease = 0;
                    if (user.motionAThreshold > 0.0) {
                        final User user2 = user;
                        user2.motionAThreshold -= 1.5;
                    }
                    return;
                }
                if (System.currentTimeMillis() - user.lastAttackPacket < 420L) {
                    final double prediction = user.lastMotionAPredictionDiff;
                    final double trim = MathUtil.trim(5, prediction);
                    if (trim > 0.0 && trim < 0.09) {
                        if (user.motionASamePrediction > 4 && (user.getSpeedPotionTicks() > 0 || user.getJumpPotionTicks() > 0)) {
                            user.lastMotionASpeedPotion = System.currentTimeMillis();
                        }
                        if (trim == user.lastMotionAPrediction) {
                            final User user3 = user;
                            ++user3.motionASamePrediction;
                        }
                        else {
                            user.motionASamePrediction = 0;
                        }
                        if (user.motionASamePrediction > 1 || TimeUtils.secondsFromLong(user.lastMotionASpeedPotion) < 10L || user.blockAboveTicks > 0) {
                            if (user.motionAThreshold > 0.0) {
                                final User user4 = user;
                                user4.motionAThreshold -= 1.5;
                            }
                            return;
                        }
                        final double threshold = user.motionAThreshold;
                        if (threshold > 0.0) {
                            if (user.stableMotionACount < 50) {
                                final User user5 = user;
                                ++user5.stableMotionACount;
                            }
                            user.stableMotionADecrease = 0;
                        }
                        else if (threshold < 0.0) {
                            if (user.stableMotionADecrease < 50) {
                                final User user6 = user;
                                ++user6.stableMotionADecrease;
                            }
                            user.stableMotionACount = 0;
                        }
                        if (threshold > 5.2 && user.stableMotionACount > 5) {
                            this.flag(user, "threshold=" + threshold);
                        }
                        final User user7 = user;
                        user7.motionAThreshold += 1.2;
                    }
                    else if (user.motionAThreshold > 0.0) {
                        final User user8 = user;
                        user8.motionAThreshold -= 1.5;
                    }
                    user.lastMotionAPrediction = trim;
                }
                else if (user.motionAThreshold > 0.0) {
                    final User user9 = user;
                    user9.motionAThreshold -= 0.5;
                }
            }
        }
    }
}
