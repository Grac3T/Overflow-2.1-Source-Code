// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Id implements Serializable
{
    private static final long serialVersionUID = -1074145490136786429L;
    private String id;
    
    public String getId() {
        return this.id;
    }
    
    public Id setId(final String id) {
        this.id = id;
        return this;
    }
}
