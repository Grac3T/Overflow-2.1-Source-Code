// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import java.util.Map;
import java.util.HashMap;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import java.io.IOException;
import java.util.List;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.client.GitHubClient;

public class OrganizationService extends GitHubService
{
    public OrganizationService() {
    }
    
    public OrganizationService(final GitHubClient client) {
        super(client);
    }
    
    protected PagedRequest<User> createOrgRequest(final String user, final int start, final int size) {
        final PagedRequest<User> request = new PagedRequest<User>(start, size);
        if (user == null) {
            request.setUri("/user/orgs");
        }
        else {
            final StringBuilder uri = new StringBuilder("/users");
            uri.append('/').append(user);
            uri.append("/orgs");
            request.setUri(uri);
        }
        request.setType(new OrganizationService$1(this).getType());
        return request;
    }
    
    public List<User> getOrganizations() throws IOException {
        final PagedRequest<User> request = (PagedRequest<User>)this.createOrgRequest(null, 1, 100);
        return (List<User>)this.getAll((PagedRequest)request);
    }
    
    public List<User> getOrganizations(final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final PagedRequest<User> request = (PagedRequest<User>)this.createOrgRequest(user, 1, 100);
        return (List<User>)this.getAll((PagedRequest)request);
    }
    
    public User getOrganization(final String name) throws IOException {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(name);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(User.class);
        return (User)this.client.get(request).getBody();
    }
    
    public User editOrganization(final User organization) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        final String name = organization.getLogin();
        if (name == null) {
            throw new IllegalArgumentException("Organization login cannot be null");
        }
        if (name.length() == 0) {
            throw new IllegalArgumentException("Organization login cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(name);
        return this.client.post(uri.toString(), organization, User.class);
    }
    
    public List<User> getMembers(final String organization) throws IOException {
        return (List<User>)this.getMembers(organization, null);
    }
    
    public List<User> getMembers(final String organization, final RoleFilter roleFilter) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        final HashMap<String, String> params = new HashMap<String, String>();
        if (roleFilter != null) {
            params.put("role", roleFilter.toString());
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/members");
        final PagedRequest<User> request = (PagedRequest<User>)this.createPagedRequest();
        request.setParams(params);
        request.setUri(uri);
        request.setType(new OrganizationService$2(this).getType());
        return (List<User>)this.getAll((PagedRequest)request);
    }
    
    public List<User> getPublicMembers(final String organization) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/public_members");
        final PagedRequest<User> request = (PagedRequest<User>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new OrganizationService$3(this).getType());
        return (List<User>)this.getAll((PagedRequest)request);
    }
    
    public boolean isMember(final String organization, final String user) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/members");
        uri.append('/').append(user);
        return this.check(uri.toString());
    }
    
    public boolean isPublicMember(final String organization, final String user) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/public_members");
        uri.append('/').append(user);
        return this.check(uri.toString());
    }
    
    public void showMembership(final String organization, final String user) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/public_members");
        uri.append('/').append(user);
        this.client.put(uri.toString());
    }
    
    public void hideMembership(final String organization, final String user) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/public_members");
        uri.append('/').append(user);
        this.client.delete(uri.toString());
    }
    
    public void removeMember(final String organization, final String user) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/members");
        uri.append('/').append(user);
        this.client.delete(uri.toString());
    }
    
    public enum RoleFilter
    {
        public static final RoleFilter all;
        public static final RoleFilter admin;
        public static final RoleFilter member;
        
        public static RoleFilter valueOf(final String name) {
            return Enum.valueOf(RoleFilter.class, name);
        }
        
        static {
            RoleFilter.all = new RoleFilter("all", 0);
            RoleFilter.admin = new RoleFilter("admin", 1);
            RoleFilter.member = new RoleFilter("member", 2);
            RoleFilter.$VALUES = new RoleFilter[] { RoleFilter.all, RoleFilter.admin, RoleFilter.member };
        }
    }
}
