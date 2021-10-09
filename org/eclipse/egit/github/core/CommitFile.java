// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class CommitFile implements Serializable
{
    private static final long serialVersionUID = -4607637532042868579L;
    private int additions;
    private int changes;
    private int deletions;
    private String blobUrl;
    private String filename;
    private String patch;
    private String rawUrl;
    private String sha;
    private String status;
    
    public int getAdditions() {
        return this.additions;
    }
    
    public CommitFile setAdditions(final int additions) {
        this.additions = additions;
        return this;
    }
    
    public int getChanges() {
        return this.changes;
    }
    
    public CommitFile setChanges(final int changes) {
        this.changes = changes;
        return this;
    }
    
    public int getDeletions() {
        return this.deletions;
    }
    
    public CommitFile setDeletions(final int deletions) {
        this.deletions = deletions;
        return this;
    }
    
    public String getBlobUrl() {
        return this.blobUrl;
    }
    
    public CommitFile setBlobUrl(final String blobUrl) {
        this.blobUrl = blobUrl;
        return this;
    }
    
    public String getFilename() {
        return this.filename;
    }
    
    public CommitFile setFilename(final String filename) {
        this.filename = filename;
        return this;
    }
    
    public String getPatch() {
        return this.patch;
    }
    
    public CommitFile setPatch(final String patch) {
        this.patch = patch;
        return this;
    }
    
    public String getRawUrl() {
        return this.rawUrl;
    }
    
    public CommitFile setRawUrl(final String rawUrl) {
        this.rawUrl = rawUrl;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public CommitFile setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public CommitFile setStatus(final String status) {
        this.status = status;
        return this;
    }
}
