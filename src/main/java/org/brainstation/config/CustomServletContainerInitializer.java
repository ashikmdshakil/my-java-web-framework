package org.brainstation.config;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.annotation.HandlesTypes;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@HandlesTypes(WebAppInitializer.class)
public class CustomServletContainerInitializer implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) {
        String classesDirPath = servletContext.getRealPath("WEB-INF/classes");
        Path path = Paths.get(classesDirPath);

        for (Class<?> clazz : set) {
            if (isConcreteClass(clazz)) {
                try {
                    WebAppInitializer initializer = createInitializerInstance(clazz);
                    initializer.findAnnotatedClasses(path);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private boolean isConcreteClass(Class<?> clazz) {
        int modifiers = clazz.getModifiers();
        return !clazz.isInterface() && !Modifier.isAbstract(modifiers);
    }

    private WebAppInitializer createInitializerInstance(Class<?> clazz) throws ReflectiveOperationException {
        return (WebAppInitializer) clazz.getDeclaredConstructor().newInstance();
    }
}
