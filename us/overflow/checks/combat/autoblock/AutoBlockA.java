// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.checks.combat.autoblock;

import us.overflow.events.Listen;
import us.overflow.base.user.User;
import us.overflow.tinyprotocol.packet.in.WrappedInUseEntityPacket;
import us.overflow.tinyprotocol.packet.in.WrappedInBlockDigPacket;
import us.overflow.events.impl.PacketEvent;
import us.overflow.base.CheckType;
import us.overflow.base.Check;

public class AutoBlockA extends Check
{
    public AutoBlockA(final String checkName, final String type, final CheckType checkType, final boolean enabled) {
        super(checkName, type, checkType, enabled);
    }
    
    @Listen
    public void onPacket(final PacketEvent e) {
        final User user = e.getUser();
        if (user != null) {
            if (e.getType().equalsIgnoreCase("PacketPlayInBlockDig")) {
                final WrappedInBlockDigPacket wrappedInBlockPlacePacket = new WrappedInBlockDigPacket(e.getPacket(), e.getPlayer());
                if (wrappedInBlockPlacePacket.getDirection().getAdjacentY() == -1) {
                    user.autoblockALastTick = user.tick;
                }
            }
            if (e.getType().equalsIgnoreCase("PacketPlayInUseEntity")) {
                final WrappedInUseEntityPacket wrappedInUseEntityPacket = new WrappedInUseEntityPacket(e.getPacket(), e.getPlayer());
                if (wrappedInUseEntityPacket.getEntity() != null && wrappedInUseEntityPacket.getAction() == WrappedInUseEntityPacket.EnumEntityUseAction.ATTACK && user.tick == user.autoblockALastTick) {
                    this.flag(user, new String[0]);
                }
            }
        }
    }
}
