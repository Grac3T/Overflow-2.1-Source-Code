// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import java.io.Serializable;

public class EventRepository implements Serializable
{
    private static final long serialVersionUID = -8910798454171899699L;
    private long id;
    private String name;
    private String url;
    
    public long getId() {
        return this.id;
    }
    
    public EventRepository setId(final long id) {
        this.id = id;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public EventRepository setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public EventRepository setUrl(final String url) {
        this.url = url;
        return this;
    }
}
