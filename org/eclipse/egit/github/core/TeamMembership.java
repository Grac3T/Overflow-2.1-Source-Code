// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class TeamMembership implements Serializable
{
    private static final long serialVersionUID = -8207728181588115431L;
    private TeamMembershipState state;
    private String url;
    
    public TeamMembershipState getState() {
        return this.state;
    }
    
    public void setState(final TeamMembershipState state) {
        this.state = state;
    }
    
    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(final String url) {
        this.url = url;
    }
    
    public enum TeamMembershipState
    {
        public static final TeamMembershipState ACTIVE;
        public static final TeamMembershipState PENDING;
        
        public static TeamMembershipState valueOf(final String name) {
            return Enum.valueOf(TeamMembershipState.class, name);
        }
        
        static {
            TeamMembershipState.ACTIVE = new TeamMembershipState("ACTIVE", 0);
            TeamMembershipState.PENDING = new TeamMembershipState("PENDING", 1);
            TeamMembershipState.$VALUES = new TeamMembershipState[] { TeamMembershipState.ACTIVE, TeamMembershipState.PENDING };
        }
    }
}
