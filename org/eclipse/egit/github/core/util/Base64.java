// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.util;

import java.util.Arrays;
import java.text.MessageFormat;
import java.io.UnsupportedEncodingException;

class Base64
{
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_DEC = -1;
    private static final byte WHITE_SPACE_DEC = -2;
    private static final byte INVALID_DEC = -3;
    private static final byte[] ENC;
    private static final byte[] DEC;
    
    private Base64() {
    }
    
    private static void encode3to4(final byte[] source, final int srcOffset, final int numSigBytes, final byte[] destination, final int destOffset) {
        int inBuff = 0;
        switch (numSigBytes) {
            case 3: {
                inBuff |= source[srcOffset + 2] << 24 >>> 24;
            }
            case 2: {
                inBuff |= source[srcOffset + 1] << 24 >>> 16;
            }
            case 1: {
                inBuff |= source[srcOffset] << 24 >>> 8;
                break;
            }
        }
        switch (numSigBytes) {
            case 3: {
                destination[destOffset] = Base64.ENC[inBuff >>> 18];
                destination[destOffset + 1] = Base64.ENC[inBuff >>> 12 & 0x3F];
                destination[destOffset + 2] = Base64.ENC[inBuff >>> 6 & 0x3F];
                destination[destOffset + 3] = Base64.ENC[inBuff & 0x3F];
                break;
            }
            case 2: {
                destination[destOffset] = Base64.ENC[inBuff >>> 18];
                destination[destOffset + 1] = Base64.ENC[inBuff >>> 12 & 0x3F];
                destination[destOffset + 2] = Base64.ENC[inBuff >>> 6 & 0x3F];
                destination[destOffset + 3] = 61;
                break;
            }
            case 1: {
                destination[destOffset] = Base64.ENC[inBuff >>> 18];
                destination[destOffset + 1] = Base64.ENC[inBuff >>> 12 & 0x3F];
                destination[destOffset + 3] = (destination[destOffset + 2] = 61);
                break;
            }
        }
    }
    
    public static String encodeBytes(final byte[] source) {
        return encodeBytes(source, 0, source.length);
    }
    
    public static String encodeBytes(final byte[] source, final int off, final int len) {
        final int len2 = len * 4 / 3;
        final byte[] outBuff = new byte[len2 + ((len % 3 > 0) ? 4 : 0)];
        int d = 0;
        int e = 0;
        for (int len3 = len - 2; d < len3; d += 3, e += 4) {
            encode3to4(source, d + off, 3, outBuff, e);
        }
        if (d < len) {
            encode3to4(source, d + off, len - d, outBuff, e);
            e += 4;
        }
        try {
            return new String(outBuff, 0, e, "UTF-8");
        }
        catch (UnsupportedEncodingException uue) {
            return new String(outBuff, 0, e);
        }
    }
    
    private static int decode4to3(final byte[] source, final int srcOffset, final byte[] destination, final int destOffset) {
        if (source[srcOffset + 2] == 61) {
            final int outBuff = (Base64.DEC[source[srcOffset]] & 0xFF) << 18 | (Base64.DEC[source[srcOffset + 1]] & 0xFF) << 12;
            destination[destOffset] = (byte)(outBuff >>> 16);
            return 1;
        }
        if (source[srcOffset + 3] == 61) {
            final int outBuff = (Base64.DEC[source[srcOffset]] & 0xFF) << 18 | (Base64.DEC[source[srcOffset + 1]] & 0xFF) << 12 | (Base64.DEC[source[srcOffset + 2]] & 0xFF) << 6;
            destination[destOffset] = (byte)(outBuff >>> 16);
            destination[destOffset + 1] = (byte)(outBuff >>> 8);
            return 2;
        }
        final int outBuff = (Base64.DEC[source[srcOffset]] & 0xFF) << 18 | (Base64.DEC[source[srcOffset + 1]] & 0xFF) << 12 | (Base64.DEC[source[srcOffset + 2]] & 0xFF) << 6 | (Base64.DEC[source[srcOffset + 3]] & 0xFF);
        destination[destOffset] = (byte)(outBuff >> 16);
        destination[destOffset + 1] = (byte)(outBuff >> 8);
        destination[destOffset + 2] = (byte)outBuff;
        return 3;
    }
    
    public static byte[] decode(final byte[] source, final int off, final int len) {
        final byte[] outBuff = new byte[len * 3 / 4];
        int outBuffPosn = 0;
        final byte[] b4 = new byte[4];
        int b4Posn = 0;
        for (int i = off; i < off + len; ++i) {
            final byte sbiCrop = (byte)(source[i] & 0x7F);
            final byte sbiDecode = Base64.DEC[sbiCrop];
            if (-1 <= sbiDecode) {
                b4[b4Posn++] = sbiCrop;
                if (b4Posn > 3) {
                    outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn);
                    b4Posn = 0;
                    if (sbiCrop == 61) {
                        break;
                    }
                }
            }
            else if (sbiDecode != -2) {
                throw new IllegalArgumentException(MessageFormat.format("Bad Base64 input character at {0} : {1} (decimal)", i, source[i] & 0xFF));
            }
        }
        if (outBuff.length == outBuffPosn) {
            return outBuff;
        }
        final byte[] out = new byte[outBuffPosn];
        System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
        return out;
    }
    
    public static byte[] decode(final String s) {
        byte[] bytes;
        try {
            bytes = s.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException uee) {
            bytes = s.getBytes();
        }
        return decode(bytes, 0, bytes.length);
    }
    
    static {
        try {
            ENC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException uee) {
            throw new RuntimeException(uee.getMessage(), uee);
        }
        Arrays.fill(DEC = new byte[128], (byte)(-3));
        for (int i = 0; i < 64; ++i) {
            Base64.DEC[Base64.ENC[i]] = (byte)i;
        }
        Base64.DEC[61] = -1;
        Base64.DEC[9] = -2;
        Base64.DEC[10] = -2;
        Base64.DEC[13] = -2;
        Base64.DEC[32] = -2;
    }
}
