// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.impl;

import java.util.Collections;
import java.util.List;
import org.bukkit.block.Block;
import us.overflow.utils.blockbox.BoundingBox;
import org.bukkit.Material;

public class RegularBox extends BlockBox
{
    public RegularBox(final Material material, final BoundingBox original) {
        super(material, original);
    }
    
    @Override
    List<BoundingBox> getBox(final Block block) {
        return Collections.singletonList(this.getOriginal().add(block.getLocation().toVector()));
    }
}
