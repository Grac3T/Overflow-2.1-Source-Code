// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class Team implements Serializable
{
    private static final long serialVersionUID = -1844276044857264413L;
    private int id;
    private int membersCount;
    private int reposCount;
    private String name;
    private String permission;
    private String url;
    
    public int getId() {
        return this.id;
    }
    
    public Team setId(final int id) {
        this.id = id;
        return this;
    }
    
    public int getMembersCount() {
        return this.membersCount;
    }
    
    public Team setMembersCount(final int membersCount) {
        this.membersCount = membersCount;
        return this;
    }
    
    public int getReposCount() {
        return this.reposCount;
    }
    
    public Team setReposCount(final int reposCount) {
        this.reposCount = reposCount;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Team setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getPermission() {
        return this.permission;
    }
    
    public Team setPermission(final String permission) {
        this.permission = permission;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public Team setUrl(final String url) {
        this.url = url;
        return this;
    }
}
