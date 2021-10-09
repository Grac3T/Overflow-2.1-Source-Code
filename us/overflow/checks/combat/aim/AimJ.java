// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimJ extends Check
{
    public AimJ(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null && System.currentTimeMillis() - user.lastAttackPacket < 1000L) {
            if (e.getType().equalsIgnoreCase("PacketPlayInPosition")) {
                if (!user.aimPSet) {
                    user.aimPSet = true;
                }
                user.lastAimPPos = System.currentTimeMillis();
            }
            if (e.getType().equalsIgnoreCase("PacketPlayInPositionLook")) {
                if (user.aimPSet && System.currentTimeMillis() - user.lastAimPPos < 50L) {
                    user.aimPSet = false;
                    if (user.aimPVerbose < 20) {
                        final User user2 = user;
                        user2.aimPVerbose += 2;
                    }
                }
                else if (user.aimPVerbose > 0) {
                    final User user3 = user;
                    --user3.aimPVerbose;
                }
                if (user.aimPVerbose > 6) {
                    this.flag(user, new String[0]);
                }
            }
        }
    }
}
