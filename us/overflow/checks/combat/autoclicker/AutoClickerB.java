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

public class AutoClickerB extends Check
{
    public AutoClickerB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
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
                if (user.autoClickerETicks < 10) {
                    user.autoClickerECounts.add(user.autoClickerETicks);
                    if (user.autoClickerECounts.size() == 50) {
                        double average = 0.0;
                        for (final int i : user.autoClickerECounts) {
                            average += i;
                        }
                        average /= user.autoClickerECounts.size();
                        double stdDev = 0.0;
                        for (final int j : user.autoClickerECounts) {
                            stdDev += Math.pow(j - average, 2.0);
                        }
                        stdDev /= user.autoClickerECounts.size();
                        stdDev = Math.sqrt(stdDev);
                        if (stdDev == user.lastAutoClickerESTD) {
                            this.flag(user, new String[0]);
                        }
                        user.lastAutoClickerESTD = stdDev;
                        user.autoClickerECounts.clear();
                    }
                }
                user.autoClickerETicks = 0;
            }
            else if (this.isPacketMovement(e.getType())) {
                final User user2 = user;
                ++user2.autoClickerETicks;
            }
        }
    }
}
