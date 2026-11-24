# CMP 129 · Connecting your first JavaFX button event

**Practice goal:** Build a tiny name badge preview. When the user types `Maya` and clicks **Preview**, the label should change to **Hello, Maya!** The user can edit the name and click again.

### 1. Start with a separate practice window

In your working JavaFX project, create `BadgePreview.java`. Paste this class into it and run the file. You should see a text field, a button, and a label. Click the button once. What happens while the marked spot is empty?

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BadgePreview extends Application {
    @Override
    public void start(Stage stage) {
        TextField nameField = new TextField();
        nameField.setPromptText("Enter a name");
        Button previewButton = new Button("Preview");
        Label badgeLabel = new Label("Your badge will appear here");

        // Step 2: connect the button to its action here.

        VBox layout = new VBox(10, nameField, previewButton, badgeLabel);
        stage.setScene(new Scene(layout, 320, 150));
        stage.setTitle("Badge Preview");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
```

### 2. Make the click visible

At the marked spot inside `start`, add this block. Run again. The original label should stay on screen until you click **Preview**.

```java
previewButton.setOnAction(event -> {
    // This line runs each time the user activates Preview.
    badgeLabel.setText("Preview clicked");
});
```

`setOnAction` connects this particular button to a handler. The code between the braces describes what to do when JavaFX delivers the action. Which line changes what the user sees?

### 3. Read the current input

Keep the handler from Step 2. Replace **only** the line `badgeLabel.setText("Preview clicked");` with these lines:

```java
// Read at click time so an edited name appears on the next preview.
String name = nameField.getText().trim();
if (name.isEmpty()) {
    badgeLabel.setText("Enter a name first.");
} else {
    // Update the label that is already visible in the window.
    badgeLabel.setText("Hello, " + name + "!");
}
```

### 4. Test the timing

| Try this | What should happen? |
|---|---|
| Type Maya without clicking. | The label keeps its original text. |
| Click Preview. | The label reads **Hello, Maya!** |
| Replace Maya with Sam and click again. | The same label now reads **Hello, Sam!** |
| Enter only spaces and click Preview. | The label reads **Enter a name first.** |

If the label changes before the first click, check which statements are inside the handler’s braces. If a click seems to do nothing, temporarily bring back the Step 2 message to test the connection on its own.

### 5. Return to your Week 12 design

Choose **one button** in the interface you already built. Before coding, finish this sentence: “When the user clicks ___, my program needs to read ___ and update ___.” If that button needs no input, say so. Write a short comment at the spot where you will connect the handler. Predict the screen before and after one click, then test your prediction before connecting a second button.
