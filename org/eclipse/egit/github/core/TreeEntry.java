// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class TreeEntry implements Serializable
{
    private static final long serialVersionUID = -6181332657279059683L;
    public static final String TYPE_BLOB = "blob";
    public static final String TYPE_TREE = "tree";
    public static final String MODE_BLOB = "100644";
    public static final String MODE_BLOB_EXECUTABLE = "100755";
    public static final String MODE_BLOB_SYMLINK = "120000";
    public static final String MODE_DIRECTORY = "040000";
    public static final String MODE_SUBMODULE = "160000";
    private long size;
    private String mode;
    private String path;
    private String sha;
    private String type;
    private String url;
    
    public long getSize() {
        return this.size;
    }
    
    public TreeEntry setSize(final long size) {
        this.size = size;
        return this;
    }
    
    public String getMode() {
        return this.mode;
    }
    
    public TreeEntry setMode(final String mode) {
        this.mode = mode;
        return this;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public TreeEntry setPath(final String path) {
        this.path = path;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public TreeEntry setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public String getType() {
        return this.type;
    }
    
    public TreeEntry setType(final String type) {
        this.type = type;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public TreeEntry setUrl(final String url) {
        this.url = url;
        return this;
    }
}
