package org.brainstation.config;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

@HandlesTypes(WebAppInitializer.class)
public class CustomServletContainerInitializer implements ServletContainerInitializer {
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        if (set != null) {
            for (Class<?> clazz : set) {
                try {
                    // Make sure the class is assignable to WebAppInitializer
                    if (WebAppInitializer.class.isAssignableFrom(clazz)) {
                        WebAppInitializer initializer =
                                (WebAppInitializer) clazz.getDeclaredConstructor().newInstance();

                        // Call the method you defined in your interface
                        initializer.findAnnotatedClasses("org");
                    }
                } catch (Exception e) {
                    throw new ServletException("Failed to instantiate WebAppInitializer: " + clazz, e);
                }
            }
        }
    }
}
