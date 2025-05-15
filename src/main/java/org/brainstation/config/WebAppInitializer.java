package org.brainstation.config;

import java.io.IOException;

public interface WebAppInitializer {
    Class<?> findAnnotatedClasses(String rootPackage) throws IOException;
}
