// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Repository implements IRepositoryIdProvider, Serializable
{
    private static final long serialVersionUID = 406671816413754925L;
    private boolean fork;
    private boolean hasDownloads;
    private boolean hasIssues;
    private boolean hasWiki;
    @SerializedName("private")
    private boolean isPrivate;
    private Date createdAt;
    private Date pushedAt;
    private Date updatedAt;
    private int forks;
    private long id;
    private int openIssues;
    private int size;
    private int watchers;
    private Repository parent;
    private Repository source;
    private String cloneUrl;
    private String description;
    private String homepage;
    private String gitUrl;
    private String htmlUrl;
    private String language;
    private String defaultBranch;
    private String mirrorUrl;
    private String name;
    private String sshUrl;
    private String svnUrl;
    private String url;
    private User owner;
    
    public boolean isFork() {
        return this.fork;
    }
    
    public Repository setFork(final boolean fork) {
        this.fork = fork;
        return this;
    }
    
    public boolean isHasDownloads() {
        return this.hasDownloads;
    }
    
    public Repository setHasDownloads(final boolean hasDownloads) {
        this.hasDownloads = hasDownloads;
        return this;
    }
    
    public boolean isHasIssues() {
        return this.hasIssues;
    }
    
    public Repository setHasIssues(final boolean hasIssues) {
        this.hasIssues = hasIssues;
        return this;
    }
    
    public boolean isHasWiki() {
        return this.hasWiki;
    }
    
    public Repository setHasWiki(final boolean hasWiki) {
        this.hasWiki = hasWiki;
        return this;
    }
    
    public boolean isPrivate() {
        return this.isPrivate;
    }
    
    public Repository setPrivate(final boolean isPrivate) {
        this.isPrivate = isPrivate;
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public Repository setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public Date getPushedAt() {
        return DateUtils.clone(this.pushedAt);
    }
    
    public Repository setPushedAt(final Date pushedAt) {
        this.pushedAt = DateUtils.clone(pushedAt);
        return this;
    }
    
    public int getForks() {
        return this.forks;
    }
    
    public Repository setForks(final int forks) {
        this.forks = forks;
        return this;
    }
    
    public int getOpenIssues() {
        return this.openIssues;
    }
    
    public Repository setOpenIssues(final int openIssues) {
        this.openIssues = openIssues;
        return this;
    }
    
    public int getSize() {
        return this.size;
    }
    
    public Repository setSize(final int size) {
        this.size = size;
        return this;
    }
    
    public int getWatchers() {
        return this.watchers;
    }
    
    public Repository setWatchers(final int watchers) {
        this.watchers = watchers;
        return this;
    }
    
    public Repository getParent() {
        return this.parent;
    }
    
    public Repository setParent(final Repository parent) {
        this.parent = parent;
        return this;
    }
    
    public Repository getSource() {
        return this.source;
    }
    
    public Repository setSource(final Repository source) {
        this.source = source;
        return this;
    }
    
    public String getCloneUrl() {
        return this.cloneUrl;
    }
    
    public Repository setCloneUrl(final String cloneUrl) {
        this.cloneUrl = cloneUrl;
        return this;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public Repository setDescription(final String description) {
        this.description = description;
        return this;
    }
    
    public String getHomepage() {
        return this.homepage;
    }
    
    public Repository setHomepage(final String homepage) {
        this.homepage = homepage;
        return this;
    }
    
    public String getGitUrl() {
        return this.gitUrl;
    }
    
    public Repository setGitUrl(final String gitUrl) {
        this.gitUrl = gitUrl;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public Repository setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getLanguage() {
        return this.language;
    }
    
    public Repository setLanguage(final String language) {
        this.language = language;
        return this;
    }
    
    public String getDefaultBranch() {
        return this.defaultBranch;
    }
    
    public Repository setDefaultBranch(final String defaultBranch) {
        this.defaultBranch = defaultBranch;
        return this;
    }
    
    @Deprecated
    public String getMasterBranch() {
        return this.defaultBranch;
    }
    
    @Deprecated
    public Repository setMasterBranch(final String masterBranch) {
        this.defaultBranch = masterBranch;
        return this;
    }
    
    public String getMirrorUrl() {
        return this.mirrorUrl;
    }
    
    public Repository setMirrorUrl(final String mirrorUrl) {
        this.mirrorUrl = mirrorUrl;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Repository setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getSshUrl() {
        return this.sshUrl;
    }
    
    public Repository setSshUrl(final String sshUrl) {
        this.sshUrl = sshUrl;
        return this;
    }
    
    public String getSvnUrl() {
        return this.svnUrl;
    }
    
    public Repository setSvnUrl(final String svnUrl) {
        this.svnUrl = svnUrl;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Repository setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public User getOwner() {
        return this.owner;
    }
    
    public Repository setOwner(final User owner) {
        this.owner = owner;
        return this;
    }
    
    public Date getUpdatedAt() {
        return DateUtils.clone(this.updatedAt);
    }
    
    public Repository setUpdatedAt(final Date updatedAt) {
        this.updatedAt = DateUtils.clone(updatedAt);
        return this;
    }
    
    public long getId() {
        return this.id;
    }
    
    public Repository setId(final long id) {
        this.id = id;
        return this;
    }
    
    @Override
    public String generateId() {
        final User owner = this.owner;
        final String name = this.name;
        if (owner == null || name == null || name.length() == 0) {
            return null;
        }
        final String login = owner.getLogin();
        if (login == null || login.length() == 0) {
            return null;
        }
        return login + "/" + name;
    }
}
