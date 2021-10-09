// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.flight;

import us.overflow.events.Listen;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class FlightC extends Check
{
    public FlightC(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final User user = event.getUser();
            final Player player = user.player;
            final Location from = user.from;
            final Location to = user.to;
            final double horizontalDistance = MathUtil.vectorDistance(from, to);
            if (user.getYVelocityTicks() > 0.0 && user.getHVelocityTicks() > 0.0) {
                return;
            }
            if (System.currentTimeMillis() - user.lastCancelPlace < 1000L || user.generalCancel() || user.didSwitchGamemode || !user.movementChecksOK || horizontalDistance == 0.0) {
                return;
            }
            if (!user.onGround && user.climableTicks < 2 && user.liquidTicks < 2 && !user.jumpPad && user.webTicks < 2) {
                final User user2 = user;
                ++user2.flightCAir;
                final double deltaY = to.getY() - from.getY();
                final double acceleration = Math.abs(deltaY - user.lastFlightCDelta);
                final double deltaAcceleration = Math.abs(acceleration - user.lastFlightCAcceleration);
                if (user.flightCAir > 9 && deltaY == 0.0) {
                    if (++user.flightCHoverVerbose > 5) {
                        this.flag(user, new String[0]);
                    }
                }
                else {
                    user.flightCHoverVerbose = 0;
                }
                if ((user.flightCAir > 8 && deltaAcceleration == 0.0) || deltaAcceleration > 0.018) {
                    if (++user.flightCAccelerationVerbose > 4) {
                        this.flag(user, new String[0]);
                    }
                }
                else {
                    user.flightCAccelerationVerbose = 0;
                }
                user.lastFlightCDelta = deltaY;
                user.lastFlightCAcceleration = acceleration;
            }
            else {
                user.flightCAir = 0;
            }
        }
    }
}
