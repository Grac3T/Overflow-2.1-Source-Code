// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import java.lang.reflect.InvocationTargetException;
import us.overflow.tinyprotocol.reflection.Reflection;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedWatchableObject extends NMSObject
{
    private static String type;
    private FieldAccessor<Integer> objectTypeField;
    private FieldAccessor<Integer> dataValueIdField;
    private FieldAccessor<Object> watchedObjectField;
    private FieldAccessor<Boolean> watchedField;
    private int objectType;
    private int dataValueId;
    private Object watchedObject;
    private boolean watched;
    
    public WrappedWatchableObject(final Object object) {
        super(object);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.objectTypeField = (FieldAccessor<Integer>)fetchField(WrappedWatchableObject.type, (Class)Integer.TYPE, 0);
        this.dataValueIdField = (FieldAccessor<Integer>)fetchField(WrappedWatchableObject.type, (Class)Integer.TYPE, 1);
        this.watchedObjectField = (FieldAccessor<Object>)fetchField(WrappedWatchableObject.type, (Class)Object.class, 0);
        this.watchedField = (FieldAccessor<Boolean>)fetchField(WrappedWatchableObject.type, (Class)Boolean.TYPE, 0);
        this.objectType = (int)this.fetch((FieldAccessor)this.objectTypeField);
        this.dataValueId = (int)this.fetch((FieldAccessor)this.dataValueIdField);
        this.watchedObject = this.fetch((FieldAccessor)this.watchedObjectField);
        this.watched = (boolean)this.fetch((FieldAccessor)this.watchedField);
    }
    
    public void setPacket(final String packet, final int type, final int data, final Object watchedObject) {
        final Class<?> c = (Class<?>)Reflection.getClass(Reflection.NMS_PREFIX + "." + packet);
        try {
            final Object o = c.getConstructor(Integer.TYPE, Integer.TYPE, Object.class).newInstance(type, data, watchedObject);
            this.setObject(o);
        }
        catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
        }
    }
    
    public WrappedWatchableObject(final FieldAccessor<Integer> objectTypeField, final FieldAccessor<Integer> dataValueIdField, final FieldAccessor<Object> watchedObjectField, final FieldAccessor<Boolean> watchedField, final int objectType, final int dataValueId, final Object watchedObject, final boolean watched) {
        this.objectTypeField = objectTypeField;
        this.dataValueIdField = dataValueIdField;
        this.watchedObjectField = watchedObjectField;
        this.watchedField = watchedField;
        this.objectType = objectType;
        this.dataValueId = dataValueId;
        this.watchedObject = watchedObject;
        this.watched = watched;
    }
    
    public FieldAccessor<Integer> getObjectTypeField() {
        return this.objectTypeField;
    }
    
    public FieldAccessor<Integer> getDataValueIdField() {
        return this.dataValueIdField;
    }
    
    public FieldAccessor<Object> getWatchedObjectField() {
        return this.watchedObjectField;
    }
    
    public FieldAccessor<Boolean> getWatchedField() {
        return this.watchedField;
    }
    
    public int getObjectType() {
        return this.objectType;
    }
    
    public int getDataValueId() {
        return this.dataValueId;
    }
    
    public Object getWatchedObject() {
        return this.watchedObject;
    }
    
    public boolean isWatched() {
        return this.watched;
    }
    
    public void setObjectTypeField(final FieldAccessor<Integer> objectTypeField) {
        this.objectTypeField = objectTypeField;
    }
    
    public void setDataValueIdField(final FieldAccessor<Integer> dataValueIdField) {
        this.dataValueIdField = dataValueIdField;
    }
    
    public void setWatchedObjectField(final FieldAccessor<Object> watchedObjectField) {
        this.watchedObjectField = watchedObjectField;
    }
    
    public void setWatchedField(final FieldAccessor<Boolean> watchedField) {
        this.watchedField = watchedField;
    }
    
    public void setObjectType(final int objectType) {
        this.objectType = objectType;
    }
    
    public void setDataValueId(final int dataValueId) {
        this.dataValueId = dataValueId;
    }
    
    public void setWatchedObject(final Object watchedObject) {
        this.watchedObject = watchedObject;
    }
    
    public void setWatched(final boolean watched) {
        this.watched = watched;
    }
    
    static {
        WrappedWatchableObject.type = NMSObject.Type.WATCHABLE_OBJECT;
    }
}
