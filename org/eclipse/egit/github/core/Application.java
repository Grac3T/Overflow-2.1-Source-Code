// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Application implements Serializable
{
    private static final long serialVersionUID = 5675660442127228497L;
    private String name;
    private String url;
    
    public String getName() {
        return this.name;
    }
    
    public Application setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Application setUrl(final String url) {
        this.url = url;
        return this;
    }
}
