// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.autoclicker;

import us.overflow.events.Listen;
import java.util.Iterator;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AutoClickerD extends Check
{
    public AutoClickerD(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final boolean flyingPacket = this.isPacketMovement(packetType);
        final boolean animationPacket = packetType.equals("PacketPlayInArmAnimation");
        final User user = event.getUser();
        if (animationPacket) {
            final int flyingTicks = user.autoClickerDTicks;
            if (!user.isBreakingAFUCKINGBLOCK && flyingTicks < 4 && user.autoClickerDSamples.size() > 10) {
                user.autoClickerDSamples.add((Object)flyingTicks);
                final double average = user.autoClickerDSamples.stream().mapToDouble(d -> d).average().orElse(0.0);
                double stdDeviation = 0.0;
                for (final Integer tick : user.autoClickerDSamples) {
                    stdDeviation += Math.pow(tick - average, 2.0);
                }
                stdDeviation /= user.autoClickerDSamples.size();
                final double skewnessTicks = MathUtil.getSkewness((Iterable)user.autoClickerDSamples);
                final double kurtosisTicks = MathUtil.getKurtosis((Iterable)user.autoClickerDSamples);
                final double sqrtDeviation = Math.sqrt(stdDeviation);
                if (skewnessTicks > 2.05 && kurtosisTicks < 0.0 && skewnessTicks == user.lastAutoClickerDSkewness && kurtosisTicks == user.lastAutoClickerDKurtosis && average == user.lastAutoClickerDAverage && sqrtDeviation < 0.6) {
                    if (++user.autoClickerDVerbose > 1) {
                        this.flag(user, new String[0]);
                    }
                }
                else {
                    user.autoClickerDVerbose = 0;
                }
                user.lastAutoClickerDAverage = average;
                user.lastAutoClickerDSkewness = skewnessTicks;
                user.lastAutoClickerDKurtosis = kurtosisTicks;
            }
            user.autoClickerDTicks = 0;
        }
        else if (flyingPacket) {
            final User user2 = user;
            ++user2.autoClickerDTicks;
        }
    }
}
