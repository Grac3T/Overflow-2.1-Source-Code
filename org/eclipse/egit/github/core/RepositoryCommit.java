// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.util.List;
import java.io.Serializable;

public class RepositoryCommit implements Serializable
{
    private static final long serialVersionUID = -8911733018395257250L;
    private Commit commit;
    private CommitStats stats;
    private List<Commit> parents;
    private List<CommitFile> files;
    private String sha;
    private String url;
    private User author;
    private User committer;
    
    public Commit getCommit() {
        return this.commit;
    }
    
    public RepositoryCommit setCommit(final Commit commit) {
        this.commit = commit;
        return this;
    }
    
    public CommitStats getStats() {
        return this.stats;
    }
    
    public RepositoryCommit setStats(final CommitStats stats) {
        this.stats = stats;
        return this;
    }
    
    public List<Commit> getParents() {
        return this.parents;
    }
    
    public RepositoryCommit setParents(final List<Commit> parents) {
        this.parents = parents;
        return this;
    }
    
    public List<CommitFile> getFiles() {
        return this.files;
    }
    
    public RepositoryCommit setFiles(final List<CommitFile> files) {
        this.files = files;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public RepositoryCommit setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public RepositoryCommit setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getAuthor() {
        return this.author;
    }
    
    public RepositoryCommit setAuthor(final User author) {
        this.author = author;
        return this;
    }
    
    public User getCommitter() {
        return this.committer;
    }
    
    public RepositoryCommit setCommitter(final User committer) {
        this.committer = committer;
        return this;
    }
}
