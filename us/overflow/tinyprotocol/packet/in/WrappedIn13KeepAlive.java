// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import us.overflow.tinyprotocol.reflection.Reflection;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedIn13KeepAlive extends NMSObject
{
    private static final String packet = "PacketPlayInKeepAlive";
    private long ping;
    private FieldAccessor<Long> pingField;
    
    public WrappedIn13KeepAlive(final Object object, final Player player) {
        super(object, player);
        this.pingField = (FieldAccessor<Long>)Reflection.getFieldSafe("PacketPlayInKeepAlive", (Class)Long.TYPE, 0);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.ping = (long)this.fetch((FieldAccessor)this.pingField);
    }
    
    public long getPing() {
        return this.ping;
    }
}
