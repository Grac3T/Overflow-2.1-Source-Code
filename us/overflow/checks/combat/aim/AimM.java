// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.utils.location.CustomLocation;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimM extends Check
{
    public AimM(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null && System.currentTimeMillis() - user.lastAttackPacket < 500L) {
            final CustomLocation from = user.newFrom;
            final CustomLocation to = user.newTo;
            final double pitch = Math.abs(to.getPitch() - from.getPitch());
            if (MathUtil.isValueVrySmall(pitch)) {
                if (user.aimMVerboseFuckYou < 20) {
                    final User user2 = user;
                    ++user2.aimMVerboseFuckYou;
                }
            }
            else if (user.aimMVerboseFuckYou > 0) {
                final User user3 = user;
                --user3.aimMVerboseFuckYou;
            }
            if (user.aimMVerboseFuckYou > 7) {
                this.flag(user, new String[0]);
            }
        }
    }
}
