// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aim;

import us.overflow.events.Listen;
import org.bukkit.Location;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimY extends Check
{
    public AimY(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent packet) {
        final String packetType = packet.getType();
        final User user = packet.getUser();
        final boolean movePacket = this.isPacketMovement(packetType);
        if (movePacket) {
            final Location from = user.from;
            final Location to = user.to;
            final float deltaYaw = Math.abs(to.getYaw() - from.getYaw());
            final float deltaPitch = Math.abs(to.getPitch() - from.getPitch());
            if (user.isUsingOptifine()) {
                return;
            }
            if (deltaYaw > 0.0 && deltaPitch > 0.0 && deltaYaw < 20.0f && deltaPitch < 30.0f) {
                user.aimYPitchSamples.add(deltaPitch);
                user.aimYYawSamples.add(deltaYaw);
                if (user.aimYYawSamples.size() == 20 && user.aimYPitchSamples.size() == 20) {
                    final long distinctYaw = user.aimYYawSamples.stream().distinct().count();
                    final long distinctPitch = user.aimYPitchSamples.stream().distinct().count();
                    final long duplicatesYaw = user.aimYYawSamples.size() - distinctYaw;
                    final long duplicatesPitch = user.aimYPitchSamples.size() - distinctPitch;
                    if (duplicatesYaw == 0L && duplicatesPitch == 0L) {
                        if (++user.aimYBuffer > 6) {
                            this.flag(user, new String[0]);
                        }
                    }
                    else {
                        user.aimYBuffer = 0;
                    }
                    user.aimYYawSamples.clear();
                    user.aimYPitchSamples.clear();
                }
            }
        }
    }
}
