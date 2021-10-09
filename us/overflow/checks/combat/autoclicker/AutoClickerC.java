// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.autoclicker;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AutoClickerC extends Check
{
    public AutoClickerC(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setExperimental(true);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        final User user = e.getUser();
        if (user != null) {
            final long now = System.currentTimeMillis();
            int ticks = user.autoClickerHTicks;
            double avgSpeed = user.avgAutoClickerH;
            if (e.getType().equalsIgnoreCase("PacketPlayInArmAnimation")) {
                if (now - user.lastTypeCSwing < 1L) {
                    user.lastDoubleClick = now;
                    return;
                }
                if (user.isBreakingAFUCKINGBLOCK || now - user.lastDoubleClick < 100L) {
                    return;
                }
                final int ticksree = ticks;
                if (ticksree > 5) {
                    ticks = 0;
                    user.autoClickerHTicks = ticks;
                    return;
                }
                final double speed = ticksree * 50;
                avgSpeed = (avgSpeed * 14.0 + speed) / 15.0;
                user.autoClickerHPattern.add(avgSpeed);
                if (user.autoClickerHPattern.size() > 10) {
                    final double range = Math.abs(user.autoClickerHPattern.get(user.autoClickerHPattern.size() - 1) - user.autoClickerHPattern.get(0));
                    final double diff = Math.abs(range - user.lastAutoClickerHRange);
                    if (diff > 6.5 && diff < 7.0) {
                        if (++user.autoClickerCVerbose > 1) {
                            this.flag(user, new String[0]);
                        }
                    }
                    else {
                        user.autoClickerCVerbose = Math.max(user.autoClickerCVerbose - 1, 0);
                    }
                    user.autoClickerHPattern.clear();
                    user.lastAutoClickerHRange = range;
                }
                user.lastTypeCSwing = now;
            }
            else if (this.isPacketMovement(e.getType())) {
                ++ticks;
            }
            user.autoClickerHTicks = ticks;
            user.avgAutoClickerH = avgSpeed;
        }
    }
}
