// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils;

import org.bukkit.potion.PotionEffect;
import us.overflow.base.user.User;
import org.bukkit.entity.Entity;
import us.overflow.utils.blockbox.ReflectionUtil;
import java.util.function.Supplier;
import java.util.Comparator;
import java.util.function.Function;
import java.util.Collection;
import us.overflow.utils.blockbox.BoundingBox;
import us.overflow.Overflow;
import org.bukkit.entity.LivingEntity;
import java.math.RoundingMode;
import java.math.BigDecimal;
import org.bukkit.Location;
import us.overflow.utils.location.CustomLocation;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.Player;
import java.util.HashMap;
import java.util.Random;
import org.bukkit.util.Vector;
import org.bukkit.entity.EntityType;
import java.util.Map;
import java.text.DecimalFormat;

public class MathUtil
{
    private static DecimalFormat decimalFormat;
    public static String xkdjf39R999FI;
    public static Map<EntityType, Vector> entityDimensions;
    private Random random;
    public static boolean hasChecked;
    public static boolean error;
    
    public MathUtil() {
        (MathUtil.entityDimensions = new HashMap()).put(EntityType.WOLF, new Vector(0.31, 0.8, 0.31));
        MathUtil.entityDimensions.put(EntityType.SHEEP, new Vector(0.45, 1.3, 0.45));
        MathUtil.entityDimensions.put(EntityType.COW, new Vector(0.45, 1.3, 0.45));
        MathUtil.entityDimensions.put(EntityType.PIG, new Vector(0.45, 0.9, 0.45));
        MathUtil.entityDimensions.put(EntityType.MUSHROOM_COW, new Vector(0.45, 1.3, 0.45));
        MathUtil.entityDimensions.put(EntityType.WITCH, new Vector(0.31, 1.95, 0.31));
        MathUtil.entityDimensions.put(EntityType.BLAZE, new Vector(0.31, 1.8, 0.31));
        MathUtil.entityDimensions.put(EntityType.PLAYER, new Vector(0.3, 1.8, 0.3));
        MathUtil.entityDimensions.put(EntityType.VILLAGER, new Vector(0.31, 1.8, 0.31));
        MathUtil.entityDimensions.put(EntityType.CREEPER, new Vector(0.31, 1.8, 0.31));
        MathUtil.entityDimensions.put(EntityType.GIANT, new Vector(1.8, 10.8, 1.8));
        MathUtil.entityDimensions.put(EntityType.SKELETON, new Vector(0.31, 1.8, 0.31));
        MathUtil.entityDimensions.put(EntityType.ZOMBIE, new Vector(0.31, 1.8, 0.31));
        MathUtil.entityDimensions.put(EntityType.SNOWMAN, new Vector(0.35, 1.9, 0.35));
        MathUtil.entityDimensions.put(EntityType.HORSE, new Vector(0.7, 1.6, 0.7));
        MathUtil.entityDimensions.put(EntityType.ENDER_DRAGON, new Vector(1.5, 1.5, 1.5));
        MathUtil.entityDimensions.put(EntityType.ENDERMAN, new Vector(0.31, 2.9, 0.31));
        MathUtil.entityDimensions.put(EntityType.CHICKEN, new Vector(0.2, 0.7, 0.2));
        MathUtil.entityDimensions.put(EntityType.OCELOT, new Vector(0.31, 0.7, 0.31));
        MathUtil.entityDimensions.put(EntityType.SPIDER, new Vector(0.7, 0.9, 0.7));
        MathUtil.entityDimensions.put(EntityType.WITHER, new Vector(0.45, 3.5, 0.45));
        MathUtil.entityDimensions.put(EntityType.IRON_GOLEM, new Vector(0.7, 2.9, 0.7));
        MathUtil.entityDimensions.put(EntityType.GHAST, new Vector(2, 4, 2));
    }
    
    public static float getDistanceBetweenAngles(final float angle1, final float angle2) {
        float distance = Math.abs(angle1 - angle2) % 360.0f;
        if (distance > 180.0f) {
            distance = 360.0f - distance;
        }
        return distance;
    }
    
    public static float getBaseSpeed(final Player player) {
        return 0.25f + getPotionEffectLevel(player, PotionEffectType.SPEED) * 0.062f + (player.getWalkSpeed() - 0.2f) * 1.6f;
    }
    
    public static double normalize(final double val, final double min, final double max) {
        if (max < min) {
            return 0.0;
        }
        return (val - min) / (max - min);
    }
    
    public static double getSkewness(final Iterable<? extends Number> iterable) {
        double sum = 0.0;
        int buffer = 0;
        final List<Double> numberList = new ArrayList<Double>();
        for (final Number num : iterable) {
            sum += num.doubleValue();
            ++buffer;
            numberList.add(num.doubleValue());
        }
        Collections.sort(numberList);
        final double mean = sum / buffer;
        final double median = (buffer % 2 != 0) ? numberList.get(buffer / 2) : ((numberList.get((buffer - 1) / 2) + numberList.get(buffer / 2)) / 2.0);
        return 3.0 * (mean - median) / deviationSquared((Iterable)iterable);
    }
    
    public static double getKurtosis(final Iterable<? extends Number> iterable) {
        double n = 0.0;
        double n2 = 0.0;
        for (final Number number : iterable) {
            n += number.doubleValue();
            ++n2;
        }
        if (n2 < 3.0) {
            return 0.0;
        }
        final double n3 = n2 * (n2 + 1.0) / ((n2 - 1.0) * (n2 - 2.0) * (n2 - 3.0));
        final double n4 = 3.0 * Math.pow(n2 - 1.0, 2.0) / ((n2 - 2.0) * (n2 - 3.0));
        final double n5 = n / n2;
        double n6 = 0.0;
        double n7 = 0.0;
        for (final Number n8 : iterable) {
            n6 += Math.pow(n5 - n8.doubleValue(), 2.0);
            n7 += Math.pow(n5 - n8.doubleValue(), 4.0);
        }
        return n3 * (n7 / Math.pow(n6 / n2, 2.0)) - n4;
    }
    
    public static double deviationSquared(final Iterable<? extends Number> iterable) {
        double n = 0.0;
        int n2 = 0;
        for (final Number anIterable : iterable) {
            n += anIterable.doubleValue();
            ++n2;
        }
        final double n3 = n / n2;
        double n4 = 0.0;
        for (final Number anIterable2 : iterable) {
            n4 += Math.pow(anIterable2.doubleValue() - n3, 2.0);
        }
        return (n4 == 0.0) ? 0.0 : (n4 / (n2 - 1));
    }
    
    public static int getRandomInteger(final int maximum, final int minimum) {
        return (int)(Math.random() * (maximum - minimum)) + minimum;
    }
    
    public static double get3DDistance(final CustomLocation from, final Location to) {
        final double deltaX = to.getX() - from.getX();
        final double deltaZ = to.getZ() - from.getZ();
        final double deltaY = Math.abs(to.getY() - from.getY());
        return Math.sqrt(deltaX * deltaX + deltaZ * deltaZ) + deltaY;
    }
    
    public static double sigmoid(final double x) {
        return 1.0 / (1.0 + Math.exp(-x));
    }
    
    public static void setData(final String gay) {
        MathUtil.xkdjf39R999FI = gay;
    }
    
    public static double round(final double value, final int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd2 = new BigDecimal(value);
        bd2 = bd2.setScale(places, RoundingMode.HALF_UP);
        return bd2.doubleValue();
    }
    
    public static double round(final double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(0, RoundingMode.UP);
        return bd.doubleValue();
    }
    
    public static double trim(final int degree, final double d) {
        String format = "#.#";
        for (int i = 1; i < degree; ++i) {
            format = String.valueOf(format) + "#";
        }
        final DecimalFormat twoDForm = new DecimalFormat(format);
        return Double.parseDouble(twoDForm.format(d).replaceAll(",", "."));
    }
    
    public static float trimFloat(final int degree, final float d) {
        String format = "#.#";
        for (int i = 1; i < degree; ++i) {
            format = String.valueOf(format) + "#";
        }
        final DecimalFormat twoDForm = new DecimalFormat(format);
        return Float.parseFloat(twoDForm.format(d).replaceAll(",", "."));
    }
    
    public static boolean onGround(final Player player) {
        final BoundingBox playerBox = getEntityBoundingBox((LivingEntity)player);
        final List<BoundingBox> box = (List<BoundingBox>)Overflow.getInstance().getBlockBoxManager().getBlockBox().getCollidingBoxes(player.getWorld(), playerBox.grow(0.5f, 0.1f, 0.5f).subtract(0.0f, 0.5f, 0.0f, 0.0f, 0.0f, 0.0f));
        for (final BoundingBox box2 : box) {
            if (box2.getMaximum().getY() <= playerBox.getMinimum().getY() + 0.3 && box2.intersectsWithBox((Object)playerBox.subtract(0.0f, 1.0E-4f, 0.0f, 0.0f, 1.4f, 0.0f))) {
                return true;
            }
        }
        return false;
    }
    
    public static <T extends Number> T getMode(final Collection<T> collect) {
        final Map<T, Integer> repeated = new HashMap<T, Integer>();
        collect.forEach(MathUtil::lambda$getMode$0);
        return (T)repeated.keySet().stream().map((Function<? super Object, ? extends Tuple>)MathUtil::lambda$getMode$1).max(Comparator.comparing((Function<? super Tuple, ?>)MathUtil::lambda$getMode$2, Comparator.naturalOrder())).orElseThrow((Supplier<? extends Throwable>)NullPointerException::new).one;
    }
    
    public static BoundingBox getEntityBoundingBox(final LivingEntity entity) {
        if (MathUtil.entityDimensions.containsKey(entity.getType())) {
            final Vector entityVector = MathUtil.entityDimensions.get(entity.getType());
            final float minX = (float)Math.min(-entityVector.getX() + entity.getLocation().getX(), entityVector.getX() + entity.getLocation().getX());
            final float minY = (float)Math.min(entity.getLocation().getY(), entityVector.getY() + entity.getLocation().getY());
            final float minZ = (float)Math.min(-entityVector.getZ() + entity.getLocation().getZ(), entityVector.getZ() + entity.getLocation().getZ());
            final float maxX = (float)Math.max(-entityVector.getX() + entity.getLocation().getX(), entityVector.getX() + entity.getLocation().getX());
            final float maxY = (float)Math.max(entity.getLocation().getY(), entityVector.getY() + entity.getLocation().getY());
            final float maxZ = (float)Math.max(-entityVector.getZ() + entity.getLocation().getZ(), entityVector.getZ() + entity.getLocation().getZ());
            return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
        }
        return ReflectionUtil.toBoundingBox(ReflectionUtil.getBoundingBox((Entity)entity));
    }
    
    public static int getLength(final double s) {
        return String.valueOf(s).length();
    }
    
    public static boolean isValueParsable(final double value) {
        try {
            Double.parseDouble(String.valueOf(value));
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static int getRandom(final int min, final int max) {
        final Random rand = new Random();
        final int randomNum = rand.nextInt(max - min + 1) + min;
        return randomNum;
    }
    
    public static boolean isValueVrySmall(final double value) {
        return String.valueOf(value).contains("E");
    }
    
    public static double calculateAverage(final List<Double> marks) {
        Double sum = 0.0;
        if (!marks.isEmpty()) {
            for (final Double mark : marks) {
                sum += mark;
            }
            return sum / marks.size();
        }
        return sum;
    }
    
    public static int floor(final double var0) {
        final int var = (int)var0;
        return (var0 < var) ? (var - 1) : var;
    }
    
    public static double hypot(final double... values) {
        return Math.sqrt(hypotSquared(values));
    }
    
    public static double hypotSquared(final double... values) {
        double total = 0.0;
        final double[] var3 = values;
        for (int var4 = values.length, var5 = 0; var5 < var4; ++var5) {
            final double value = var3[var5];
            total += Math.pow(value, 2.0);
        }
        return total;
    }
    
    public static BoundingBox getHitbox(final LivingEntity entity, final CustomLocation l, final User user) {
        final float d = (float)user.deltaXZ;
        final Vector dimensions = MathUtil.entityDimensions.getOrDefault(entity.getType(), new Vector(0.4, 2.0, 0.4));
        return new BoundingBox(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f).add((float)l.getX(), (float)l.getY(), (float)l.getZ()).grow((float)dimensions.getX(), (float)dimensions.getY(), (float)dimensions.getZ()).grow(0.1f, 0.1f, 0.1f).grow(((entity.getVelocity().getY() > 0.0) ? 0.15f : 0.0f) + d / 1.25f, 0.0f, ((entity.getVelocity().getY() > 0.0) ? 0.15f : 0.0f) + d / 1.25f);
    }
    
    public static float getPotionEffectLevel(final Player player, final PotionEffectType pet) {
        for (final PotionEffect pe : player.getActivePotionEffects()) {
            if (!pe.getType().getName().equals(pet.getName())) {
                continue;
            }
            return (float)(pe.getAmplifier() + 1);
        }
        return 0.0f;
    }
    
    public static double decFormated(final double d) {
        return Double.parseDouble(MathUtil.decimalFormat.format(d));
    }
    
    public static long gcd(final long current, final long previous) {
        try {
            try {
                return (previous <= 16384L) ? current : gcd(previous, current % previous);
            }
            catch (StackOverflowError ignored2) {
                return 100000000000L;
            }
        }
        catch (Exception ignored3) {
            return 100000000000L;
        }
    }
    
    public static int getPotionLevel(final Player player, final PotionEffectType type) {
        for (final PotionEffect potionEffect : player.getActivePotionEffects()) {
            if (potionEffect.getType().getId() != type.getId()) {
                continue;
            }
            return potionEffect.getAmplifier() + 1;
        }
        return 0;
    }
    
    public static boolean nextToWall(final Player p) {
        return false;
    }
    
    public static boolean looked(final Location from, final Location to) {
        return (from.getYaw() != 0.0f && to.getYaw() != 0.0f) || (from.getPitch() != 0.0f && from.getPitch() != 0.0f);
    }
    
    public static double vectorDistance(final Location from, final Location to) {
        final Vector first = from.toVector();
        final Vector second = to.toVector();
        first.setY(0);
        second.setY(0);
        return first.subtract(second).length();
    }
    
    public static float getDelta(final float one, final float two) {
        return Math.abs(one - two);
    }
    
    public static double preciseRound(final double value, final int precision) {
        final int scale = (int)Math.pow(10.0, precision);
        return Math.round(value * scale) / (double)scale;
    }
    
    static {
        MathUtil.decimalFormat = new DecimalFormat("##.##");
        MathUtil.xkdjf39R999FI = "NULL";
    }
}
