// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.aimassist;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AimAssistC extends Check
{
    public AimAssistC(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final User user = event.getUser();
        final boolean movePacket = this.isPacketMovement(packetType);
        final boolean aimPacket = user.getFrom().getYaw() != user.getTo().getYaw() && user.getFrom().getPitch() != user.getTo().getPitch();
        if (movePacket && aimPacket) {
            final float yawDelta = Math.abs(user.from.getYaw() - user.getTo().getYaw());
            final float pitchDelta = Math.abs(user.from.getPitch() - user.getTo().getPitch());
            if (user.isUsingOptifine() || yawDelta > 30.0) {
                return;
            }
            if (user.aimAssistCSamples.add(pitchDelta) && user.aimAssistCSamples.size() == 120) {
                final long distinct = user.aimAssistCSamples.stream().distinct().count();
                final long duplicates = user.aimAssistCSamples.size() - distinct;
                if (duplicates <= 9L) {
                    if (++user.aimAssistCVerbose > 2) {
                        this.flag(user, new String[0]);
                    }
                }
                else {
                    user.aimAssistCVerbose = 0;
                }
                if (duplicates <= 3L) {
                    final User user2 = user;
                    user2.aimAssistCVerbose += 2;
                }
                user.aimAssistCSamples.clear();
            }
        }
    }
}
