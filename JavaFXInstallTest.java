import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * JavaFX installation test only. This is NOT the calculator solution.
 */
public class JavaFXInstallTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Label message = new Label("JavaFX is working!");
        Button button = new Button("Test Button");
        button.setOnAction(event -> message.setText("Success! Button clicked."));

        VBox layout = new VBox(12, message, button);
        layout.setPadding(new Insets(20));

        stage.setScene(new Scene(layout, 340, 150));
        stage.setTitle("CMP-129 JavaFX Setup Test");
        stage.show();
    }
}
