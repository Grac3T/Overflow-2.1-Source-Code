// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import java.io.IOException;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.User;
import java.util.List;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class CollaboratorService extends GitHubService
{
    public CollaboratorService() {
    }
    
    public CollaboratorService(final GitHubClient client) {
        super(client);
    }
    
    public List<User> getCollaborators(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/collaborators");
        final PagedRequest<User> request = (PagedRequest<User>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new CollaboratorService$1(this).getType());
        return (List<User>)this.getAll((PagedRequest)request);
    }
    
    protected String createUpdateUri(final IRepositoryIdProvider repository, final String user) {
        final String id = this.getId(repository);
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/collaborators");
        uri.append('/').append(user);
        return uri.toString();
    }
    
    public boolean isCollaborator(final IRepositoryIdProvider repository, final String user) throws IOException {
        return this.check(this.createUpdateUri(repository, user));
    }
    
    public void addCollaborator(final IRepositoryIdProvider repository, final String user) throws IOException {
        this.client.put(this.createUpdateUri(repository, user));
    }
    
    public void removeCollaborator(final IRepositoryIdProvider repository, final String user) throws IOException {
        this.client.delete(this.createUpdateUri(repository, user));
    }
}
