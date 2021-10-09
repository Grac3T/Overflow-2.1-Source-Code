// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.autoclicker;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AutoClickerF extends Check
{
    public AutoClickerF(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final boolean movePacket = this.isPacketMovement(packetType);
        final boolean animationPacket = packetType.equals("PacketPlayInArmAnimation");
        final User user = event.getUser();
        if (movePacket) {
            final int ticks = ++user.autoClickerFTicks;
            if (ticks == 20) {
                final boolean clicking = user.autoClickerFCps > 10;
                if (clicking) {
                    final int clicks = user.autoClickerFCps;
                    if (user.autoClickerFSamples.add(clicks) && user.autoClickerFSamples.size() == 5) {
                        final double min = user.autoClickerFSamples.stream().mapToDouble(d -> d).min().orElse(0.0);
                        final double max = user.autoClickerFSamples.stream().mapToDouble(d -> d).max().orElse(0.0);
                        if (min == max) {
                            this.flag(user, new String[0]);
                        }
                        user.autoClickerFSamples.clear();
                    }
                }
                final User user2 = user;
                final User user3 = user;
                final int n = 0;
                user3.autoClickerFTicks = n;
                user2.autoClickerFCps = n;
            }
        }
        else if (animationPacket) {
            final boolean digging = user.isBreakingAFUCKINGBLOCK;
            if (!digging) {
                final User user4 = user;
                ++user4.autoClickerFCps;
            }
        }
    }
}
