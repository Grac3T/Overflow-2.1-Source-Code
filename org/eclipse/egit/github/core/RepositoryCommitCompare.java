// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.util.List;
import java.io.Serializable;

public class RepositoryCommitCompare implements Serializable
{
    private static final long serialVersionUID = -6268028122983164123L;
    private int aheadBy;
    private int behindBy;
    private int totalCommits;
    private List<CommitFile> files;
    private List<RepositoryCommit> commits;
    private RepositoryCommit baseCommit;
    private String diffUrl;
    private String htmlUrl;
    private String patchUrl;
    private String permalinkUrl;
    private String status;
    private String url;
    
    public int getAheadBy() {
        return this.aheadBy;
    }
    
    public RepositoryCommitCompare setAheadBy(final int aheadBy) {
        this.aheadBy = aheadBy;
        return this;
    }
    
    public int getBehindBy() {
        return this.behindBy;
    }
    
    public RepositoryCommitCompare setBehindBy(final int behindBy) {
        this.behindBy = behindBy;
        return this;
    }
    
    public int getTotalCommits() {
        return this.totalCommits;
    }
    
    public RepositoryCommitCompare setTotalCommits(final int totalCommits) {
        this.totalCommits = totalCommits;
        return this;
    }
    
    public List<CommitFile> getFiles() {
        return this.files;
    }
    
    public RepositoryCommitCompare setFiles(final List<CommitFile> files) {
        this.files = files;
        return this;
    }
    
    public List<RepositoryCommit> getCommits() {
        return this.commits;
    }
    
    public RepositoryCommitCompare setCommits(final List<RepositoryCommit> commits) {
        this.commits = commits;
        return this;
    }
    
    public RepositoryCommit getBaseCommit() {
        return this.baseCommit;
    }
    
    public RepositoryCommitCompare setBaseCommit(final RepositoryCommit baseCommit) {
        this.baseCommit = baseCommit;
        return this;
    }
    
    public String getDiffUrl() {
        return this.diffUrl;
    }
    
    public RepositoryCommitCompare setDiffUrl(final String diffUrl) {
        this.diffUrl = diffUrl;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public RepositoryCommitCompare setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getPatchUrl() {
        return this.patchUrl;
    }
    
    public RepositoryCommitCompare setPatchUrl(final String patchUrl) {
        this.patchUrl = patchUrl;
        return this;
    }
    
    public String getPermalinkUrl() {
        return this.permalinkUrl;
    }
    
    public RepositoryCommitCompare setPermalinkUrl(final String permalinkUrl) {
        this.permalinkUrl = permalinkUrl;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public RepositoryCommitCompare setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public String getStatus() {
        return this.status;
    }
    
    public RepositoryCommitCompare setStatus(final String status) {
        this.status = status;
        return this;
    }
}
