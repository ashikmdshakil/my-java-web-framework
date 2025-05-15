package org.brainstation.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WebAppInitializerImpl implements WebAppInitializer {
    @Override
    public Class<?> findAnnotatedClasses(String rootPackage) throws IOException {
        String rootDirectoryPath = rootPackage.replace('.', '/');

        Path rootDirectory = Paths.get(rootDirectoryPath);
        if (!Files.exists(rootDirectory)) {
            throw new RuntimeException("Root package is not right");
        }

        List<String> fullyAnnotatedControllerClassNames = new ArrayList<>();
        controllerClassNames(rootDirectory, fullyAnnotatedControllerClassNames);

        fullyAnnotatedControllerClassNames.stream().forEach(s -> System.out.println("Class name is " + s));
        return null;
    }

    public void controllerClassNames(Path rootDirectory, List<String> classNames) throws IOException {
        Stream<Path> elements = Files.list(rootDirectory);

        elements.forEach(element -> {
            if (Files.isDirectory(element)) {
                try {
                    controllerClassNames(element, classNames);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (Files.isRegularFile(element)) {
                if (element.endsWith(".class")) {
                    classNames.add(element.toString().replace('/', '.'));
                }
            }
        });
    }
}
