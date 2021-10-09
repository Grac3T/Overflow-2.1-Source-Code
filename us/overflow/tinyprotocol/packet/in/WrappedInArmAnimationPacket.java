// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInArmAnimationPacket extends NMSObject
{
    private static final String packet = "PacketPlayInArmAnimation";
    
    public WrappedInArmAnimationPacket(final Object object, final Player player) {
        super(object, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
    }
}
