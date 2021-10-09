// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.PageIterator;
import java.io.IOException;
import java.util.List;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class StargazerService extends GitHubService
{
    public StargazerService() {
    }
    
    public StargazerService(final GitHubClient client) {
        super(client);
    }
    
    protected PagedRequest<User> createStargazerRequest(final IRepositoryIdProvider repository, final int start, final int size) {
        final String id = this.getId(repository);
        final PagedRequest<User> request = (PagedRequest<User>)this.createPagedRequest(start, size);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/stargazers");
        request.setUri(uri);
        request.setType(new StargazerService$1(this).getType());
        return request;
    }
    
    public List<User> getStargazers(final IRepositoryIdProvider repository) throws IOException {
        final PagedRequest<User> request = (PagedRequest<User>)this.createStargazerRequest(repository, 1, 100);
        return (List<User>)this.getAll((PagedRequest)request);
    }
    
    public PageIterator<User> pageStargazers(final IRepositoryIdProvider repository) {
        return (PageIterator<User>)this.pageStargazers(repository, 100);
    }
    
    public PageIterator<User> pageStargazers(final IRepositoryIdProvider repository, final int size) {
        return (PageIterator<User>)this.pageStargazers(repository, 1, size);
    }
    
    public PageIterator<User> pageStargazers(final IRepositoryIdProvider repository, final int start, final int size) {
        final PagedRequest<User> request = (PagedRequest<User>)this.createStargazerRequest(repository, start, size);
        return (PageIterator<User>)this.createPageIterator((PagedRequest)request);
    }
    
    protected PagedRequest<Repository> createStarredRequest(final String user, final int start, final int size) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest(start, size);
        final StringBuilder uri = new StringBuilder("/users");
        uri.append('/').append(user);
        uri.append("/starred");
        request.setUri(uri);
        request.setType(new StargazerService$2(this).getType());
        return request;
    }
    
    protected PagedRequest<Repository> createStarredRequest(final int start, final int size) {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest(start, size);
        request.setUri("/user/starred");
        request.setType(new StargazerService$3(this).getType());
        return request;
    }
    
    public List<Repository> getStarred(final String user) throws IOException {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createStarredRequest(user, 1, 100);
        return (List<Repository>)this.getAll((PagedRequest)request);
    }
    
    public PageIterator<Repository> pageStarred(final String user) throws IOException {
        return (PageIterator<Repository>)this.pageStarred(user, 100);
    }
    
    public PageIterator<Repository> pageStarred(final String user, final int size) throws IOException {
        return (PageIterator<Repository>)this.pageStarred(user, 1, size);
    }
    
    public PageIterator<Repository> pageStarred(final String user, final int start, final int size) throws IOException {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createStarredRequest(user, start, size);
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    public List<Repository> getStarred() throws IOException {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createStarredRequest(1, 100);
        return (List<Repository>)this.getAll((PagedRequest)request);
    }
    
    public PageIterator<Repository> pageStarred() throws IOException {
        return (PageIterator<Repository>)this.pageStarred(100);
    }
    
    public PageIterator<Repository> pageStarred(final int size) throws IOException {
        return (PageIterator<Repository>)this.pageStarred(1, size);
    }
    
    public PageIterator<Repository> pageStarred(final int start, final int size) throws IOException {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createStarredRequest(start, size);
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    public boolean isStarring(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/user");
        uri.append("/starred");
        uri.append('/').append(id);
        return this.check(uri.toString());
    }
    
    public void star(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/user");
        uri.append("/starred");
        uri.append('/').append(id);
        this.client.put(uri.toString());
    }
    
    public void unstar(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/user");
        uri.append("/starred");
        uri.append('/').append(id);
        this.client.delete(uri.toString());
    }
}
