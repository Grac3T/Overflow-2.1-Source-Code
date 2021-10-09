// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.misc.badpackets;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.tinyprotocol.packet.in.WrappedInBlockDigPacket;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class BadPacketsE extends Check
{
    public BadPacketsE(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        if (e.getTo() == null || e.getFrom() == null || e.getNewTo() == null || e.getNewFrom() == null) {
            return;
        }
        final User user = e.getUser();
        if (user != null && user.hasSwordInHand()) {
            if (e.getType().equalsIgnoreCase("PacketPlayInBlockDig")) {
                final WrappedInBlockDigPacket blockDigPacket = new WrappedInBlockDigPacket(e.getPacket(), e.getPlayer());
                if (blockDigPacket.getAction() == WrappedInBlockDigPacket.EnumPlayerDigType.RELEASE_USE_ITEM) {
                    user.lastLastBadPacketsEBD = user.lastBadPacketsEBlockPlaceTick;
                    user.lastBadPacketsEBlockPlaceTick = (int)(System.currentTimeMillis() - user.lastAttackPacket);
                }
            }
            if (e.getType().equalsIgnoreCase("PacketPlayInBlockPlace")) {
                if (System.currentTimeMillis() - user.lastAttackPacket < 420L) {
                    final int p1 = user.lastLastBadPacketsEBD - 1;
                    final int p2 = user.lastLastBadPacketsEBP;
                    if (user.badPacketsEVerbose > 9) {
                        this.flag(user, new String[0]);
                    }
                    if (p1 == p2 - 1) {
                        final long attack = user.lastAttackPacket;
                        final long current = user.lastBadPacketsEBlockDig;
                        if (attack - current < 0L) {
                            if (user.badPacketsEVerbose < 50) {
                                final User user2 = user;
                                user2.badPacketsEVerbose += 2;
                            }
                        }
                        else if (user.badPacketsEVerbose > 0) {
                            final User user3 = user;
                            --user3.badPacketsEVerbose;
                        }
                        user.lastBadPacketsEBlockDig = System.currentTimeMillis();
                    }
                    user.lastLastBadPacketsEBP = user.lastBadPacketsEBlockPlaceTick;
                    user.lastBadPacketsEBlockDigTick = (int)(System.currentTimeMillis() - user.lastAttackPacket);
                }
                else if (user.badPacketsEVerbose > 0) {
                    final User user4 = user;
                    --user4.badPacketsEVerbose;
                }
            }
        }
    }
}
