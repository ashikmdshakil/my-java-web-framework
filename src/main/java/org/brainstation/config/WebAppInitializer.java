package org.brainstation.config;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface WebAppInitializer {
    List<Class<?>> findAnnotatedClasses(Path rootDirectory) throws IOException;
}
