// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox;

import java.util.function.Consumer;
import org.bukkit.block.Block;
import java.util.Collection;
import org.bukkit.Material;
import us.overflow.utils.block.BlockUtil;
import us.overflow.Overflow;
import org.bukkit.Location;
import us.overflow.utils.MathUtil;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class BoundingBox
{
    public float minX;
    public float minY;
    public float minZ;
    public float maxX;
    public float maxY;
    public float maxZ;
    
    public BoundingBox(final float minX, final float minY, final float minZ, final float maxX, final float maxY, final float maxZ) {
        this.minX = minX;
        this.minY = minY;
        this.minZ = minZ;
        this.maxX = maxX;
        this.maxY = maxY;
        this.maxZ = maxZ;
    }
    
    public BoundingBox(final Vector min, final Vector max) {
        this.minX = (float)Math.min(min.getX(), max.getX());
        this.minY = (float)Math.min(min.getY(), max.getY());
        this.minZ = (float)Math.min(min.getZ(), max.getZ());
        this.maxX = (float)Math.max(min.getX(), max.getX());
        this.maxY = (float)Math.max(min.getY(), max.getY());
        this.maxZ = (float)Math.max(min.getZ(), max.getZ());
    }
    
    public BoundingBox add(final float x, final float y, final float z) {
        final float newMinX = this.minX + x;
        final float newMaxX = this.maxX + x;
        final float newMinY = this.minY + y;
        final float newMaxY = this.maxY + y;
        final float newMinZ = this.minZ + z;
        final float newMaxZ = this.maxZ + z;
        return new BoundingBox(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }
    
    public BoundingBox add(final Vector vector) {
        final float x = (float)vector.getX();
        final float y = (float)vector.getY();
        final float z = (float)vector.getZ();
        final float newMinX = this.minX + x;
        final float newMaxX = this.maxX + x;
        final float newMinY = this.minY + y;
        final float newMaxY = this.maxY + y;
        final float newMinZ = this.minZ + z;
        final float newMaxZ = this.maxZ + z;
        return new BoundingBox(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }
    
    public BoundingBox grow(final float x, final float y, final float z) {
        final float newMinX = this.minX - x;
        final float newMaxX = this.maxX + x;
        final float newMinY = this.minY - y;
        final float newMaxY = this.maxY + y;
        final float newMinZ = this.minZ - z;
        final float newMaxZ = this.maxZ + z;
        return new BoundingBox(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }
    
    public BoundingBox shrink(final float x, final float y, final float z) {
        final float newMinX = this.minX + x;
        final float newMaxX = this.maxX - x;
        final float newMinY = this.minY + y;
        final float newMaxY = this.maxY - y;
        final float newMinZ = this.minZ + z;
        final float newMaxZ = this.maxZ - z;
        return new BoundingBox(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
    }
    
    public BoundingBox add(final float minX, final float minY, final float minZ, final float maxX, final float maxY, final float maxZ) {
        return new BoundingBox(this.minX + minX, this.minY + minY, this.minZ + minZ, this.maxX + maxX, this.maxY + maxY, this.maxZ + maxZ);
    }
    
    public BoundingBox subtract(final float minX, final float minY, final float minZ, final float maxX, final float maxY, final float maxZ) {
        return new BoundingBox(this.minX - minX, this.minY - minY, this.minZ - minZ, this.maxX - maxX, this.maxY - maxY, this.maxZ - maxZ);
    }
    
    public boolean intersectsWithBox(final Vector vector) {
        return vector.getX() > this.minX && vector.getX() < this.maxX && vector.getY() > this.minY && vector.getY() < this.maxY && vector.getZ() > this.minZ && vector.getZ() < this.maxZ;
    }
    
    public List<BoundingBox> getCollidingBlockBoxes(final Player player) {
        final List<BoundingBox> toReturn = new ArrayList<BoundingBox>();
        final int minX = MathUtil.floor((double)this.minX);
        final int maxX = MathUtil.floor((double)(this.maxX + 1.0f));
        final int minY = MathUtil.floor((double)this.minY);
        final int maxY = MathUtil.floor((double)(this.maxY + 1.0f));
        final int minZ = MathUtil.floor((double)this.minZ);
        final int maxZ = MathUtil.floor((double)(this.maxZ + 1.0f));
        for (int x = minX; x < maxX; ++x) {
            for (int z = minZ; z < maxZ; ++z) {
                for (int y = minY - 1; y < maxY; ++y) {
                    final Location loc = new Location(player.getWorld(), (double)x, (double)y, (double)z);
                    if (Overflow.getInstance().getBlockBoxManager().getBlockBox().isChunkLoaded(loc)) {
                        final Block block = BlockUtil.getBlock(loc);
                        if (!block.getType().equals((Object)Material.AIR)) {
                            toReturn.addAll(Overflow.getInstance().getBoxes().getBoundingBox(block));
                        }
                    }
                }
            }
        }
        return toReturn;
    }
    
    public Vector getMinimum() {
        return new Vector(this.minX, this.minY, this.minZ);
    }
    
    public Vector getMaximum() {
        return new Vector(this.maxX, this.maxY, this.maxZ);
    }
    
    public List<Block> getCollidingBlocks(final Player player) {
        final List<Block> toReturn = new ArrayList<Block>();
        this.getCollidingBlockBoxes(player).forEach(BoundingBox::lambda$getCollidingBlocks$0);
        return toReturn;
    }
    
    public List<Block> getAllBlocks(final Player player) {
        final Location min = new Location(player.getWorld(), (double)MathUtil.floor((double)this.minX), (double)MathUtil.floor((double)this.minY), (double)MathUtil.floor((double)this.minZ));
        final Location max = new Location(player.getWorld(), (double)MathUtil.floor((double)this.maxX), (double)MathUtil.floor((double)this.maxY), (double)MathUtil.floor((double)this.maxZ));
        final List<Block> all = new ArrayList<Block>();
        for (float x = (float)min.getX(); x < max.getX(); ++x) {
            for (float y = (float)min.getY(); y < max.getY(); ++y) {
                for (float z = (float)min.getZ(); z < max.getZ(); ++z) {
                    final Block block = BlockUtil.getBlock(new Location(player.getWorld(), (double)x, (double)y, (double)z));
                    if (!block.getType().equals((Object)Material.AIR)) {
                        all.add(block);
                    }
                }
            }
        }
        return all;
    }
    
    public boolean inBlock(final Player player) {
        return this.getCollidingBlocks(player).size() > 0;
    }
    
    public boolean intersectsWithBox(final Object other) {
        if (other instanceof BoundingBox) {
            final BoundingBox otherBox = (BoundingBox)other;
            return otherBox.maxX > this.minX && otherBox.minX < this.maxX && otherBox.maxY > this.minY && otherBox.minY < this.maxY && otherBox.maxZ > this.minZ && otherBox.minZ < this.maxZ;
        }
        final float otherMinX = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "a"), other);
        final float otherMinY = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "b"), other);
        final float otherMinZ = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "c"), other);
        final float otherMaxX = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "d"), other);
        final float otherMaxY = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "e"), other);
        final float otherMaxZ = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "f"), other);
        return otherMaxX > this.minX && otherMinX < this.maxX && otherMaxY > this.minY && otherMinY < this.maxY && otherMaxZ > this.minZ && otherMinZ < this.maxZ;
    }
    
    public boolean collides(final Vector vector) {
        return vector.getX() >= this.minX && vector.getX() <= this.maxX && vector.getY() >= this.minY && vector.getY() <= this.maxY && vector.getZ() >= this.minZ && vector.getZ() <= this.maxZ;
    }
    
    public boolean collides(final Object other) {
        if (other instanceof BoundingBox) {
            final BoundingBox otherBox = (BoundingBox)other;
            return otherBox.maxX >= this.minX && otherBox.minX <= this.maxX && otherBox.maxY >= this.minY && otherBox.minY <= this.maxY && otherBox.maxZ >= this.minZ && otherBox.minZ <= this.maxZ;
        }
        final float otherMinX = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "a"), other);
        final float otherMinY = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "b"), other);
        final float otherMinZ = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "c"), other);
        final float otherMaxX = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "d"), other);
        final float otherMaxY = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "e"), other);
        final float otherMaxZ = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "f"), other);
        return otherMaxX >= this.minX && otherMinX <= this.maxX && otherMaxY >= this.minY && otherMinY <= this.maxY && otherMaxZ >= this.minZ && otherMinZ <= this.maxZ;
    }
    
    public boolean collidesHorizontally(final Vector vector) {
        return vector.getX() >= this.minX && vector.getX() <= this.maxX && vector.getY() > this.minY && vector.getY() < this.maxY && vector.getZ() >= this.minZ && vector.getZ() <= this.maxZ;
    }
    
    public boolean collidesHorizontally(final Object other) {
        if (other instanceof BoundingBox) {
            final BoundingBox otherBox = (BoundingBox)other;
            return otherBox.maxX >= this.minX && otherBox.minX <= this.maxX && otherBox.maxY > this.minY && otherBox.minY < this.maxY && otherBox.maxZ >= this.minZ && otherBox.minZ <= this.maxZ;
        }
        final float otherMinX = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "a"), other);
        final float otherMinY = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "b"), other);
        final float otherMinZ = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "c"), other);
        final float otherMaxX = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "d"), other);
        final float otherMaxY = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "e"), other);
        final float otherMaxZ = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "f"), other);
        return otherMaxX >= this.minX && otherMinX <= this.maxX && otherMaxY > this.minY && otherMinY < this.maxY && otherMaxZ >= this.minZ && otherMinZ <= this.maxZ;
    }
    
    public boolean collidesVertically(final Vector vector) {
        return vector.getX() > this.minX && vector.getX() < this.maxX && vector.getY() >= this.minY && vector.getY() <= this.maxY && vector.getZ() > this.minZ && vector.getZ() < this.maxZ;
    }
    
    public boolean collidesVertically(final Object other) {
        if (other instanceof BoundingBox) {
            final BoundingBox otherBox = (BoundingBox)other;
            return otherBox.maxX > this.minX && otherBox.minX < this.maxX && otherBox.maxY >= this.minY && otherBox.minY <= this.maxY && otherBox.maxZ > this.minZ && otherBox.minZ < this.maxZ;
        }
        final float otherMinX = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "a"), other);
        final float otherMinY = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "b"), other);
        final float otherMinZ = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "c"), other);
        final float otherMaxX = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "d"), other);
        final float otherMaxY = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "e"), other);
        final float otherMaxZ = (float)(double)ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName((Class)other.getClass(), "f"), other);
        return otherMaxX > this.minX && otherMinX < this.maxX && otherMaxY >= this.minY && otherMinY <= this.maxY && otherMaxZ > this.minZ && otherMinZ < this.maxZ;
    }
    
    public Object toAxisAlignedBB() {
        return ReflectionUtil.newAxisAlignedBB((double)this.minX, (double)this.minY, (double)this.minZ, (double)this.maxX, (double)this.maxY, (double)this.maxZ);
    }
    
    @Override
    public String toString() {
        return "[" + this.minX + ", " + this.minY + ", " + this.minZ + ", " + this.maxX + ", " + this.maxY + ", " + this.maxZ + "]";
    }
}
