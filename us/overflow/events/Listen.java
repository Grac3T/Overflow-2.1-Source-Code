// 
// Decompiled by Procyon v0.5.36
// 

package us.overflow.events;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface Listen {
    ListenerPriority priority() default ListenerPriority.NORMAL;
    
    boolean ignoreCancelled() default false;
}
