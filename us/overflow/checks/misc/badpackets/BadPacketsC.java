// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import org.bukkit.GameMode;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsC extends Check
{
    public BadPacketsC(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null) {
            if (user.fenceTicks > 0 || System.currentTimeMillis() - user.lastAttackedByPlayer < 1099L || System.currentTimeMillis() - user.lastBukkitMovement > 1000L || user.getPlayer().getAllowFlight() || user.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE) || user.getPlayer().isFlying()) {
                user.badPacketsCVerbose = 0;
            }
            if (this.isPacketMovement(e.getType())) {
                if (user.slabTicks > 0 || user.stairTicks > 0 || user.mountedTicks > 0 || user.railTicks > 0L) {
                    user.badPacketsCVerbose = 0;
                    return;
                }
                if ((!user.onGround || !user.onGroundMixed) && user.badPacketsCVerbose > 0) {
                    user.badPacketsCVerbose = 0;
                }
                if ((user.onGround || user.onGroundMixed) && e.getTo().getY() > e.getFrom().getY() && Math.abs(e.getTo().getY() - e.getFrom().getY()) > 0.0 && user.badPacketsCVerbose < 20) {
                    final User user2 = user;
                    user2.badPacketsCVerbose += 2;
                }
                if (user.badPacketsCVerbose > 15) {
                    if (Math.abs(e.getTo().getY() - e.getFrom().getY()) == 0.0) {
                        user.badPacketsCVerbose = 0;
                    }
                    else {
                        this.flag(user, new String[0]);
                        user.lastBadPacketsCFlag = System.currentTimeMillis();
                    }
                }
            }
        }
    }
}
