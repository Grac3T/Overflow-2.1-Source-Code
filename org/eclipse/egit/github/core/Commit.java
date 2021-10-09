// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.util.List;
import java.io.Serializable;

public class Commit implements Serializable
{
    private static final long serialVersionUID = -1893280210470143372L;
    private CommitUser author;
    private CommitUser committer;
    private int commentCount;
    private List<Commit> parents;
    private String message;
    private String sha;
    private String url;
    private Tree tree;
    
    public CommitUser getAuthor() {
        return this.author;
    }
    
    public Commit setAuthor(final CommitUser author) {
        this.author = author;
        return this;
    }
    
    public CommitUser getCommitter() {
        return this.committer;
    }
    
    public Commit setCommitter(final CommitUser committer) {
        this.committer = committer;
        return this;
    }
    
    public int getCommentCount() {
        return this.commentCount;
    }
    
    public Commit setCommentCount(final int commentCount) {
        this.commentCount = commentCount;
        return this;
    }
    
    public List<Commit> getParents() {
        return this.parents;
    }
    
    public Commit setParents(final List<Commit> parents) {
        this.parents = parents;
        return this;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public Commit setMessage(final String message) {
        this.message = message;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public Commit setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Commit setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public Tree getTree() {
        return this.tree;
    }
    
    public Commit setTree(final Tree tree) {
        this.tree = tree;
        return this;
    }
}
