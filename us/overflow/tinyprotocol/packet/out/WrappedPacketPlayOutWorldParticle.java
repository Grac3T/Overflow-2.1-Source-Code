// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.out;

import java.lang.reflect.InvocationTargetException;
import org.bukkit.Particle;
import us.overflow.utils.blockbox.ReflectionUtil;
import us.overflow.tinyprotocol.api.ProtocolVersion;
import org.bukkit.entity.Player;
import us.overflow.tinyprotocol.packet.types.WrappedEnumParticle;

public class WrappedPacketPlayOutWorldParticle
{
    private WrappedEnumParticle type;
    private boolean j;
    private float x;
    private float y;
    private float z;
    private float xOffset;
    private float yOffset;
    private float zOffset;
    private float speed;
    private int amount;
    private int[] data;
    
    public WrappedPacketPlayOutWorldParticle(final WrappedEnumParticle type, final boolean var2, final float x, final float y, final float z, final float xOffset, final float yOffset, final float ZOffset, final float speed, final int amount, final int... data) {
        this.type = type;
        this.j = var2;
        this.x = x;
        this.y = y;
        this.z = z;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.zOffset = ZOffset;
        this.speed = speed;
        this.amount = amount;
        this.data = data;
    }
    
    public void sendPacket(final Player player) {
        Object packet = null;
        if (ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_13)) {
            try {
                ReflectionUtil.CraftPlayer.getMethod("spawnParticle", Particle.class, Double.TYPE, Double.TYPE, Double.TYPE, Integer.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Double.TYPE, Object.class).invoke(player, Particle.FLAME, this.x, this.y, this.z, this.amount, this.xOffset, this.yOffset, this.zOffset, this.speed, this.data);
            }
            catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException ex4) {
                final ReflectiveOperationException ex;
                final ReflectiveOperationException e = ex;
                e.printStackTrace();
            }
        }
        else if (ProtocolVersion.getGameVersion().isOrAbove(ProtocolVersion.V1_8)) {
            try {
                packet = ReflectionUtil.getNMSClass("PacketPlayOutWorldParticles").getConstructor(ReflectionUtil.getNMSClass("EnumParticle"), Boolean.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Integer.TYPE, int[].class).newInstance(this.type.toNMS(), this.j, this.x, this.y, this.z, this.xOffset, this.yOffset, this.zOffset, this.speed, this.amount, this.data);
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex5) {
                final ReflectiveOperationException ex2;
                final ReflectiveOperationException e2 = ex2;
                e2.printStackTrace();
            }
            final Object pCon = ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName(ReflectionUtil.getNMSClass("EntityPlayer"), "playerConnection"), ReflectionUtil.getEntityPlayer(player));
            ReflectionUtil.getMethodValue(ReflectionUtil.getMethod(ReflectionUtil.getNMSClass("PlayerConnection"), "sendPacket", new Class[] { ReflectionUtil.getNMSClass("Packet") }), pCon, new Object[] { packet });
        }
        else {
            try {
                packet = ReflectionUtil.getNMSClass("PacketPlayOutWorldParticles").getConstructor(String.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Integer.TYPE).newInstance(this.type.toString(), this.x, this.y, this.z, this.xOffset, this.yOffset, this.zOffset, this.speed, this.amount);
            }
            catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException ex6) {
                final ReflectiveOperationException ex3;
                final ReflectiveOperationException e2 = ex3;
                e2.printStackTrace();
            }
            final Object pCon = ReflectionUtil.getFieldValue(ReflectionUtil.getFieldByName(ReflectionUtil.getNMSClass("EntityPlayer"), "playerConnection"), ReflectionUtil.getEntityPlayer(player));
            ReflectionUtil.getMethodValue(ReflectionUtil.getMethod(ReflectionUtil.getNMSClass("PlayerConnection"), "sendPacket", new Class[] { ReflectionUtil.getNMSClass("Packet") }), pCon, new Object[] { packet });
        }
    }
    
    public WrappedEnumParticle getType() {
        return this.type;
    }
    
    public boolean isJ() {
        return this.j;
    }
    
    public float getX() {
        return this.x;
    }
    
    public float getY() {
        return this.y;
    }
    
    public float getZ() {
        return this.z;
    }
    
    public float getXOffset() {
        return this.xOffset;
    }
    
    public float getYOffset() {
        return this.yOffset;
    }
    
    public float getZOffset() {
        return this.zOffset;
    }
    
    public float getSpeed() {
        return this.speed;
    }
    
    public int getAmount() {
        return this.amount;
    }
    
    public int[] getData() {
        return this.data;
    }
}
