// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutAttachEntity extends NMSObject
{
    private static final String packet = "PacketPlayOutAttachEntity";
    private static FieldAccessor<Integer> fieldA;
    private static FieldAccessor<Integer> fieldB;
    private static FieldAccessor<Integer> fieldC;
    private int a;
    private int b;
    private int c;
    
    public WrappedOutAttachEntity(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.a = (int)this.fetch((FieldAccessor)WrappedOutAttachEntity.fieldA);
        this.b = (int)this.fetch((FieldAccessor)WrappedOutAttachEntity.fieldB);
        this.c = (int)this.fetch((FieldAccessor)WrappedOutAttachEntity.fieldC);
    }
    
    public int getA() {
        return this.a;
    }
    
    public int getB() {
        return this.b;
    }
    
    public int getC() {
        return this.c;
    }
    
    static {
        WrappedOutAttachEntity.fieldA = (FieldAccessor<Integer>)fetchField("PacketPlayOutAttachEntity", (Class)Integer.TYPE, 0);
        WrappedOutAttachEntity.fieldB = (FieldAccessor<Integer>)fetchField("PacketPlayOutAttachEntity", (Class)Integer.TYPE, 1);
        WrappedOutAttachEntity.fieldC = (FieldAccessor<Integer>)fetchField("PacketPlayOutAttachEntity", (Class)Integer.TYPE, 2);
    }
}
