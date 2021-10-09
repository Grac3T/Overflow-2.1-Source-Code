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

@Deprecated
public class WatcherService extends GitHubService
{
    public WatcherService() {
    }
    
    public WatcherService(final GitHubClient client) {
        super(client);
    }
    
    @Deprecated
    protected PagedRequest<User> createWatcherRequest(final IRepositoryIdProvider repository, final int start, final int size) {
        final String id = this.getId(repository);
        final PagedRequest<User> request = (PagedRequest<User>)this.createPagedRequest(start, size);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/watchers");
        request.setUri(uri);
        request.setType(new WatcherService$1(this).getType());
        return request;
    }
    
    @Deprecated
    public List<User> getWatchers(final IRepositoryIdProvider repository) throws IOException {
        final PagedRequest<User> request = (PagedRequest<User>)this.createWatcherRequest(repository, 1, 100);
        return (List<User>)this.getAll((PagedRequest)request);
    }
    
    @Deprecated
    public PageIterator<User> pageWatchers(final IRepositoryIdProvider repository) {
        return (PageIterator<User>)this.pageWatchers(repository, 100);
    }
    
    @Deprecated
    public PageIterator<User> pageWatchers(final IRepositoryIdProvider repository, final int size) {
        return (PageIterator<User>)this.pageWatchers(repository, 1, size);
    }
    
    @Deprecated
    public PageIterator<User> pageWatchers(final IRepositoryIdProvider repository, final int start, final int size) {
        final PagedRequest<User> request = (PagedRequest<User>)this.createWatcherRequest(repository, start, size);
        return (PageIterator<User>)this.createPageIterator((PagedRequest)request);
    }
    
    @Deprecated
    protected PagedRequest<Repository> createWatchedRequest(final String user, final int start, final int size) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest(start, size);
        final StringBuilder uri = new StringBuilder("/users");
        uri.append('/').append(user);
        uri.append("/watched");
        request.setUri(uri);
        request.setType(new WatcherService$2(this).getType());
        return request;
    }
    
    @Deprecated
    protected PagedRequest<Repository> createWatchedRequest(final int start, final int size) {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest(start, size);
        request.setUri("/user/watched");
        request.setType(new WatcherService$3(this).getType());
        return request;
    }
    
    @Deprecated
    public List<Repository> getWatched(final String user) throws IOException {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createWatchedRequest(user, 1, 100);
        return (List<Repository>)this.getAll((PagedRequest)request);
    }
    
    @Deprecated
    public PageIterator<Repository> pageWatched(final String user) throws IOException {
        return (PageIterator<Repository>)this.pageWatched(user, 100);
    }
    
    @Deprecated
    public PageIterator<Repository> pageWatched(final String user, final int size) throws IOException {
        return (PageIterator<Repository>)this.pageWatched(user, 1, size);
    }
    
    @Deprecated
    public PageIterator<Repository> pageWatched(final String user, final int start, final int size) throws IOException {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createWatchedRequest(user, start, size);
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    @Deprecated
    public List<Repository> getWatched() throws IOException {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createWatchedRequest(1, 100);
        return (List<Repository>)this.getAll((PagedRequest)request);
    }
    
    @Deprecated
    public PageIterator<Repository> pageWatched() throws IOException {
        return (PageIterator<Repository>)this.pageWatched(100);
    }
    
    @Deprecated
    public PageIterator<Repository> pageWatched(final int size) throws IOException {
        return (PageIterator<Repository>)this.pageWatched(1, size);
    }
    
    @Deprecated
    public PageIterator<Repository> pageWatched(final int start, final int size) throws IOException {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createWatchedRequest(start, size);
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    @Deprecated
    public boolean isWatching(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/user");
        uri.append("/watched");
        uri.append('/').append(id);
        return this.check(uri.toString());
    }
    
    @Deprecated
    public void watch(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/user");
        uri.append("/watched");
        uri.append('/').append(id);
        this.client.put(uri.toString());
    }
    
    @Deprecated
    public void unwatch(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/user");
        uri.append("/watched");
        uri.append('/').append(id);
        this.client.delete(uri.toString());
    }
}
