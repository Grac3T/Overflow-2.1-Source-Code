// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Reference implements Serializable
{
    private static final long serialVersionUID = -4092126502387796380L;
    private String ref;
    private String url;
    private TypedResource object;
    
    public String getRef() {
        return this.ref;
    }
    
    public Reference setRef(final String ref) {
        this.ref = ref;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Reference setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public TypedResource getObject() {
        return this.object;
    }
    
    public Reference setObject(final TypedResource object) {
        this.object = object;
        return this;
    }
}
