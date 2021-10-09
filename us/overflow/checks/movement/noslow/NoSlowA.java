// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.noslow;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class NoSlowA extends Check
{
    public NoSlowA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setExperimental(true);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                final boolean block = user.getPlayer().isBlocking();
                if (block && user.liquidTicks > 1 && user.iceTicks > 1 && user.stairTicks > 1 && user.slabTicks > 1) {
                    if (user.onGround && user.getGroundTicks() > 17) {
                        if (user.noSlowAVerbose < 20) {
                            final User user2 = user;
                            ++user2.noSlowAVerbose;
                        }
                    }
                    else if (user.noSlowAVerbose > 0) {
                        final User user3 = user;
                        --user3.noSlowAVerbose;
                    }
                }
                else if (user.noSlowAVerbose > 0) {
                    final User user4 = user;
                    --user4.noSlowAVerbose;
                }
                if (user.noSlowAVerbose > 18 && MathUtil.preciseRound(user.movementSpeed, 2) > 0.07 && Math.abs(e.getTo().getY() - e.getFrom().getY()) == 0.0) {
                    this.flag(user, new String[0]);
                }
            }
        }
    }
}
