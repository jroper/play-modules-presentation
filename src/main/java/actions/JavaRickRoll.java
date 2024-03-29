package actions;

import play.mvc.With;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@With(JavaCustomRickRollAction.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface JavaRickRoll {

    /**
     * The name of the header to add to use to rick roll people
     *
     * @return The header name, defaults to X-Rick-Roll
     */
    String headerName() default DEFAULT;

    public static final String DEFAULT = "_None";
}
