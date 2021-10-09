// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import java.util.ArrayList;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import java.util.List;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedOutEntityMetadata extends NMSObject
{
    private static final String packet = "PacketPlayOutEntityMetadata";
    private static FieldAccessor<Integer> entityidField;
    private static FieldAccessor<List> watchableObjectsField;
    private List<Object> watchableObjects;
    private int entityId;
    
    public WrappedOutEntityMetadata(final Object object, final Player player) {
        super(object, player);
    }
    
    public WrappedOutEntityMetadata(final int entityId, final List<Object> objects) {
        this.setPacket("PacketPlayOutEntityMetadata", new Object[] { entityId, objects });
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        WrappedOutEntityMetadata.watchableObjectsField = (FieldAccessor<List>)fetchField("PacketPlayOutEntityMetadata", (Class)List.class, 0);
        WrappedOutEntityMetadata.entityidField = (FieldAccessor<Integer>)fetchField("PacketPlayOutEntityMetadata", (Class)Integer.TYPE, 0);
        this.watchableObjects = new ArrayList<Object>();
        this.entityId = (int)this.fetch((FieldAccessor)WrappedOutEntityMetadata.entityidField);
        final List list = (List)this.fetch((FieldAccessor)WrappedOutEntityMetadata.watchableObjectsField);
        if (list != null) {
            list.forEach(object -> this.watchableObjects.add(object));
        }
    }
    
    public List<Object> getWatchableObjects() {
        return this.watchableObjects;
    }
    
    public int getEntityId() {
        return this.entityId;
    }
    
    public void setWatchableObjects(final List<Object> watchableObjects) {
        this.watchableObjects = watchableObjects;
    }
    
    public void setEntityId(final int entityId) {
        this.entityId = entityId;
    }
}
