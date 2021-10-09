// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.util.List;
import java.io.Serializable;

public class Tree implements Serializable
{
    private static final long serialVersionUID = 6518261551932913340L;
    private List<TreeEntry> tree;
    private String sha;
    private String url;
    
    public List<TreeEntry> getTree() {
        return this.tree;
    }
    
    public Tree setTree(final List<TreeEntry> tree) {
        this.tree = tree;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public Tree setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Tree setUrl(final String url) {
        this.url = url;
        return this;
    }
}
