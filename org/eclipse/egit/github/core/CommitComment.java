// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

public class CommitComment extends Comment
{
    private static final long serialVersionUID = 5932088388457362322L;
    private int line;
    private int position;
    private int originalPosition;
    private String commitId;
    private String originalCommitId;
    private String path;
    private String diffHunk;
    
    public int getLine() {
        return this.line;
    }
    
    public CommitComment setLine(final int line) {
        this.line = line;
        return this;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public CommitComment setPosition(final int position) {
        this.position = position;
        return this;
    }
    
    public int getOriginalPosition() {
        return this.originalPosition;
    }
    
    public CommitComment setOriginalPosition(final int originalPosition) {
        this.originalPosition = originalPosition;
        return this;
    }
    
    public String getCommitId() {
        return this.commitId;
    }
    
    public CommitComment setCommitId(final String commitId) {
        this.commitId = commitId;
        return this;
    }
    
    public String getOriginalCommitId() {
        return this.originalCommitId;
    }
    
    public CommitComment setOriginalCommitId(final String originalCommitId) {
        this.originalCommitId = originalCommitId;
        return this;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public CommitComment setPath(final String path) {
        this.path = path;
        return this;
    }
    
    public String getDiffHunk() {
        return this.diffHunk;
    }
    
    public CommitComment setDiffHunk(final String diffHunk) {
        this.diffHunk = diffHunk;
        return this;
    }
}
