// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.movement.flight;

import java.util.Iterator;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import us.overflow.events.Listen;
import org.bukkit.Location;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class FlightD extends Check
{
    public FlightD(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final User user = event.getUser();
            final Location from = user.from;
            final Location to = user.to;
            final double threshold = 1.5 + this.getAddition(user);
            if (System.currentTimeMillis() - user.getLastCancelPlace() < 1000L || user.getHVelocityTicks() > 0.0 || user.getYVelocityTicks() > 0.0) {
                return;
            }
            if (user.didSwitchGamemode || user.generalCancel() || !user.movementChecksOK) {
                return;
            }
            if (!user.jumpPad && !user.onGround && user.waterTicks < 2 && user.webTicks < 2 && user.climableTicks < 2 && !user.isOnSlime) {
                final double deltaY = to.getY() - from.getY();
                final double distanceGround = to.getY() - user.lastFlightDGround;
                if (deltaY > 0.41999998688697815 && distanceGround > threshold) {
                    if (++user.flightDVerbose > 5) {
                        this.flag(user, new String[0]);
                    }
                }
                else {
                    user.flightDVerbose = 0;
                }
            }
            else {
                user.lastFlightDGround = to.getY();
            }
        }
    }
    
    private double getAddition(final User user) {
        double addition = 0.0;
        if (user.player.hasPotionEffect(PotionEffectType.JUMP)) {
            for (final PotionEffect effect : user.player.getActivePotionEffects()) {
                if (effect.getType().equals((Object)PotionEffectType.JUMP)) {
                    final int level = effect.getAmplifier() + 1;
                    addition = Math.pow(level + 4.2, 2.0) / 16.0;
                    break;
                }
            }
        }
        return addition;
    }
}
