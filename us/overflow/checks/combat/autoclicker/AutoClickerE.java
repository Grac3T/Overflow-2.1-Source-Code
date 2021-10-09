// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.autoclicker;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AutoClickerE extends Check
{
    public AutoClickerE(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final long now = System.currentTimeMillis();
        final boolean movePacket = this.isPacketMovement(packetType);
        final boolean animationPacket = packetType.equals("PacketPlayInArmAnimation");
        final User user = event.getUser();
        if (movePacket) {
            final boolean sent = user.autoClickerESent;
            if (sent) {
                final long delay = now - user.autoClickerESwing;
                if (delay > 40L && delay < 100L) {
                    final User user2 = user;
                    user2.autoClickerEVerbose += 0.25;
                    if (user.autoClickerEVerbose > 0.5) {}
                }
                else {
                    user.autoClickerEVerbose = Math.max(user.autoClickerEVerbose - 0.025, 0.0);
                }
                user.autoClickerESent = false;
            }
            user.autoClickerEFlying = now;
        }
        else if (animationPacket) {
            final long delay2 = now - user.autoClickerEFlying;
            if (delay2 > 10L) {
                user.autoClickerESent = true;
                user.autoClickerESwing = now;
            }
        }
    }
}
