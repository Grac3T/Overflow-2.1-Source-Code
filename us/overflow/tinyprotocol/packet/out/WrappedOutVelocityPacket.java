// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutVelocityPacket extends NMSObject
{
    private static final String packet = "PacketPlayOutEntityVelocity";
    private static FieldAccessor<Integer> fieldId;
    private static FieldAccessor<Integer> fieldX;
    private static FieldAccessor<Integer> fieldY;
    private static FieldAccessor<Integer> fieldZ;
    private int id;
    private double x;
    private double y;
    private double z;
    
    public WrappedOutVelocityPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.id = (int)this.fetch((FieldAccessor)WrappedOutVelocityPacket.fieldId);
        this.x = (int)this.fetch((FieldAccessor)WrappedOutVelocityPacket.fieldX) / 8000.0;
        this.y = (int)this.fetch((FieldAccessor)WrappedOutVelocityPacket.fieldY) / 8000.0;
        this.z = (int)this.fetch((FieldAccessor)WrappedOutVelocityPacket.fieldZ) / 8000.0;
    }
    
    public int getId() {
        return this.id;
    }
    
    public double getX() {
        return this.x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    static {
        WrappedOutVelocityPacket.fieldId = (FieldAccessor<Integer>)fetchField("PacketPlayOutEntityVelocity", (Class)Integer.TYPE, 0);
        WrappedOutVelocityPacket.fieldX = (FieldAccessor<Integer>)fetchField("PacketPlayOutEntityVelocity", (Class)Integer.TYPE, 1);
        WrappedOutVelocityPacket.fieldY = (FieldAccessor<Integer>)fetchField("PacketPlayOutEntityVelocity", (Class)Integer.TYPE, 2);
        WrappedOutVelocityPacket.fieldZ = (FieldAccessor<Integer>)fetchField("PacketPlayOutEntityVelocity", (Class)Integer.TYPE, 3);
    }
}
