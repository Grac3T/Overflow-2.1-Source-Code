// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import java.io.IOException;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.Key;
import java.util.List;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class DeployKeyService extends GitHubService
{
    public DeployKeyService() {
    }
    
    public DeployKeyService(final GitHubClient client) {
        super(client);
    }
    
    public List<Key> getKeys(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/keys");
        final PagedRequest<Key> request = (PagedRequest<Key>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new DeployKeyService$1(this).getType());
        return (List<Key>)this.getAll((PagedRequest)request);
    }
    
    public Key getKey(final IRepositoryIdProvider repository, final int id) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/keys");
        uri.append('/').append(id);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType((Type)Key.class);
        return (Key)this.client.get(request).getBody();
    }
    
    public Key createKey(final IRepositoryIdProvider repository, final Key key) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/keys");
        return this.client.post(uri.toString(), key, Key.class);
    }
    
    public Key editKey(final IRepositoryIdProvider repository, final Key key) throws IOException {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/keys");
        uri.append('/').append(key.getId());
        return this.client.post(uri.toString(), key, Key.class);
    }
    
    public void deleteKey(final IRepositoryIdProvider repository, final int id) throws IOException {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/keys");
        uri.append('/').append(id);
        this.client.delete(uri.toString());
    }
}
