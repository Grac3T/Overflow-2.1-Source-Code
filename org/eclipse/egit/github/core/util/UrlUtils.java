// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.util;

import java.net.URI;
import java.util.Iterator;
import java.util.Map;
import java.net.URLDecoder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.eclipse.egit.github.core.IRepositoryIdProvider;

public abstract class UrlUtils
{
    public static String createRemoteSshUrl(final IRepositoryIdProvider repository) {
        return createRemoteSshUrl(repository, "github.com");
    }
    
    public static String createRemoteSshUrl(final IRepositoryIdProvider repository, final String host) {
        return "git@" + host + ":" + repository.generateId() + ".git";
    }
    
    public static String createRemoteHttpsUrl(final IRepositoryIdProvider repository, final String user) {
        return createRemoteHttpsUrl(repository, "github.com", user);
    }
    
    public static String createRemoteHttpsUrl(final IRepositoryIdProvider repository, final String host, final String user) {
        return "https://" + user + "@" + host + "/" + repository.generateId() + ".git";
    }
    
    public static String createRemoteReadOnlyUrl(final IRepositoryIdProvider repository) {
        return createRemoteReadOnlyUrl(repository, "github.com");
    }
    
    public static String createRemoteReadOnlyUrl(final IRepositoryIdProvider repository, final String host) {
        return "git://" + host + "/" + repository.generateId() + ".git";
    }
    
    public static String encode(final String value) {
        try {
            return URLEncoder.encode(value, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    public static String decode(final String value) {
        try {
            return URLDecoder.decode(value, "ISO-8859-1");
        }
        catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    public static void addParam(final String name, final String value, final StringBuilder uri) {
        if (uri.length() > 0) {
            uri.append('&');
        }
        uri.append(encode(name)).append('=');
        if (value != null) {
            uri.append(encode(value));
        }
    }
    
    public static void addParams(final Map<String, String> params, final StringBuilder uri) {
        if (params == null || params.isEmpty()) {
            return;
        }
        for (final Map.Entry<String, String> param : params.entrySet()) {
            addParam(param.getKey(), param.getValue(), uri);
        }
    }
    
    public static String getParam(final URI uri, final String name) {
        final String query = uri.getRawQuery();
        if (query == null || query.length() == 0) {
            return null;
        }
        final String[] split;
        final String[] params = split = query.split("&");
        for (final String param : split) {
            final String[] parts = param.split("=");
            if (parts.length == 2) {
                if (name.equals(parts[0])) {
                    return decode(parts[1]);
                }
            }
        }
        return null;
    }
}
