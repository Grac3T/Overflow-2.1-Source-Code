// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Key implements Serializable
{
    private static final long serialVersionUID = -7763033793023520265L;
    private int id;
    private String key;
    private String title;
    private String url;
    
    public int getId() {
        return this.id;
    }
    
    public Key setId(final int id) {
        this.id = id;
        return this;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public Key setKey(final String key) {
        this.key = key;
        return this;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public Key setTitle(final String title) {
        this.title = title;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Key setUrl(final String url) {
        this.url = url;
        return this;
    }
}
