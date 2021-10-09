// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import org.bukkit.potion.PotionEffectType;
import us.overflow.utils.other.TimeUtils;
import us.overflow.tinyprotocol.packet.in.WrappedInEntityActionPacket;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsD extends Check
{
    public BadPacketsD(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null) {
            if (e.getType().equalsIgnoreCase("PacketPlayInEntityAction")) {
                final WrappedInEntityActionPacket entityActionPacket = new WrappedInEntityActionPacket(e.getPacket(), e.getPlayer());
                if (entityActionPacket.getAction() == WrappedInEntityActionPacket.EnumPlayerAction.START_SPRINTING) {
                    user.badPacketsDSprinting = true;
                }
                else if (entityActionPacket.getAction() == WrappedInEntityActionPacket.EnumPlayerAction.STOP_SPRINTING) {
                    user.badPacketsDSprinting = false;
                }
                user.hasSentPacketBadPacketsD = true;
            }
            if (this.isPacketMovement(e.getType()) && user.hasSentPacketBadPacketsD) {
                if (System.currentTimeMillis() - user.lastTeleport < 100L || TimeUtils.secondsFromLong(user.lastAttackPacket) < 5L) {
                    user.badPacketsDSprinting = true;
                }
                if (user.iceTicks > 0 || user.isLagging() || user.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
                    user.badPacketsDVerbose.setVerbose(0);
                    return;
                }
                if (user.groundTicks <= 15 || user.badPacketsDSprinting || user.movementSpeed <= 0.266 || user.badPacketsDVerbose.flag(6, 500L)) {}
            }
        }
    }
}
