// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.service;

import org.eclipse.egit.github.core.client.GitHubRequest;
import java.lang.reflect.Type;
import org.eclipse.egit.github.core.client.PagedRequest;
import java.util.Collections;
import java.io.IOException;
import org.eclipse.egit.github.core.Milestone;
import java.util.List;
import org.eclipse.egit.github.core.IRepositoryIdProvider;
import org.eclipse.egit.github.core.client.GitHubClient;

public class MilestoneService extends GitHubService
{
    public MilestoneService() {
    }
    
    public MilestoneService(final GitHubClient client) {
        super(client);
    }
    
    public List<Milestone> getMilestones(final IRepositoryIdProvider repository, final String state) throws IOException {
        final String repoId = this.getId(repository);
        return (List<Milestone>)this.getMilestones(repoId, state);
    }
    
    public List<Milestone> getMilestones(final String user, final String repository, final String state) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return (List<Milestone>)this.getMilestones(repoId, state);
    }
    
    private List<Milestone> getMilestones(final String id, final String state) throws IOException {
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/milestones");
        final PagedRequest<Milestone> request = (PagedRequest<Milestone>)this.createPagedRequest();
        if (state != null) {
            request.setParams(Collections.singletonMap("state", state));
        }
        request.setUri(uri).setType(new MilestoneService$1(this).getType());
        return (List<Milestone>)this.getAll((PagedRequest)request);
    }
    
    public Milestone createMilestone(final IRepositoryIdProvider repository, final Milestone milestone) throws IOException {
        final String repoId = this.getId(repository);
        return this.createMilestone(repoId, milestone);
    }
    
    public Milestone createMilestone(final String user, final String repository, final Milestone milestone) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.createMilestone(repoId, milestone);
    }
    
    private Milestone createMilestone(final String id, final Milestone milestone) throws IOException {
        if (milestone == null) {
            throw new IllegalArgumentException("Milestone cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/milestones");
        return this.client.post(uri.toString(), milestone, Milestone.class);
    }
    
    public Milestone getMilestone(final IRepositoryIdProvider repository, final int number) throws IOException {
        return this.getMilestone(repository, Integer.toString(number));
    }
    
    public Milestone getMilestone(final IRepositoryIdProvider repository, final String number) throws IOException {
        final String repoId = this.getId(repository);
        return this.getMilestone(repoId, number);
    }
    
    public Milestone getMilestone(final String user, final String repository, final int number) throws IOException {
        return this.getMilestone(user, repository, Integer.toString(number));
    }
    
    public Milestone getMilestone(final String user, final String repository, final String number) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        return this.getMilestone(repoId, number);
    }
    
    private Milestone getMilestone(final String id, final String number) throws IOException {
        if (number == null) {
            throw new IllegalArgumentException("Milestone cannot be null");
        }
        if (number.length() == 0) {
            throw new IllegalArgumentException("Milestone cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/milestones");
        uri.append('/').append(number);
        final GitHubRequest request = this.createRequest();
        request.setUri(uri);
        request.setType(Milestone.class);
        return (Milestone)this.client.get(request).getBody();
    }
    
    public void deleteMilestone(final IRepositoryIdProvider repository, final int milestone) throws IOException {
        this.deleteMilestone(repository, Integer.toString(milestone));
    }
    
    public void deleteMilestone(final IRepositoryIdProvider repository, final String milestone) throws IOException {
        final String repoId = this.getId(repository);
        this.deleteMilestone(repoId, milestone);
    }
    
    public void deleteMilestone(final String user, final String repository, final int milestone) throws IOException {
        this.deleteMilestone(user, repository, Integer.toString(milestone));
    }
    
    public void deleteMilestone(final String user, final String repository, final String milestone) throws IOException {
        this.verifyRepository(user, repository);
        final String repoId = user + '/' + repository;
        this.deleteMilestone(repoId, milestone);
    }
    
    private void deleteMilestone(final String id, final String milestone) throws IOException {
        if (milestone == null) {
            throw new IllegalArgumentException("Milestone cannot be null");
        }
        if (milestone.length() == 0) {
            throw new IllegalArgumentException("Milestone cannot be empty");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(id);
        uri.append("/milestones");
        uri.append('/').append(milestone);
        this.client.delete(uri.toString());
    }
    
    public Milestone editMilestone(final IRepositoryIdProvider repository, final Milestone milestone) throws IOException {
        final String repoId = this.getId(repository);
        if (milestone == null) {
            throw new IllegalArgumentException("Milestone cannot be null");
        }
        final StringBuilder uri = new StringBuilder("/repos");
        uri.append('/').append(repoId);
        uri.append("/milestones");
        uri.append('/').append(milestone.getNumber());
        return this.client.post(uri.toString(), milestone, Milestone.class);
    }
}
