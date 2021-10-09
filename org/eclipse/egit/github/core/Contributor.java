// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Contributor implements Serializable
{
    private static final long serialVersionUID = -8434028880839230626L;
    public static final String TYPE_ANONYMOUS = "Anonymous";
    private int contributions;
    private int id;
    private String avatarUrl;
    private String login;
    private String name;
    private String type;
    private String url;
    
    public int getContributions() {
        return this.contributions;
    }
    
    public Contributor setContributions(final int contributions) {
        this.contributions = contributions;
        return this;
    }
    
    public int getId() {
        return this.id;
    }
    
    public Contributor setId(final int id) {
        this.id = id;
        return this;
    }
    
    public String getAvatarUrl() {
        return this.avatarUrl;
    }
    
    public Contributor setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public Contributor setLogin(final String login) {
        this.login = login;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Contributor setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getType() {
        return this.type;
    }
    
    public Contributor setType(final String type) {
        this.type = type;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Contributor setUrl(final String url) {
        this.url = url;
        return this;
    }
}
