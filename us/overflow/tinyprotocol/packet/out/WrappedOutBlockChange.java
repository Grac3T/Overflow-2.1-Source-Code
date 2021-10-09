// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import org.bukkit.entity.Player;
import us.overflow.utils.blockbox.ReflectionUtil;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.block.Block;
import us.overflow.tinyprotocol.packet.types.BaseBlockPosition;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedMethod;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedField;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutBlockChange extends NMSObject
{
    private static final String packet = "PacketPlayOutBlockChange";
    private static FieldAccessor<Integer> legacyX;
    private static FieldAccessor<Integer> legacyY;
    private static FieldAccessor<Integer> legacyZ;
    private static WrappedField blockChangeBlockField;
    private static WrappedField blockDataIntField;
    private static WrappedMethod getDataMethod;
    private static FieldAccessor<Object> blockPosAccessor;
    private static WrappedField iBlockDataField;
    private static WrappedClass blockChangeClass;
    private static WrappedClass nmsBlockClass;
    private static WrappedClass craftBlockClass;
    private BaseBlockPosition position;
    
    public WrappedOutBlockChange(final Object packet) {
        super(packet);
    }
    
    public WrappedOutBlockChange(final Block block) {
        if (ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_7_10)) {
            this.setPacket("PacketPlayOutBlockChange", new Object[] { block.getX(), block.getY(), block.getZ(), ReflectionUtil.getWorldHandle(block.getWorld()) });
        }
        else {
            this.setPacket("PacketPlayOutBlockChange", new Object[] { ReflectionUtil.getWorldHandle(block.getWorld()), new BaseBlockPosition(block.getX(), block.getY(), block.getZ()).getAsBlockPosition() });
        }
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            this.position = new BaseBlockPosition((int)this.fetch((FieldAccessor)WrappedOutBlockChange.legacyX), (int)this.fetch((FieldAccessor)WrappedOutBlockChange.legacyY), (int)this.fetch((FieldAccessor)WrappedOutBlockChange.legacyZ));
        }
        else {
            this.position = new BaseBlockPosition(this.fetch((FieldAccessor)WrappedOutBlockChange.blockPosAccessor));
        }
    }
    
    public BaseBlockPosition getPosition() {
        return this.position;
    }
    
    static {
        WrappedOutBlockChange.blockChangeClass = Reflections.getNMSClass("PacketPlayOutBlockChange");
        WrappedOutBlockChange.nmsBlockClass = Reflections.getNMSClass("Block");
        WrappedOutBlockChange.craftBlockClass = Reflections.getCBClass("CraftBlock");
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            WrappedOutBlockChange.legacyX = (FieldAccessor<Integer>)fetchField("PacketPlayOutBlockChange", (Class)Integer.TYPE, 0);
            WrappedOutBlockChange.legacyY = (FieldAccessor<Integer>)fetchField("PacketPlayOutBlockChange", (Class)Integer.TYPE, 1);
            WrappedOutBlockChange.legacyZ = (FieldAccessor<Integer>)fetchField("PacketPlayOutBlockChange", (Class)Integer.TYPE, 2);
            WrappedOutBlockChange.blockChangeBlockField = WrappedOutBlockChange.blockChangeClass.getFirstFieldByType(WrappedOutBlockChange.nmsBlockClass.getParent());
            WrappedOutBlockChange.blockDataIntField = WrappedOutBlockChange.blockChangeClass.getFieldByName("data");
            WrappedOutBlockChange.getDataMethod = Reflections.getNMSClass("World").getMethod("getData", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE });
        }
        else {
            WrappedOutBlockChange.blockPosAccessor = (FieldAccessor<Object>)fetchField("PacketPlayOutBlockChange", (Class)Object.class, 0);
            WrappedOutBlockChange.iBlockDataField = WrappedOutBlockChange.blockChangeClass.getFieldByType(ReflectionUtil.iBlockData, 0);
        }
    }
}
