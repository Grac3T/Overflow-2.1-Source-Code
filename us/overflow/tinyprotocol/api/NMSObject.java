// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.api;

import java.util.HashMap;
import us.overflow.tinyprotocol.reflection.FieldAccessor;
import org.bukkit.inventory.ItemStack;
import java.lang.reflect.Field;
import us.overflow.tinyprotocol.reflection.Reflection;
import org.bukkit.entity.Player;
import java.util.Map;
import us.overflow.tinyprotocol.reflection.MethodInvoker;

public abstract class NMSObject
{
    private static final MethodInvoker asCraftMirror;
    private static Map<String, Class<?>> constructors;
    private Object object;
    private boolean cancelled;
    private Player player;
    
    public NMSObject(final Object object) {
        this.object = object;
        this.process(this.player, ProtocolVersion.getGameVersion());
    }
    
    public NMSObject() {
    }
    
    public NMSObject(final Object object, final Player player) {
        this.object = object;
        this.process(this.player = player, ProtocolVersion.getGameVersion());
    }
    
    public static Object construct(final String packet, final Object... args) {
        try {
            Class<?> c = NMSObject.constructors.get(packet);
            if (c == null) {
                c = (Class<?>)Reflection.getMinecraftClass(packet);
                NMSObject.constructors.put(packet, c);
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
    
    public static Object construct(final String packet, final Object arg) {
        try {
            Class<?> c = NMSObject.constructors.get(packet);
            if (c == null) {
                c = (Class<?>)Reflection.getMinecraftClass(packet);
                NMSObject.constructors.put(packet, c);
            }
            final Object p = c.newInstance();
            final Field[] fields = c.getDeclaredFields();
            if (arg != null) {
                fields[0].setAccessible(true);
                fields[0].set(p, arg);
            }
            return p;
        }
        catch (Exception e) {
            System.out.println("The plugin cannot work as protocol incompatibilities were detected... Disabling...");
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object construct(final Object p, final String packet, final Object... args) {
        try {
            Class<?> c = NMSObject.constructors.get(packet);
            if (c == null) {
                c = (Class<?>)Reflection.getMinecraftClass(packet);
                NMSObject.constructors.put(packet, c);
            }
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
    
    public static ItemStack toBukkitStack(final Object nmsStack) {
        return (ItemStack)NMSObject.asCraftMirror.invoke((Object)null, new Object[] { nmsStack });
    }
    
    public static <T> FieldAccessor<T> fetchField(final String className, final Class<T> fieldType, final int index) {
        return (FieldAccessor<T>)Reflection.getFieldSafe(Reflection.NMS_PREFIX + "." + className, (Class)fieldType, index);
    }
    
    public static <T> FieldAccessor<T> fetchFieldByName(final String className, final String name, final Class<T> fieldType) {
        return (FieldAccessor<T>)Reflection.getField(Reflection.NMS_PREFIX + "." + className, name, (Class)fieldType);
    }
    
    public static <T> FieldAccessor<T> fetchField(final String className, final String fieldType, final int index) {
        return (FieldAccessor<T>)Reflection.getFieldSafe(Reflection.NMS_PREFIX + "." + className, Reflection.getClass(fieldType), index);
    }
    
    public String getPacketName() {
        final String name = this.object.getClass().getName();
        return name.substring(name.lastIndexOf(".") + 1);
    }
    
    public void process(final Player player, final ProtocolVersion version) {
    }
    
    public void setPacket(final String packet, final Object... args) {
        this.object = construct(packet, args);
    }
    
    public void setPacketArg(final String packet, final Object arg) {
        this.object = construct(packet, arg);
    }
    
    public void setPacket(final String packet, final Object arg) {
        this.setPacketArg(packet, arg);
    }
    
    public <T> T fetch(final FieldAccessor<T> field) {
        return (T)field.get(this.object);
    }
    
    public <T> T fetch(final FieldAccessor<T> field, final Object obj) {
        return (T)field.get(obj);
    }
    
    public Object getObject() {
        return this.object;
    }
    
    public boolean isCancelled() {
        return this.cancelled;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setCancelled(final boolean cancelled) {
        this.cancelled = cancelled;
    }
    
    public void setPlayer(final Player player) {
        this.player = player;
    }
    
    public void setObject(final Object object) {
        this.object = object;
    }
    
    static {
        NMSObject.asCraftMirror = Reflection.getMethod(Type.CRAFTITEMSTACK, "asCraftMirror", new Class[] { Reflection.getClass(Type.ITEMSTACK) });
        NMSObject.constructors = new HashMap();
    }
    
    public static class Type
    {
        public static final String WATCHABLE_OBJECT;
        public static final String BASEBLOCKPOSITION = "BaseBlockPosition";
        public static final String BLOCKPOSITION = "BlockPosition";
        public static final String ITEMSTACK;
        public static final String ENTITY;
        public static final String DATAWATCHER;
        public static final String DATAWATCHEROBJECT;
        public static final String CHATMESSAGE;
        public static final String CRAFTITEMSTACK;
        public static final String GAMEPROFILE;
        public static final String PROPERTYMAP;
        public static final String VEC3D;
        public static final String PLAYERINFODATA;
        
        static {
            Type.WATCHABLE_OBJECT = (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8_5) ? "WatchableObject" : "DataWatcher$WatchableObject");
            Type.ITEMSTACK = Reflection.NMS_PREFIX + ".ItemStack";
            Type.ENTITY = Reflection.NMS_PREFIX + ".Entity";
            Type.DATAWATCHER = Reflection.NMS_PREFIX + ".DataWatcher";
            Type.DATAWATCHEROBJECT = Reflection.NMS_PREFIX + ".DataWatcherObject";
            Type.CHATMESSAGE = Reflection.NMS_PREFIX + ".ChatMessage";
            Type.CRAFTITEMSTACK = Reflection.OBC_PREFIX + ".inventory.CraftItemStack";
            Type.GAMEPROFILE = (Reflection.VERSION.startsWith("v1_7") ? "net.minecraft.util." : "") + "com.mojang.authlib.GameProfile";
            Type.PROPERTYMAP = (Reflection.VERSION.startsWith("v1_7") ? "net.minecraft.util." : "") + "com.mojang.authlib.PropertyMap";
            Type.VEC3D = Reflection.NMS_PREFIX + ".Vec3D";
            Type.PLAYERINFODATA = Reflection.NMS_PREFIX + "PacketPlayOutPlayerInfo" + ".PlayerInfoData";
        }
    }
    
    public static class Client
    {
        private static final String CLIENT = "PacketPlayIn";
        public static final String KEEP_ALIVE = "PacketPlayInKeepAlive";
        public static final String FLYING = "PacketPlayInFlying";
        public static final String POSITION = "PacketPlayInFlying$PacketPlayInPosition";
        public static final String POSITION_LOOK = "PacketPlayInFlying$PacketPlayInPositionLook";
        public static final String LOOK = "PacketPlayInFlying$PacketPlayInLook";
        public static final String LEGACY_POSITION = "PacketPlayInPosition";
        public static final String LEGACY_POSITION_LOOK = "PacketPlayInPositionLook";
        public static final String LEGACY_LOOK = "PacketPlayInLook";
        public static final String TRANSACTION = "PacketPlayInTransaction";
        public static final String BLOCK_DIG = "PacketPlayInBlockDig";
        public static final String ENTITY_ACTION = "PacketPlayInEntityAction";
        public static final String USE_ENTITY = "PacketPlayInUseEntity";
        public static final String WINDOW_CLICK = "PacketPlayInWindowClick";
        public static final String CUSTOM_PAYLOAD = "PacketPlayInCustomPayload";
        public static final String ARM_ANIMATION = "PacketPlayInArmAnimation";
        public static final String BLOCK_PLACE = "PacketPlayInBlockPlace";
        public static final String STEER_VEHICLE = "PacketPlayInSteerVehicle";
        public static final String HELD_ITEM = "PacketPlayInHeldItemSlot";
        public static final String CLIENT_COMMAND = "PacketPlayInClientCommand";
        public static final String CLOSE_WINDOW = "PacketPlayInCloseWindow";
        public static final String ABILITIES = "PacketPlayInAbilities";
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
        public static final String ENTITY_METADATA = "PacketPlayOutEntityMetadata";
        public static final String ENTITY_VELOCITY = "PacketPlayOutEntityVelocity";
        public static final String ENTITY_DESTROY = "PacketPlayOutEntityDestroy";
        public static final String ENTITY = "PacketPlayOutEntity";
        public static final String REL_POSITION = "PacketPlayOutEntity$PacketPlayOutEntityMove";
        public static final String REL_POSITION_LOOK = "PacketPlayOutEntity$PacketPlayOutEntityMoveLook";
        public static final String REL_LOOK = "PacketPlayOutEntity$PacketPlayOutEntityLook";
        public static final String LEGACY_REL_POSITION = "PacketPlayOutEntityMove";
        public static final String LEGACY_REL_POSITION_LOOK = "PacketPlayOutEntityMoveLook";
        public static final String LEGACY_REL_LOOK = "PacketPlayOutEntityLook";
        public static final String ABILITIES = "PacketPlayOutAbilities";
        public static final String OPEN_WINDOW = "PacketPlayOutOpenWindow";
        public static final String HELD_ITEM = "PacketPlayOutHeldItemSlot";
        public static final String PLAYER_INFO = "PacketPlayOutPlayerInfo";
        public static final String TAB_COMPLETE = "PacketPlayOutTabComplete";
        public static final String RESPAWN = "PacketPlayOutRespawn";
        public static final String ATTACH = "PacketPlayOutAttachEntity";
    }
}
