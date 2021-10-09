// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Arrays;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.craftbukkit.libs.jline.internal.Nullable;
import us.overflow.tinyprotocol.packet.types.WrappedEnumTeleportFlag;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutPositionPacket extends NMSObject
{
    private static final String packet = "PacketPlayOutPosition";
    private static FieldAccessor<Double> fieldX;
    private static FieldAccessor<Double> fieldY;
    private static FieldAccessor<Double> fieldZ;
    private static FieldAccessor<Float> fieldYaw;
    private static FieldAccessor<Float> fieldPitch;
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    
    public WrappedOutPositionPacket(final Object packet, final Player player) {
        super(packet, player);
    }
    
    public WrappedOutPositionPacket(final Location location, final int teleportAwait, @Nullable final WrappedEnumTeleportFlag... flags) {
        if (ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_9)) {
            this.setPacket("PacketPlayOutPosition", new Object[] { location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), (flags != null) ? Arrays.stream(flags).map((Function<? super WrappedEnumTeleportFlag, ?>)WrappedEnumTeleportFlag::getObject).collect((Collector<? super Object, ?, Set<? super Object>>)Collectors.toSet()) : new HashSet<Object>(), teleportAwait });
        }
        else if (ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_7_10)) {
            this.setPacket("PacketPlayOutPosition", new Object[] { location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), (flags != null) ? Arrays.stream(flags).map((Function<? super WrappedEnumTeleportFlag, ?>)WrappedEnumTeleportFlag::getObject).collect((Collector<? super Object, ?, Set<? super Object>>)Collectors.toSet()) : new HashSet<Object>() });
        }
        else {
            this.setPacket("PacketPlayOutPosition", new Object[] { location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch(), flags != null });
        }
    }
    
    public WrappedOutPositionPacket(final Location location, @Nullable final WrappedEnumTeleportFlag... flags) {
        this(location, 0, flags);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.x = (double)this.fetch((FieldAccessor)WrappedOutPositionPacket.fieldX);
        this.y = (double)this.fetch((FieldAccessor)WrappedOutPositionPacket.fieldY);
        this.z = (double)this.fetch((FieldAccessor)WrappedOutPositionPacket.fieldZ);
        this.yaw = (float)this.fetch((FieldAccessor)WrappedOutPositionPacket.fieldYaw);
        this.pitch = (float)this.fetch((FieldAccessor)WrappedOutPositionPacket.fieldPitch);
    }
    
    private List<Integer> toOrdinal(final Set<Enum> enums) {
        final List<Integer> ordinals = new ArrayList<Integer>();
        enums.forEach(e -> ordinals.add(e.ordinal()));
        return ordinals;
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
    
    public WrappedOutPositionPacket() {
    }
    
    static {
        WrappedOutPositionPacket.fieldX = (FieldAccessor<Double>)fetchField("PacketPlayOutPosition", (Class)Double.TYPE, 0);
        WrappedOutPositionPacket.fieldY = (FieldAccessor<Double>)fetchField("PacketPlayOutPosition", (Class)Double.TYPE, 1);
        WrappedOutPositionPacket.fieldZ = (FieldAccessor<Double>)fetchField("PacketPlayOutPosition", (Class)Double.TYPE, 2);
        WrappedOutPositionPacket.fieldYaw = (FieldAccessor<Float>)fetchField("PacketPlayOutPosition", (Class)Float.TYPE, 0);
        WrappedOutPositionPacket.fieldPitch = (FieldAccessor<Float>)fetchField("PacketPlayOutPosition", (Class)Float.TYPE, 1);
    }
}
