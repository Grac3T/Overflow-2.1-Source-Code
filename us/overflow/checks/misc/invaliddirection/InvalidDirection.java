// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.invaliddirection;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class InvalidDirection extends Check
{
    public InvalidDirection(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final boolean movePacket = this.isPacketMovement(packetType);
        final User user = event.getUser();
        if (movePacket) {
            final float currentPitch = event.getTo().getPitch();
            final float thresholdPitch = (user.getClimableTicks() > 2) ? 90.1f : 90.0f;
            if (Math.abs(currentPitch) > thresholdPitch) {
                this.flag(user, new String[0]);
            }
        }
    }
}
