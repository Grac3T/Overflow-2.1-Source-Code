// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.util;

import java.util.Iterator;
import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.Map;

public class MultiPartUtils
{
    public static HttpURLConnection post(final String url, final Map<String, Object> parts) throws IOException {
        final HttpURLConnection post = (HttpURLConnection)new URL(url).openConnection();
        post.setRequestMethod("POST");
        return post(post, parts);
    }
    
    public static HttpURLConnection post(final HttpURLConnection post, final Map<String, Object> parts) throws IOException {
        final String boundary = "00content0boundary00";
        post.setDoOutput(true);
        post.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        final BufferedOutputStream output = new BufferedOutputStream(post.getOutputStream());
        final byte[] buffer = new byte[8192];
        final byte[] boundarySeparator = ("--" + boundary + "\r\n").getBytes("UTF-8");
        final byte[] newline = "\r\n".getBytes("UTF-8");
        try {
            for (final Map.Entry<String, Object> part : parts.entrySet()) {
                output.write(boundarySeparator);
                final StringBuilder partBuffer = new StringBuilder("Content-Disposition: ");
                partBuffer.append("form-data; name=\"");
                partBuffer.append(part.getKey());
                partBuffer.append('\"');
                output.write(partBuffer.toString().getBytes("UTF-8"));
                output.write(newline);
                output.write(newline);
                final Object value = part.getValue();
                if (value instanceof InputStream) {
                    final InputStream input = (InputStream)value;
                    int read;
                    while ((read = input.read(buffer)) != -1) {
                        output.write(buffer, 0, read);
                    }
                    input.close();
                }
                else {
                    output.write(part.getValue().toString().getBytes("UTF-8"));
                }
                output.write(newline);
            }
            output.write(("--" + boundary + "--\r\n").getBytes("UTF-8"));
        }
        finally {
            output.close();
        }
        return post;
    }
}
