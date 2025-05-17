package org.brainstation.config;

import java.io.IOException;
import java.nio.file.Path;

public interface WebAppInitializer {
    Class<?> findAnnotatedClasses(Path rootDirectory) throws IOException;
}
