// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.in;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.types.BaseBlockPosition;
import org.bukkit.inventory.ItemStack;
import us.overflow.tinyprotocol.packet.types.EnumDirection;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedInBlockPlacePacket extends NMSObject
{
    private static final String packet = "PacketPlayInBlockPlace";
    private static FieldAccessor<Integer> fieldFace;
    private static FieldAccessor<Enum> fieldFace1_9;
    private static FieldAccessor<Object> fieldBlockPosition;
    private static FieldAccessor<Object> fieldItemStack;
    private static FieldAccessor<Integer> fieldPosX;
    private static FieldAccessor<Integer> fieldPosY;
    private static FieldAccessor<Integer> fieldPosZ;
    private static FieldAccessor<Float> fieldVecX;
    private static FieldAccessor<Float> fieldVecY;
    private static FieldAccessor<Float> fieldVecZ;
    private EnumDirection face;
    private ItemStack itemStack;
    private BaseBlockPosition position;
    private float vecX;
    private float vecY;
    private float vecZ;
    
    public WrappedInBlockPlacePacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            WrappedInBlockPlacePacket.fieldPosX = (FieldAccessor<Integer>)fetchField("PacketPlayInBlockPlace", (Class)Integer.TYPE, 0);
            WrappedInBlockPlacePacket.fieldPosY = (FieldAccessor<Integer>)fetchField("PacketPlayInBlockPlace", (Class)Integer.TYPE, 1);
            WrappedInBlockPlacePacket.fieldPosZ = (FieldAccessor<Integer>)fetchField("PacketPlayInBlockPlace", (Class)Integer.TYPE, 2);
            WrappedInBlockPlacePacket.fieldFace = (FieldAccessor<Integer>)fetchField("PacketPlayInBlockPlace", (Class)Integer.TYPE, 3);
            WrappedInBlockPlacePacket.fieldItemStack = (FieldAccessor<Object>)fetchField("PacketPlayInBlockPlace", (Class)Object.class, 0);
            WrappedInBlockPlacePacket.fieldVecX = (FieldAccessor<Float>)fetchField("PacketPlayInBlockPlace", (Class)Float.TYPE, 0);
            WrappedInBlockPlacePacket.fieldVecY = (FieldAccessor<Float>)fetchField("PacketPlayInBlockPlace", (Class)Float.TYPE, 1);
            WrappedInBlockPlacePacket.fieldVecZ = (FieldAccessor<Float>)fetchField("PacketPlayInBlockPlace", (Class)Float.TYPE, 2);
            this.position = new BaseBlockPosition((int)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldPosX), (int)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldPosY), (int)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldPosZ));
            this.face = EnumDirection.values()[Math.min((int)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldFace), 5)];
            this.itemStack = toBukkitStack(this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldItemStack));
            this.vecX = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecX);
            this.vecY = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecY);
            this.vecZ = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecZ);
        }
        else if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_9)) {
            WrappedInBlockPlacePacket.fieldBlockPosition = (FieldAccessor<Object>)fetchField("PacketPlayInBlockPlace", (Class)Object.class, 1);
            WrappedInBlockPlacePacket.fieldFace = (FieldAccessor<Integer>)fetchField("PacketPlayInBlockPlace", (Class)Integer.TYPE, 0);
            WrappedInBlockPlacePacket.fieldItemStack = (FieldAccessor<Object>)fetchField("PacketPlayInBlockPlace", (Class)Object.class, 2);
            WrappedInBlockPlacePacket.fieldVecX = (FieldAccessor<Float>)fetchField("PacketPlayInBlockPlace", (Class)Float.TYPE, 0);
            WrappedInBlockPlacePacket.fieldVecY = (FieldAccessor<Float>)fetchField("PacketPlayInBlockPlace", (Class)Float.TYPE, 1);
            WrappedInBlockPlacePacket.fieldVecZ = (FieldAccessor<Float>)fetchField("PacketPlayInBlockPlace", (Class)Float.TYPE, 2);
            this.position = new BaseBlockPosition(this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldBlockPosition));
            this.face = EnumDirection.values()[Math.min((int)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldFace), 5)];
            this.itemStack = toBukkitStack(this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldItemStack));
            this.vecX = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecX);
            this.vecY = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecY);
            this.vecZ = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecZ);
        }
        else if (!this.getObject().toString().contains("BlockPlace")) {
            WrappedInBlockPlacePacket.fieldBlockPosition = (FieldAccessor<Object>)fetchField("PacketPlayInUseItem", (Class)Object.class, 0);
            WrappedInBlockPlacePacket.fieldFace1_9 = (FieldAccessor<Enum>)fetchField("PacketPlayInUseItem", (Class)Enum.class, 1);
            WrappedInBlockPlacePacket.fieldVecX = (FieldAccessor<Float>)fetchField("PacketPlayInUseItem", (Class)Float.TYPE, 0);
            WrappedInBlockPlacePacket.fieldVecY = (FieldAccessor<Float>)fetchField("PacketPlayInUseItem", (Class)Float.TYPE, 1);
            WrappedInBlockPlacePacket.fieldVecZ = (FieldAccessor<Float>)fetchField("PacketPlayInUseItem", (Class)Float.TYPE, 2);
            this.position = new BaseBlockPosition(this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldBlockPosition));
            this.face = EnumDirection.values()[((Enum)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldFace1_9)).ordinal()];
            this.vecX = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecX);
            this.vecY = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecY);
            this.vecZ = (float)this.fetch((FieldAccessor)WrappedInBlockPlacePacket.fieldVecZ);
        }
    }
    
    public EnumDirection getFace() {
        return this.face;
    }
    
    public ItemStack getItemStack() {
        return this.itemStack;
    }
    
    public BaseBlockPosition getPosition() {
        return this.position;
    }
    
    public float getVecX() {
        return this.vecX;
    }
    
    public float getVecY() {
        return this.vecY;
    }
    
    public float getVecZ() {
        return this.vecZ;
    }
}
