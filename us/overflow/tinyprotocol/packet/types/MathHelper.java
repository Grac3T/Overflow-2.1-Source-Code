// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.tinyprotocol.packet.types;

import java.util.UUID;
import java.util.Random;

public class MathHelper
{
    public static final float a;
    private static final float[] b;
    private static final int[] c;
    private static final double d;
    private static final double[] e;
    private static final double[] f;
    
    public static float sin(final float var0) {
        return MathHelper.b[(int)(var0 * 10430.378f) & 0xFFFF];
    }
    
    public static float cos(final float var0) {
        return MathHelper.b[(int)(var0 * 10430.378f + 16384.0f) & 0xFFFF];
    }
    
    public static float c(final float var0) {
        return (float)Math.sqrt(var0);
    }
    
    public static float sqrt(final double var0) {
        return (float)Math.sqrt(var0);
    }
    
    public static int d(final float var0) {
        final int var = (int)var0;
        return (var0 < var) ? (var - 1) : var;
    }
    
    public static int floor(final double var0) {
        final int var = (int)var0;
        return (var0 < var) ? (var - 1) : var;
    }
    
    public static long d(final double var0) {
        final long var = (long)var0;
        return (var0 < var) ? (var - 1L) : var;
    }
    
    public static float e(final float var0) {
        return (var0 >= 0.0f) ? var0 : (-var0);
    }
    
    public static int a(final int var0) {
        return (var0 >= 0) ? var0 : (-var0);
    }
    
    public static int f(final float var0) {
        final int var = (int)var0;
        return (var0 > var) ? (var + 1) : var;
    }
    
    public static int f(final double var0) {
        final int var = (int)var0;
        return (var0 > var) ? (var + 1) : var;
    }
    
    public static int clamp(final int var0, final int var1, final int var2) {
        if (var0 < var1) {
            return var1;
        }
        return (var0 > var2) ? var2 : var0;
    }
    
    public static float a(final float var0, final float var1, final float var2) {
        if (var0 < var1) {
            return var1;
        }
        return (var0 > var2) ? var2 : var0;
    }
    
    public static double a(final double var0, final double var2, final double var4) {
        if (var0 < var2) {
            return var2;
        }
        return (var0 > var4) ? var4 : var0;
    }
    
    public static double b(final double var0, final double var2, final double var4) {
        if (var4 < 0.0) {
            return var0;
        }
        return (var4 > 1.0) ? var2 : (var0 + (var2 - var0) * var4);
    }
    
    public static double a(double var0, double var2) {
        if (var0 < 0.0) {
            var0 = -var0;
        }
        if (var2 < 0.0) {
            var2 = -var2;
        }
        return (var0 > var2) ? var0 : var2;
    }
    
    public static int nextInt(final Random var0, final int var1, final int var2) {
        return (var1 >= var2) ? var1 : (var0.nextInt(var2 - var1 + 1) + var1);
    }
    
    public static float a(final Random var0, final float var1, final float var2) {
        return (var1 >= var2) ? var1 : (var0.nextFloat() * (var2 - var1) + var1);
    }
    
    public static double a(final Random var0, final double var1, final double var3) {
        return (var1 >= var3) ? var1 : (var0.nextDouble() * (var3 - var1) + var1);
    }
    
    public static double a(final long[] var0) {
        long var = 0L;
        final long[] var2 = var0;
        for (int var3 = var0.length, var4 = 0; var4 < var3; ++var4) {
            final long var5 = var2[var4];
            var += var5;
        }
        return var / (double)var0.length;
    }
    
    public static float g(float var0) {
        var0 %= 360.0f;
        if (var0 >= 180.0f) {
            var0 -= 360.0f;
        }
        if (var0 < -180.0f) {
            var0 += 360.0f;
        }
        return var0;
    }
    
    public static double g(double var0) {
        var0 %= 360.0;
        if (var0 >= 180.0) {
            var0 -= 360.0;
        }
        if (var0 < -180.0) {
            var0 += 360.0;
        }
        return var0;
    }
    
    public static int a(final String var0, final int var1) {
        try {
            return Integer.parseInt(var0);
        }
        catch (Throwable var2) {
            return var1;
        }
    }
    
    public static int a(final String var0, final int var1, final int var2) {
        return Math.max(var2, a(var0, var1));
    }
    
    public static double a(final String var0, final double var1) {
        try {
            return Double.parseDouble(var0);
        }
        catch (Throwable var2) {
            return var1;
        }
    }
    
    public static double a(final String var0, final double var1, final double var3) {
        return Math.max(var3, a(var0, var1));
    }
    
    public static int b(final int var0) {
        int var = var0 - 1;
        var |= var >> 1;
        var |= var >> 2;
        var |= var >> 4;
        var |= var >> 8;
        var |= var >> 16;
        return var + 1;
    }
    
    private static boolean d(final int var0) {
        return var0 != 0 && (var0 & var0 - 1) == 0x0;
    }
    
    private static int e(int var0) {
        var0 = (d(var0) ? var0 : b(var0));
        return MathHelper.c[(int)(var0 * 125613361L >> 27) & 0x1F];
    }
    
    public static int c(final int var0) {
        return e(var0) - (d(var0) ? 0 : 1);
    }
    
    public static int c(final int var0, int var1) {
        if (var1 == 0) {
            return 0;
        }
        if (var0 == 0) {
            return var1;
        }
        if (var0 < 0) {
            var1 *= -1;
        }
        final int var2 = var0 % var1;
        return (var2 == 0) ? var0 : (var0 + var1 - var2);
    }
    
    public static UUID a(final Random var0) {
        final long var = (var0.nextLong() & 0xFFFFFFFFFFFF0FFFL) | 0x4000L;
        final long var2 = (var0.nextLong() & 0x3FFFFFFFFFFFFFFFL) | Long.MIN_VALUE;
        return new UUID(var, var2);
    }
    
    public static double c(final double var0, final double var2, final double var4) {
        return (var0 - var2) / (var4 - var2);
    }
    
    public static double b(double var0, double var2) {
        final double var3 = var2 * var2 + var0 * var0;
        if (Double.isNaN(var3)) {
            return Double.NaN;
        }
        final boolean var4 = var0 < 0.0;
        if (var4) {
            var0 = -var0;
        }
        final boolean var5 = var2 < 0.0;
        if (var5) {
            var2 = -var2;
        }
        final boolean var6 = var0 > var2;
        if (var6) {
            final double var7 = var2;
            var2 = var0;
            var0 = var7;
        }
        final double var7 = i(var3);
        var2 *= var7;
        var0 *= var7;
        final double var8 = MathHelper.d + var0;
        final int var9 = (int)Double.doubleToRawLongBits(var8);
        final double var10 = MathHelper.e[var9];
        final double var11 = MathHelper.f[var9];
        final double var12 = var8 - MathHelper.d;
        final double var13 = var0 * var11 - var2 * var12;
        final double var14 = (6.0 + var13 * var13) * var13 * 0.16666666666666666;
        double var15 = var10 + var14;
        if (var6) {
            var15 = 1.5707963267948966 - var15;
        }
        if (var5) {
            var15 = 3.141592653589793 - var15;
        }
        if (var4) {
            var15 = -var15;
        }
        return var15;
    }
    
    public static double i(double var0) {
        final double var = 0.5 * var0;
        long var2 = Double.doubleToRawLongBits(var0);
        var2 = 6910469410427058090L - (var2 >> 1);
        var0 = Double.longBitsToDouble(var2);
        var0 *= 1.5 - var * var0 * var0;
        return var0;
    }
    
    static {
        MathHelper.a = c(2.0f);
        MathHelper.b = new float[65536];
        for (int var0 = 0; var0 < 65536; ++var0) {
            MathHelper.b[var0] = (float)Math.sin(var0 * 3.141592653589793 * 2.0 / 65536.0);
        }
        MathHelper.c = new int[] { 0, 1, 28, 2, 29, 14, 24, 3, 30, 22, 20, 15, 25, 17, 4, 8, 31, 27, 13, 23, 21, 19, 16, 7, 26, 12, 18, 6, 11, 5, 10, 9 };
        MathHelper.d = Double.longBitsToDouble(4805340802404319232L);
        MathHelper.e = new double[257];
        MathHelper.f = new double[257];
        for (int var0 = 0; var0 < 257; ++var0) {
            final double var2 = var0 / 256.0;
            final double var3 = Math.asin(var2);
            MathHelper.f[var0] = Math.cos(var3);
            MathHelper.e[var0] = var3;
        }
    }
}
