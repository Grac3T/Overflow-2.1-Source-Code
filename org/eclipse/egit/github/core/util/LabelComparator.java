// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.util;

import java.io.Serializable;
import org.eclipse.egit.github.core.Label;
import java.util.Comparator;

public class LabelComparator implements Comparator<Label>, Serializable
{
    private static final long serialVersionUID = -3185701121586168554L;
    
    @Override
    public int compare(final Label label1, final Label label2) {
        return label1.getName().compareToIgnoreCase(label2.getName());
    }
}
