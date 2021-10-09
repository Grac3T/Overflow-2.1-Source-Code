// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInChatPacket extends NMSObject
{
    private static String packet;
    private static FieldAccessor<String> messageAccessor;
    private String message;
    
    public WrappedInChatPacket(final Object object, final Player player) {
        super(object, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.message = (String)this.fetch((FieldAccessor)WrappedInChatPacket.messageAccessor);
    }
    
    public String getMessage() {
        return this.message;
    }
    
    static {
        WrappedInChatPacket.packet = "PacketPlayInChat";
        WrappedInChatPacket.messageAccessor = (FieldAccessor<String>)fetchField(WrappedInChatPacket.packet, (Class)String.class, 0);
    }
}
