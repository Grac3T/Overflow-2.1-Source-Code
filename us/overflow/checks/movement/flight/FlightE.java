// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.flight;

import org.bukkit.entity.Player;
import us.overflow.events.Listen;
import org.bukkit.Location;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class FlightE extends Check
{
    public FlightE(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final User user = event.getUser();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final Location from = user.from;
            final Location to = user.to;
            final double deltaX = to.getX() - from.getX();
            final double deltaZ = to.getZ() - from.getZ();
            final double deltaXZ = Math.hypot(deltaX, deltaZ);
            if (!user.movementChecksOK || user.didSwitchGamemode || user.getPlayer().getAllowFlight()) {
                return;
            }
            if (user.getHVelocityTicks() > 0.0 || user.getYVelocityTicks() > 0.0) {
                return;
            }
            if (!user.jumpPad && !user.onGround && user.waterTicks < 2 && user.webTicks < 2 && user.climableTicks < 2 && !user.isOnSlime) {
                final double motionX = this.getMotionX(user);
                final double motionZ = this.getMotionZ(user);
                final double motionXZ = motionX + motionZ;
                if (++user.flightETicks > 7 && deltaXZ > 0.3 && motionXZ == 0.0) {
                    if (++user.flightEVerbose > 3) {
                        this.flag(user, new String[0]);
                    }
                }
                else {
                    user.flightEVerbose = 0;
                }
            }
            else {
                user.flightETicks = 0;
            }
        }
    }
    
    private double getMotionX(final User user) {
        double motionX = 0.0;
        final Player player = user.getPlayer();
        try {
            final Object handle = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(player, new Object[0]);
            motionX = handle.getClass().getField("motX").getDouble(handle);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return motionX;
    }
    
    private double getMotionZ(final User user) {
        double motionZ = 0.0;
        final Player player = user.getPlayer();
        try {
            final Object handle = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(player, new Object[0]);
            motionZ = handle.getClass().getField("motZ").getDouble(handle);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return motionZ;
    }
}
