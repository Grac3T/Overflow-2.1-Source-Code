// 
// Decompiled by Procyon v0.5.36
// 

package org.eclipse.egit.github.core.client;

import java.util.Iterator;
import java.util.List;
import java.text.MessageFormat;
import org.eclipse.egit.github.core.FieldError;
import org.eclipse.egit.github.core.RequestError;
import java.io.IOException;

public class RequestException extends IOException
{
    private static final String FIELD_INVALID_WITH_VALUE = "Invalid value of ''{0}'' for ''{1}'' field";
    private static final String FIELD_INVALID = "Invalid value for ''{0}'' field";
    private static final String FIELD_MISSING = "Missing required ''{0}'' field";
    private static final String FIELD_ERROR = "Error with ''{0}'' field in {1} resource";
    private static final String FIELD_EXISTS = "{0} resource with ''{1}'' field already exists";
    private static final long serialVersionUID = 1197051396535284852L;
    private final RequestError error;
    private final int status;
    
    public RequestException(final RequestError error, final int status) {
        this.error = error;
        this.status = status;
    }
    
    @Override
    public String getMessage() {
        return (this.error != null) ? this.formatErrors() : super.getMessage();
    }
    
    public RequestError getError() {
        return this.error;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    protected String format(final FieldError error) {
        final String code = error.getCode();
        final String value = error.getValue();
        final String field = error.getField();
        if ("invalid".equals(code)) {
            if (value != null) {
                return MessageFormat.format("Invalid value of ''{0}'' for ''{1}'' field", value, field);
            }
            return MessageFormat.format("Invalid value for ''{0}'' field", field);
        }
        else {
            if ("missing_field".equals(code)) {
                return MessageFormat.format("Missing required ''{0}'' field", field);
            }
            if ("already_exists".equals(code)) {
                return MessageFormat.format("{0} resource with ''{1}'' field already exists", error.getResource(), field);
            }
            if ("custom".equals(code)) {
                final String message = error.getMessage();
                if (message != null && message.length() > 0) {
                    return message;
                }
            }
            return MessageFormat.format("Error with ''{0}'' field in {1} resource", field, error.getResource());
        }
    }
    
    public String formatErrors() {
        String errorMessage = this.error.getMessage();
        if (errorMessage == null) {
            errorMessage = "";
        }
        final StringBuilder message = new StringBuilder(errorMessage);
        if (message.length() > 0) {
            message.append(' ').append('(').append(this.status).append(')');
        }
        else {
            message.append(this.status);
        }
        final List<FieldError> errors = (List<FieldError>)this.error.getErrors();
        if (errors != null && errors.size() > 0) {
            message.append(':');
            for (final FieldError fieldError : errors) {
                message.append(' ').append(this.format(fieldError)).append(',');
            }
            message.deleteCharAt(message.length() - 1);
        }
        return message.toString();
    }
}
