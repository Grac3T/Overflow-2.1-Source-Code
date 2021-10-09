// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.boxes;

import us.overflow.utils.blockbox.ReflectionUtil;
import net.minecraft.server.v1_7_R4.EntityPlayer;
import net.minecraft.server.v1_7_R4.WorldServer;
import net.minecraft.server.v1_7_R4.EntityTrackerEntry;
import net.minecraft.server.v1_7_R4.GenericAttributes;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import net.minecraft.server.v1_7_R4.EntityHuman;
import net.minecraft.server.v1_7_R4.EnumAnimation;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftHumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.LivingEntity;
import java.util.Collections;
import org.bukkit.block.Block;
import java.util.function.Predicate;
import java.util.Collection;
import net.minecraft.server.v1_7_R4.IBlockAccess;
import net.minecraft.server.v1_7_R4.Entity;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.Material;
import us.overflow.utils.block.BlockUtil;
import org.bukkit.Location;
import us.overflow.utils.MathUtil;
import net.minecraft.server.v1_7_R4.AxisAlignedBB;
import java.util.ArrayList;
import java.util.List;
import us.overflow.utils.blockbox.BoundingBox;
import org.bukkit.World;
import us.overflow.utils.blockbox.BlockBox;

public class BlockBox1_7_R4 implements BlockBox
{
    public List<BoundingBox> getCollidingBoxes(final World world, final BoundingBox box) {
        final List<AxisAlignedBB> aabbs = new ArrayList<AxisAlignedBB>();
        final List<BoundingBox> boxes = new ArrayList<BoundingBox>();
        final int minX = MathUtil.floor((double)box.minX);
        final int maxX = MathUtil.floor((double)(box.maxX + 1.0f));
        final int minY = MathUtil.floor((double)box.minY);
        final int maxY = MathUtil.floor((double)(box.maxY + 1.0f));
        final int minZ = MathUtil.floor((double)box.minZ);
        final int maxZ = MathUtil.floor((double)(box.maxZ + 1.0f));
        for (int x = minX; x < maxX; ++x) {
            for (int z = minZ; z < maxZ; ++z) {
                for (int y = minY - 1; y < maxY; ++y) {
                    final Block block = BlockUtil.getBlock(new Location(world, (double)x, (double)y, (double)z));
                    if (block != null && !block.getType().equals((Object)Material.AIR)) {
                        if (BlockUtil.collisionBoundingBoxes.containsKey(block.getType())) {
                            aabbs.add((AxisAlignedBB)BlockUtil.collisionBoundingBoxes.get(block.getType()).add(block.getLocation().toVector()).toAxisAlignedBB());
                        }
                        else {
                            final int aX = x;
                            final int aY = y;
                            final int aZ = z;
                            final net.minecraft.server.v1_7_R4.World nmsWorld = (net.minecraft.server.v1_7_R4.World)((CraftWorld)world).getHandle();
                            final net.minecraft.server.v1_7_R4.Block nmsBlock = ((CraftWorld)world).getHandle().getType(aX, aY, aZ);
                            nmsBlock.a(nmsWorld, aX, aY, aZ, (AxisAlignedBB)box.toAxisAlignedBB(), (List)aabbs, (Entity)null);
                            final List<AxisAlignedBB> preBoxes = new ArrayList<AxisAlignedBB>();
                            nmsBlock.updateShape((IBlockAccess)nmsWorld, aX, aY, aZ);
                            nmsBlock.a(nmsWorld, aX, aY, aZ, (AxisAlignedBB)box.toAxisAlignedBB(), (List)preBoxes, (Entity)null);
                            if (preBoxes.size() > 0) {
                                aabbs.addAll(preBoxes);
                            }
                            else {
                                boxes.add(new BoundingBox((float)nmsBlock.x(), (float)nmsBlock.z(), (float)nmsBlock.B(), (float)nmsBlock.y(), (float)nmsBlock.A(), (float)nmsBlock.C()).add(block.getLocation().toVector()));
                            }
                        }
                    }
                }
            }
        }
        aabbs.stream().filter(box::collides).forEach(BlockBox1_7_R4::lambda$getCollidingBoxes$0);
        return boxes;
    }
    
    public List<BoundingBox> getSpecificBox(final Location loc) {
        return Collections.synchronizedList((List<BoundingBox>)this.getCollidingBoxes(loc.getWorld(), new BoundingBox(loc.toVector(), loc.toVector())));
    }
    
    public boolean isChunkLoaded(final Location loc) {
        final net.minecraft.server.v1_7_R4.World world = (net.minecraft.server.v1_7_R4.World)((CraftWorld)loc.getWorld()).getHandle();
        return !world.isStatic && world.isLoaded(loc.getBlockX(), 0, loc.getBlockZ()) && world.getChunkAtWorldCoords(loc.getBlockX(), loc.getBlockZ()).d;
    }
    
    public boolean isRiptiding(final LivingEntity entity) {
        return false;
    }
    
    public boolean isUsingItem(final Player player) {
        final EntityHuman entity = ((CraftHumanEntity)player).getHandle();
        return entity.bF() != null && entity.bF().getItem().d(entity.bF()) != EnumAnimation.NONE;
    }
    
    public float getMovementFactor(final Player player) {
        return (float)((CraftPlayer)player).getHandle().getAttributeInstance(GenericAttributes.d).getValue();
    }
    
    public int getTrackerId(final Player player) {
        final EntityPlayer entityPlayer = ((CraftPlayer)player).getHandle();
        final EntityTrackerEntry entry = (EntityTrackerEntry)((WorldServer)entityPlayer.getWorld()).tracker.trackedEntities.get(entityPlayer.getId());
        return entry.tracker.getId();
    }
    
    public float getAiSpeed(final Player player) {
        return ((CraftPlayer)player).getHandle().bl();
    }
}
