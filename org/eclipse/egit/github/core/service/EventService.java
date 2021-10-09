// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.client.GitHubClient;

public class EventService extends GitHubService
{
    public EventService() {
    }
    
    public EventService(final GitHubClient client) {
        super(client);
    }
    
    public PageIterator<Event> pagePublicEvents() {
        return (PageIterator<Event>)this.pagePublicEvents(100);
    }
    
    public PageIterator<Event> pagePublicEvents(final int size) {
        return (PageIterator<Event>)this.pagePublicEvents(1, size);
    }
    
    public PageIterator<Event> pagePublicEvents(final int start, final int size) {
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createPagedRequest(start, size);
        request.setUri("/events");
        request.setType(new EventService$1(this).getType());
        return (PageIterator<Event>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Event> pageEvents(final IRepositoryIdProvider repository) {
        return (PageIterator<Event>)this.pageEvents(repository, 100);
    }
    
    public PageIterator<Event> pageEvents(final IRepositoryIdProvider repository, final int size) {
        return (PageIterator<Event>)this.pageEvents(repository, 1, size);
    }
    
    public PageIterator<Event> pageEvents(final IRepositoryIdProvider repository, final int start, final int size) {
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createRepoEventRequest(repository, start, size);
        return (PageIterator<Event>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Event> pageNetworkEvents(final IRepositoryIdProvider repository) {
        return (PageIterator<Event>)this.pageNetworkEvents(repository, 100);
    }
    
    public PageIterator<Event> pageNetworkEvents(final IRepositoryIdProvider repository, final int size) {
        return (PageIterator<Event>)this.pageNetworkEvents(repository, 1, size);
    }
    
    public PageIterator<Event> pageNetworkEvents(final IRepositoryIdProvider repository, final int start, final int size) {
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createNetworkRepoEventRequest(repository, start, size);
        return (PageIterator<Event>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Event> pageOrgEvents(final String org) {
        return (PageIterator<Event>)this.pageOrgEvents(org, 100);
    }
    
    public PageIterator<Event> pageOrgEvents(final String org, final int size) {
        return (PageIterator<Event>)this.pageOrgEvents(org, 1, size);
    }
    
    public PageIterator<Event> pageOrgEvents(final String org, final int start, final int size) {
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createOrgEventRequest(org, start, size);
        return (PageIterator<Event>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Event> pageUserReceivedEvents(final String user) {
        return (PageIterator<Event>)this.pageUserReceivedEvents(user, false);
    }
    
    public PageIterator<Event> pageUserReceivedEvents(final String user, final boolean isPublic) {
        return (PageIterator<Event>)this.pageUserReceivedEvents(user, isPublic, 100);
    }
    
    public PageIterator<Event> pageUserReceivedEvents(final String user, final boolean isPublic, final int size) {
        return (PageIterator<Event>)this.pageUserReceivedEvents(user, isPublic, 1, size);
    }
    
    public PageIterator<Event> pageUserReceivedEvents(final String user, final boolean isPublic, final int start, final int size) {
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createUserReceivedEventRequest(user, isPublic, start, size);
        return (PageIterator<Event>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Event> pageUserEvents(final String user) {
        return (PageIterator<Event>)this.pageUserEvents(user, false);
    }
    
    public PageIterator<Event> pageUserEvents(final String user, final boolean isPublic) {
        return (PageIterator<Event>)this.pageUserEvents(user, isPublic, 100);
    }
    
    public PageIterator<Event> pageUserEvents(final String user, final boolean isPublic, final int size) {
        return (PageIterator<Event>)this.pageUserEvents(user, isPublic, 1, size);
    }
    
    public PageIterator<Event> pageUserEvents(final String user, final boolean isPublic, final int start, final int size) {
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createUserEventRequest(user, isPublic, start, size);
        return (PageIterator<Event>)this.createPageIterator((PagedRequest)request);
    }
    
    public PageIterator<Event> pageUserOrgEvents(final String user, final String org) {
        return (PageIterator<Event>)this.pageUserOrgEvents(user, org, 100);
    }
    
    public PageIterator<Event> pageUserOrgEvents(final String user, final String org, final int size) {
        return (PageIterator<Event>)this.pageUserOrgEvents(user, org, 1, size);
    }
    
    public PageIterator<Event> pageUserOrgEvents(final String user, final String org, final int start, final int size) {
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createUserOrgEventRequest(user, org, start, size);
        return (PageIterator<Event>)this.createPageIterator((PagedRequest)request);
    }
    
    protected PagedRequest<Event> createRepoEventRequest(final IRepositoryIdProvider repository, final int start, final int size) {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/events");
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createPagedRequest(start, size);
        request.setUri(uri).setType(new EventService$2(this).getType());
        return request;
    }
    
    protected PagedRequest<Event> createNetworkRepoEventRequest(final IRepositoryIdProvider repository, final int start, final int size) {
        final String repoId = this.getId(repository);
        final StringBuilder uri = new StringBuilder("/networks");
        uri.append('/').append(repoId);
        uri.append("/events");
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createPagedRequest(start, size);
        request.setUri(uri).setType(new EventService$3(this).getType());
        return request;
    }
    
    protected PagedRequest<Event> createOrgEventRequest(final String org, final int start, final int size) {
        final StringBuilder uri = new StringBuilder("/orgs");
        uri.append('/').append(org);
        uri.append("/events");
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createPagedRequest(start, size);
        request.setUri(uri).setType(new EventService$4(this).getType());
        return request;
    }
    
    protected PagedRequest<Event> createUserReceivedEventRequest(final String user, final boolean isPublic, final int start, final int size) {
        final StringBuilder uri = new StringBuilder("/users");
        uri.append('/').append(user);
        uri.append("/received_events");
        if (isPublic) {
            uri.append("/public");
        }
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createPagedRequest(start, size);
        request.setUri(uri).setType(new EventService$5(this).getType());
        return request;
    }
    
    protected PagedRequest<Event> createUserEventRequest(final String user, final boolean isPublic, final int start, final int size) {
        final StringBuilder uri = new StringBuilder("/users");
        uri.append('/').append(user);
        uri.append("/events");
        if (isPublic) {
            uri.append("/public");
        }
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createPagedRequest(start, size);
        request.setUri(uri).setType(new EventService$6(this).getType());
        return request;
    }
    
    protected PagedRequest<Event> createUserOrgEventRequest(final String user, final String org, final int start, final int size) {
        final StringBuilder uri = new StringBuilder("/users");
        uri.append('/').append(user);
        uri.append("/events").append("/orgs");
        uri.append('/').append(org);
        final PagedRequest<Event> request = (PagedRequest<Event>)this.createPagedRequest(start, size);
        request.setUri(uri).setType(new EventService$7(this).getType());
        return request;
    }
}
