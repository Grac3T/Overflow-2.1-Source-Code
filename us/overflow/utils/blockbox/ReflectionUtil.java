// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox;

import java.util.Arrays;
import java.io.File;
import org.bukkit.inventory.ItemStack;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Field;
import org.bukkit.Material;
import org.bukkit.Server;
import java.util.logging.Level;
import org.bukkit.material.WoodenStep;
import org.bukkit.material.Step;
import us.overflow.utils.block.BlockUtil;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.Bukkit;
import org.bukkit.util.Vector;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import us.overflow.Overflow;
import java.lang.reflect.Method;

public class ReflectionUtil
{
    public static Class<?> blockPosition;
    private static String version;
    public static Class<?> EntityPlayer;
    private static Class<?> Entity;
    public static Class<?> CraftPlayer;
    private static Class<?> CraftEntity;
    private static Class<?> CraftWorld;
    private static Class<?> World;
    private static Class<?> worldServer;
    public static Class<?> iBlockData;
    public static Class<?> iBlockAccess;
    private static Class<?> vanillaBlock;
    private static Method getCubes;
    private static Method getCubes1_12;
    
    public ReflectionUtil() {
        if (Overflow.getInstance().getBukkitVerbose().contains("1_8")) {
            ReflectionUtil.iBlockData = getNMSClass("IBlockData");
            ReflectionUtil.blockPosition = getNMSClass("BlockPosition");
            ReflectionUtil.iBlockAccess = getNMSClass("IBlockAccess");
        }
    }
    
    public static Object getEntityPlayer(final Player player) {
        return getMethodValue(getMethod(ReflectionUtil.CraftPlayer, "getHandle", new Class[0]), (Object)player, new Object[0]);
    }
    
    public static Object getEntity(final Entity entity) {
        return getMethodValue(getMethod(ReflectionUtil.CraftEntity, "getHandle", new Class[0]), (Object)entity, new Object[0]);
    }
    
    public static Object getExpandedBoundingBox(final Object box, final double x, final double y, final double z) {
        return getMethodValue(getMethod((Class)box.getClass(), "grow", new Class[] { Double.TYPE, Double.TYPE, Double.TYPE }), box, new Object[] { x, y, z });
    }
    
    public static Object modifyBoundingBox(final Object box, final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ) {
        final double newminX = (double)getFieldValue(getFieldByName((Class)box.getClass(), "a"), box) - minX;
        final double newminY = (double)getFieldValue(getFieldByName((Class)box.getClass(), "b"), box) - minY;
        final double newminZ = (double)getFieldValue(getFieldByName((Class)box.getClass(), "c"), box) - minZ;
        final double newmaxX = (double)getFieldValue(getFieldByName((Class)box.getClass(), "d"), box) + maxX;
        final double newmaxY = (double)getFieldValue(getFieldByName((Class)box.getClass(), "e"), box) + maxY;
        final double newmaxZ = (double)getFieldValue(getFieldByName((Class)box.getClass(), "f"), box) + maxZ;
        return newInstance(getNMSClass("AxisAlignedBB"), new Object[] { newminX, newminY, newminZ, newmaxX, newmaxY, newmaxZ });
    }
    
    private static Vector getBoxMin(final Object box) {
        if (hasField((Class)box.getClass(), "a")) {
            final double x = (double)getFieldValue(getFieldByName((Class)box.getClass(), "a"), box);
            final double y = (double)getFieldValue(getFieldByName((Class)box.getClass(), "b"), box);
            final double z = (double)getFieldValue(getFieldByName((Class)box.getClass(), "c"), box);
            return new Vector(x, y, z);
        }
        final double x = (double)getFieldValue(getFieldByName((Class)box.getClass(), "minX"), box);
        final double y = (double)getFieldValue(getFieldByName((Class)box.getClass(), "minY"), box);
        final double z = (double)getFieldValue(getFieldByName((Class)box.getClass(), "minZ"), box);
        return new Vector(x, y, z);
    }
    
    public static Object getMinecraftServer() {
        return getMethodValue(getMethod(getCBClass("MinecraftServer"), "getServer", new Class[0]), (Object)Bukkit.getServer(), new Object[0]);
    }
    
    private static Vector getBoxMax(final Object box) {
        if (hasField((Class)box.getClass(), "d")) {
            final double x = (double)getFieldValue(getFieldByName((Class)box.getClass(), "d"), box);
            final double y = (double)getFieldValue(getFieldByName((Class)box.getClass(), "e"), box);
            final double z = (double)getFieldValue(getFieldByName((Class)box.getClass(), "f"), box);
            return new Vector(x, y, z);
        }
        final double x = (double)getFieldValue(getFieldByName((Class)box.getClass(), "maxX"), box);
        final double y = (double)getFieldValue(getFieldByName((Class)box.getClass(), "maxY"), box);
        final double z = (double)getFieldValue(getFieldByName((Class)box.getClass(), "maxZ"), box);
        return new Vector(x, y, z);
    }
    
    public static BoundingBox toBoundingBox(final Object aaBB) {
        final Vector min = getBoxMin(aaBB);
        final Vector max = getBoxMax(aaBB);
        return new BoundingBox((float)min.getX(), (float)min.getY(), (float)min.getZ(), (float)max.getX(), (float)max.getY(), (float)max.getZ());
    }
    
    public static float getBlockDurability(final Block block) {
        final Object vanillaBlock = getVanillaBlock(block);
        return (float)getFieldValue(getFieldByName(getNMSClass("Block"), "strength"), vanillaBlock);
    }
    
    public static boolean canDestroyBlock(final Player player, final Block block) {
        final Object inventory = getVanillaInventory(player);
        return (boolean)getMethodValue(getMethod(getNMSClass("PlayerInventory"), "b", new Class[] { getNMSClass("Block") }), inventory, new Object[] { Overflow.getInstance().getBukkitVerbose().contains("1_8") ? getBlockData(block) : getVanillaBlock(block) });
    }
    
    public static Object getVanillaInventory(final Player player) {
        return getMethodValue(getMethod(getCBClass("inventory.CraftInventoryPlayer"), "getInventory", new Class[0]), (Object)player.getInventory(), new Object[0]);
    }
    
    public static float getFriction(final Block block) {
        final Object blockNMS = getVanillaBlock(block);
        return (float)getFieldValue(getFieldByName(ReflectionUtil.vanillaBlock, "frictionFactor"), blockNMS);
    }
    
    public static Object getBlockPosition(final Location location) {
        if (Overflow.getInstance().getBukkitVerbose().contains("1_8")) {
            return newInstance(ReflectionUtil.blockPosition, new Object[] { location.getBlockX(), location.getBlockY(), location.getBlockZ() });
        }
        return null;
    }
    
    public static List<Entity> getEntitiesInWorld(final World world) {
        final Object worldHandle = getWorldHandle(world);
        final List<Entity> toReturn = new ArrayList<Entity>();
        final List<Object> entityList = new ArrayList<Object>((Collection<?>)getFieldValue(getFieldByName(getNMSClass("World"), "entityList"), worldHandle));
        final Class<?> entity = (Class<?>)getNMSClass("Entity");
        entityList.forEach(ReflectionUtil::lambda$getEntitiesInWorld$0);
        return toReturn;
    }
    
    public static BoundingBox getBlockBoundingBox(final Block block) {
        try {
            if (!isBukkitVerison("1_7")) {
                final Object bPos = ReflectionUtil.blockPosition.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());
                final Object world = getWorldHandle(block.getWorld());
                final Object data = getMethodValue(getMethod((Class)world.getClass(), "getType", new Class[] { ReflectionUtil.blockPosition }), world, new Object[] { bPos });
                final Object blockNMS = getMethodValue(getMethod(getNMSClass("IBlockData"), "getBlock", new Class[0]), data, new Object[0]);
                if (!Overflow.getInstance().getBukkitVerbose().contains("1_13")) {
                    final Object voxelShape = getMethodValue(getMethod(ReflectionUtil.vanillaBlock, "a", new Class[] { ReflectionUtil.iBlockData, getNMSClass("IBlockAccess"), ReflectionUtil.blockPosition }), blockNMS, new Object[] { data, world, bPos });
                    final Object axisAlignedBB = getMethodValue(getMethod(getNMSClass("VoxelShape"), "a", new Class[0]), voxelShape, new Object[0]);
                    return toBoundingBox(axisAlignedBB);
                }
                if (!isNewVersion()) {
                    if (getMethodValueNoST(getMethodNoST((Class)blockNMS.getClass(), "a", new Class[] { ReflectionUtil.World, ReflectionUtil.blockPosition, ReflectionUtil.iBlockData }), blockNMS, new Object[] { world, bPos, data }) != null && !BlockUtil.isSlab(block)) {
                        BoundingBox box = toBoundingBox(getMethodValue(getMethod((Class)blockNMS.getClass(), "a", new Class[] { ReflectionUtil.World, ReflectionUtil.blockPosition, ReflectionUtil.iBlockData }), blockNMS, new Object[] { world, bPos, data }));
                        if (Overflow.getInstance().getBukkitVerbose().contains("1_13")) {
                            if (block.getType().toString().contains("STEP") && !block.getType().toString().contains("WOOD")) {
                                final Step slab = (Step)block.getType().getNewData(block.getData());
                                box.minY = (float)block.getY();
                                box.maxY = (float)block.getY();
                                if (slab.isInverted()) {
                                    box = box.add(0.0f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f);
                                }
                                else {
                                    box = box.add(0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f);
                                }
                            }
                            else if (block.getType().toString().contains("STEP")) {
                                final WoodenStep slab2 = (WoodenStep)block.getType().getNewData(block.getData());
                                box.minY = (float)block.getY();
                                box.maxY = (float)block.getY();
                                if (slab2.isInverted()) {
                                    box = box.add(0.0f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f);
                                }
                                else {
                                    box = box.add(0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f);
                                }
                            }
                        }
                        return box;
                    }
                    if (getMethodValueNoST(getMethodNoST(ReflectionUtil.vanillaBlock, "a", new Class[] { ReflectionUtil.World, ReflectionUtil.blockPosition, ReflectionUtil.iBlockData }), blockNMS, new Object[] { world, bPos, data }) != null) {
                        BoundingBox box = toBoundingBox(getMethodValue(getMethod(ReflectionUtil.vanillaBlock, "a", new Class[] { ReflectionUtil.World, ReflectionUtil.blockPosition, ReflectionUtil.iBlockData }), blockNMS, new Object[] { world, bPos, data }));
                        if (Overflow.getInstance().getBukkitVerbose().contains("1_13")) {
                            if (block.getType().toString().contains("STEP") && !block.getType().toString().contains("WOOD")) {
                                final Step slab = (Step)block.getType().getNewData(block.getData());
                                box.minY = (float)block.getY();
                                box.maxY = (float)block.getY();
                                if (slab.isInverted()) {
                                    box = box.add(0.0f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f);
                                }
                                else {
                                    box = box.add(0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f);
                                }
                            }
                            else if (block.getType().toString().contains("STEP")) {
                                final WoodenStep slab2 = (WoodenStep)block.getType().getNewData(block.getData());
                                box.minY = (float)block.getY();
                                box.maxY = (float)block.getY();
                                if (slab2.isInverted()) {
                                    box = box.add(0.0f, 0.5f, 0.0f, 0.0f, 1.0f, 0.0f);
                                }
                                else {
                                    box = box.add(0.0f, 0.0f, 0.0f, 0.0f, 0.5f, 0.0f);
                                }
                            }
                        }
                        return box;
                    }
                    return new BoundingBox((float)block.getX(), (float)block.getY(), (float)block.getZ(), (float)block.getX(), (float)block.getY(), (float)block.getZ());
                }
                else {
                    if (getMethodValueNoST(getMethodNoST((Class)blockNMS.getClass(), "a", new Class[] { ReflectionUtil.iBlockData, getNMSClass("IBlockAccess"), ReflectionUtil.blockPosition }), blockNMS, new Object[] { data, world, bPos }) != null) {
                        return toBoundingBox(getMethodValue(getMethod((Class)blockNMS.getClass(), "a", new Class[] { ReflectionUtil.iBlockData, getNMSClass("IBlockAccess"), ReflectionUtil.blockPosition }), blockNMS, new Object[] { data, world, bPos })).add((float)block.getX(), (float)block.getY(), (float)block.getZ(), (float)block.getX(), (float)block.getY(), (float)block.getZ());
                    }
                    if (getMethodValueNoST(getMethodNoST(ReflectionUtil.vanillaBlock, "a", new Class[] { ReflectionUtil.iBlockData, getNMSClass("IBlockAccess"), ReflectionUtil.blockPosition }), blockNMS, new Object[] { data, world, bPos }) != null) {
                        return toBoundingBox(getMethodValue(getMethod(ReflectionUtil.vanillaBlock, "a", new Class[] { ReflectionUtil.iBlockData, getNMSClass("IBlockAccess"), ReflectionUtil.blockPosition }), blockNMS, new Object[] { data, world, bPos })).add((float)block.getX(), (float)block.getY(), (float)block.getZ(), (float)block.getX(), (float)block.getY(), (float)block.getZ());
                    }
                    return new BoundingBox((float)block.getX(), (float)block.getY(), (float)block.getZ(), (float)block.getX(), (float)block.getY(), (float)block.getZ());
                }
            }
            else {
                final Object blockNMS2 = getVanillaBlock(block);
                final Object world = getWorldHandle(block.getWorld());
                if (getMethodValueNoST(getMethodNoST(ReflectionUtil.vanillaBlock, "a", new Class[] { getNMSClass("World"), Integer.TYPE, Integer.TYPE, Integer.TYPE }), blockNMS2, new Object[] { world, block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ() }) != null) {
                    return toBoundingBox(getMethodValue(getMethod(ReflectionUtil.vanillaBlock, "a", new Class[] { getNMSClass("World"), Integer.TYPE, Integer.TYPE, Integer.TYPE }), blockNMS2, new Object[] { world, block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ() }));
                }
                return new BoundingBox((float)block.getX(), (float)block.getY(), (float)block.getZ(), (float)block.getX(), (float)block.getY(), (float)block.getZ());
            }
        }
        catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE, "Error occured with block: " + block.getType().toString());
            e.printStackTrace();
            return null;
        }
    }
    
    public static double getTPS(final Server server) {
        final Object handle = getMethodValue(getMethod(getCBClass("CraftServer"), "getHandle", new Class[0]), (Object)server, new Object[0]);
        return (int)getFieldValue(getFieldByName(getNMSClass("MinecraftServer"), "TPS"), handle);
    }
    
    public static float getBlockHardness(final Material material) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        if (!material.isBlock()) {
            return 0.0f;
        }
        final int blockId = material.getId();
        final Object nmsBlock = getNMSClass("Block").getMethod("getById", Integer.TYPE).invoke(null, blockId);
        try {
            final Field field = nmsBlock.getClass().getDeclaredField("strength");
            field.setAccessible(true);
            return (float)field.get(nmsBlock);
        }
        catch (NoSuchFieldException e) {
            return 0.0f;
        }
    }
    
    public static float getDestroySpeed(final Block block, final Player player) {
        if (Overflow.getInstance().getBukkitVerbose().contains("1_8")) {
            final Object item = getVanillaItem(player.getItemInHand());
            return (float)getMethodValue(getMethod(getNMSClass("Item"), "getDestroySpeed", new Class[] { getNMSClass("ItemStack"), getNMSClass("IBlockData") }), item, new Object[] { getVanillaItemStack(player.getItemInHand()), getBlockData(block) });
        }
        final Object item = getVanillaItem(player.getInventory().getItemInHand());
        return (float)getMethodValue(getMethod(getNMSClass("Item"), "getDestroySpeed", new Class[] { getNMSClass("ItemStack"), getNMSClass("Block") }), item, new Object[] { getVanillaItemStack(player.getInventory().getItemInHand()), getVanillaBlock(block) });
    }
    
    public static Object getVanillaItem(final ItemStack itemStack) {
        return getMethodValue(getMethod(getNMSClass("ItemStack"), "getItem", new Class[0]), getVanillaItemStack(itemStack), new Object[0]);
    }
    
    public static Object getVanillaItemStack(final ItemStack itemStack) {
        return getMethodValue(getMethod(getCBClass("inventory.CraftItemStack"), "asNMSCopy", new Class[] { getClass("org.bukkit.inventory.ItemStack") }), (Object)itemStack, new Object[] { itemStack });
    }
    
    public static Object getVanillaBlock(final Block block) {
        if (!isBukkitVerison("1_7")) {
            final Object getType = getBlockData(block);
            return getMethodValue(getMethod(ReflectionUtil.iBlockData, "getBlock", new Class[0]), getType, new Object[0]);
        }
        final Object world = getWorldHandle(block.getWorld());
        return getMethodValue(getMethod(ReflectionUtil.worldServer, "getType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE }), world, new Object[] { block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ() });
    }
    
    public static Object getBlockData(final Block block) {
        try {
            if (!isBukkitVerison("1_7")) {
                final Object bPos = ReflectionUtil.blockPosition.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());
                final Object world = getWorldHandle(block.getWorld());
                return getMethodValue(getMethod(ReflectionUtil.worldServer, "getType", new Class[] { ReflectionUtil.blockPosition }), world, new Object[] { bPos });
            }
            final Object world2 = getWorldHandle(block.getWorld());
            return getMethodValue(getMethod(ReflectionUtil.worldServer, "getType", new Class[] { Integer.TYPE, Integer.TYPE, Integer.TYPE }), world2, new Object[] { block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ() });
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object getBelowBox(final Player player, final double below) {
        final Object box = getBoundingBox(player);
        final double minX = (double)getFieldValue(getFieldByName((Class)box.getClass(), "a"), box);
        final double minY = (double)getFieldValue(getFieldByName((Class)box.getClass(), "b"), box) - below;
        final double minZ = (double)getFieldValue(getFieldByName((Class)box.getClass(), "c"), box);
        final double maxX = (double)getFieldValue(getFieldByName((Class)box.getClass(), "d"), box);
        final double maxY = (double)getFieldValue(getFieldByName((Class)box.getClass(), "e"), box);
        final double maxZ = (double)getFieldValue(getFieldByName((Class)box.getClass(), "f"), box);
        try {
            return getNMSClass("AxisAlignedBB").getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE).newInstance(minX, minY, minZ, maxX, maxY, maxZ);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object getBoundingBox(final Player player) {
        return getBoundingBox((Entity)player);
    }
    
    public static Object getBoundingBox(final Entity entity) {
        return isBukkitVerison("1_7") ? getFieldValue(getFieldByName(ReflectionUtil.Entity, "boundingBox"), getEntity(entity)) : getMethodValue(getMethod(ReflectionUtil.Entity, "getBoundingBox", new Class[0]), getEntity(entity), new Object[0]);
    }
    
    public static boolean isBukkitVerison(final String version) {
        return Overflow.getInstance().getBukkitVerbose().contains(version);
    }
    
    public static File getPluginFolder() {
        final Object console = getMethodValue(getMethod(getCBClass("CraftServer"), "console", new Class[0]), (Object)Overflow.getInstance().getServer(), new Object[0]);
        final Object options = getFieldValue(getFieldByName(getNMSClass("MinecraftServer"), "options"), console);
        return (File)getMethodValue(getMethod(getNMSClass("OptionSet"), "valueOf", new Class[] { String.class }), options, new Object[] { "plugins" });
    }
    
    public static boolean isNewVersion() {
        return isBukkitVerison("1_9") || isBukkitVerison("1_1");
    }
    
    public static Class<?> getCBClass(final String string) {
        return (Class<?>)getClass("org.bukkit.craftbukkit." + ReflectionUtil.version + "." + string);
    }
    
    public static Object newAxisAlignedBB(final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ) {
        try {
            return isBukkitVerison("1_7") ? getMethodValue(getMethod(getNMSClass("AxisAlignedBB"), "a", new Class[] { Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE }), (Object)null, new Object[] { minX, minY, minZ, maxX, maxY, maxZ }) : getNMSClass("AxisAlignedBB").getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE).newInstance(minX, minY, minZ, maxX, maxY, maxZ);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object newVoxelShape(final double minX, final double minY, final double minZ, final double maxX, final double maxY, final double maxZ) {
        try {
            return getNMSClass("AxisAlignedBB").getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE).newInstance(minX, minY, minZ, maxX, maxY, maxZ);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    public static double getMotionY(final Player player) {
        double motionY = 0.0;
        try {
            motionY = (double)getEntityPlayer(player).getClass().getField("motY").get(getEntityPlayer(player));
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
        return motionY;
    }
    
    public static Object newAxisAlignedBB(final Location from, final Location to) {
        final double minX = Math.min(from.getX(), to.getX());
        final double minY = Math.min(from.getY(), to.getY());
        final double minZ = Math.min(from.getZ(), to.getZ());
        final double maxX = Math.max(from.getX(), to.getX());
        final double maxY = Math.max(from.getY(), to.getY());
        final double maxZ = Math.max(from.getZ(), to.getZ());
        try {
            return getNMSClass("AxisAlignedBB").getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE).newInstance(minX, minY, minZ, maxX, maxY, maxZ);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    public static Class<?> getClass(final String string) {
        try {
            return Class.forName(string);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Enum<?> getEnum(final Class<?> clazz, final String enumName) {
        return Enum.valueOf(clazz, enumName);
    }
    
    public static Field getFieldByName(final Class<?> clazz, final String fieldName) {
        try {
            final Field field = (clazz.getDeclaredField(fieldName) != null) ? clazz.getDeclaredField(fieldName) : clazz.getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object setFieldValue(final Object object, final Field field, final Object value) {
        try {
            field.set(object, value);
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return field.getDeclaringClass();
    }
    
    public static boolean inBlock(final Player player, final Object axisAlignedBB) {
        return getCollidingBlocks(player, axisAlignedBB).size() > 0;
    }
    
    public static Collection<?> getCollidingBlocks(final Player player, final Object axisAlignedBB) {
        final Object world = getWorldHandle(player.getWorld());
        return (Collection<?>)(isNewVersion() ? getMethodValue(ReflectionUtil.getCubes1_12, world, new Object[] { null, axisAlignedBB }) : getMethodValue(ReflectionUtil.getCubes, world, new Object[] { axisAlignedBB }));
    }
    
    public static Object getWorldHandle(final World world) {
        return getMethodValue(getMethod(ReflectionUtil.CraftWorld, "getHandle", new Class[0]), (Object)world, new Object[0]);
    }
    
    public static Field getFirstFieldByType(final Class<?> clazz, final Class<?> type) {
        try {
            for (final Field field : clazz.getDeclaredFields()) {
                if (field.getType().equals(type)) {
                    field.setAccessible(true);
                    return field;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static Method getMethod(final Class<?> clazz, final String methodName, final Class<?>... args) {
        try {
            final Method method = clazz.getMethod(methodName, args);
            method.setAccessible(true);
            return method;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private static Method getMethodNoST(final Class<?> clazz, final String methodName, final Class<?>... args) {
        try {
            final Method method = clazz.getMethod(methodName, args);
            method.setAccessible(true);
            return method;
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static boolean hasMethod(final Class clazz, final Method method) {
        return Arrays.stream(clazz.getMethods()).anyMatch(ReflectionUtil::lambda$hasMethod$1);
    }
    
    public static boolean hasMethod(final Class clazz, final String methodName) {
        return Arrays.stream(clazz.getMethods()).anyMatch(ReflectionUtil::lambda$hasMethod$2);
    }
    
    public static Object getMethodValue(final Method method, final Object object, final Object... args) {
        try {
            return method.invoke(object, args);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static boolean hasField(final Class<?> object, final String fieldName) {
        return Arrays.stream(object.getFields()).anyMatch(ReflectionUtil::lambda$hasField$3);
    }
    
    public static Object getMethodValueNoST(final Method method, final Object object, final Object... args) {
        try {
            return method.invoke(object, args);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static Object getFieldValue(final Field field, final Object object) {
        try {
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static Object getFieldValueNoST(final Field field, final Object object) {
        try {
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static Field getFieldByNameNoST(final Class<?> clazz, final String fieldName) {
        try {
            final Field field = (clazz.getDeclaredField(fieldName) != null) ? clazz.getDeclaredField(fieldName) : clazz.getSuperclass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field;
        }
        catch (Exception e) {
            return null;
        }
    }
    
    public static Object newInstance(final Class<?> objectClass, final Object... args) {
        try {
            return objectClass.getConstructor(args.getClass()).newInstance(args);
        }
        catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex2) {
            final ReflectiveOperationException ex;
            final ReflectiveOperationException e = ex;
            e.printStackTrace();
            return null;
        }
    }
    
    public static Class<?> getNMSClass(final String string) {
        return (Class<?>)getClass("net.minecraft.server." + ReflectionUtil.version + "." + string);
    }
    
    static {
        ReflectionUtil.blockPosition = null;
        ReflectionUtil.version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
        ReflectionUtil.EntityPlayer = getNMSClass("EntityPlayer");
        ReflectionUtil.Entity = getNMSClass("Entity");
        ReflectionUtil.CraftPlayer = getCBClass("entity.CraftPlayer");
        ReflectionUtil.CraftEntity = getCBClass("entity.CraftEntity");
        ReflectionUtil.CraftWorld = getCBClass("CraftWorld");
        ReflectionUtil.World = getNMSClass("World");
        ReflectionUtil.worldServer = getNMSClass("WorldServer");
        ReflectionUtil.iBlockData = null;
        ReflectionUtil.iBlockAccess = null;
        ReflectionUtil.vanillaBlock = getNMSClass("Block");
        ReflectionUtil.getCubes = getMethod(ReflectionUtil.World, "a", new Class[] { getNMSClass("AxisAlignedBB") });
        ReflectionUtil.getCubes1_12 = getMethod(ReflectionUtil.World, "getCubes", new Class[] { getNMSClass("Entity"), getNMSClass("AxisAlignedBB") });
    }
}
