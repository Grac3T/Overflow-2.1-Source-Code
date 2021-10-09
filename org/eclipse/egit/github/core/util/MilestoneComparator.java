// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.util;

import java.io.Serializable;
import org.eclipse.egit.github.core.Milestone;
import java.util.Comparator;

public class MilestoneComparator implements Comparator<Milestone>, Serializable
{
    private static final long serialVersionUID = 7166479273639101758L;
    
    @Override
    public int compare(final Milestone m1, final Milestone m2) {
        return m1.getTitle().compareToIgnoreCase(m2.getTitle());
    }
}
