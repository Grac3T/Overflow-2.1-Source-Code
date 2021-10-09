// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutKeepAlivePacket extends NMSObject
{
    private static final String packet = "PacketPlayOutKeepAlive";
    private static FieldAccessor<Integer> fieldLegacy;
    private static FieldAccessor<Long> field;
    private long time;
    
    public WrappedOutKeepAlivePacket(final long time) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_12)) {
            this.setPacket("PacketPlayOutKeepAlive", (Object)(int)time);
        }
        else {
            this.setPacket("PacketPlayOutKeepAlive", (Object)time);
        }
    }
    
    public WrappedOutKeepAlivePacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_12)) {
            WrappedOutKeepAlivePacket.fieldLegacy = (FieldAccessor<Integer>)fetchField("PacketPlayOutKeepAlive", (Class)Integer.TYPE, 0);
            this.time = (int)this.fetch((FieldAccessor)WrappedOutKeepAlivePacket.fieldLegacy);
        }
        else {
            WrappedOutKeepAlivePacket.field = (FieldAccessor<Long>)fetchField("PacketPlayOutKeepAlive", (Class)Long.TYPE, 0);
            this.time = (long)this.fetch((FieldAccessor)WrappedOutKeepAlivePacket.field);
        }
    }
    
    public long getTime() {
        return this.time;
    }
}
