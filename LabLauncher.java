import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;
import javafx.application.Application;

/**
 * Course launcher for CMP-129 JavaFX examples.
 * Keep this class unchanged. Run and Debug starts it automatically.
 */
public class LabLauncher {
    public static void main(String[] args) throws IOException {
        List<Class<? extends Application>> apps = new ArrayList<>();

        try (Stream<Path> files = Files.list(Path.of("."))) {
            files.filter(path -> path.toString().endsWith(".java"))
                 .sorted(Comparator.comparing(Path::toString))
                 .forEach(path -> {
                     String filename = path.getFileName().toString();
                     String className = filename.substring(0, filename.length() - 5);
                     try {
                         Class<?> candidate = Class.forName(
                             className, false, LabLauncher.class.getClassLoader());
                         if (Application.class.isAssignableFrom(candidate)
                                 && !Modifier.isAbstract(candidate.getModifiers())) {
                             apps.add(candidate.asSubclass(Application.class));
                         }
                     } catch (ClassNotFoundException exception) {
                         System.err.println("Not compiled yet: " + filename
                             + ". Save files and check the Problems panel.");
                     }
                 });
        }

        if (apps.isEmpty()) {
            System.err.println("No JavaFX app found. Run JavaFXInstallTest first, "
                + "or complete CalculatorApp.java and save it.");
            return;
        }

        int selected = 0;
        if (apps.size() > 1) {
            System.out.println("Choose a JavaFX application:");
            for (int i = 0; i < apps.size(); i++) {
                System.out.println((i + 1) + ". " + apps.get(i).getSimpleName());
            }
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.print("Enter a number: ");
                if (!scanner.hasNextLine()) return;
                try {
                    selected = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    if (selected >= 0 && selected < apps.size()) break;
                } catch (NumberFormatException ignored) {
                    // Ask again for a valid menu number.
                }
                System.out.println("Enter a number from 1 to " + apps.size() + ".");
            }
        }

        Application.launch(apps.get(selected), args);
    }
}
