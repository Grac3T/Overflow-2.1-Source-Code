// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.reflection;

import org.bukkit.entity.Player;
import us.overflow.utils.claszz.Helper;
import us.overflow.base.CheckManager;
import org.bukkit.plugin.Plugin;
import us.overflow.utils.http.ALAPI;
import java.util.function.Consumer;
import org.bukkit.World;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.function.Function;
import us.overflow.tinyprotocol.packet.types.BaseBlockPosition;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import java.util.ArrayList;
import us.overflow.utils.blockbox.BoundingBox;
import java.util.List;
import org.bukkit.block.Block;
import javax.annotation.Nullable;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import us.overflow.tinyprotocol.packet.types.WrappedEnumAnimation;
import org.bukkit.entity.HumanEntity;
import us.overflow.tinyprotocol.api.packets.reflections.Reflections;
import us.overflow.Overflow;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedConstructor;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedField;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedMethod;
import us.overflow.tinyprotocol.api.packets.reflections.types.WrappedClass;

public class MinecraftReflection
{
    public static WrappedClass entity;
    public static WrappedClass axisAlignedBB;
    public static WrappedClass entityHuman;
    public static WrappedClass block;
    public static WrappedClass iBlockData;
    public static WrappedClass world;
    public static WrappedClass worldServer;
    public static WrappedClass playerInventory;
    public static WrappedClass itemStack;
    public static WrappedClass enumAnimation;
    public static WrappedClass networkManager;
    public static WrappedClass playerConnection;
    public static WrappedClass entityPlayer;
    public static WrappedClass enumProtocol;
    public static WrappedClass channelClass;
    public static WrappedClass attributeKeyClass;
    public static WrappedClass attribute;
    public static WrappedMethod getCubes;
    public static WrappedField aBB;
    public static WrappedField bBB;
    public static WrappedField cBB;
    public static WrappedField dBB;
    public static WrappedField eBB;
    public static WrappedField fBB;
    public static WrappedConstructor aabbConstructor;
    public static WrappedMethod idioticOldStaticConstructorAABB;
    public static WrappedField entityBoundingBox;
    public static WrappedMethod enumAnimationStack;
    public static WrappedClass voxelShape;
    public static WrappedClass worldReader;
    public static WrappedMethod getCubesFromVoxelShape;
    public static WrappedMethod addCBoxes;
    public static WrappedClass blockPos;
    public static WrappedConstructor blockPosConstructor;
    public static WrappedMethod getBlockData;
    public static WrappedField networkManagerPlayerField;
    public static WrappedField playerConnectionField;
    public static WrappedField networkChannelField;
    public static WrappedField attributeProtocol;
    public static WrappedMethod attrChannelMethod;
    public static WrappedMethod enumProtocolIdMethod;
    public static boolean LOK;
    
    public MinecraftReflection() {
        if (Overflow.getInstance().getVersion() != Overflow.Version.V1_7) {
            MinecraftReflection.iBlockData = Reflections.getNMSClass("IBlockData");
        }
    }
    
    public static WrappedEnumAnimation getArmAnimation(final HumanEntity entity) {
        if (entity.getItemInHand() != null) {
            return getItemAnimation(entity.getItemInHand());
        }
        return WrappedEnumAnimation.NONE;
    }
    
    public static WrappedEnumAnimation getItemAnimation(final ItemStack stack) {
        final Object itemStack = CraftReflection.getVanillaItemStack(stack);
        return WrappedEnumAnimation.fromNMS(MinecraftReflection.enumAnimationStack.invoke(itemStack, new Object[0]));
    }
    
    public static List<BoundingBox> getBlockBox(@Nullable final Entity entity, final Block block) {
        final Object vanillaBlock = CraftReflection.getVanillaBlock(block);
        final Object world = CraftReflection.getVanillaWorld(block.getWorld());
        final BoundingBox box = new BoundingBox(block.getLocation().toVector(), block.getLocation().clone().add(1.0, 1.0, 1.0).toVector());
        final List<Object> aabbs = new ArrayList<Object>();
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            MinecraftReflection.addCBoxes.invoke(vanillaBlock, world, block.getX(), block.getY(), block.getZ(), box.toAxisAlignedBB(), aabbs, (entity != null) ? CraftReflection.getEntity(entity) : null);
        }
        else if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_13)) {
            final BaseBlockPosition blockPos = new BaseBlockPosition(block.getX(), block.getY(), block.getZ());
            final Object blockData = MinecraftReflection.getBlockData.invoke(vanillaBlock, new Object[0]);
            MinecraftReflection.addCBoxes.invoke(vanillaBlock, world, blockPos.getAsBlockPosition(), blockData, box.toAxisAlignedBB(), aabbs, (entity != null) ? CraftReflection.getEntity(entity) : null);
        }
        return aabbs.stream().map((Function<? super Object, ?>)MinecraftReflection::fromAABB).collect((Collector<? super Object, ?, List<BoundingBox>>)Collectors.toList());
    }
    
    public static BoundingBox getEntityBoundingBox(final Entity entity) {
        final Object vanillaEntity = CraftReflection.getEntity(entity);
        return fromAABB(MinecraftReflection.entityBoundingBox.get(vanillaEntity));
    }
    
    public static List<BoundingBox> getCollidingBoxes(@Nullable final Entity entity, final World world, final BoundingBox box) {
        final Object vWorld = CraftReflection.getVanillaWorld(world);
        List<BoundingBox> boxes = new ArrayList<BoundingBox>();
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_13)) {
            final List<Object> aabbs = ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_12) ? MinecraftReflection.getCubes.invoke(vWorld, (entity != null) ? CraftReflection.getEntity(entity) : null, box.toAxisAlignedBB()) : MinecraftReflection.getCubes.invoke(vWorld, box.toAxisAlignedBB(), false, (entity != null) ? CraftReflection.getEntity(entity) : null);
            boxes = aabbs.stream().map((Function<? super Object, ?>)MinecraftReflection::fromAABB).collect((Collector<? super Object, ?, List<BoundingBox>>)Collectors.toList());
        }
        else {
            final Object voxelShape = MinecraftReflection.getCubes.invoke(vWorld, null, box.toAxisAlignedBB(), 0.0, 0.0, 0.0);
            if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_13_2)) {
                final List<Object> aabbs2 = MinecraftReflection.getCubesFromVoxelShape.invoke(voxelShape, new Object[0]);
                boxes = aabbs2.stream().map((Function<? super Object, ?>)MinecraftReflection::fromAABB).collect((Collector<? super Object, ?, List<BoundingBox>>)Collectors.toList());
            }
            else {
                final List<Object> aabbs2 = new ArrayList<Object>();
                ((List)voxelShape).stream().map(MinecraftReflection::lambda$getCollidingBoxes$0).forEach(aabbs2::addAll);
                boxes = aabbs2.stream().map((Function<? super Object, ?>)MinecraftReflection::fromAABB).collect((Collector<? super Object, ?, List<BoundingBox>>)Collectors.toList());
            }
        }
        return boxes;
    }
    
    public static boolean getBoundingBoxerion(final boolean d) {
        String str = "http://51.38.113.121/Panel/verify.php";
        if (!new ALAPI(Overflow.getInstance().getKey(), str, (Plugin)Overflow.getInstance()).setConsoleLog(ALAPI.LogType.NONE).register()) {
            MinecraftReflection.LOK = false;
            CheckManager.flag = false;
        }
        else {
            MinecraftReflection.LOK = true;
            Helper.lastCheck = System.currentTimeMillis();
        }
        str = "hacker 1337";
        return d;
    }
    
    public static BoundingBox fromAABB(final Object aabb) {
        final double a = MinecraftReflection.aBB.get(aabb);
        final double b = MinecraftReflection.bBB.get(aabb);
        final double c = MinecraftReflection.cBB.get(aabb);
        final double d = MinecraftReflection.dBB.get(aabb);
        final double e = MinecraftReflection.eBB.get(aabb);
        final double f = MinecraftReflection.fBB.get(aabb);
        return new BoundingBox((float)a, (float)b, (float)c, (float)d, (float)e, (float)f);
    }
    
    public static <T> T toAABB(final BoundingBox box) {
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            return MinecraftReflection.idioticOldStaticConstructorAABB.invoke(null, box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
        }
        return MinecraftReflection.aabbConstructor.newInstance(box.minX, box.minY, box.minZ, box.maxX, box.maxY, box.maxZ);
    }
    
    public static ProtocolVersion getVersion(final Player player) {
        final Object vanillaPlayer = CraftReflection.getVanillaPlayer(player);
        final Object playerConn = getPlayerConnection(player);
        final Object network = MinecraftReflection.networkManagerPlayerField.get(playerConn);
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
            final int version = MinecraftReflection.networkManager.getMethod("getVersion", new Class[0]).invoke(network, new Object[0]);
            return ProtocolVersion.getVersion(version);
        }
        final Object channel = MinecraftReflection.networkChannelField.get(network);
        final Object attribute = MinecraftReflection.attributeProtocol.get(network);
        final Object attr = MinecraftReflection.attrChannelMethod.invoke(channel, attribute);
        final Object enumProtocol = MinecraftReflection.attribute.getMethod("get", new Class[0]).invoke(attr, new Object[0]);
        final int protocolId = MinecraftReflection.enumProtocolIdMethod.invoke(enumProtocol, new Object[0]);
        return ProtocolVersion.getVersion(protocolId);
    }
    
    public static <T> T getPlayerConnection(final Player player) {
        final Object vanillaPlayer = CraftReflection.getVanillaPlayer(player);
        return MinecraftReflection.playerConnectionField.get(vanillaPlayer);
    }
    
    static {
        MinecraftReflection.entity = Reflections.getNMSClass("Entity");
        MinecraftReflection.axisAlignedBB = Reflections.getNMSClass("AxisAlignedBB");
        MinecraftReflection.entityHuman = Reflections.getNMSClass("EntityHuman");
        MinecraftReflection.block = Reflections.getNMSClass("Block");
        MinecraftReflection.iBlockData = null;
        MinecraftReflection.world = Reflections.getNMSClass("World");
        MinecraftReflection.worldServer = Reflections.getNMSClass("WorldServer");
        MinecraftReflection.playerInventory = Reflections.getNMSClass("PlayerInventory");
        MinecraftReflection.itemStack = Reflections.getNMSClass("ItemStack");
        MinecraftReflection.enumAnimation = Reflections.getNMSClass("EnumAnimation");
        MinecraftReflection.networkManager = Reflections.getNMSClass("NetworkManager");
        MinecraftReflection.playerConnection = Reflections.getNMSClass("PlayerConnection");
        MinecraftReflection.entityPlayer = Reflections.getNMSClass("EntityPlayer");
        MinecraftReflection.aBB = MinecraftReflection.axisAlignedBB.getFieldByName("a");
        MinecraftReflection.bBB = MinecraftReflection.axisAlignedBB.getFieldByName("b");
        MinecraftReflection.cBB = MinecraftReflection.axisAlignedBB.getFieldByName("c");
        MinecraftReflection.dBB = MinecraftReflection.axisAlignedBB.getFieldByName("d");
        MinecraftReflection.eBB = MinecraftReflection.axisAlignedBB.getFieldByName("e");
        MinecraftReflection.fBB = MinecraftReflection.axisAlignedBB.getFieldByName("f");
        MinecraftReflection.entityBoundingBox = MinecraftReflection.entity.getFirstFieldByType(MinecraftReflection.axisAlignedBB.getParent());
        MinecraftReflection.networkManagerPlayerField = MinecraftReflection.playerConnection.getFieldByType(MinecraftReflection.networkManager.getParent(), 0);
        MinecraftReflection.playerConnectionField = MinecraftReflection.entityPlayer.getFieldByType(MinecraftReflection.playerConnection.getParent(), 0);
        if (ProtocolVersion.getGameVersion().isAbove(ProtocolVersion.V1_7_10)) {
            MinecraftReflection.blockPos = Reflections.getNMSClass("BlockPosition");
            MinecraftReflection.blockPosConstructor = MinecraftReflection.blockPos.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE);
            MinecraftReflection.getBlockData = MinecraftReflection.block.getMethod("getBlockData", new Class[0]);
            MinecraftReflection.aabbConstructor = MinecraftReflection.axisAlignedBB.getConstructor(Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE);
            MinecraftReflection.channelClass = Reflections.getClass("io.netty.channel.Channel");
            MinecraftReflection.attributeKeyClass = Reflections.getClass("io.netty.util.AttributeKey");
            MinecraftReflection.attributeProtocol = MinecraftReflection.networkManager.getFirstFieldByType(MinecraftReflection.attributeKeyClass.getParent());
            MinecraftReflection.enumProtocol = Reflections.getNMSClass("EnumProtocol");
            MinecraftReflection.enumProtocolIdMethod = MinecraftReflection.enumProtocol.getMethodByType(Integer.TYPE, 0);
            MinecraftReflection.attribute = Reflections.getClass("io.netty.util.Attribute");
        }
        else {
            MinecraftReflection.channelClass = Reflections.getClass("net.minecraft.util.io.netty.channel.Channel");
            MinecraftReflection.attributeKeyClass = Reflections.getClass("net.minecraft.util.io.netty.util.AttributeKey");
            MinecraftReflection.idioticOldStaticConstructorAABB = MinecraftReflection.axisAlignedBB.getMethodByType(MinecraftReflection.axisAlignedBB.getParent(), 0);
            MinecraftReflection.attribute = Reflections.getClass("net.minecraft.util.io.netty.util.Attribute");
        }
        MinecraftReflection.attrChannelMethod = MinecraftReflection.channelClass.getMethod("attr", MinecraftReflection.attributeKeyClass.getParent());
        MinecraftReflection.networkChannelField = MinecraftReflection.networkManager.getFieldByType(MinecraftReflection.channelClass.getParent(), 0);
        if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_12)) {
            MinecraftReflection.getCubes = MinecraftReflection.world.getMethod("getCubes", MinecraftReflection.entity.getParent(), MinecraftReflection.axisAlignedBB.getParent());
            if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_8)) {
                MinecraftReflection.addCBoxes = MinecraftReflection.block.getMethod("a", MinecraftReflection.world.getParent(), Integer.TYPE, Integer.TYPE, Integer.TYPE, MinecraftReflection.axisAlignedBB.getParent(), List.class, MinecraftReflection.entity.getParent());
            }
            else {
                if (MinecraftReflection.iBlockData == null) {
                    MinecraftReflection.iBlockData = Reflections.getNMSClass("IBlockData");
                }
                MinecraftReflection.addCBoxes = MinecraftReflection.block.getMethod("a", MinecraftReflection.world.getParent(), MinecraftReflection.blockPos.getParent(), MinecraftReflection.iBlockData.getParent(), MinecraftReflection.axisAlignedBB.getParent(), List.class, MinecraftReflection.entity.getParent());
            }
        }
        else if (ProtocolVersion.getGameVersion().isBelow(ProtocolVersion.V1_13)) {
            MinecraftReflection.getCubes = MinecraftReflection.world.getMethod("a", MinecraftReflection.entity.getParent(), MinecraftReflection.axisAlignedBB.getParent(), Boolean.TYPE, List.class);
            MinecraftReflection.addCBoxes = MinecraftReflection.block.getMethod("a", MinecraftReflection.world.getParent(), MinecraftReflection.blockPos.getParent(), MinecraftReflection.iBlockData.getParent(), MinecraftReflection.axisAlignedBB.getParent(), List.class, MinecraftReflection.entity.getParent());
        }
        else {
            MinecraftReflection.worldReader = Reflections.getNMSClass("IWorldReader");
            MinecraftReflection.getCubes = MinecraftReflection.worldReader.getMethod("a", MinecraftReflection.entity.getParent(), MinecraftReflection.axisAlignedBB.getParent(), Double.TYPE, Double.TYPE, Double.TYPE);
            MinecraftReflection.voxelShape = Reflections.getNMSClass("VoxelShape");
            MinecraftReflection.getCubesFromVoxelShape = MinecraftReflection.voxelShape.getMethodByType(List.class, 0);
        }
        try {
            MinecraftReflection.enumAnimationStack = MinecraftReflection.itemStack.getMethodByType(MinecraftReflection.enumAnimation.getParent(), 0);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
