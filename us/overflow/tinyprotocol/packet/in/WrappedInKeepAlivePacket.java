// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInKeepAlivePacket extends NMSObject
{
    private static final String packet = "PacketPlayInKeepAlive";
    private static FieldAccessor<Integer> fieldLegacy;
    private static FieldAccessor<Long> field;
    private long time;
    
    public WrappedInKeepAlivePacket(final long time) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_12)) {
            this.setPacket("PacketPlayInKeepAlive", (Object)(int)time);
        }
        else {
            this.setPacket("PacketPlayInKeepAlive", (Object)time);
        }
    }
    
    public WrappedInKeepAlivePacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_12)) {
            WrappedInKeepAlivePacket.fieldLegacy = (FieldAccessor<Integer>)fetchField("PacketPlayInKeepAlive", (Class)Integer.TYPE, 0);
            this.time = (int)this.fetch((FieldAccessor)WrappedInKeepAlivePacket.fieldLegacy);
        }
        else {
            WrappedInKeepAlivePacket.field = (FieldAccessor<Long>)fetchField("PacketPlayInKeepAlive", (Class)Long.TYPE, 0);
            this.time = (long)this.fetch((FieldAccessor)WrappedInKeepAlivePacket.field);
        }
    }
    
    public long getTime() {
        return this.time;
    }
}
