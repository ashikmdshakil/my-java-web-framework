package org.brainstation.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WebAppInitializerImpl implements WebAppInitializer {
    @Override
    public List<Class<?>> findAnnotatedClasses(Path rootDirectory) throws IOException {
        if (!Files.exists(rootDirectory)) {
            throw new RuntimeException("Root package is not right");
        }

        List<Class<?>> classList = new ArrayList<>();
        findByFullyQualifiedClassNames(rootDirectory, "", classList);

        return classList;
    }

    public void findByFullyQualifiedClassNames(Path rootDirectory, String prefix, List<Class<?>> classList) throws IOException {
        Stream<Path> elements = Files.list(rootDirectory);  // Fetching files and directories in current directory
        elements.forEach(element -> {
            if ("META-INF".equals(element.getFileName().toString())) {
                return;
            } else if (Files.isDirectory(element)) {
                try {
                    findByFullyQualifiedClassNames(element, prefix.concat(element.getFileName().toString().concat(".")), classList);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (Files.isRegularFile(element)) {
                if (element.toString().endsWith(".class")) {
                    String className = prefix.concat(element.getFileName().toString());
                    ClassLoader loader = Thread.currentThread().getContextClassLoader();
                    Class<?> clazz = null;
                    try {
                        clazz = loader.loadClass(className.substring(0, className.length() - 6));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    classList.add(clazz);
                }
            }
        });
    }
}
