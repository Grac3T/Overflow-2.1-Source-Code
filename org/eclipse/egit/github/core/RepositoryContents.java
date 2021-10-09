// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class RepositoryContents implements Serializable
{
    private static final long serialVersionUID = -70974727412738287L;
    public static final String ENCODING_BASE64 = "base64";
    public static final String TYPE_FILE = "file";
    public static final String TYPE_DIR = "dir";
    private long size;
    private String content;
    private String encoding;
    private String name;
    private String path;
    private String sha;
    private String type;
    
    public long getSize() {
        return this.size;
    }
    
    public RepositoryContents setSize(final long size) {
        this.size = size;
        return this;
    }
    
    public String getContent() {
        return this.content;
    }
    
    public RepositoryContents setContent(final String content) {
        this.content = content;
        return this;
    }
    
    public String getEncoding() {
        return this.encoding;
    }
    
    public RepositoryContents setEncoding(final String encoding) {
        this.encoding = encoding;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public RepositoryContents setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public RepositoryContents setPath(final String path) {
        this.path = path;
        return this;
    }
    
    public String getType() {
        return this.type;
    }
    
    public RepositoryContents setType(final String type) {
        this.type = type;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public RepositoryContents setSha(final String sha) {
        this.sha = sha;
        return this;
    }
}
