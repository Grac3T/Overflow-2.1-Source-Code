// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import org.eclipse.egit.github.core.util.DateUtils;
import java.util.Date;
import java.io.Serializable;

public class CommitUser implements Serializable
{
    private static final long serialVersionUID = -180887492938484405L;
    private Date date;
    private String email;
    private String name;
    
    public Date getDate() {
        return DateUtils.clone(this.date);
    }
    
    public CommitUser setDate(final Date date) {
        this.date = DateUtils.clone(date);
        return this;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public CommitUser setEmail(final String email) {
        this.email = email;
        return this;
    }
    
    public String getName() {
        return this.name;
    }
    
    public CommitUser setName(final String name) {
        this.name = name;
        return this;
    }
}
