// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInSteerVehiclePacket extends NMSObject
{
    private static final String packet = "PacketPlayInSteerVehicle";
    
    public WrappedInSteerVehiclePacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
    }
}
