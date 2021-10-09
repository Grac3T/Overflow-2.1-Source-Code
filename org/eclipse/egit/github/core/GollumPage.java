// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class GollumPage implements Serializable
{
    private static final long serialVersionUID = -5841603600916978606L;
    private String pageName;
    private String title;
    private String action;
    private String sha;
    private String htmlUrl;
    
    public String getPageName() {
        return this.pageName;
    }
    
    public GollumPage setPageName(final String pageName) {
        this.pageName = pageName;
        return this;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public GollumPage setTitle(final String title) {
        this.title = title;
        return this;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public GollumPage setAction(final String action) {
        this.action = action;
        return this;
    }
    
    public String getSha() {
        return this.sha;
    }
    
    public GollumPage setSha(final String sha) {
        this.sha = sha;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public GollumPage setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
}
