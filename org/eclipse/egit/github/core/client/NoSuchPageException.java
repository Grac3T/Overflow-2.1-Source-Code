// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import java.io.IOException;
import java.util.NoSuchElementException;

public class NoSuchPageException extends NoSuchElementException
{
    private static final long serialVersionUID = 6795637952359586293L;
    protected final IOException cause;
    
    public NoSuchPageException(final IOException cause) {
        this.cause = cause;
    }
    
    @Override
    public String getMessage() {
        return (this.cause != null) ? this.cause.getMessage() : super.getMessage();
    }
    
    @Override
    public IOException getCause() {
        return this.cause;
    }
}
