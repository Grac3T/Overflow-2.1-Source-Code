// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import java.util.List;
import java.io.IOException;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Collections;
import org.eclipse.egit.github.core.RepositoryContents;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class ContentsService extends GitHubService
{
    public ContentsService() {
    }
    
    public ContentsService(final GitHubClient client) {
        super(client);
    }
    
    public RepositoryContents getReadme(final IRepositoryIdProvider repository) throws Exception {
        return this.getReadme(repository, null);
    }
    
    public RepositoryContents getReadme(final IRepositoryIdProvider repository, final String ref) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/readme");
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        if (ref != null && ref.length() > 0) {
            request.setParams((Map)Collections.singletonMap("ref", ref));
        }
        request.setType((Type)RepositoryContents.class);
        return (RepositoryContents)this.client.get(request).getBody();
    }
    
    public List<RepositoryContents> getContents(final IRepositoryIdProvider repository) throws IOException {
        return (List<RepositoryContents>)this.getContents(repository, null);
    }
    
    public List<RepositoryContents> getContents(final IRepositoryIdProvider repository, final String path) throws IOException {
        return (List<RepositoryContents>)this.getContents(repository, path, null);
    }
    
    public List<RepositoryContents> getContents(final IRepositoryIdProvider repository, final String path, final String ref) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/contents");
        if (path != null && path.length() > 0) {
            if (path.charAt(0) != '/') {
                uri.append('/');
            }
            uri.append(path);
        }
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType((Type)RepositoryContents.class);
        request.setArrayType(new ContentsService$1(this).getType());
        if (ref != null && ref.length() > 0) {
            request.setParams((Map)Collections.singletonMap("ref", ref));
        }
        final Object body = this.client.get(request).getBody();
        if (body instanceof RepositoryContents) {
            return Collections.singletonList(body);
        }
        return (List<RepositoryContents>)body;
    }
}
