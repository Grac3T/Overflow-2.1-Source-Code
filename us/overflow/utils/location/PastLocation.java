// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.location;

import org.bukkit.Location;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.List;

public class PastLocation
{
    private List<CustomLocation> previousLocations;
    
    public PastLocation() {
        this.previousLocations = new CopyOnWriteArrayList<CustomLocation>();
    }
    
    public CustomLocation getPreviousLocation(final long time) {
        return this.previousLocations.stream().min(Comparator.comparingLong(loc -> Math.abs(loc.getTimeStamp() - (System.currentTimeMillis() - time)))).orElse(this.previousLocations.get(this.previousLocations.size() - 1));
    }
    
    public List<CustomLocation> getEstimatedLocation(final long time, final long delta) {
        final List<CustomLocation> locs = new ArrayList<CustomLocation>();
        this.previousLocations.stream().sorted(Comparator.comparingLong(loc -> Math.abs(loc.getTimeStamp() - (System.currentTimeMillis() - time)))).filter(loc -> Math.abs(loc.getTimeStamp() - (System.currentTimeMillis() - time)) < delta).forEach(locs::add);
        return locs;
    }
    
    public void clearLocations() {
        this.previousLocations.clear();
    }
    
    public void addLocation(final Location location) {
        if (this.previousLocations.size() >= 20) {
            this.previousLocations.remove(0);
        }
        this.previousLocations.add(new CustomLocation(location));
    }
    
    public void addLocation(final CustomLocation location) {
        if (this.previousLocations.size() >= 20) {
            this.previousLocations.remove(0);
        }
        this.previousLocations.add(location);
    }
    
    public List<CustomLocation> getPreviousLocations() {
        return this.previousLocations;
    }
}
