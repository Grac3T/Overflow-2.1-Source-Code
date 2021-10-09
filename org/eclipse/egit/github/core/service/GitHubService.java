// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.client.NoSuchPageException;
import java.util.Collection;
import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.client.GitHubClient;

public abstract class GitHubService
{
    public static final String ACCEPT_RAW = "application/vnd.github.v3.raw+json";
    public static final String ACCEPT_HTML = "application/vnd.github.v3.html+json";
    public static final String ACCEPT_TEXT = "application/vnd.github.v3.text+json";
    public static final String ACCEPT_FULL = "application/vnd.github.v3.full+json";
    public static final String ACCEPT_PREVIEW_IRONMAN = "application/vnd.github.ironman-preview+json";
    public static final String ACCEPT_PREVIEW_LOKI = "application/vnd.github.loki-preview+json";
    public static final String ACCEPT_PREVIEW_DRAX = "application/vnd.github.drax-preview+json";
    protected final GitHubClient client;
    
    public GitHubService() {
        this(new GitHubClient());
    }
    
    public GitHubService(final GitHubClient client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null");
        }
        this.client = client;
    }
    
    public GitHubClient getClient() {
        return this.client;
    }
    
    protected GitHubRequest createRequest() {
        return new GitHubRequest();
    }
    
    protected <V> PagedRequest<V> createPagedRequest() {
        return (PagedRequest<V>)this.createPagedRequest(1, 100);
    }
    
    protected <V> PagedRequest<V> createPagedRequest(final int start, final int size) {
        return new PagedRequest<V>(start, size);
    }
    
    protected <V> PageIterator<V> createPageIterator(final PagedRequest<V> request) {
        return new PageIterator<V>(request, this.client);
    }
    
    protected <V> List<V> getAll(final PagedRequest<V> request) throws IOException {
        return (List<V>)this.getAll(this.createPageIterator(request));
    }
    
    protected <V> List<V> getAll(final PageIterator<V> iterator) throws IOException {
        final List<V> elements = new ArrayList<V>();
        try {
            while (iterator.hasNext()) {
                elements.addAll((Collection<? extends V>)iterator.next());
            }
        }
        catch (NoSuchPageException pageException) {
            throw pageException.getCause();
        }
        return elements;
    }
    
    protected boolean check(final String uri) throws IOException {
        try {
            this.client.get(this.createRequest().setUri(uri));
            return true;
        }
        catch (RequestException e) {
            if (e.getStatus() == 404) {
                return false;
            }
            throw e;
        }
    }
    
    protected String getId(final IRepositoryIdProvider provider) {
        if (provider == null) {
            throw new IllegalArgumentException("Repository provider cannot be null");
        }
        final String id = provider.generateId();
        if (id == null) {
            throw new IllegalArgumentException("Repository id cannot be null");
        }
        if (id.length() == 0) {
            throw new IllegalArgumentException("Repository id cannot be empty");
        }
        return id;
    }
    
    protected GitHubService verifyRepository(final String user, final String repository) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        if (repository.length() == 0) {
            throw new IllegalArgumentException("Repository cannot be empty");
        }
        return this;
    }
}
