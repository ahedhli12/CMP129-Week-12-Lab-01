import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// A small installation check for the CMP 128 Honors GUI project.
// Save this file as JavaFXInstallTest.java.
public class JavaFXInstallTest extends Application {

    // Remember how many successful button clicks have occurred.
    private int clickCount = 0;

    // Standard Java entry point.
    // For the saved SDK settings, use Run and Debug > Run Honors JavaFX.
    public static void main(String[] args) {
        launch(args);
    }

    // JavaFX calls start to create the application window.
    @Override
    public void start(Stage stage) {
        // Create the message, input field, and button.
        Label status = new Label("JavaFX started successfully.");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");
        Button testButton = new Button("Test JavaFX");

        // React when the user clicks the button.
        testButton.setOnAction(event -> {
            String name = nameField.getText().trim();

            // Check input before displaying the greeting.
            if (name.isEmpty()) {
                status.setText("Enter a name, then try again.");
            } else {
                clickCount++;
                status.setText("Hello, " + name + "! Button clicks: " + clickCount);
            }
        });

        // Arrange the controls vertically with 12 pixels between them.
        VBox root = new VBox(12, status, nameField, testButton);
        root.setPadding(new Insets(20));

        // Put the layout into a scene, then display it in the window.
        Scene scene = new Scene(root, 440, 180);
        stage.setTitle("CMP 128 Honors JavaFX Installation Test");
        stage.setScene(scene);
        stage.show();
    }
}
