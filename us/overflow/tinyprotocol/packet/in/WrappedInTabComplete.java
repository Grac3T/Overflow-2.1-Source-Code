// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.types.BaseBlockPosition;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInTabComplete extends NMSObject
{
    private static final String packet = "PacketPlayInTabComplete";
    private static FieldAccessor<String> messageAccessor;
    private static FieldAccessor<Boolean> hasToolTipAccessor;
    private String message;
    private BaseBlockPosition blockPosition;
    private boolean hasToolTip;
    
    public WrappedInTabComplete(final Object object, final Player player) {
        super(object, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.message = (String)this.fetch(WrappedInTabComplete.messageAccessor);
        if (ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_8_9)) {
            WrappedInTabComplete.hasToolTipAccessor = fetchField("PacketPlayInTabComplete", (Class)Boolean.TYPE, 0);
            this.hasToolTip = (boolean)this.fetch(WrappedInTabComplete.hasToolTipAccessor);
        }
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public BaseBlockPosition getBlockPosition() {
        return this.blockPosition;
    }
    
    public boolean isHasToolTip() {
        return this.hasToolTip;
    }
    
    static {
        WrappedInTabComplete.messageAccessor = fetchField("PacketPlayInTabComplete", (Class)String.class, 0);
    }
}
