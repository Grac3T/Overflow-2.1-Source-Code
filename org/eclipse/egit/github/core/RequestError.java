// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.util.List;
import java.io.Serializable;

public class RequestError implements Serializable
{
    private static final long serialVersionUID = -7842670602124573940L;
    private String error;
    private String message;
    private List<FieldError> errors;
    
    public String getMessage() {
        return (this.message != null) ? this.message : this.error;
    }
    
    public List<FieldError> getErrors() {
        return (List<FieldError>)this.errors;
    }
}
