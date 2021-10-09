// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.utils.MathUtil;
import org.bukkit.entity.Player;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsJ extends Check
{
    public BadPacketsJ(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (this.isPacketMovement(e.getType())) {
            final User user = e.getUser();
            if (user != null && user.lastEntityAttacked instanceof Player && System.currentTimeMillis() - user.lastAttackPacket < 100L && user.isOnGround() && user.movementSpeed > MathUtil.getBaseSpeed(user.getPlayer()) && user.sprinting && user.getBadPacketsJVerbose1().flag(10, 810L)) {
                this.flag(user, new String[0]);
            }
        }
    }
}
