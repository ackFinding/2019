package tinytomcat.simple.tomcat.servlet;

import java.lang.annotation.*;

/**
 * Annotation used to declare a servlet.
 *
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyServlet {

    String value() default "";
}
