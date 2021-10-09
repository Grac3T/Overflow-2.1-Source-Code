// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.blockbox;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.Location;
import java.util.List;
import org.bukkit.World;

public interface BlockBox
{
    List<BoundingBox> getCollidingBoxes(final World p0, final BoundingBox p1);
    
    List<BoundingBox> getSpecificBox(final Location p0);
    
    boolean isChunkLoaded(final Location p0);
    
    boolean isUsingItem(final Player p0);
    
    boolean isRiptiding(final LivingEntity p0);
    
    float getMovementFactor(final Player p0);
    
    @Deprecated
    int getTrackerId(final Player p0);
    
    float getAiSpeed(final Player p0);
}
