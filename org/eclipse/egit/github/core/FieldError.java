// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core;

import java.io.Serializable;

public class FieldError implements Serializable
{
    private static final long serialVersionUID = 1447694681624322597L;
    public static final String CODE_INVALID = "invalid";
    public static final String CODE_MISSING = "missing";
    public static final String CODE_MISSING_FIELD = "missing_field";
    public static final String CODE_ALREADY_EXISTS = "already_exists";
    public static final String CODE_CUSTOM = "custom";
    private String code;
    private String field;
    private String message;
    private String resource;
    private String value;
    
    public String getCode() {
        return this.code;
    }
    
    public FieldError setCode(final String code) {
        this.code = code;
        return this;
    }
    
    public String getField() {
        return this.field;
    }
    
    public FieldError setField(final String field) {
        this.field = field;
        return this;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public FieldError setMessage(final String message) {
        this.message = message;
        return this;
    }
    
    public String getResource() {
        return this.resource;
    }
    
    public FieldError setResource(final String resource) {
        this.resource = resource;
        return this;
    }
    
    public String getValue() {
        return this.value;
    }
    
    public FieldError setValue(final String value) {
        this.value = value;
        return this;
    }
}
