// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

public class TypedResource extends ShaResource
{
    private static final long serialVersionUID = -7285665432528832240L;
    public static final String TYPE_COMMIT = "commit";
    public static final String TYPE_TAG = "tag";
    public static final String TYPE_BLOB = "blob";
    private String type;
    private String url;
    
    public String getType() {
        return this.type;
    }
    
    public TypedResource setType(final String type) {
        this.type = type;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public TypedResource setUrl(final String url) {
        this.url = url;
        return this;
    }
}
