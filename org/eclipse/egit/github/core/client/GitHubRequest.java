// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import org.eclipse.egit.github.core.util.UrlUtils;
import java.lang.reflect.Type;
import java.util.Map;

public class GitHubRequest
{
    private String uri;
    private Map<String, String> params;
    private Type type;
    private String responseContentType;
    private Type arrayType;
    
    public GitHubRequest setArrayType(final Type arrayType) {
        this.arrayType = arrayType;
        return this;
    }
    
    public Type getArrayType() {
        return this.arrayType;
    }
    
    public String getUri() {
        return this.uri;
    }
    
    protected void addParams(final StringBuilder uri) {
        UrlUtils.addParams(this.getParams(), uri);
    }
    
    public String generateUri() {
        final String baseUri = this.uri;
        if (baseUri == null) {
            return null;
        }
        if (baseUri.indexOf(63) != -1) {
            return baseUri;
        }
        final StringBuilder params = new StringBuilder();
        this.addParams(params);
        if (params.length() > 0) {
            return baseUri + '?' + (Object)params;
        }
        return baseUri;
    }
    
    public GitHubRequest setUri(final StringBuilder uri) {
        return this.setUri((uri != null) ? uri.toString() : null);
    }
    
    public GitHubRequest setUri(final String uri) {
        this.uri = uri;
        return this;
    }
    
    public Map<String, String> getParams() {
        return (Map<String, String>)this.params;
    }
    
    public GitHubRequest setParams(final Map<String, String> params) {
        this.params = params;
        return this;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public GitHubRequest setType(final Type type) {
        this.type = type;
        return this;
    }
    
    public String getResponseContentType() {
        return this.responseContentType;
    }
    
    public GitHubRequest setResponseContentType(final String responseContentType) {
        this.responseContentType = responseContentType;
        return this;
    }
    
    @Override
    public int hashCode() {
        final String fullUri = this.generateUri();
        return (fullUri != null) ? fullUri.hashCode() : super.hashCode();
    }
    
    @Override
    public boolean equals(final Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof GitHubRequest)) {
            return false;
        }
        final String fullUri = this.generateUri();
        final String objUri = ((GitHubRequest)obj).generateUri();
        return fullUri != null && objUri != null && fullUri.equals(objUri);
    }
    
    @Override
    public String toString() {
        final String fullUri = this.generateUri();
        return (fullUri != null) ? fullUri : super.toString();
    }
}
