// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox.impl;

import java.util.Arrays;
import org.bukkit.material.PistonExtensionMaterial;
import java.util.Collections;
import org.bukkit.material.PistonBaseMaterial;
import java.util.List;
import org.bukkit.block.Block;
import us.overflow.utils.blockbox.BoundingBox;
import org.bukkit.Material;

public class PistonBox extends BlockBox
{
    public PistonBox(final Material material) {
        super(material, new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f));
    }
    
    @Override
    List<BoundingBox> getBox(final Block block) {
        Label_0316: {
            switch (PistonBox$1.$SwitchMap$org$bukkit$Material[this.getMaterial().ordinal()]) {
                case 1:
                case 2: {
                    final PistonBaseMaterial piston = (PistonBaseMaterial)block.getType().getNewData(block.getData());
                    if (!piston.isPowered()) {
                        return Collections.singletonList(this.getOriginal().add(block.getLocation().toVector()));
                    }
                    switch (PistonBox$1.$SwitchMap$org$bukkit$block$BlockFace[piston.getFacing().ordinal()]) {
                        case 1: {
                            return Collections.singletonList(new BoundingBox(0.0f, 0.25f, 0.0f, 1.0f, 1.0f, 1.0f).add(block.getLocation().toVector()));
                        }
                        case 2: {
                            return Collections.singletonList(new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.75f, 1.0f).add(block.getLocation().toVector()));
                        }
                        case 3: {
                            return Collections.singletonList(new BoundingBox(0.0f, 0.0f, 0.25f, 1.0f, 1.0f, 1.0f).add(block.getLocation().toVector()));
                        }
                        case 4: {
                            return Collections.singletonList(new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.75f).add(block.getLocation().toVector()));
                        }
                        case 5: {
                            return Collections.singletonList(new BoundingBox(0.25f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f).add(block.getLocation().toVector()));
                        }
                        case 6: {
                            return Collections.singletonList(new BoundingBox(0.0f, 0.0f, 0.0f, 0.75f, 1.0f, 1.0f).add(block.getLocation().toVector()));
                        }
                        default: {
                            break Label_0316;
                        }
                    }
                    break;
                }
                case 3:
                case 4: {
                    final PistonExtensionMaterial piston2 = (PistonExtensionMaterial)block.getType().getNewData(block.getData());
                    switch (PistonBox$1.$SwitchMap$org$bukkit$block$BlockFace[piston2.getFacing().ordinal()]) {
                        case 1: {
                            return Arrays.asList(new BoundingBox(0.375f, 0.25f, 0.375f, 0.625f, 1.0f, 0.625f).add(block.getLocation().toVector()), new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 0.25f, 1.0f).add(block.getLocation().toVector()));
                        }
                        case 2: {
                            return Arrays.asList(new BoundingBox(0.375f, 0.0f, 0.375f, 0.625f, 0.75f, 0.625f).add(block.getLocation().toVector()), new BoundingBox(0.0f, 0.75f, 0.0f, 1.0f, 1.0f, 1.0f).add(block.getLocation().toVector()));
                        }
                        case 3: {
                            return Arrays.asList(new BoundingBox(0.25f, 0.375f, 0.25f, 0.75f, 0.625f, 1.0f).add(block.getLocation().toVector()), new BoundingBox(0.0f, 0.0f, 0.0f, 1.0f, 1.0f, 0.25f).add(block.getLocation().toVector()));
                        }
                        case 4: {
                            return Arrays.asList(new BoundingBox(0.25f, 0.375f, 0.0f, 0.75f, 0.625f, 0.75f).add(block.getLocation().toVector()), new BoundingBox(0.0f, 0.0f, 0.75f, 1.0f, 1.0f, 1.0f).add(block.getLocation().toVector()));
                        }
                        case 5: {
                            return Arrays.asList(new BoundingBox(0.375f, 0.25f, 0.25f, 0.625f, 0.75f, 1.0f).add(block.getLocation().toVector()), new BoundingBox(0.0f, 0.0f, 0.0f, 0.25f, 1.0f, 1.0f).add(block.getLocation().toVector()));
                        }
                        case 6: {
                            return Arrays.asList(new BoundingBox(0.0f, 0.375f, 0.25f, 0.75f, 0.625f, 0.75f).add(block.getLocation().toVector()), new BoundingBox(0.75f, 0.0f, 0.0f, 1.0f, 1.0f, 1.0f).add(block.getLocation().toVector()));
                        }
                        default: {
                            break Label_0316;
                        }
                    }
                    break;
                }
            }
        }
        return Collections.singletonList(this.getOriginal());
    }
}
