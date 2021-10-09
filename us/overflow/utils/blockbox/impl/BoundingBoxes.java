// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.impl;

import us.overflow.Overflow;
import us.overflow.utils.block.BlockUtil;
import org.bukkit.block.Block;
import java.util.List;
import java.util.Arrays;
import us.overflow.utils.blockbox.BoundingBox;
import java.util.ArrayList;
import java.util.HashMap;
import org.bukkit.Material;
import java.util.Map;

public class BoundingBoxes
{
    private Map<Material, BlockBox> blockBoxes;
    
    public BoundingBoxes() {
        this.blockBoxes = new HashMap();
    }
    
    public void addBox() {
        final List<String> strings = new ArrayList<String>();
        this.blockBoxes.put(Material.AIR, new RegularBox(Material.AIR, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.STONE, new RegularBox(Material.STONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.GRASS, new RegularBox(Material.GRASS, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.DIRT, new RegularBox(Material.DIRT, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.COBBLESTONE, new RegularBox(Material.COBBLESTONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.WOOD, new RegularBox(Material.WOOD, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.SAPLING, new RegularBox(Material.SAPLING, new BoundingBox(0.1f, 0.0f, 0.1f, 0.9f, 0.8f, 0.9f)));
        this.blockBoxes.put(Material.BEDROCK, new RegularBox(Material.BEDROCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.WATER, new RegularBox(Material.WATER, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.STATIONARY_WATER, new RegularBox(Material.STATIONARY_WATER, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.LAVA, new RegularBox(Material.LAVA, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.STATIONARY_LAVA, new RegularBox(Material.STATIONARY_LAVA, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.SAND, new RegularBox(Material.SAND, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.GRAVEL, new RegularBox(Material.GRAVEL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.GOLD_ORE, new RegularBox(Material.GOLD_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.IRON_ORE, new RegularBox(Material.IRON_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.COAL_ORE, new RegularBox(Material.COAL_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.LOG, new RegularBox(Material.LOG, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.LEAVES, new RegularBox(Material.LEAVES, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.SPONGE, new RegularBox(Material.SPONGE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.GLASS, new RegularBox(Material.GLASS, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.LAPIS_ORE, new RegularBox(Material.LAPIS_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.LAPIS_BLOCK, new RegularBox(Material.LAPIS_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.DISPENSER, new RegularBox(Material.DISPENSER, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.SANDSTONE, new RegularBox(Material.SANDSTONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.NOTE_BLOCK, new RegularBox(Material.NOTE_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.BED_BLOCK, new RegularBox(Material.BED_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.POWERED_RAIL, new RegularBox(Material.POWERED_RAIL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f)));
        this.blockBoxes.put(Material.DETECTOR_RAIL, new RegularBox(Material.DETECTOR_RAIL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f)));
        this.blockBoxes.put(Material.PISTON_STICKY_BASE, new PistonBox(Material.PISTON_STICKY_BASE));
        this.blockBoxes.put(Material.WEB, new RegularBox(Material.WEB, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.PISTON_BASE, new PistonBox(Material.PISTON_BASE));
        this.blockBoxes.put(Material.PISTON_EXTENSION, new PistonBox(Material.PISTON_EXTENSION));
        this.blockBoxes.put(Material.WOOL, new RegularBox(Material.WOOL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.PISTON_MOVING_PIECE, new PistonBox(Material.PISTON_MOVING_PIECE));
        this.blockBoxes.put(Material.YELLOW_FLOWER, new RegularBox(Material.YELLOW_FLOWER, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.RED_ROSE, new RegularBox(Material.RED_ROSE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.BROWN_MUSHROOM, new RegularBox(Material.BROWN_MUSHROOM, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.RED_MUSHROOM, new RegularBox(Material.RED_MUSHROOM, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.GOLD_BLOCK, new RegularBox(Material.GOLD_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.IRON_BLOCK, new RegularBox(Material.IRON_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.BRICK, new RegularBox(Material.BRICK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.TNT, new RegularBox(Material.TNT, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.BOOKSHELF, new RegularBox(Material.BOOKSHELF, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.MOSSY_COBBLESTONE, new RegularBox(Material.MOSSY_COBBLESTONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.OBSIDIAN, new RegularBox(Material.OBSIDIAN, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.FIRE, new RegularBox(Material.FIRE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.MOB_SPAWNER, new RegularBox(Material.MOB_SPAWNER, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.DIAMOND_ORE, new RegularBox(Material.DIAMOND_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.DIAMOND_BLOCK, new RegularBox(Material.DIAMOND_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        this.blockBoxes.put(Material.WORKBENCH, new RegularBox(Material.WORKBENCH, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)));
        Arrays.stream(Material.values()).filter(Material::isBlock).forEach(BoundingBoxes::lambda$addBox$0);
        final StringBuilder bodyBuilder = new StringBuilder();
        strings.forEach(BoundingBoxes::lambda$addBox$1);
    }
    
    public List<BoundingBox> getBoundingBox(final Block block) {
        if (this.blockBoxes.containsKey(block.getType())) {
            return this.blockBoxes.get(block.getType()).getBox(block);
        }
        if (BlockUtil.isStair(block)) {
            return (List<BoundingBox>)new StairBox(block.getType()).getBox(block);
        }
        if (BlockUtil.isPiston(block)) {
            return (List<BoundingBox>)new PistonBox(block.getType()).getBox(block);
        }
        return (List<BoundingBox>)Overflow.getInstance().getBlockBoxManager().getBlockBox().getSpecificBox(block.getLocation());
    }
}
