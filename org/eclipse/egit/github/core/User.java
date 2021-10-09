// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import java.io.Serializable;

public class User implements Serializable
{
    private static final long serialVersionUID = -1211802439119529774L;
    public static final String TYPE_USER = "User";
    public static final String TYPE_ORG = "Organization";
    private boolean hireable;
    private Date createdAt;
    private int collaborators;
    private int diskUsage;
    private int followers;
    private int following;
    private int id;
    private int ownedPrivateRepos;
    private int privateGists;
    private int publicGists;
    private int publicRepos;
    private int totalPrivateRepos;
    private String avatarUrl;
    private String bio;
    private String blog;
    private String company;
    private String email;
    private String gravatarId;
    private String htmlUrl;
    private String location;
    private String login;
    private String name;
    private String type;
    private String url;
    private UserPlan plan;
    
    public boolean isHireable() {
        return this.hireable;
    }
    
    public User setHireable(final boolean hireable) {
        this.hireable = hireable;
        return this;
    }
    
    public Date getCreatedAt() {
        return DateUtils.clone(this.createdAt);
    }
    
    public User setCreatedAt(final Date createdAt) {
        this.createdAt = DateUtils.clone(createdAt);
        return this;
    }
    
    public int getCollaborators() {
        return this.collaborators;
    }
    
    public User setCollaborators(final int collaborators) {
        this.collaborators = collaborators;
        return this;
    }
    
    public int getDiskUsage() {
        return this.diskUsage;
    }
    
    public User setDiskUsage(final int diskUsage) {
        this.diskUsage = diskUsage;
        return this;
    }
    
    public int getFollowers() {
        return this.followers;
    }
    
    public User setFollowers(final int followers) {
        this.followers = followers;
        return this;
    }
    
    public int getFollowing() {
        return this.following;
    }
    
    public User setFollowing(final int following) {
        this.following = following;
        return this;
    }
    
    public int getId() {
        return this.id;
    }
    
    public User setId(final int id) {
        this.id = id;
        return this;
    }
    
    public int getOwnedPrivateRepos() {
        return this.ownedPrivateRepos;
    }
    
    public User setOwnedPrivateRepos(final int ownedPrivateRepos) {
        this.ownedPrivateRepos = ownedPrivateRepos;
        return this;
    }
    
    public int getPrivateGists() {
        return this.privateGists;
    }
    
    public User setPrivateGists(final int privateGists) {
        this.privateGists = privateGists;
        return this;
    }
    
    public int getPublicGists() {
        return this.publicGists;
    }
    
    public User setPublicGists(final int publicGists) {
        this.publicGists = publicGists;
        return this;
    }
    
    public int getPublicRepos() {
        return this.publicRepos;
    }
    
    public User setPublicRepos(final int publicRepos) {
        this.publicRepos = publicRepos;
        return this;
    }
    
    public int getTotalPrivateRepos() {
        return this.totalPrivateRepos;
    }
    
    public User setTotalPrivateRepos(final int totalPrivateRepos) {
        this.totalPrivateRepos = totalPrivateRepos;
        return this;
    }
    
    public String getAvatarUrl() {
        return this.avatarUrl;
    }
    
    public User setAvatarUrl(final String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }
    
    public String getBio() {
        return this.bio;
    }
    
    public User setBio(final String bio) {
        this.bio = bio;
        return this;
    }
    
    public String getBlog() {
        return this.blog;
    }
    
    public User setBlog(final String blog) {
        this.blog = blog;
        return this;
    }
    
    public String getCompany() {
        return this.company;
    }
    
    public User setCompany(final String company) {
        this.company = company;
        return this;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public User setEmail(final String email) {
        this.email = email;
        return this;
    }
    
    @Deprecated
    public String getGravatarId() {
        return this.gravatarId;
    }
    
    @Deprecated
    public User setGravatarId(final String gravatarId) {
        this.gravatarId = gravatarId;
        return this;
    }
    
    public String getHtmlUrl() {
        return this.htmlUrl;
    }
    
    public User setHtmlUrl(final String htmlUrl) {
        this.htmlUrl = htmlUrl;
        return this;
    }
    
    public String getLocation() {
        return this.location;
    }
    
    public User setLocation(final String location) {
        this.location = location;
        return this;
    }
    
    public String getLogin() {
        return this.login;
    }
    
    public User setLogin(final String login) {
        this.login = login;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public User setName(final String name) {
        this.name = name;
        return this;
    }
    
    public String getType() {
        return this.type;
    }
    
    public User setType(final String type) {
        this.type = type;
        return this;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public User setUrl(final String url) {
        this.url = url;
        return this;
    }
    
    public UserPlan getPlan() {
        return this.plan;
    }
    
    public User setPlan(final UserPlan plan) {
        this.plan = plan;
        return this;
    }
}
