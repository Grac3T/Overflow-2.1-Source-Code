// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInCustomPayloadPacket extends NMSObject
{
    private static String packet;
    private static FieldAccessor<String> channelAccessor;
    private static FieldAccessor<Object> dataAccessor;
    private String channel;
    private Object data;
    
    public WrappedInCustomPayloadPacket(final Object object, final Player player) {
        super(object, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        WrappedInCustomPayloadPacket.channelAccessor = (FieldAccessor<String>)fetchField(WrappedInCustomPayloadPacket.packet, (Class)String.class, 0);
        WrappedInCustomPayloadPacket.dataAccessor = (FieldAccessor<Object>)fetchField(WrappedInCustomPayloadPacket.packet, (Class)Object.class, 1);
        this.channel = (String)this.fetch((FieldAccessor)WrappedInCustomPayloadPacket.channelAccessor);
        this.data = this.fetch((FieldAccessor)WrappedInCustomPayloadPacket.dataAccessor);
    }
    
    public String getChannel() {
        return this.channel;
    }
    
    public Object getData() {
        return this.data;
    }
    
    static {
        WrappedInCustomPayloadPacket.packet = "PacketPlayInCustomPayload";
    }
}
