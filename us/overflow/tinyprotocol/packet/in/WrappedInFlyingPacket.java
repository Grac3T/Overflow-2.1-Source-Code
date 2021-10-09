// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInFlyingPacket extends NMSObject
{
    private static final String packet = "PacketPlayInFlying";
    private static FieldAccessor<Double> fieldX;
    private static FieldAccessor<Double> fieldY;
    private static FieldAccessor<Double> fieldZ;
    private static FieldAccessor<Float> fieldYaw;
    private static FieldAccessor<Float> fieldPitch;
    private static FieldAccessor<Boolean> fieldGround;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private boolean look;
    private boolean pos;
    private boolean ground;
    
    public WrappedInFlyingPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        final String name = this.getPacketName();
        this.pos = name.contains("Position");
        this.look = name.contains("Look");
        if (this.pos) {
            this.x = (double)this.fetch((FieldAccessor)WrappedInFlyingPacket.fieldX);
            this.y = (double)this.fetch((FieldAccessor)WrappedInFlyingPacket.fieldY);
            this.z = (double)this.fetch((FieldAccessor)WrappedInFlyingPacket.fieldZ);
        }
        if (this.look) {
            this.yaw = (float)this.fetch((FieldAccessor)WrappedInFlyingPacket.fieldYaw);
            this.pitch = (float)this.fetch((FieldAccessor)WrappedInFlyingPacket.fieldPitch);
        }
        this.ground = (boolean)this.fetch((FieldAccessor)WrappedInFlyingPacket.fieldGround);
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
    
    public float getYaw() {
        return this.yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public boolean isLook() {
        return this.look;
    }
    
    public boolean isPos() {
        return this.pos;
    }
    
    public boolean isGround() {
        return this.ground;
    }
    
    static {
        WrappedInFlyingPacket.fieldX = (FieldAccessor<Double>)fetchField("PacketPlayInFlying", (Class)Double.TYPE, 0);
        WrappedInFlyingPacket.fieldY = (FieldAccessor<Double>)fetchField("PacketPlayInFlying", (Class)Double.TYPE, 1);
        WrappedInFlyingPacket.fieldZ = (FieldAccessor<Double>)fetchField("PacketPlayInFlying", (Class)Double.TYPE, 2);
        WrappedInFlyingPacket.fieldYaw = (FieldAccessor<Float>)fetchField("PacketPlayInFlying", (Class)Float.TYPE, 0);
        WrappedInFlyingPacket.fieldPitch = (FieldAccessor<Float>)fetchField("PacketPlayInFlying", (Class)Float.TYPE, 1);
        WrappedInFlyingPacket.fieldGround = (FieldAccessor<Boolean>)fetchField("PacketPlayInFlying", (Class)Boolean.TYPE, 0);
    }
}
