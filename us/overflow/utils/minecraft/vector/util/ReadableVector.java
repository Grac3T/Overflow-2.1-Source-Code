// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.minecraft.vector.util;

import java.nio.FloatBuffer;

public interface ReadableVector
{
    float length();
    
    float lengthSquared();
    
    Vector store(final FloatBuffer p0);
}
