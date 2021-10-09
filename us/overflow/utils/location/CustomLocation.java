// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.location;

import org.bukkit.util.NumberConversions;
import com.sun.istack.internal.NotNull;
import org.bukkit.util.Vector;
import org.bukkit.Location;
import org.bukkit.World;

public class CustomLocation
{
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;
    private long timeStamp;
    private World world;
    
    public CustomLocation(final double x, final double y, final double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.timeStamp = System.currentTimeMillis();
    }
    
    public CustomLocation(final double x, final double y, final double z, final float yaw, final float pitch) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.timeStamp = System.currentTimeMillis();
    }
    
    public CustomLocation(final double x, final double y, final double z, final float yaw, final float pitch, final World world) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.world = world;
        this.timeStamp = System.currentTimeMillis();
    }
    
    public CustomLocation(final double x, final double y, final double z, final float yaw, final float pitch, final long timeStamp) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.timeStamp = timeStamp;
    }
    
    public CustomLocation(final Location loc) {
        this.x = loc.getX();
        this.y = loc.getY();
        this.z = loc.getZ();
        this.yaw = loc.getYaw();
        this.pitch = loc.getPitch();
        this.timeStamp = System.currentTimeMillis();
    }
    
    public CustomLocation clone() {
        return new CustomLocation(this.x, this.y, this.z, this.yaw, this.pitch, this.timeStamp);
    }
    
    public Location toLocation(final World world) {
        return new Location(world, this.x, this.y, this.z, this.yaw, this.pitch);
    }
    
    public Vector toVector() {
        return new Vector(this.x, this.y, this.z);
    }
    
    public double getX() {
        return this.x;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public double getY() {
        return this.y;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public float getYaw() {
        return this.yaw;
    }
    
    public void setYaw(final float yaw) {
        this.yaw = yaw;
    }
    
    public float getPitch() {
        return this.pitch;
    }
    
    public void setPitch(final float pitch) {
        this.pitch = pitch;
    }
    
    public long getTimeStamp() {
        return this.timeStamp;
    }
    
    public void setTimeStamp(final long timeStamp) {
        this.timeStamp = timeStamp;
    }
    
    public double distance(@NotNull final CustomLocation o) {
        return Math.sqrt(this.distanceSquared(o));
    }
    
    public double distanceSquared(@NotNull final CustomLocation o) {
        if (o.world != null && this.world != null && o.world == this.world) {
            return NumberConversions.square(this.x - o.getX()) + NumberConversions.square(this.y - o.getY()) + NumberConversions.square(this.z - o.getZ());
        }
        return 0.0;
    }
}
