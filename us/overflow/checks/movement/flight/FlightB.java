// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.flight;

import us.overflow.events.Listen;
import org.bukkit.block.Block;
import us.overflow.base.user.User;
import org.bukkit.Material;
import us.overflow.utils.block.BlockUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class FlightB extends Check
{
    public FlightB(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null && user.isGAY) {
                if (System.currentTimeMillis() - user.getLastCancelPlace() < 1000L || System.currentTimeMillis() - user.lastBowDamage < 1000L || user.didSwitchGamemode || user.generalCancel() || !user.movementChecksOK || user.climbableTicks > 0 || user.slimeTicks > 0 || user.slime || System.currentTimeMillis() - user.getLastAttackByEntity() < 1000L || user.generalCancel() || user.liquidTicks > 0 || user.generalCancel() || user.nearBoat || user.boatTicks > 0) {
                    user.flightBVerbose = 0;
                    return;
                }
                final double diff = e.getTo().getY() - e.getFrom().getY();
                if (System.currentTimeMillis() - user.lastPlace < 100L) {
                    return;
                }
                final Block block = BlockUtil.getBlock(user.newTo.toLocation(user.getPlayer().getWorld()).clone().add(0.0, -1.0, 0.0));
                if (block != null) {
                    if (!user.isOnGround() && !user.lastGround) {
                        final double abs = Math.abs(diff);
                        if (abs < 0.42 && user.getAirTicks() > 15 && block.getType() == Material.AIR) {
                            if (user.flightBVerbose < 50) {
                                final User user2 = user;
                                ++user2.flightBVerbose;
                            }
                        }
                        else if (user.flightBVerbose > 0) {
                            final User user3 = user;
                            --user3.flightBVerbose;
                        }
                        if (user.flightBVerbose > 6) {
                            this.flag(user, new String[0]);
                        }
                    }
                    else {
                        user.flightBVerbose = 0;
                    }
                    user.lastFlighBDiff = diff;
                }
            }
        }
    }
}
