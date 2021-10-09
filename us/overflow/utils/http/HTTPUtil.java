// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.utils.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonElement;
import java.io.InputStream;
import com.google.gson.JsonParser;
import java.util.function.BiConsumer;
import java.util.HashMap;
import java.net.URLConnection;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.net.URL;

public class HTTPUtil
{
    public static String getResponse(final String URL) {
        String out = null;
        try {
            final URLConnection connection = new URL(URL).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            connection.connect();
            final BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }
            final String value = out = sb.toString();
        }
        catch (IOException ex) {
            out = "[ERROR] - " + ex.getMessage();
        }
        return out;
    }
    
    public static String getResponse(final String URL, final HashMap<String, String> header) {
        String out = null;
        try {
            final URLConnection connection = new URL(URL).openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            header.forEach(connection::setRequestProperty);
            connection.connect();
            final BufferedReader r = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charset.forName("UTF-8")));
            final StringBuilder sb = new StringBuilder();
            String line;
            while ((line = r.readLine()) != null) {
                sb.append(line);
            }
            final String value = out = sb.toString();
        }
        catch (IOException ex) {
            out = "[ERROR] - " + ex.getMessage();
        }
        return out;
    }
    
    public static String getUUID(final String mcName) {
        final String sURL = "https://minecraft-techworld.com/admin/api/uuid?action=uuid&username=" + mcName;
        try {
            final URL url = new URL(sURL);
            final URLConnection request = url.openConnection();
            request.setRequestProperty("User-Agent", "Mozilla/5.0");
            request.connect();
            final JsonParser jp = new JsonParser();
            final JsonElement root = jp.parse((Reader)new InputStreamReader((InputStream)request.getContent()));
            final JsonObject JSONObject = root.getAsJsonObject();
            return JSONObject.get("output").getAsString();
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
