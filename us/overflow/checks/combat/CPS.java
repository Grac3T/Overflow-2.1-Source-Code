// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.Overflow;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class CPS extends Check
{
    public CPS(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setExperimental(true);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final boolean animationPacket = packetType.equals("PacketPlayInArmAnimation");
        final boolean movePacket = this.isPacketMovement(packetType);
        final User user = event.getUser();
        if (animationPacket) {
            final boolean breakingBlock = user.isBreakingAFUCKINGBLOCK;
            if (!breakingBlock) {
                final User user2 = user;
                ++user2.cps;
            }
        }
        else if (movePacket) {
            final int ticks = ++user.cpsTicks;
            if (ticks == 20) {
                final int cps = user.cps;
                final int threshold = Overflow.getInstance().getConfigValues().getMaxCPS();
                if (cps > threshold) {
                    this.flag(user, new String[0]);
                }
                user.cpsTicks = 0;
                user.cps = 0;
            }
        }
    }
}
