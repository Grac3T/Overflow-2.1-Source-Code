// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.IResourceProvider;
import org.eclipse.egit.github.core.RepositoryHook;
import org.eclipse.egit.github.core.Contributor;
import org.eclipse.egit.github.core.RepositoryTag;
import org.eclipse.egit.github.core.RepositoryBranch;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.RepositoryId;
import java.util.Iterator;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.net.URLEncoder;
import org.eclipse.egit.github.core.SearchRepository;
import java.util.Collections;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.client.PageIterator;
import java.io.IOException;
import java.util.Map;
import org.eclipse.egit.github.core.Repository;
import java.util.List;
import org.eclipse.egit.github.core.client.GitHubClient;

public class RepositoryService extends GitHubService
{
    public static final String FIELD_NAME = "name";
    public static final String FIELD_DESCRIPTION = "description";
    public static final String FIELD_HOMEPAGE = "homepage";
    public static final String FIELD_PUBLIC = "public";
    public static final String FILTER_TYPE = "type";
    public static final String TYPE_PUBLIC = "public";
    public static final String TYPE_PRIVATE = "private";
    public static final String TYPE_MEMBER = "member";
    public static final String TYPE_ALL = "all";
    
    public RepositoryService() {
    }
    
    public RepositoryService(final GitHubClient client) {
        super(client);
    }
    
    public List<Repository> getRepositories() throws IOException {
        return (List<Repository>)this.getRepositories((Map)null);
    }
    
    public List<Repository> getRepositories(final Map<String, String> filterData) throws IOException {
        return (List<Repository>)this.getAll(this.pageRepositories(filterData));
    }
    
    public PageIterator<Repository> pageRepositories() {
        return (PageIterator<Repository>)this.pageRepositories(100);
    }
    
    public PageIterator<Repository> pageRepositories(final int size) {
        return (PageIterator<Repository>)this.pageRepositories(1, size);
    }
    
    public PageIterator<Repository> pageRepositories(final int start, final int size) {
        return (PageIterator<Repository>)this.pageRepositories((Map)null, start, size);
    }
    
    public PageIterator<Repository> pageRepositories(final Map<String, String> filterData) {
        return (PageIterator<Repository>)this.pageRepositories(filterData, 100);
    }
    
    public PageIterator<Repository> pageRepositories(final Map<String, String> filterData, final int size) {
        return (PageIterator<Repository>)this.pageRepositories(filterData, 1, size);
    }
    
    public PageIterator<Repository> pageRepositories(final Map<String, String> filterData, final int start, final int size) {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest(start, size);
        request.setUri("/user/repos");
        request.setParams(filterData);
        request.setType(new RepositoryService$1(this).getType());
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Repository> pageAllRepositories() {
        return (PageIterator<Repository>)this.pageAllRepositories(-1L);
    }
    
    public PageIterator<Repository> pageAllRepositories(final long since) {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest();
        request.setUri("/repositories");
        if (since > 0L) {
            request.setParams(Collections.singletonMap("since", Long.toString(since)));
        }
        request.setType(new RepositoryService$2(this).getType());
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    public List<Repository> getRepositories(final String user) throws IOException {
        return (List<Repository>)this.getAll(this.pageRepositories(user));
    }
    
    public PageIterator<Repository> pageRepositories(final String user) {
        return (PageIterator<Repository>)this.pageRepositories(user, 100);
    }
    
    public PageIterator<Repository> pageRepositories(final String user, final int size) {
        return (PageIterator<Repository>)this.pageRepositories(user, 1, size);
    }
    
    public PageIterator<Repository> pageRepositories(final String user, final int start, final int size) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        if (user.length() == 0) {
            throw new IllegalArgumentException("User cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/users");
        uri.append('/').append(user);
        uri.append("/repos");
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest(start, size);
        request.setUri(uri);
        request.setType(new RepositoryService$3(this).getType());
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    public List<Repository> getOrgRepositories(final String organization) throws IOException {
        return (List<Repository>)this.getOrgRepositories(organization, null);
    }
    
    public PageIterator<Repository> pageOrgRepositories(final String organization) {
        return (PageIterator<Repository>)this.pageOrgRepositories(organization, 100);
    }
    
    public PageIterator<Repository> pageOrgRepositories(final String organization, final int size) {
        return (PageIterator<Repository>)this.pageOrgRepositories(organization, 1, size);
    }
    
    public PageIterator<Repository> pageOrgRepositories(final String organization, final int start, final int size) {
        return (PageIterator<Repository>)this.pageOrgRepositories(organization, null, start, size);
    }
    
    public List<Repository> getOrgRepositories(final String organization, final Map<String, String> filterData) throws IOException {
        return (List<Repository>)this.getAll(this.pageOrgRepositories(organization, filterData));
    }
    
    public PageIterator<Repository> pageOrgRepositories(final String organization, final Map<String, String> filterData) {
        return (PageIterator<Repository>)this.pageOrgRepositories(organization, filterData, 100);
    }
    
    public PageIterator<Repository> pageOrgRepositories(final String organization, final Map<String, String> filterData, final int size) {
        return (PageIterator<Repository>)this.pageOrgRepositories(organization, filterData, 1, size);
    }
    
    public PageIterator<Repository> pageOrgRepositories(final String organization, final Map<String, String> filterData, final int start, final int size) {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/repos");
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest(start, size);
        request.setParams(filterData);
        request.setUri(uri);
        request.setType(new RepositoryService$4(this).getType());
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    public List<SearchRepository> searchRepositories(final String query) throws IOException {
        return (List<SearchRepository>)this.searchRepositories(query, -1);
    }
    
    public List<SearchRepository> searchRepositories(final String query, final int startPage) throws IOException {
        return (List<SearchRepository>)this.searchRepositories(query, null, startPage);
    }
    
    public List<SearchRepository> searchRepositories(final String query, final String language) throws IOException {
        return (List<SearchRepository>)this.searchRepositories(query, language, -1);
    }
    
    public List<SearchRepository> searchRepositories(final String query, final String language, final int startPage) throws IOException {
        if (query == null) {
            throw new IllegalArgumentException("Query cannot be null");
        }
        if (query.length() == 0) {
            throw new IllegalArgumentException("Query cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/legacy/repos/search");
        final String encodedQuery = URLEncoder.encode(query, "UTF-8").replace("+", "%20").replace(".", "%2E");
        uri.append('/').append(encodedQuery);
        final PagedRequest<SearchRepository> request = (PagedRequest<SearchRepository>)this.createPagedRequest();
        final Map<String, String> params = new HashMap<String, String>(2, 1.0f);
        if (language != null && language.length() > 0) {
            params.put("language", language);
        }
        if (startPage > 0) {
            params.put("start_page", Integer.toString(startPage));
        }
        if (!params.isEmpty()) {
            request.setParams(params);
        }
        request.setUri(uri);
        request.setType(RepositoryContainer.class);
        return (List<SearchRepository>)this.getAll((PagedRequest)request);
    }
    
    public List<SearchRepository> searchRepositories(final Map<String, String> params) throws IOException {
        return (List<SearchRepository>)this.searchRepositories(params, -1);
    }
    
    public List<SearchRepository> searchRepositories(final Map<String, String> queryParams, final int startPage) throws IOException {
        if (queryParams == null) {
            throw new IllegalArgumentException("Params cannot be null");
        }
        if (queryParams.isEmpty()) {
            throw new IllegalArgumentException("Params cannot be empty");
        }
        final StringBuilder query = new StringBuilder();
        for (final Map.Entry<String, String> param : queryParams.entrySet()) {
            query.append(param.getKey()).append(':').append(param.getValue()).append(' ');
        }
        return (List<SearchRepository>)this.searchRepositories(query.toString(), startPage);
    }
    
    public Repository createRepository(final Repository repository) throws IOException {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        return this.client.post("/user/repos", repository, Repository.class);
    }
    
    public Repository createRepository(final String organization, final Repository repository) throws IOException {
        if (organization == null) {
            throw new IllegalArgumentException("Organization cannot be null");
        }
        if (organization.length() == 0) {
            throw new IllegalArgumentException("Organization cannot be empty");
        }
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(organization);
        uri.append("/repos");
        return this.client.post(uri.toString(), repository, Repository.class);
    }
    
    public Repository getRepository(final String owner, final String name) throws IOException {
        return this.getRepository(RepositoryId.create(owner, name));
    }
    
    public Repository getRepository(final IRepositoryIdProvider provider) throws IOException {
        final String id = this.getId(provider);
        final GitHubRequest request = this.createRequest();
        request.setUri("/repos/" + id);
        request.setType(Repository.class);
        return (Repository)this.client.get(request).getBody();
    }
    
    protected PagedRequest<Repository> createPagedForkRequest(final IRepositoryIdProvider repository, final int start, final int size) {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/forks");
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedRequest(start, size);
        request.setUri(uri);
        request.setType(new RepositoryService$5(this).getType());
        return request;
    }
    
    public List<Repository> getForks(final IRepositoryIdProvider repository) throws IOException {
        return (List<Repository>)this.getAll(this.pageForks(repository));
    }
    
    public PageIterator<Repository> pageForks(final IRepositoryIdProvider repository) {
        return (PageIterator<Repository>)this.pageForks(repository, 100);
    }
    
    public PageIterator<Repository> pageForks(final IRepositoryIdProvider repository, final int size) {
        return (PageIterator<Repository>)this.pageForks(repository, 1, size);
    }
    
    public PageIterator<Repository> pageForks(final IRepositoryIdProvider repository, final int start, final int size) {
        final PagedRequest<Repository> request = (PagedRequest<Repository>)this.createPagedForkRequest(repository, start, size);
        return (PageIterator<Repository>)this.createPageIterator((PagedRequest)request);
    }
    
    public Repository editRepository(final Repository repository) throws IOException {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        final String repoId = this.getId((IRepositoryIdProvider)repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        return this.client.post(uri.toString(), repository, Repository.class);
    }
    
    public Repository editRepository(final String owner, final String name, final Map<String, Object> fields) throws IOException {
        this.verifyRepository(owner, name);
        if (fields == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(owner).append('/').append(name);
        return this.client.post(uri.toString(), fields, Repository.class);
    }
    
    public Repository editRepository(final IRepositoryIdProvider provider, final Map<String, Object> fields) throws IOException {
        final String id = this.getId(provider);
        if (fields == null) {
            throw new IllegalArgumentException("Fields cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        return this.client.post(uri.toString(), fields, Repository.class);
    }
    
    public Repository forkRepository(final IRepositoryIdProvider repository) throws IOException {
        return this.forkRepository(repository, null);
    }
    
    public Repository forkRepository(final IRepositoryIdProvider repository, final String organization) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/forks");
        if (organization != null) {
            uri.append("?org=").append(organization);
        }
        return this.client.post(uri.toString(), null, Repository.class);
    }
    
    public Map<String, Long> getLanguages(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/languages");
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(new RepositoryService$6(this).getType());
        return (Map<String, Long>)this.client.get(request).getBody();
    }
    
    public List<RepositoryBranch> getBranches(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/branches");
        final PagedRequest<RepositoryBranch> request = (PagedRequest<RepositoryBranch>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new RepositoryService$7(this).getType());
        return (List<RepositoryBranch>)this.getAll((PagedRequest)request);
    }
    
    public List<RepositoryTag> getTags(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/tags");
        final PagedRequest<RepositoryTag> request = (PagedRequest<RepositoryTag>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new RepositoryService$8(this).getType());
        return (List<RepositoryTag>)this.getAll((PagedRequest)request);
    }
    
    public List<Contributor> getContributors(final IRepositoryIdProvider repository, final boolean includeAnonymous) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/contributors");
        final PagedRequest<Contributor> request = (PagedRequest<Contributor>)this.createPagedRequest();
        request.setUri(uri);
        if (includeAnonymous) {
            request.setParams(Collections.singletonMap("anon", "1"));
        }
        request.setType(new RepositoryService$9(this).getType());
        return (List<Contributor>)this.getAll((PagedRequest)request);
    }
    
    public List<RepositoryHook> getHooks(final IRepositoryIdProvider repository) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/hooks");
        final PagedRequest<RepositoryHook> request = (PagedRequest<RepositoryHook>)this.createPagedRequest();
        request.setUri(uri);
        request.setType(new RepositoryService$10(this).getType());
        return (List<RepositoryHook>)this.getAll((PagedRequest)request);
    }
    
    public RepositoryHook getHook(final IRepositoryIdProvider repository, final int hookId) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/hooks");
        uri.append('/').append(hookId);
        final GitHubRequest request = this.createRequest();
        request.setType(RepositoryHook.class);
        request.setUri(uri);
        return (RepositoryHook)this.client.get(request).getBody();
    }
    
    public RepositoryHook createHook(final IRepositoryIdProvider repository, final RepositoryHook hook) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/hooks");
        return this.client.post(uri.toString(), hook, RepositoryHook.class);
    }
    
    public RepositoryHook editHook(final IRepositoryIdProvider repository, final RepositoryHook hook) throws IOException {
        final String id = this.getId(repository);
        if (hook == null) {
            throw new IllegalArgumentException("Hook cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/hooks");
        uri.append('/').append(hook.getId());
        return this.client.post(uri.toString(), hook, RepositoryHook.class);
    }
    
    public void deleteHook(final IRepositoryIdProvider repository, final int hookId) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/hooks");
        uri.append('/').append(hookId);
        this.client.delete(uri.toString());
    }
    
    public void testHook(final IRepositoryIdProvider repository, final int hookId) throws IOException {
        final String id = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/hooks");
        uri.append('/').append(hookId);
        uri.append("/test");
        this.client.post(uri.toString());
    }
    
    private static class RepositoryContainer implements IResourceProvider<SearchRepository>
    {
        private List<SearchRepository> repositories;
        
        @Override
        public List<SearchRepository> getResources() {
            return (List<SearchRepository>)this.repositories;
        }
    }
}
