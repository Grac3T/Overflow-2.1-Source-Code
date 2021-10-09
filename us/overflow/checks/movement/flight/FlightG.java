// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.flight;

import us.overflow.events.Listen;
import org.bukkit.Location;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.api.VelocitySnapshot;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public final class FlightG extends Check
{
    public FlightG(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final User user = event.getUser();
        final String packetType = event.getType();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final Location from = user.getFrom();
            final Location to = user.getTo();
            final boolean moved = to.distance(from) > 0.05;
            final boolean touchingAir = !user.onGround && !user.wasOnGround;
            final double velocityMax = user.velocityLengthSamples.stream().mapToDouble(VelocitySnapshot::getLength).max().orElse(0.0);
            if (touchingAir) {
                final double groundDistance = MathUtil.vectorDistance(to, user.flightGGroundLocation);
                if (groundDistance > 5.0 && velocityMax == 0.0 && moved) {
                    this.flag(user, new String[0]);
                }
            }
            else {
                user.setFlightGGroundLocation(to);
            }
        }
    }
}
