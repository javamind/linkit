package controllers.api;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom exclusion strategy
 */
public class NoExposeExclusionStrategy implements ExclusionStrategy {

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.FIELD })
    public @interface NoExpose {
        // Field tag only annotation
    }

    public NoExposeExclusionStrategy() {
    }

    public boolean shouldSkipClass(Class<?> clazz) {
        return (false);
    }

    public boolean shouldSkipField(FieldAttributes f) {
        return f.getAnnotation(NoExpose.class) != null;
    }

}