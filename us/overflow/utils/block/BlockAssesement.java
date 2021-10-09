// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.block;

import org.bukkit.block.Block;
import org.bukkit.Location;
import us.overflow.Overflow;
import org.bukkit.Material;
import org.bukkit.World;
import us.overflow.utils.blockbox.BoundingBox;
import us.overflow.base.user.User;

public class BlockAssesement
{
    private User user;
    private BoundingBox boundingBox;
    private boolean soulSand;
    private boolean collidesVertically;
    private boolean collidesHorizontally;
    private boolean snow;
    private boolean onGround;
    private boolean onIce;
    private boolean onNearIce;
    private boolean blockAbove;
    private boolean stair;
    private boolean slab;
    private boolean pistion;
    private boolean climbale;
    private boolean groundSlime;
    private boolean web;
    private boolean chests;
    private boolean halfblock;
    private boolean liquid;
    private boolean wall;
    private boolean carpet;
    private boolean stairSlabs;
    private boolean slime;
    private boolean fence;
    private boolean rail;
    
    public BlockAssesement(final BoundingBox box, final User user) {
        this.user = user;
        this.boundingBox = box;
    }
    
    public void update(final BoundingBox bb, final World world) {
        final Location location = bb.getMinimum().toLocation(world);
        final Block block = BlockUtil.getBlock(location);
        if (bb.collidesHorizontally((Object)this.boundingBox)) {
            this.collidesHorizontally = true;
        }
        if (bb.collidesVertically((Object)this.boundingBox)) {
            this.collidesVertically = true;
        }
        if (block != null) {
            if ((bb.getMinimum().getY() <= this.boundingBox.getMinimum().getY() && (bb.collidesVertically((Object)this.boundingBox.subtract(0.0f, 0.1f, 0.0f, 0.0f, 0.0f, 0.0f)) || bb.collidesVertically((Object)this.boundingBox.subtract(0.0f, 0.2f, 0.0f, 0.0f, 0.0f, 0.0f)) || bb.collidesVertically((Object)this.boundingBox.subtract(0.0f, 0.3f, 0.0f, 0.0f, 0.0f, 0.0f)) || bb.collidesVertically((Object)this.boundingBox.subtract(0.0f, 0.4f, 0.0f, 0.0f, 0.0f, 0.0f)) || bb.collidesVertically((Object)this.boundingBox.subtract(0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f)))) || bb.collidesVertically((Object)this.boundingBox.subtract(0.0f, 0.12f, 0.0f, 0.0f, 1.0f, 0.0f))) {
                this.onGround = true;
            }
            if (block.getType() == Material.SNOW || block.getType() == Material.SNOW_BLOCK) {
                this.snow = true;
            }
            if (block.getType() == Material.SOUL_SAND) {
                this.soulSand = true;
            }
            if (BlockUtil.isIce(block) && this.boundingBox.subtract(-0.5f, 0.6f, -0.5f, 0.0f, 0.0f, 0.0f).collidesVertically((Object)bb)) {
                this.onIce = true;
            }
            if (BlockUtil.isIce(block) && this.boundingBox.subtract(0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f).collidesVertically((Object)bb)) {
                this.onNearIce = true;
            }
            if (bb.getMaximum().getY() >= this.boundingBox.getMaximum().getY() && bb.collidesVertically((Object)this.boundingBox.add(0.0f, 0.0f, 0.0f, 0.0f, 0.35f, 0.0f))) {
                this.blockAbove = true;
            }
            if (BlockUtil.isStair(block)) {
                this.stair = true;
            }
            if (BlockUtil.isFence(block)) {
                this.fence = true;
            }
            if (BlockUtil.isSlab(block)) {
                this.slab = true;
            }
            if (block.getType() == Material.RAILS || block.getType() == Material.DETECTOR_RAIL || block.getType() == Material.ACTIVATOR_RAIL || block.getType() == Material.POWERED_RAIL) {
                this.rail = true;
            }
            if (BlockUtil.isPiston(block)) {
                this.pistion = true;
            }
            if (BlockUtil.isClimbableBlock(block)) {
                this.climbale = true;
            }
            if (BlockUtil.isChest(block)) {
                this.chests = true;
            }
            if (BlockUtil.isHalfBlock(block)) {
                this.halfblock = true;
            }
            if (BlockUtil.isLiquid(block)) {
                this.liquid = true;
            }
            if (block.getType() == Material.COBBLE_WALL || block.getType().getId() == 85 || block.getType().getId() == 139 || block.getType().getId() == 113 || block.getTypeId() == 188 || block.getTypeId() == 189 || block.getTypeId() == 190 || block.getTypeId() == 191 || block.getTypeId() == 192) {
                this.wall = true;
            }
            if (block.getType() == Material.CARPET) {
                this.carpet = true;
            }
            if (BlockUtil.isStair(block) || BlockUtil.isSlab(block)) {
                this.stairSlabs = true;
            }
            if (Overflow.getInstance().getVersion() == Overflow.Version.V1_8 && block.getType() == Material.SLIME_BLOCK) {
                this.slime = true;
            }
            if (BlockUtil.isLiquid(block)) {
                this.liquid = true;
            }
            if (block.getType() == Material.WEB && this.boundingBox.collidesVertically((Object)bb)) {
                this.web = true;
            }
        }
    }
    
    public User getUser() {
        return this.user;
    }
    
    public BoundingBox getBoundingBox() {
        return this.boundingBox;
    }
    
    public boolean isSoulSand() {
        return this.soulSand;
    }
    
    public boolean isCollidesVertically() {
        return this.collidesVertically;
    }
    
    public boolean isCollidesHorizontally() {
        return this.collidesHorizontally;
    }
    
    public boolean isSnow() {
        return this.snow;
    }
    
    public boolean isOnGround() {
        return this.onGround;
    }
    
    public boolean isOnIce() {
        return this.onIce;
    }
    
    public boolean isOnNearIce() {
        return this.onNearIce;
    }
    
    public boolean isBlockAbove() {
        return this.blockAbove;
    }
    
    public boolean isStair() {
        return this.stair;
    }
    
    public boolean isSlab() {
        return this.slab;
    }
    
    public boolean isPistion() {
        return this.pistion;
    }
    
    public boolean isClimbale() {
        return this.climbale;
    }
    
    public boolean isGroundSlime() {
        return this.groundSlime;
    }
    
    public boolean isWeb() {
        return this.web;
    }
    
    public boolean isChests() {
        return this.chests;
    }
    
    public boolean isHalfblock() {
        return this.halfblock;
    }
    
    public boolean isLiquid() {
        return this.liquid;
    }
    
    public boolean isWall() {
        return this.wall;
    }
    
    public boolean isCarpet() {
        return this.carpet;
    }
    
    public boolean isStairSlabs() {
        return this.stairSlabs;
    }
    
    public boolean isSlime() {
        return this.slime;
    }
    
    public boolean isFence() {
        return this.fence;
    }
    
    public boolean isRail() {
        return this.rail;
    }
    
    public void setUser(final User user) {
        this.user = user;
    }
    
    public void setBoundingBox(final BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }
    
    public void setSoulSand(final boolean soulSand) {
        this.soulSand = soulSand;
    }
    
    public void setCollidesVertically(final boolean collidesVertically) {
        this.collidesVertically = collidesVertically;
    }
    
    public void setCollidesHorizontally(final boolean collidesHorizontally) {
        this.collidesHorizontally = collidesHorizontally;
    }
    
    public void setSnow(final boolean snow) {
        this.snow = snow;
    }
    
    public void setOnGround(final boolean onGround) {
        this.onGround = onGround;
    }
    
    public void setOnIce(final boolean onIce) {
        this.onIce = onIce;
    }
    
    public void setOnNearIce(final boolean onNearIce) {
        this.onNearIce = onNearIce;
    }
    
    public void setBlockAbove(final boolean blockAbove) {
        this.blockAbove = blockAbove;
    }
    
    public void setStair(final boolean stair) {
        this.stair = stair;
    }
    
    public void setSlab(final boolean slab) {
        this.slab = slab;
    }
    
    public void setPistion(final boolean pistion) {
        this.pistion = pistion;
    }
    
    public void setClimbale(final boolean climbale) {
        this.climbale = climbale;
    }
    
    public void setGroundSlime(final boolean groundSlime) {
        this.groundSlime = groundSlime;
    }
    
    public void setWeb(final boolean web) {
        this.web = web;
    }
    
    public void setChests(final boolean chests) {
        this.chests = chests;
    }
    
    public void setHalfblock(final boolean halfblock) {
        this.halfblock = halfblock;
    }
    
    public void setLiquid(final boolean liquid) {
        this.liquid = liquid;
    }
    
    public void setWall(final boolean wall) {
        this.wall = wall;
    }
    
    public void setCarpet(final boolean carpet) {
        this.carpet = carpet;
    }
    
    public void setStairSlabs(final boolean stairSlabs) {
        this.stairSlabs = stairSlabs;
    }
    
    public void setSlime(final boolean slime) {
        this.slime = slime;
    }
    
    public void setFence(final boolean fence) {
        this.fence = fence;
    }
    
    public void setRail(final boolean rail) {
        this.rail = rail;
    }
}
