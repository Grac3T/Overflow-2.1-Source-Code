// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api;

import java.util.HashMap;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import java.lang.reflect.Field;
import us.overflow.tinyprotocol.reflection.Reflection;
import java.util.Map;

public abstract class Packet
{
    private static Map<String, Class<?>> constructors;
    private Object packet;
    private boolean cancelled;
    
    public Packet(final Object packet) {
        this.packet = packet;
    }
    
    public static Object construct(final String packet, final Object... args) {
        try {
            Class<?> c = Packet.constructors.get(packet);
            if (c == null) {
                c = (Class<?>)Reflection.getMinecraftClass(packet);
                Packet.constructors.put(packet, c);
            }
            final Object p = c.newInstance();
            final Field[] fields = c.getDeclaredFields();
            int failed = 0;
            for (int i = 0; i < args.length; ++i) {
                final Object o = args[i];
                if (o != null) {
                    fields[i - failed].setAccessible(true);
                    try {
                        fields[i - failed].set(p, o);
                    }
                    catch (Exception e2) {
                        ++failed;
                    }
                }
            }
            return p;
        }
        catch (Exception e) {
            System.out.println("The plugin cannot work as protocol incompatibilities were detected... Disabling...");
            e.printStackTrace();
            return null;
        }
    }
    
    public static <T> FieldAccessor<T> fetchField(final String className, final Class<T> fieldType, final int index) {
        return (FieldAccessor<T>)Reflection.getFieldSafe(Reflection.NMS_PREFIX + "." + className, (Class)fieldType, index);
    }
    
    public static boolean isPositionLook(final String type) {
        switch (type) {
            case "PacketPlayInPositionLook":
            case "PacketPlayInFlying$PacketPlayInPositionLook": {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isPosition(final String type) {
        switch (type) {
            case "PacketPlayInPosition":
            case "PacketPlayInFlying$PacketPlayInPosition": {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isLook(final String type) {
        switch (type) {
            case "PacketPlayInLook":
            case "PacketPlayInFlying$PacketPlayInLook": {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public String getPacketName() {
        final String name = this.packet.getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
    }
    
    public void setPacket(final String packet, final Object... args) {
        this.packet = construct(packet, args);
    }
    
    public <T> T fetch(final FieldAccessor<T> field) {
        return (T)field.get(this.packet);
    }
    
    public Packet() {
    }
    
    public Object getPacket() {
        return this.packet;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    static {
        Packet.constructors = new HashMap();
    }
    
    public static class Type
    {
        public static final String WATCHABLE_OBJECT;
        public static final String BASEBLOCKPOSITION = "BaseBlockPosition";
        public static final String ITEMSTACK;
        public static final String ENTITY;
        public static final String DATAWATCHER;
        public static final String DATAWATCHEROBJECT;
        public static final String CRAFTITEMSTACK;
        public static final String GAMEPROFILE = "com.mojang.authlib.GameProfile";
        public static final String PROPERTYMAP = "com.mojang.authlib.PropertyMap";
        
        static {
            Type.WATCHABLE_OBJECT = ((Reflection.VERSION.startsWith("v1_7") || Reflection.VERSION.startsWith("v1_8_R1")) ? "WatchableObject" : "DataWatcher$WatchableObject");
            Type.ITEMSTACK = Reflection.NMS_PREFIX + ".ItemStack";
            Type.ENTITY = Reflection.NMS_PREFIX + ".Entity";
            Type.DATAWATCHER = Reflection.NMS_PREFIX + ".DataWatcher";
            Type.DATAWATCHEROBJECT = Reflection.NMS_PREFIX + ".DataWatcherObject";
            Type.CRAFTITEMSTACK = Reflection.OBC_PREFIX + ".inventory.CraftItemStack";
        }
    }
    
    public static class Client
    {
        private static final String CLIENT = "PacketPlayIn";
        public static final String KEEP_ALIVE = "PacketPlayInKeepAlive";
        public static final String FLYING = "PacketPlayInFlying";
        public static final String POSITION = "PacketPlayInPosition";
        public static final String POSITION_LOOK = "PacketPlayInPositionLook";
        public static final String LOOK = "PacketPlayInLook";
        @Deprecated
        public static final String LEGACY_POSITION = "PacketPlayInFlying$PacketPlayInPosition";
        @Deprecated
        public static final String LEGACY_POSITION_LOOK = "PacketPlayInFlying$PacketPlayInPositionLook";
        @Deprecated
        public static final String LEGACY_LOOK = "PacketPlayInFlying$PacketPlayInLook";
        public static final String TRANSACTION = "PacketPlayInTransaction";
        public static final String BLOCK_DIG = "PacketPlayInBlockDig";
        public static final String ENTITY_ACTION = "PacketPlayInEntityAction";
        public static final String USE_ENTITY = "PacketPlayInUseEntity";
        public static final String WINDOW_CLICK = "PacketPlayInWindowClick";
        public static final String CUSTOM_PAYLOAD = "PacketPlayInCustomPayload";
        public static final String ARM_ANIMATION = "PacketPlayInArmAnimation";
        public static final String BLOCK_PLACE = "PacketPlayInBlockPlace";
        public static final String ABILITIES = "PacketPlayInAbilities";
        public static final String HELD_ITEM_SLOT = "PacketPlayInHeldItemSlot";
        public static final String CLOSE_WINDOW = "PacketPlayInCloseWindow";
        public static final String TAB_COMPLETE = "PacketPlayInTabComplete";
        public static final String CHAT = "PacketPlayInChat";
    }
    
    public static class Server
    {
        private static final String SERVER = "PacketPlayOut";
        public static final String KEEP_ALIVE = "PacketPlayOutKeepAlive";
        public static final String CHAT = "PacketPlayOutChat";
        public static final String POSITION = "PacketPlayOutPosition";
        public static final String TRANSACTION = "PacketPlayOutTransaction";
        public static final String NAMED_ENTITY_SPAWN = "PacketPlayOutNamedEntitySpawn";
        public static final String SPAWN_ENTITY_LIVING = "PacketPlayOutSpawnEntityLiving";
        public static final String SPAWN_ENTITY = "PacketPlayOutSpawnEntity";
        public static final String CUSTOM_PAYLOAD = "PacketPlayOutCustomPayload";
        public static final String ABILITIES = "PacketPlayOutAbilities";
        public static final String ENTITY_METADATA = "PacketPlayOutEntityMetadata";
        public static final String ENTITY_VELOCITY = "PacketPlayOutEntityVelocity";
        public static final String ENTITY_DESTROY = "PacketPlayOutEntityDestroy";
        public static final String BLOCK_CHANGE = "PacketPlayOutBlockChange";
        public static final String CLOSE_WINDOW = "PacketPlayOutCloseWindow";
        public static final String HELD_ITEM = "PacketPlayOutHeldItemSlot";
        public static final String TAB_COMPLETE = "PacketPlayOutTabComplete";
        public static final String RESPAWN = "PacketPlayOutRespawn";
        public static final String ATTACH = "PacketPlayOutAttachEntity";
    }
}
