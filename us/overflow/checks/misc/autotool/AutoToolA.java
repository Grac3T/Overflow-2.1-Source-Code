// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.autotool;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import org.bukkit.GameMode;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AutoToolA extends Check
{
    public AutoToolA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
        this.setExperimental(true);
    }
    
    @Listen
    public void onPacket(final PacketEvent event) {
        final String packetType = event.getType();
        final User user = event.getUser();
        final long now = System.currentTimeMillis();
        final long lastFlying = user.autoToolFlying;
        final boolean movePacket = this.isPacketMovement(packetType);
        final boolean heldPacket = packetType.equals("PacketPlayInHeldItemSlot");
        if (movePacket) {
            final boolean sent = user.autoToolSent;
            if (sent) {
                final long lastHeld = user.autoToolHeld;
                final long delay = now - lastHeld;
                if (delay > 40L && user.getPlayer().getGameMode() != GameMode.CREATIVE) {
                    this.flag(user, new String[0]);
                }
                user.autoToolSent = false;
            }
            user.autoToolFlying = now;
        }
        else if (heldPacket && now - lastFlying < 10L) {
            user.autoToolHeld = lastFlying;
            user.autoToolSent = true;
        }
    }
}
