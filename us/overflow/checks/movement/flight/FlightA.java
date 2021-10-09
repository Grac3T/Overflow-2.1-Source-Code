// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.flight;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class FlightA extends Check
{
    public FlightA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getUser() != null && this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (System.currentTimeMillis() - user.lastCancelPlace < 1000L || System.currentTimeMillis() - user.lastTeleport < 1000L || System.currentTimeMillis() - user.lastBowDamage < 1000L || !user.movementChecksOK || user.generalCancel() || user.isOnGround() || user.lastGround || user.getGroundTicks() > 15 || e.getTo().getY() < 0.0) {
                user.flightADistance = 0;
                return;
            }
            if (!user.generalCancel() || user.didSwitchGamemode || user.stairTicks > 0 || user.slabTicks > 0) {
                if (user.isOnGround() || user.lastGround || user.getGroundTicks() > 15) {
                    user.flightADistance = 0;
                }
                else if (user.getTo() != null && user.getFrom() != null && user.getAirTicks() > 8) {
                    final double x = Math.floor(user.getFrom().getX());
                    final double z = Math.floor(user.getFrom().getZ());
                    if (Math.floor(user.getTo().getX()) != x || Math.floor(user.getTo().getZ()) != z) {
                        final User user2 = user;
                        ++user2.flightADistance;
                    }
                    int max = 15;
                    if (e.getTo().getY() - e.getFrom().getY() < -0.56) {
                        max = 24;
                    }
                    if (e.getTo().getY() - e.getFrom().getY() < -2.0) {
                        max = 50;
                    }
                    final int distance = user.flightADistance;
                    if (distance >= max) {
                        this.flag(user, new String[0]);
                    }
                }
            }
        }
    }
}
