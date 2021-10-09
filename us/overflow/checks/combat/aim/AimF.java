// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import org.bukkit.entity.Player;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimF extends Check
{
    public AimF(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (this.isPacketMovement(e.getType()) && user != null && System.currentTimeMillis() - user.lastAttackPacket < 100L) {
            if (user.getLastEntityAttacked() != null && !(user.getLastEntityAttacked() instanceof Player)) {
                return;
            }
            final double yawDiff = Math.abs(e.getTo().getYaw() - e.getFrom().getYaw());
            final double pitchDiff = Math.abs(e.getTo().getPitch() - e.getFrom().getPitch());
            if (yawDiff > 0.95) {
                if (Math.abs(e.getFrom().getPitch() - e.getTo().getPitch()) == 0.0) {
                    return;
                }
                if (pitchDiff == 0.0) {
                    final User user2 = user;
                    ++user2.aimJZeroHits;
                    user.aimJGoodHits = 0;
                }
                else if (pitchDiff > 0.0) {
                    user.aimJZeroHits = 0;
                    final User user3 = user;
                    ++user3.aimJGoodHits;
                }
                if (user.aimJZeroHits > 0 && user.aimJZeroHits < 5 && user.aimJGoodHits < 5 && user.aimJZeroHits > user.aimJGoodHits) {
                    final User user4 = user;
                    user4.aimJVerbose += ((user.aimJVerbose < 20) ? 5 : 0);
                    final User user5 = user;
                    ++user5.aimJVerbose;
                }
                else {
                    final User user6 = user;
                    user6.aimJVerbose -= ((user.aimJVerbose > 0) ? 1 : 0);
                }
                final int verbose = user.aimJVerbose;
                if (verbose > 13) {
                    final User user7 = user;
                    ++user7.aimJStable;
                }
                else {
                    user.aimJStable = 0;
                }
                if (verbose > 15 && user.aimJStable > 10) {
                    this.flag(user, new String[0]);
                }
            }
            else {
                user.aimJStable = 0;
                user.aimJVerbose = 0;
                user.aimJExpectingNext = false;
            }
        }
    }
}
