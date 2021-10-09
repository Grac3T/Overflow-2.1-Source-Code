// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.util;

import java.util.Date;

public abstract class DateUtils
{
    public static Date clone(final Date date) {
        if (date == null) {
            return null;
        }
        return new Date(date.getTime());
    }
}
