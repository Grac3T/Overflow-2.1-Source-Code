// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.util;

import java.io.UnsupportedEncodingException;

public abstract class EncodingUtils
{
    public static final byte[] fromBase64(final String content) {
        return Base64.decode(content);
    }
    
    public static final String toBase64(final byte[] content) {
        return Base64.encodeBytes(content);
    }
    
    public static final String toBase64(final String content) {
        byte[] bytes;
        try {
            bytes = content.getBytes("UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            bytes = content.getBytes();
        }
        return toBase64(bytes);
    }
}
