// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.autoclicker;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AutoClickerG extends Check
{
    public AutoClickerG(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final User user = event.getUser();
        final boolean armAnimation = "PacketPlayInArmAnimation".equals(packetType);
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final int ticks = ++user.autoClickerGTicks;
            final double cps = user.autoClickerGCps;
            if (ticks == 20) {
                if (cps > 7.0 && user.autoClickerGSamples.add(cps) && user.autoClickerGSamples.size() == 10) {
                    final double averageCps = user.autoClickerGSamples.stream().mapToDouble(d -> d).average().orElse(0.0);
                    final double maxCps = user.autoClickerGSamples.stream().mapToDouble(d -> d).max().orElse(0.0);
                    final double ratio = averageCps / maxCps;
                    if (averageCps > 10.0 && maxCps > 14.0 && ratio > 0.99) {
                        this.flag(user, new String[0]);
                    }
                    user.autoClickerGSamples.clear();
                }
                user.autoClickerGCps = 0.0;
                user.autoClickerGTicks = 0;
            }
        }
        else if (armAnimation) {
            final boolean digging = user.isBreakingAFUCKINGBLOCK;
            if (!digging) {
                final User user2 = user;
                ++user2.autoClickerGCps;
            }
        }
    }
}
