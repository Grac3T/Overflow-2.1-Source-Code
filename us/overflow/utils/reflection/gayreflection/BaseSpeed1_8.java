// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.reflection.gayreflection;

import java.util.Iterator;
import us.overflow.utils.MathUtil;
import org.bukkit.potion.PotionEffectType;
import net.minecraft.server.v1_8_R3.AttributeModifier;
import net.minecraft.server.v1_8_R3.GenericAttributes;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import net.minecraft.server.v1_8_R3.AttributeModifiable;
import us.overflow.base.user.User;

public class BaseSpeed1_8
{
    public static float getMoveSpeedWithoutSprint1_8(final User data, final boolean sprint) {
        final AttributeModifiable attribute = (AttributeModifiable)((CraftPlayer)Bukkit.getPlayer(data.getPlayer().getUniqueId())).getHandle().getAttributeInstance(GenericAttributes.MOVEMENT_SPEED);
        double value;
        final double base = value = attribute.b();
        try {
            for (final AttributeModifier modifier : attribute.a(0)) {
                value += modifier.d();
            }
            for (final AttributeModifier modifier : attribute.a(1)) {
                value += modifier.d() * base;
            }
            if (sprint) {
                value += value * 0.30000001192092896;
            }
            if (data.getPlayer().hasPotionEffect(PotionEffectType.SPEED)) {
                value += value * (MathUtil.getPotionLevel(data.getPlayer(), PotionEffectType.SPEED) * 0.20000000298023224);
            }
            if (data.getPlayer().hasPotionEffect(PotionEffectType.SLOW)) {
                value += value * (MathUtil.getPotionLevel(data.getPlayer(), PotionEffectType.SLOW) * -0.15000000596046448);
            }
        }
        catch (Throwable t) {}
        return (float)value;
    }
}
