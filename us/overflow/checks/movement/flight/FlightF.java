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

public final class FlightF extends Check
{
    public FlightF(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final User user = event.getUser();
        final String packetType = event.getType();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final Location from = user.getFrom();
            final Location to = user.to;
            final double motionY = this.getMotionY(user);
            final double deltaY = to.getY() - from.getY();
            if (user.jumpPad || user.joinTicks < 3 || user.slimeTicks < 3 || user.teleportTicks < 3) {
                return;
            }
            if (motionY <= 0.0 && deltaY > 0.41999998688697815) {
                this.flag(user, new String[0]);
            }
        }
    }
    
    private double getMotionY(final User user) {
        final Player player = user.getPlayer();
        double motionY;
        try {
            final Object entityPlayer = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(player, new Object[0]);
            motionY = entityPlayer.getClass().getField("motY").getDouble(entityPlayer);
        }
        catch (Exception e) {
            motionY = 1.0;
        }
        return motionY;
    }
}
