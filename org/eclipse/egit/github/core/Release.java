// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.util.Date;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Release implements Serializable
{
    private static final long serialVersionUID = 1L;
    private String url;
    private String htmlUrl;
    private String assetsUrl;
    private String uploadUrl;
    private String tarballUrl;
    private String zipballUrl;
    private long id;
    private String tagName;
    private String targetCommitish;
    private String name;
    private String body;
    @SerializedName("draft")
    private boolean isDraft;
    @SerializedName("prerelease")
    private boolean isPrerelease;
    private Date createdAt;
    private Date publishedAt;
    private User author;
    
    public String getUrl() {
        return this.url;
    }
    
    public Release setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public Release setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getAssetsUrl() {
        return this.assetsUrl;
    }
    
    public Release setAssetsUrl(final String assetsUrl) {
        this.assetsUrl = assetsUrl;
        return this;
    }
    
    public String getUploadUrl() {
        return this.uploadUrl;
    }
    
    public Release setUploadUrl(final String uploadUrl) {
        this.uploadUrl = uploadUrl;
        return this;
    }
    
    public String getTarballUrl() {
        return this.tarballUrl;
    }
    
    public Release setTarballUrl(final String tarballUrl) {
        this.tarballUrl = tarballUrl;
        return this;
    }
    
    public String getZipballUrl() {
        return this.zipballUrl;
    }
    
    public Release setZipballUrl(final String zipballUrl) {
        this.zipballUrl = zipballUrl;
        return this;
    }
    
    public long getId() {
        return this.id;
    }
    
    public Release setId(final long id) {
        this.id = id;
        return this;
    }
    
    public String getTagName() {
        return this.tagName;
    }
    
    public Release setTagName(final String tagName) {
        this.tagName = tagName;
        return this;
    }
    
    public String getTargetCommitish() {
        return this.targetCommitish;
    }
    
    public Release setTargetCommitish(final String targetCommitish) {
        this.targetCommitish = targetCommitish;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Release setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getBody() {
        return this.body;
    }
    
    public Release setBody(final String body) {
        this.body = body;
        return this;
    }
    
    public boolean isDraft() {
        return this.isDraft;
    }
    
    public Release setDraft(final boolean isDraft) {
        this.isDraft = isDraft;
        return this;
    }
    
    public boolean isPrerelease() {
        return this.isPrerelease;
    }
    
    public Release setPrerelease(final boolean isPrerelease) {
        this.isPrerelease = isPrerelease;
        return this;
    }
    
    public Date getCreatedAt() {
        return this.createdAt;
    }
    
    public Release setCreatedAt(final Date createdAt) {
        this.createdAt = createdAt;
        return this;
    }
    
    public Date getPublishedAt() {
        return this.publishedAt;
    }
    
    public Release setPublishedAt(final Date publishedAt) {
        this.publishedAt = publishedAt;
        return this;
    }
    
    public User getAuthor() {
        return this.author;
    }
    
    public Release setAuthor(final User author) {
        this.author = author;
        return this;
    }
}
