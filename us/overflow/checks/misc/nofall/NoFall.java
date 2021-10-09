// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.nofall;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.other.TimeUtils;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class NoFall extends Check
{
    public NoFall(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                final boolean faked = System.currentTimeMillis() - user.lastCancelPlace > 1000L && user.movementChecksOK && user.totalBlocksCheck > 5 && user.boatTicks < 1 && !user.nearBoat && TimeUtils.secondsFromLong(user.lastTeleport) > 5L && user.clientGround && !user.isOnGround() && !user.lastonground;
                if (System.currentTimeMillis() - user.lastJoin > 1000L && faked && user.noFallAVerbose.flag(2, 999L)) {
                    this.flag(user, new String[0]);
                }
            }
        }
    }
}
