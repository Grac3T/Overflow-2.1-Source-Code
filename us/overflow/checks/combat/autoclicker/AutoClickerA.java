// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.autoclicker;

import us.overflow.events.Listen;
import java.util.Iterator;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AutoClickerA extends Check
{
    public AutoClickerA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setExperimental(true);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        final User user = e.getUser();
        if (user != null) {
            if (e.getType().equalsIgnoreCase("PacketPlayInArmAnimation")) {
                if (user.isBreakingAFUCKINGBLOCK) {
                    return;
                }
                if (user.autoClickerBTicks < 10) {
                    user.autoClickerBCounts.add(user.autoClickerBTicks);
                    if (user.autoClickerBCounts.size() == 50) {
                        double average = 0.0;
                        for (final int i : user.autoClickerBCounts) {
                            average += i;
                        }
                        average /= user.autoClickerBCounts.size();
                        double stdDev = 0.0;
                        for (final int j : user.autoClickerBCounts) {
                            stdDev += Math.pow(j - average, 2.0);
                        }
                        stdDev /= user.autoClickerBCounts.size();
                        stdDev = Math.sqrt(stdDev);
                        if (stdDev < 0.45) {
                            this.flag(user, new String[0]);
                        }
                        user.autoClickerBCounts.clear();
                    }
                }
                user.autoClickerBTicks = 0;
            }
            else if (this.isPacketMovement(e.getType())) {
                final User user2 = user;
                ++user2.autoClickerBTicks;
            }
        }
    }
}
