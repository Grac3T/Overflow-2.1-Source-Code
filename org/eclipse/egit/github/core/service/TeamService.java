// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.TeamMembership;
import org.eclipse.egit.github.core.User;
import java.util.Map;
import java.util.HashMap;
import org.eclipse.egit.github.core.client.PagedRequest;
import java.util.List;
import java.io.IOException;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.Team;
import org.eclipse.egit.github.core.client.GitHubClient;

public class TeamService extends GitHubService
{
    public TeamService() {
    }
    
    public TeamService(final GitHubClient client) {
        super(client);
    }
    
    public Team getTeam(final int id) throws IOException {
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(Team.class);
        return (Team)this.client.get(request).getBody();
    }
    
    public List<Team> getTeams(final String organization) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/teams");
        final PagedRequest<Team> request = (PagedRequest<Team>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new TeamService$1(this).getType());
        return (List<Team>)this.getAll((PagedRequest)request);
    }
    
    public Team createTeam(final String organization, final Team team) throws IOException {
        return this.createTeam(organization, team, null);
    }
    
    public Team createTeam(final String organization, final Team team, final List<String> repoNames) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/teams");
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", team.getName());
        params.put("permission", team.getPermission());
        if (repoNames != null) {
            params.put("repo_names", repoNames);
        }
        return this.client.post(uri.toString(), params, Team.class);
    }
    
    public Team editTeam(final Team team) throws IOException {
        if (team == null) {
            throw new IllegalArgumentException("Team cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(team.getId());
        return this.client.post(uri.toString(), team, Team.class);
    }
    
    public void deleteTeam(final int id) throws IOException {
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        this.client.delete(uri.toString());
    }
    
    public List<User> getMembers(final int id) throws IOException {
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/members");
        final PagedRequest<User> request = (PagedRequest<User>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new TeamService$2(this).getType());
        return (List<User>)this.getAll((PagedRequest)request);
    }
    
    public boolean isMember(final int id, final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/members");
        uri.append('/').append(user);
        return this.check(uri.toString());
    }
    
    public void addMember(final int id, final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/members");
        uri.append('/').append(user);
        this.client.put(uri.toString());
    }
    
    public void removeMember(final int id, final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/members");
        uri.append('/').append(user);
        this.client.delete(uri.toString());
    }
    
    public TeamMembership getMembership(final int id, final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/memberships");
        uri.append('/').append(user);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(TeamMembership.class);
        return (TeamMembership)this.client.get(request).getBody();
    }
    
    public TeamMembership addMembership(final int id, final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/memberships");
        uri.append('/').append(user);
        return this.client.put(uri.toString(), null, TeamMembership.class);
    }
    
    public void removeMembership(final int id, final String user) throws IOException {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/memberships");
        uri.append('/').append(user);
        this.client.delete(uri.toString());
    }
    
    public List<Repository> getRepositories(final int id) throws IOException {
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/repos");
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new TeamService$3(this).getType());
        return (List<Repository>)this.getAll((PagedRequest)request);
    }
    
    public boolean isTeamRepository(final int id, final IRepositoryIdProvider repository) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/repos");
        uri.append('/').append(repoId);
        return this.check(uri.toString());
    }
    
    public void addRepository(final int id, final IRepositoryIdProvider repository) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/repos");
        uri.append('/').append(repoId);
        this.client.put(uri.toString());
    }
    
    public void removeRepository(final int id, final IRepositoryIdProvider repository) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/teams");
        uri.append('/').append(id);
        uri.append("/repos");
        uri.append('/').append(repoId);
        this.client.delete(uri.toString());
    }
    
    public List<Team> getTeams(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/teams");
        final PagedRequest<Team> request = (PagedRequest<Team>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new TeamService$4(this).getType());
        return (List<Team>)this.getAll((PagedRequest)request);
    }
}
