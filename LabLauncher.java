import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import javafx.application.Application;

/**
 * Runs the student's JavaFX Application without hardcoding its class name.
 * Students may rename their public class and matching .java file.
 */
public class LabLauncher {
    public static void main(String[] args) throws Exception {
        List<Class<? extends Application>> apps = new ArrayList<>();

        try (Stream<Path> files = Files.list(Path.of("."))) {
            for (Path file : files.filter(p -> p.getFileName().toString().endsWith(".java")).toList()) {
                String name = file.getFileName().toString().replaceFirst("\\.java$", "");
                if (name.equals("LabLauncher")) {
                    continue;
                }
                try {
                    Class<?> candidate = Class.forName(name, false, LabLauncher.class.getClassLoader());
                    if (Application.class.isAssignableFrom(candidate)
                            && !java.lang.reflect.Modifier.isAbstract(candidate.getModifiers())) {
                        apps.add(candidate.asSubclass(Application.class));
                    }
                } catch (ClassNotFoundException ignored) {
                    // The source file has not been compiled yet.
                }
            }
        } catch (IOException e) {
            throw new IllegalStateException("Cannot read Java files in the project folder.", e);
        }

        if (apps.size() != 1) {
            throw new IllegalStateException("Expected exactly one JavaFX Application in the project root; found "
                    + apps.size() + ". Ensure your class extends javafx.application.Application, "
                    + "its public class name matches its .java filename, and the project is compiled.");
        }

        System.out.println("Launching JavaFX class: " + apps.get(0).getName());
        Application.launch(apps.get(0), args);
    }
}
