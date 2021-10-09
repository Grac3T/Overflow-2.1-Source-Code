// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Download implements Serializable
{
    private static final long serialVersionUID = 6554996867709945406L;
    private int downloadCount;
    private int id;
    private long size;
    private String description;
    private String contentType;
    private String htmlUrl;
    private String name;
    private String url;
    
    public int getDownloadCount() {
        return this.downloadCount;
    }
    
    public Download setDownloadCount(final int downloadCount) {
        this.downloadCount = downloadCount;
        return this;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Download setId(final int id) {
        this.id = id;
        return this;
    }
    
    public long getSize() {
        return this.size;
    }
    
    public Download setSize(final long size) {
        this.size = size;
        return this;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public Download setDescription(final String description) {
        this.description = description;
        return this;
    }
    
    public String getContentType() {
        return this.contentType;
    }
    
    public Download setContentType(final String contentType) {
        this.contentType = contentType;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public Download setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Download setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Download setUrl(final String url) {
        this.url = url;
        return this;
    }
}
