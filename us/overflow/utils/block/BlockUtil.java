// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.block;

import java.util.function.Consumer;
import org.bukkit.World;
import org.bukkit.inventory.ItemStack;
import java.util.Iterator;
import java.util.List;
import org.bukkit.util.NumberConversions;
import us.overflow.utils.location.CustomLocation;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Entity;
import java.util.ArrayList;
import org.bukkit.entity.Player;
import us.overflow.Overflow;
import org.bukkit.block.Block;
import org.bukkit.Location;
import us.overflow.utils.claszz.Helper;
import java.util.HashMap;
import us.overflow.utils.blockbox.BoundingBox;
import org.bukkit.Material;
import java.util.Map;

public class BlockUtil
{
    public static Map<Material, BoundingBox> collisionBoundingBoxes;
    private static String[] HalfBlocksArray;
    
    public BlockUtil() {
        BlockUtil.collisionBoundingBoxes = new HashMap();
        this.setupCollisionBB();
        new Helper().init();
    }
    
    public static Block getBlock(final Location location) {
        if (Overflow.getInstance().getBlockBoxManager().getBlockBox().isChunkLoaded(location)) {
            return location.getBlock();
        }
        return null;
    }
    
    public static boolean isOnFuckingBoat(final Player player) {
        final List<Entity> entities = Overflow.getInstance().getEntities().getOrDefault(player.getWorld().getUID(), new ArrayList<Entity>());
        boolean found = false;
        int c = 0;
        for (final Entity entity : entities) {
            final Entity e = entities.get(c);
            if (e != null && e.getType() == EntityType.BOAT) {
                final CustomLocation customLocation = new CustomLocation(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ());
                final CustomLocation to = Overflow.getInstance().getUserManager().getUser(player.getUniqueId()).newTo;
                found = (NumberConversions.square(to.getX() - customLocation.getX()) + NumberConversions.square(to.getY() - customLocation.getY()) + NumberConversions.square(to.getZ() - customLocation.getZ()) < 2.0);
                if (found) {
                    break;
                }
            }
            ++c;
        }
        return found;
    }
    
    public static boolean isSolid(final Block block) {
        final int type = block.getType().getId();
        switch (type) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 29:
            case 33:
            case 34:
            case 35:
            case 36:
            case 41:
            case 42:
            case 43:
            case 44:
            case 45:
            case 46:
            case 47:
            case 48:
            case 49:
            case 52:
            case 53:
            case 54:
            case 56:
            case 57:
            case 58:
            case 60:
            case 61:
            case 62:
            case 64:
            case 65:
            case 67:
            case 71:
            case 73:
            case 74:
            case 78:
            case 79:
            case 80:
            case 81:
            case 82:
            case 84:
            case 85:
            case 86:
            case 87:
            case 88:
            case 89:
            case 91:
            case 92:
            case 93:
            case 94:
            case 95:
            case 96:
            case 97:
            case 98:
            case 99:
            case 100:
            case 101:
            case 102:
            case 103:
            case 106:
            case 107:
            case 108:
            case 109:
            case 110:
            case 111:
            case 112:
            case 113:
            case 114:
            case 116:
            case 117:
            case 118:
            case 120:
            case 121:
            case 122:
            case 123:
            case 124:
            case 125:
            case 126:
            case 127:
            case 128:
            case 129:
            case 130:
            case 133:
            case 134:
            case 135:
            case 136:
            case 137:
            case 138:
            case 139:
            case 140:
            case 144:
            case 145:
            case 146:
            case 149:
            case 150:
            case 151:
            case 152:
            case 153:
            case 154:
            case 155:
            case 156:
            case 158:
            case 159:
            case 160:
            case 161:
            case 162:
            case 163:
            case 164:
            case 165:
            case 166:
            case 167:
            case 168:
            case 169:
            case 170:
            case 171:
            case 172:
            case 173:
            case 174:
            case 178:
            case 179:
            case 180:
            case 181:
            case 182:
            case 183:
            case 184:
            case 185:
            case 186:
            case 187:
            case 188:
            case 189:
            case 190:
            case 191:
            case 192:
            case 193:
            case 194:
            case 195:
            case 196:
            case 197:
            case 198:
            case 199:
            case 200:
            case 201:
            case 202:
            case 203:
            case 204:
            case 205:
            case 206:
            case 207:
            case 208:
            case 210:
            case 211:
            case 212:
            case 213:
            case 214:
            case 215:
            case 216:
            case 218:
            case 219:
            case 220:
            case 221:
            case 222:
            case 223:
            case 224:
            case 225:
            case 226:
            case 227:
            case 228:
            case 229:
            case 230:
            case 231:
            case 232:
            case 233:
            case 234:
            case 235:
            case 236:
            case 237:
            case 238:
            case 239:
            case 240:
            case 241:
            case 242:
            case 243:
            case 244:
            case 245:
            case 246:
            case 247:
            case 248:
            case 249:
            case 250:
            case 251:
            case 252:
            case 255:
            case 355:
            case 397: {
                return true;
            }
            default: {
                return false;
            }
        }
    }
    
    public static boolean isHalfBlock(final Block block) {
        final Material type = block.getType();
        for (final String types : BlockUtil.HalfBlocksArray) {
            if (type.toString().toLowerCase().contains(types)) {
                return true;
            }
        }
        return false;
    }
    
    public static List<BoundingBox> getBlockBoundingBox(final Block block) {
        final List<BoundingBox> boxes = (List<BoundingBox>)Overflow.getInstance().getBlockBoxManager().getBlockBox().getSpecificBox(block.getLocation());
        for (int i = 0; i < boxes.size(); ++i) {
            if (boxes.get(i).getMaximum().length() == boxes.get(i).getMinimum().length()) {
                boxes.remove(i);
                boxes.add(BlockUtil.collisionBoundingBoxes.getOrDefault(block.getType(), new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f)).add(block.getLocation().toVector()));
            }
        }
        return boxes;
    }
    
    public static boolean isLiquid(final Block block) {
        return block.getType().toString().contains("WATER") || block.getType().toString().contains("LAVA");
    }
    
    public static boolean isClimbableBlock(final Block block) {
        return block.getType() == Material.LADDER || block.getType() == Material.VINE;
    }
    
    public static boolean isIce(final Block block) {
        return block.getType().toString().contains("ICE");
    }
    
    public static boolean isFence(final Block block) {
        return (block.getType().toString().contains("FENCE") && !block.getType().toString().contains("GATE")) | block.getType().toString().contains("WALL");
    }
    
    public static boolean isSlime(final Block block) {
        return block.getType().toString().contains("SLIME");
    }
    
    public static boolean isDoor(final Block block) {
        return block.getType().toString().contains("DOOR") && !block.getType().toString().contains("TRAP");
    }
    
    public static boolean isBed(final Block block) {
        return block.getType().toString().contains("BED");
    }
    
    public static boolean isTrapDoor(final Block block) {
        return block.getType().toString().contains("DOOR") && block.getType().toString().contains("TRAP");
    }
    
    public static boolean isChest(final Block block) {
        return block.getType().equals((Object)Material.CHEST) || block.getType().equals((Object)Material.TRAPPED_CHEST) || block.getType().equals((Object)Material.ENDER_CHEST) || block.getType().toString().contains("SHULKER");
    }
    
    public static boolean isPiston(final Block block) {
        return block.getType().getId() == 36 || block.getType().getId() == 34 || block.getType().getId() == 33 || block.getType().getId() == 29;
    }
    
    public static boolean isFenceGate(final Block block) {
        return block.getType().toString().contains("FENCE") && block.getType().toString().contains("GATE");
    }
    
    public static boolean isStair(final Block block) {
        return block.getType().toString().contains("STAIR");
    }
    
    public static boolean isSlab(final Block block) {
        return block.getType().toString().contains("STEP") || block.getType().toString().contains("SLAB");
    }
    
    public static boolean isEdible(final Material material) {
        return material.equals((Object)Material.COOKED_BEEF) || material.equals((Object)Material.COOKED_CHICKEN) || material.getId() == 350 || material.getId() == 424 || material.getId() == 412 || material.equals((Object)Material.ROTTEN_FLESH) || material.getId() == 391 || material.equals((Object)Material.CARROT) || material.equals((Object)Material.GOLDEN_APPLE) || material.equals((Object)Material.GOLDEN_CARROT) || material.getId() == 320 || material.getId() == 363 || material.getId() == 365 || material.getId() == 349 || material.equals((Object)Material.SPIDER_EYE) || material.equals((Object)Material.getMaterial("BEETROOT_SOUP")) || material.getId() == 282 || material.getId() == 392 || material.equals((Object)Material.BAKED_POTATO) || material.equals((Object)Material.POISONOUS_POTATO) || material.equals((Object)Material.PUMPKIN_PIE) || material.equals((Object)Material.APPLE) || material.getId() == 423 || material.getId() == 411 || material.equals((Object)Material.MELON) || material.equals((Object)Material.getMaterial("CHORUS_FRUIT")) || material.equals((Object)Material.COOKIE) || material.equals((Object)Material.POTION);
    }
    
    public static boolean isTool(final ItemStack stack) {
        final String name = stack.getType().name().toLowerCase();
        return name.contains("axe") || name.contains("spade") || name.contains("shovel") || name.contains("shear") || name.contains("sword");
    }
    
    public static List<Block> getBlocks(final BoundingBox box, final World world) {
        final List<Block> block = new ArrayList<Block>();
        Overflow.getInstance().getBlockBoxManager().getBlockBox().getCollidingBoxes(world, box).forEach(BlockUtil::lambda$getBlocks$0);
        return block;
    }
    
    public static Location findGround(final World world, final Location point) {
        for (int y = point.toVector().getBlockY(); y > 0; --y) {
            final Location loc = new Location(world, point.getX(), (double)y, point.getZ());
            final Block block = getBlock(loc);
            if (block.getType().isBlock() && block.getType().isSolid() && !block.isEmpty()) {
                final Location toReturn = loc.clone();
                toReturn.setY((double)(y + 1));
                return toReturn;
            }
        }
        return point;
    }
    
    private void setupCollisionBB() {
        BlockUtil.collisionBoundingBoxes.put(Material.FIRE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.STONE_PLATE, new BoundingBox(0.0625f, 0.0f, 0.0625f, 0.9375f, 0.0625f, 0.9375f));
        BlockUtil.collisionBoundingBoxes.put(Material.GRAVEL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.COBBLESTONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.NETHER_BRICK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.PUMPKIN, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.CARROT, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.TNT, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.SAND, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.WOOD_PLATE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.SIGN_POST, new BoundingBox(0.25f, 0.0f, 0.25f, 0.75f, 1.0f, 0.75f));
        BlockUtil.collisionBoundingBoxes.put(Material.COCOA, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.DETECTOR_RAIL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.HARD_CLAY, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.NETHERRACK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.STONE_BUTTON, new BoundingBox(0.3125f, 0.0f, 0.375f, 0.6875f, 0.125f, 0.625f));
        BlockUtil.collisionBoundingBoxes.put(Material.CLAY, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.QUARTZ_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.HUGE_MUSHROOM_1, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.HUGE_MUSHROOM_2, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.LAVA, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.BEACON, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.GRASS, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.DEAD_BUSH, new BoundingBox(0.099999994f, 0.0f, 0.099999994f, 0.9f, 0.8f, 0.9f));
        BlockUtil.collisionBoundingBoxes.put(Material.GLOWSTONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.ICE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.BRICK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.REDSTONE_TORCH_ON, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.REDSTONE_TORCH_OFF, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.POWERED_RAIL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.DISPENSER, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.JUKEBOX, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.EMERALD_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.STONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.BOOKSHELF, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.MYCEL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.OBSIDIAN, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.PORTAL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.GOLD_PLATE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.COAL_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.GOLD_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.STAINED_CLAY, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.MOB_SPAWNER, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.BEDROCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.IRON_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.REDSTONE_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.SIGN, new BoundingBox(0.25f, 0.0f, 0.25f, 0.75f, 1.0f, 0.75f));
        BlockUtil.collisionBoundingBoxes.put(Material.IRON_PLATE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.GOLD_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.POTATO, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.MOSSY_COBBLESTONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.RAILS, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.125f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.HAY_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.TORCH, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.CARPET, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.0625f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.DIRT, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.EMERALD_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.REDSTONE_LAMP_ON, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.REDSTONE_LAMP_OFF, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.NETHER_WARTS, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.SPONGE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.WORKBENCH, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.SANDSTONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.LAPIS_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.NOTE_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.WOOL, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.COMMAND, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.ENDER_STONE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.TRIPWIRE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.15625f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.SAPLING, new BoundingBox(0.099999994f, 0.0f, 0.099999994f, 0.9f, 0.8f, 0.9f));
        BlockUtil.collisionBoundingBoxes.put(Material.PACKED_ICE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.LAPIS_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.SMOOTH_BRICK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.RED_MUSHROOM, new BoundingBox(0.3f, 0.0f, 0.3f, 0.7f, 0.4f, 0.7f));
        BlockUtil.collisionBoundingBoxes.put(Material.BROWN_MUSHROOM, new BoundingBox(0.3f, 0.0f, 0.3f, 0.7f, 0.4f, 0.7f));
        BlockUtil.collisionBoundingBoxes.put(Material.DIAMOND_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.CROPS, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.IRON_BLOCK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.MELON, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.DIAMOND_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.LEVER, new BoundingBox(0.25f, 0.0f, 0.25f, 0.75f, 0.6f, 0.75f));
        BlockUtil.collisionBoundingBoxes.put(Material.SUGAR_CANE, new BoundingBox(0.125f, 0.0f, 0.125f, 0.875f, 1.0f, 0.875f));
        BlockUtil.collisionBoundingBoxes.put(Material.COAL_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.WATER_LILY, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.015625f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.QUARTZ_ORE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.GLASS, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.TRIPWIRE_HOOK, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.VINE, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.WEB, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
        BlockUtil.collisionBoundingBoxes.put(Material.WATER, new BoundingBox(0.0f, 0.0f, 0.0f, 0.9f, 0.9f, 0.9f));
        BlockUtil.collisionBoundingBoxes.put(Material.getMaterial("STATIONARY_WATER"), new BoundingBox(0.0f, 0.0f, 0.0f, 0.9f, 0.9f, 0.9f));
        BlockUtil.collisionBoundingBoxes.put(Material.getMaterial("STATIONARY_LAVA"), new BoundingBox(0.0f, 0.0f, 0.0f, 0.9f, 0.9f, 0.9f));
    }
    
    static {
        BlockUtil.HalfBlocksArray = new String[] { "pot", "flower", "step", "slab", "snow", "detector", "daylight", "comparator", "repeater", "diode", "water", "lava", "ladder", "vine", "carpet", "sign", "pressure", "plate", "button", "mushroom", "torch", "frame", "armor", "banner", "lever", "hook", "redstone", "rail", "brewing", "rose", "skull", "enchantment", "cake", "bed" };
    }
}
