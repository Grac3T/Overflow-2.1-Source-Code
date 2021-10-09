// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutRelativePosition extends NMSObject
{
    private static final String packet = "PacketPlayOutEntity";
    private static FieldAccessor<Integer> fieldId;
    private static FieldAccessor<Byte> fieldX;
    private static FieldAccessor<Byte> fieldY;
    private static FieldAccessor<Byte> fieldZ;
    private static FieldAccessor<Byte> fieldYaw;
    private static FieldAccessor<Byte> fieldPitch;
    private static FieldAccessor<Boolean> fieldGround;
    private int id;
    private byte x;
    private byte y;
    private byte z;
    private byte yaw;
    private byte pitch;
    private boolean look;
    private boolean pos;
    private boolean ground;
    
    public WrappedOutRelativePosition(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        final String name = this.getPacketName();
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            this.pos = (name.equals("PacketPlayOutEntityMove") || name.equals("PacketPlayOutEntityMoveLook"));
            this.look = (name.equals("PacketPlayOutEntityLook") || name.equals("PacketPlayOutEntityMoveLook"));
        }
        else {
            this.pos = (name.equals("PacketPlayOutEntity$PacketPlayOutEntityMove") || name.equals("PacketPlayOutEntity$PacketPlayOutEntityMoveLook"));
            this.look = (name.equals("PacketPlayOutEntity$PacketPlayOutEntityLook") || name.equals("PacketPlayOutEntity$PacketPlayOutEntityMoveLook"));
        }
        this.id = (int)this.fetch((FieldAccessor)WrappedOutRelativePosition.fieldId);
        this.x = (byte)this.fetch((FieldAccessor)WrappedOutRelativePosition.fieldX);
        this.y = (byte)this.fetch((FieldAccessor)WrappedOutRelativePosition.fieldY);
        this.z = (byte)this.fetch((FieldAccessor)WrappedOutRelativePosition.fieldZ);
        this.yaw = (byte)this.fetch((FieldAccessor)WrappedOutRelativePosition.fieldYaw);
        this.pitch = (byte)this.fetch((FieldAccessor)WrappedOutRelativePosition.fieldPitch);
        this.ground = (boolean)this.fetch((FieldAccessor)WrappedOutRelativePosition.fieldGround);
    }
    
    public int getId() {
        return this.id;
    }
    
    public byte getX() {
        return this.x;
    }
    
    public byte getY() {
        return this.y;
    }
    
    public byte getZ() {
        return this.z;
    }
    
    public byte getYaw() {
        return this.yaw;
    }
    
    public byte getPitch() {
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
        WrappedOutRelativePosition.fieldId = (FieldAccessor<Integer>)fetchField("PacketPlayOutEntity", (Class)Integer.TYPE, 0);
        WrappedOutRelativePosition.fieldX = (FieldAccessor<Byte>)fetchField("PacketPlayOutEntity", (Class)Byte.TYPE, 0);
        WrappedOutRelativePosition.fieldY = (FieldAccessor<Byte>)fetchField("PacketPlayOutEntity", (Class)Byte.TYPE, 1);
        WrappedOutRelativePosition.fieldZ = (FieldAccessor<Byte>)fetchField("PacketPlayOutEntity", (Class)Byte.TYPE, 2);
        WrappedOutRelativePosition.fieldYaw = (FieldAccessor<Byte>)fetchField("PacketPlayOutEntity", (Class)Byte.TYPE, 0);
        WrappedOutRelativePosition.fieldPitch = (FieldAccessor<Byte>)fetchField("PacketPlayOutEntity", (Class)Byte.TYPE, 1);
        WrappedOutRelativePosition.fieldGround = (FieldAccessor<Boolean>)fetchField("PacketPlayOutEntity", (Class)Boolean.TYPE, 0);
    }
}
