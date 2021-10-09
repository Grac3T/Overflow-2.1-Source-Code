// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.event;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.Team;
import java.io.Serializable;

public class TeamAddPayload extends EventPayload implements Serializable
{
    private static final long serialVersionUID = 7660176723347977144L;
    private Team team;
    private User user;
    private Repository repo;
    
    public Team getTeam() {
        return this.team;
    }
    
    public TeamAddPayload setTeam(final Team team) {
        this.team = team;
        return this;
    }
    
    public User getUser() {
        return this.user;
    }
    
    public TeamAddPayload setUser(final User user) {
        this.user = user;
        return this;
    }
    
    public Repository getRepo() {
        return this.repo;
    }
    
    public TeamAddPayload setRepo(final Repository repo) {
        this.repo = repo;
        return this;
    }
}
