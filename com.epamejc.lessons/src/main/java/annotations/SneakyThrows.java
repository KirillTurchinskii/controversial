package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target ({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention (RetentionPolicy.SOURCE)
public @interface SneakyThrows {

  Class<? extends Throwable>[] value() default java.lang.Throwable.class;

  //The fully qualified name is used for java.lang.Throwable in the parameter only. This works around a bug in javac:
  //   presence of an annotation processor throws off the type resolver for some reason.
}