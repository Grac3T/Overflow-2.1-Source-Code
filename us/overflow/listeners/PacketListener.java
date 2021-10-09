// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.listeners;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.tinyprotocol.packet.in.WrappedInTabComplete;
import us.overflow.Overflow;
import us.overflow.events.impl.PacketEvent;
import us.overflow.events.OverflowListener;

public class PacketListener implements OverflowListener
{
    @Listen
    public void onPacket(final PacketEvent e) {
        final User user = e.getUser();
        user.getCombatProcessor().update(e.getPacket(), e.getType());
        user.getLagProcessor().update(e.getPacket(), e.getType());
        user.getMovementProcessor().update(e.getPacket(), e.getType());
        if (e.getType().equalsIgnoreCase("PacketPlayInTabComplete") && Overflow.getInstance().getConfigValues().isBlockTabComplete()) {
            if (e.getPlayer().isOp()) {
                return;
            }
            final WrappedInTabComplete wrappedInTabComplete = new WrappedInTabComplete(e.getPacket(), e.getPlayer());
            final String cmd = wrappedInTabComplete.getMessage().toLowerCase();
            if (Overflow.getInstance().getConfigValues().isHider() && Overflow.getInstance().getConfigValues().isBlockTabComplete()) {
                if (cmd.contains("/minecraft:o") || cmd.isEmpty() || cmd.startsWith("/")) {
                    e.setCancelled(true);
                }
                if ((cmd.contains("/o") && !cmd.contains(" ")) || (cmd.startsWith("/ver") && !cmd.contains("  ")) || (cmd.startsWith("/version") && !cmd.contains("  ")) || (cmd.startsWith("/?") && !cmd.contains("  ")) || (cmd.startsWith("/about") && !cmd.contains("  ")) || (cmd.startsWith("/help") && !cmd.contains("  "))) {
                    e.setCancelled(true);
                }
            }
        }
    }
}
