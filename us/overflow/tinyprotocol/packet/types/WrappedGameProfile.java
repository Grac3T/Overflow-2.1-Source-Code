// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import us.overflow.tinyprotocol.api.ProtocolVersion;
import us.overflow.tinyprotocol.reflection.Reflection;
import us.overflow.utils.blockbox.ReflectionUtil;
import org.bukkit.entity.Player;
import java.util.UUID;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import us.overflow.tinyprotocol.api.NMSObject;

public class WrappedGameProfile extends NMSObject
{
    private static final String type;
    private static FieldAccessor<UUID> fieldId;
    private static FieldAccessor<String> fieldName;
    private static FieldAccessor<?> fieldPropertyMap;
    public UUID id;
    public String name;
    public Object propertyMap;
    
    public WrappedGameProfile(final Object type) {
        super(type);
    }
    
    public WrappedGameProfile(final Player player) {
        final Object entityPlayer = ReflectionUtil.getEntityPlayer(player);
        final FieldAccessor<Object> gameProfileAcessor = (FieldAccessor<Object>)fetchField("EntityHuman", Reflection.NMS_PREFIX + WrappedGameProfile.type, 0);
        this.setObject(this.fetch((FieldAccessor)gameProfileAcessor));
        this.id = (UUID)WrappedGameProfile.fieldId.get(this.getObject());
        this.name = (String)WrappedGameProfile.fieldName.get(this.getObject());
        this.propertyMap = WrappedGameProfile.fieldPropertyMap.get(this.getObject());
    }
    
    public void process(final Player player, final ProtocolVersion version) {
        this.id = (UUID)WrappedGameProfile.fieldId.get(this.getObject());
        this.name = (String)WrappedGameProfile.fieldName.get(this.getObject());
        this.propertyMap = WrappedGameProfile.fieldPropertyMap.get(this.getObject());
    }
    
    public UUID getId() {
        return this.id;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Object getPropertyMap() {
        return this.propertyMap;
    }
    
    static {
        WrappedGameProfile.type = NMSObject.Type.GAMEPROFILE;
        WrappedGameProfile.fieldId = fetchField(WrappedGameProfile.type, (Class)UUID.class, 0);
        WrappedGameProfile.fieldName = fetchField(WrappedGameProfile.type, (Class)String.class, 0);
        WrappedGameProfile.fieldPropertyMap = fetchField(WrappedGameProfile.type, Reflection.getClass(NMSObject.Type.PROPERTYMAP), 0);
    }
}
