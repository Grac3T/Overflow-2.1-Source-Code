// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.reflection;

import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import us.overflow.tinyprotocol.reflection.Reflection;
import org.bukkit.entity.Player;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.ItemStack;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedMethod;
import us.overflow.tinyprotocol.reflection.MethodInvoker;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;

public class CraftReflection
{
    public static Class<?> craftHumanEntity;
    public static Class<?> craftEntity;
    public static Class<?> craftItemStack;
    public static Class<?> craftBlock;
    public static Class<?> craftWorld;
    public static WrappedClass craftplayer;
    public static MethodInvoker itemStackInstance;
    public static MethodInvoker humanEntityInstance;
    public static MethodInvoker entityInstance;
    public static MethodInvoker blockInstance;
    public static MethodInvoker worldInstance;
    public static WrappedMethod bukkitEntity;
    public static WrappedMethod entityPlayerHandle;
    
    public static Object getVanillaItemStack(final ItemStack stack) {
        return CraftReflection.itemStackInstance.invoke(null, stack);
    }
    
    public static Object getEntityHuman(final HumanEntity entity) {
        return CraftReflection.humanEntityInstance.invoke(entity, new Object[0]);
    }
    
    public static Object getEntity(final Entity entity) {
        return CraftReflection.entityInstance.invoke(entity, new Object[0]);
    }
    
    public static Object getVanillaBlock(final Block block) {
        return CraftReflection.blockInstance.invoke(block, new Object[0]);
    }
    
    public static Object getVanillaWorld(final World world) {
        return CraftReflection.worldInstance.invoke(world, new Object[0]);
    }
    
    public static Entity getBukkitEntity(final Object vanillaEntity) {
        return CraftReflection.bukkitEntity.invoke(vanillaEntity, new Object[0]);
    }
    
    public static <T> T getVanillaPlayer(final Player player) {
        return CraftReflection.entityPlayerHandle.invoke(player, new Object[0]);
    }
    
    static {
        CraftReflection.craftHumanEntity = (Class<?>)Reflection.getCraftBukkitClass("entity.CraftHumanEntity");
        CraftReflection.craftEntity = (Class<?>)Reflection.getCraftBukkitClass("entity.CraftEntity");
        CraftReflection.craftItemStack = (Class<?>)Reflection.getCraftBukkitClass("inventory.CraftItemStack");
        CraftReflection.craftBlock = (Class<?>)Reflection.getCraftBukkitClass("block.CraftBlock");
        CraftReflection.craftWorld = (Class<?>)Reflection.getCraftBukkitClass("CraftWorld");
        CraftReflection.craftplayer = Reflections.getCBClass("entity.CraftPlayer");
        CraftReflection.itemStackInstance = Reflection.getMethod((Class)CraftReflection.craftItemStack, "asNMSCopy", new Class[] { ItemStack.class });
        CraftReflection.humanEntityInstance = Reflection.getMethod((Class)CraftReflection.craftHumanEntity, "getHandle", new Class[0]);
        CraftReflection.entityInstance = Reflection.getMethod((Class)CraftReflection.craftEntity, "getHandle", new Class[0]);
        CraftReflection.blockInstance = Reflection.getMethod((Class)CraftReflection.craftBlock, "getNMSBlock", new Class[0]);
        CraftReflection.worldInstance = Reflection.getMethod((Class)CraftReflection.craftWorld, "getHandle", new Class[0]);
        CraftReflection.bukkitEntity = MinecraftReflection.entity.getMethod("getBukkitEntity", new Class[0]);
        CraftReflection.entityPlayerHandle = CraftReflection.craftplayer.getMethodByType(MinecraftReflection.entityPlayer.getParent(), 0);
    }
}
