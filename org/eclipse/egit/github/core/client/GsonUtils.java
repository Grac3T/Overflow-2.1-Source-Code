// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import java.io.Reader;
import com.google.gson.FieldNamingPolicy;
import org.eclipse.egit.github.core.event.Event;
import java.lang.reflect.Type;
import java.util.Date;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;

public abstract class GsonUtils
{
    private static final Gson GSON;
    private static final Gson GSON_NO_NULLS;
    
    public static final Gson createGson() {
        return createGson(true);
    }
    
    public static final Gson createGson(final boolean serializeNulls) {
        final GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter((Type)Date.class, (Object)new DateFormatter());
        builder.registerTypeAdapter((Type)Event.class, (Object)new EventFormatter());
        builder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        if (serializeNulls) {
            builder.serializeNulls();
        }
        return builder.create();
    }
    
    public static final Gson getGson() {
        return GsonUtils.GSON;
    }
    
    public static final Gson getGson(final boolean serializeNulls) {
        return serializeNulls ? GsonUtils.GSON : GsonUtils.GSON_NO_NULLS;
    }
    
    public static final String toJson(final Object object) {
        return toJson(object, true);
    }
    
    public static final String toJson(final Object object, final boolean includeNulls) {
        return includeNulls ? GsonUtils.GSON.toJson(object) : GsonUtils.GSON_NO_NULLS.toJson(object);
    }
    
    public static final <V> V fromJson(final String json, final Class<V> type) {
        return (V)GsonUtils.GSON.fromJson(json, (Class)type);
    }
    
    public static final <V> V fromJson(final String json, final Type type) {
        return (V)GsonUtils.GSON.fromJson(json, type);
    }
    
    public static final <V> V fromJson(final Reader reader, final Class<V> type) {
        return (V)GsonUtils.GSON.fromJson(reader, (Class)type);
    }
    
    public static final <V> V fromJson(final Reader reader, final Type type) {
        return (V)GsonUtils.GSON.fromJson(reader, type);
    }
    
    static {
        GsonUtils.GSON = createGson(true);
        GsonUtils.GSON_NO_NULLS = createGson(false);
    }
}
