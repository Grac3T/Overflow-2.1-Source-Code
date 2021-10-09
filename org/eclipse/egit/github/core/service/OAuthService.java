// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import java.util.Map;
import java.util.Collections;
import java.util.Collection;
import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import java.io.IOException;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.Authorization;
import java.util.List;
import org.eclipse.egit.github.core.client.GitHubClient;

public class OAuthService extends GitHubService
{
    public OAuthService() {
    }
    
    public OAuthService(final GitHubClient client) {
        super(client);
    }
    
    public List<Authorization> getAuthorizations() throws IOException {
        final PagedRequest<Authorization> request = (PagedRequest<Authorization>)this.createPagedRequest();
        request.setUri("/authorizations");
        request.setType(new OAuthService$1(this).getType());
        return (List<Authorization>)this.getAll((PagedRequest)request);
    }
    
    public Authorization getAuthorization(final int id) throws IOException {
        final GitHubRequest request = this.createRequest();
        final StringBuilder uri = new StringBuilder("/authorizations");
        uri.append('/').append(id);
        request.setUri(uri);
        request.setType(Authorization.class);
        return (Authorization)this.client.get(request).getBody();
    }
    
    public void deleteAuthorization(final int id) throws IOException {
        final StringBuilder uri = new StringBuilder("/authorizations");
        uri.append('/').append(id);
        this.client.delete(uri.toString());
    }
    
    public Authorization createAuthorization(final Authorization authorization) throws IOException {
        return this.client.post("/authorizations", authorization, Authorization.class);
    }
    
    public Authorization addScopes(final int id, final Collection<String> scopes) throws IOException {
        final StringBuilder uri = new StringBuilder("/authorizations");
        uri.append('/').append(id);
        final Map<String, Collection<String>> params = Collections.singletonMap("add_scopes", scopes);
        return this.client.post(uri.toString(), params, Authorization.class);
    }
    
    public Authorization removeScopes(final int id, final Collection<String> scopes) throws IOException {
        final StringBuilder uri = new StringBuilder("/authorizations");
        uri.append('/').append(id);
        final Map<String, Collection<String>> params = Collections.singletonMap("remove_scopes", scopes);
        return this.client.post(uri.toString(), params, Authorization.class);
    }
    
    public Authorization setScopes(final int id, final Collection<String> scopes) throws IOException {
        final StringBuilder uri = new StringBuilder("/authorizations");
        uri.append('/').append(id);
        final Map<String, Collection<String>> params = Collections.singletonMap("scopes", scopes);
        return this.client.post(uri.toString(), params, Authorization.class);
    }
}
