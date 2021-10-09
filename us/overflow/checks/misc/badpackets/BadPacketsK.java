// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsK extends Check
{
    public BadPacketsK(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null) {
                if (user.generalCancel() || user.didSwitchGamemode || user.getGroundTicks() > 8) {
                    return;
                }
                final double y = e.getTo().getY();
                if (y < 0.0) {
                    user.badPacketsDYInvalid = true;
                    user.badPacketsDDown = System.currentTimeMillis();
                }
                else if (user.badPacketsDYInvalid && System.currentTimeMillis() - user.badPacketsDDown < 1000L) {
                    this.flag(user, new String[0]);
                    user.badPacketsDYInvalid = false;
                }
            }
        }
    }
}
