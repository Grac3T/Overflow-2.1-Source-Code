// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.impl;

import java.lang.reflect.Method;
import java.util.Collections;
import us.overflow.utils.blockbox.ReflectionUtil;
import java.util.List;
import org.bukkit.block.Block;
import us.overflow.utils.blockbox.BoundingBox;
import org.bukkit.Material;

public class StairBox extends BlockBox
{
    public StairBox(final Material material) {
        super(material, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
    }
    
    @Override
    List<BoundingBox> getBox(final Block block) {
        final Object vBlock = ReflectionUtil.getVanillaBlock(block);
        final Object world = ReflectionUtil.getWorldHandle(block.getWorld());
        final Method voxelShapeMethod = ReflectionUtil.getMethod(ReflectionUtil.getNMSClass("BlockStairs"), "a", new Class[] { ReflectionUtil.iBlockData, ReflectionUtil.iBlockAccess, ReflectionUtil.blockPosition });
        final Object voxelShape = ReflectionUtil.getMethodValue(voxelShapeMethod, vBlock, new Object[] { ReflectionUtil.getBlockData(block), world, ReflectionUtil.getBlockPosition(block.getLocation()) });
        return Collections.singletonList(ReflectionUtil.toBoundingBox(ReflectionUtil.getMethodValue(ReflectionUtil.getMethod(ReflectionUtil.getNMSClass("VoxelShape"), "a", new Class[0]), voxelShape, new Object[0])).add(block.getLocation().toVector()));
    }
}
