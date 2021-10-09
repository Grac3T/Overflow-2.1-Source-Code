// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.Packet;

public class WrappedInVelocityPacket extends Packet
{
    private static final String packet = "PacketPlayInFlying";
    private static FieldAccessor<Integer> fieldX;
    private static FieldAccessor<Integer> fieldY;
    private static FieldAccessor<Integer> fieldZ;
    private double x;
    private double y;
    private double z;
    
    public WrappedInVelocityPacket(final Object packet) {
        super(packet);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.x = (int)WrappedInVelocityPacket.fieldX.get(this.getPacket());
        this.y = (int)WrappedInVelocityPacket.fieldY.get(this.getPacket());
        this.z = (int)WrappedInVelocityPacket.fieldZ.get(this.getPacket());
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
        WrappedInVelocityPacket.fieldX = (FieldAccessor<Integer>)fetchField("PacketPlayInFlying", (Class)Integer.TYPE, 0);
        WrappedInVelocityPacket.fieldY = (FieldAccessor<Integer>)fetchField("PacketPlayInFlying", (Class)Integer.TYPE, 1);
        WrappedInVelocityPacket.fieldZ = (FieldAccessor<Integer>)fetchField("PacketPlayInFlying", (Class)Integer.TYPE, 2);
    }
}
