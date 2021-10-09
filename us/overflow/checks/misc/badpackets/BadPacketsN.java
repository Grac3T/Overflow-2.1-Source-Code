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

public class BadPacketsN extends Check
{
    public BadPacketsN(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                if (user.snowTicks > 0 || user.slimeTicks > 0 || user.stairTicks > 0 || user.slime || user.liquidTicks > 0 || user.blockAboveTicks > 0 || user.slabTicks > 0 || !user.isGAY() || user.fenceTicks > 0 || user.getPlayer().getAllowFlight() || user.getPlayer().getGameMode().equals((Object)GameMode.CREATIVE) || user.getPlayer().isFlying()) {
                    user.badPacketsBVerbose1 = 0;
                    return;
                }
                final double y = e.getTo().getY();
                final double diff = Math.abs(y - user.lastBadPacketsBY);
                if (diff > 0.0 && Math.abs(diff - user.badPacketsBDiff) < 0.055) {
                    if (user.badPacketsBStable < 50) {
                        final User user2 = user;
                        ++user2.badPacketsBStable;
                    }
                }
                else if (user.badPacketsBStable > 0) {
                    final User user3 = user;
                    --user3.badPacketsBStable;
                }
                user.badPacketsBDiff = diff;
                if (user.badPacketsBStable > 3) {
                    if (user.stairTicks > 0 || user.getMountedTicks() > 0 || user.railTicks > 0 || System.currentTimeMillis() - user.lastBlockJump < 1000L) {
                        user.badPacketsBVerbose1 = 0;
                        return;
                    }
                    if (!user.isOnGround() && user.badPacketsBVerbose1 > 0) {
                        user.badPacketsBVerbose1 = 0;
                    }
                    if (user.isOnGround() && e.getTo().getY() > e.getFrom().getY() && Math.abs(e.getTo().getY() - e.getFrom().getY()) > 0.0 && user.badPacketsBVerbose1 < 20) {
                        final User user4 = user;
                        user4.badPacketsBVerbose1 += 2;
                    }
                    if (user.badPacketsBVerbose1 > 15 && user.isOnGround()) {
                        if (Math.abs(e.getTo().getY() - e.getFrom().getY()) == 0.0) {
                            user.badPacketsBVerbose1 = 0;
                        }
                        else {
                            this.flag(user, new String[0]);
                        }
                    }
                }
                user.lastBadPacketsBY = y;
            }
        }
    }
}
