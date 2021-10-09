// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.impl;

import java.util.List;
import org.bukkit.block.Block;
import us.overflow.utils.blockbox.BoundingBox;
import org.bukkit.Material;

public abstract class BlockBox
{
    private Material material;
    private BoundingBox original;
    
    BlockBox(final Material material, final BoundingBox original) {
        this.material = material;
        this.original = original;
    }
    
    abstract List<BoundingBox> getBox(final Block p0);
    
    public Material getMaterial() {
        return this.material;
    }
    
    public BoundingBox getOriginal() {
        return this.original;
    }
    
    public void setMaterial(final Material material) {
        this.material = material;
    }
    
    public void setOriginal(final BoundingBox original) {
        this.original = original;
    }
}
