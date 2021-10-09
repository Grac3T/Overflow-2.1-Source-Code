// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class GistFile implements Serializable
{
    private static final long serialVersionUID = 2067939890126207032L;
    private int size;
    private String content;
    private String filename;
    private String rawUrl;
    
    public int getSize() {
        return this.size;
    }
    
    public GistFile setSize(final int size) {
        this.size = size;
        return this;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public GistFile setContent(final String content) {
        this.content = content;
        return this;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    public GistFile setFilename(final String filename) {
        this.filename = filename;
        return this;
    }
    
    public String getRawUrl() {
        return this.rawUrl;
    }
    
    public GistFile setRawUrl(final String rawUrl) {
        this.rawUrl = rawUrl;
        return this;
    }
}
