// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import java.text.ParseException;
import com.google.gson.JsonParseException;
import com.google.gson.JsonDeserializationContext;
import java.lang.reflect.Type;
import com.google.gson.JsonElement;
import java.util.TimeZone;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import com.google.gson.JsonSerializer;
import java.util.Date;
import com.google.gson.JsonDeserializer;

public class DateFormatter implements JsonDeserializer<Date>, JsonSerializer<Date>
{
    private final DateFormat[] formats;
    
    public DateFormatter() {
        (this.formats = new DateFormat[3])[0] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        this.formats[1] = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z");
        this.formats[2] = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        final TimeZone timeZone = TimeZone.getTimeZone("Zulu");
        for (final DateFormat format : this.formats) {
            format.setTimeZone(timeZone);
        }
    }
    
    public Date deserialize(final JsonElement json, final Type typeOfT, final JsonDeserializationContext context) throws JsonParseException {
        JsonParseException exception = null;
        final String value = json.getAsString();
        final DateFormat[] formats = this.formats;
        final int length = formats.length;
        int i = 0;
        while (i < length) {
            final DateFormat format = formats[i];
            try {
                synchronized (format) {
                    return format.parse(value);
                }
            }
            catch (ParseException e) {
                exception = new JsonParseException((Throwable)e);
                ++i;
                continue;
            }
            break;
        }
        throw exception;
    }
    
    public JsonElement serialize(final Date date, final Type type, final JsonSerializationContext context) {
        final DateFormat primary = this.formats[0];
        final String formatted;
        synchronized (primary) {
            formatted = primary.format(date);
        }
        return (JsonElement)new JsonPrimitive(formatted);
    }
}
