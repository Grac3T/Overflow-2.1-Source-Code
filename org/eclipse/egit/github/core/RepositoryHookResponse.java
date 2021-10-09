// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class RepositoryHookResponse implements Serializable
{
    private static final long serialVersionUID = -1168379336046512838L;
    private int code;
    private String message;
    
    public int getCode() {
        return this.code;
    }
    
    public RepositoryHookResponse setCode(final int code) {
        this.code = code;
        return this;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public RepositoryHookResponse setMessage(final String message) {
        this.message = message;
        return this;
    }
}
