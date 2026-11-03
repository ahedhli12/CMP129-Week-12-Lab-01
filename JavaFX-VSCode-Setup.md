# JavaFX Setup for VS Code (Windows — No Maven Required)

## CMP-129 Week 12 Lab 01 — Calculator

These instructions explain how to set up JavaFX to run the calculator in Visual Studio Code on Windows **without Maven**. You do not need to change the calculator logic to configure JavaFX.

### 1. Install Java and VS Code extensions

- Install a full **JDK** (not just a JRE) and check it with `java -version` and `javac -version` in VS Code's terminal.
- In VS Code, install Microsoft's **Extension Pack for Java**, which includes the Java debugger.
- Use a **JavaFX SDK version compatible with your installed JDK**. For example, use JavaFX 26 with JDK 26. JavaFX 27 may require a newer JDK and may not work with JDK 26.

### 2. Download and extract JavaFX SDK

1. Visit https://gluonhq.com/products/javafx/.
2. Choose the **Windows x64 SDK** matching your JDK (not the jmods or source archive).
3. Extract the ZIP file using **Extract All**.
4. Locate the extracted SDK's `lib` folder. It should contain `javafx.controls.jar`, `javafx.graphics.jar`, and `javafx.base.jar`.
5. In File Explorer, open `lib`, click the address bar, and copy the **full folder path**.

**Important:** The ZIP file itself is not a module path. You must extract it first.

### 3. Configure VS Code

1. Clone/open this repository in VS Code.
2. Open `.vscode/launch.json`.
3. In `vmArgs`, replace the instructor's example path with **your own** extracted SDK `lib` folder path.
4. In JSON, each Windows backslash must be doubled (for example, `C:\\JavaFX\\javafx-sdk-26\\lib`).

The configuration should look like this, substituting your real SDK path:

```json
{
  "version": "0.2.0",
  "configurations": [
    {
      "type": "java",
      "name": "Run JavaFX Calculator (SDK, no Maven)",
      "request": "launch",
      "mainClass": "CalculatorApp",
      "vmArgs": "--module-path \"C:\\JavaFX\\javafx-sdk-26\\lib\" --add-modules javafx.controls"
    }
  ]
}
```

The repository currently contains the instructor's **machine-specific** JavaFX 27 SDK path. It is not expected to work on student computers without editing it.

### 4. Make sure the Java class name matches

The assignment's required file is `CalculatorApp.java` in the repository root. Its public class must be named `CalculatorApp` and extend `javafx.application.Application`. Implement the required `start(Stage stage)` method and a `main` method that calls `launch(args)`.

Do not rename your class to `SimpleCalculator` unless you also update the launch configuration's `mainClass`. Do not create a `src` folder for this assignment.

### 5. Configure the JavaFX compile-time libraries if imports are unresolved

If VS Code shows red underlines on `javafx.*` imports, open `.vscode/settings.json` and add the following property inside the existing JSON object (keep the other settings and use a comma between properties):

```json
"java.project.referencedLibraries": [
  "C:/JavaFX/javafx-sdk-26/lib/*.jar"
]
```

Replace the sample path with your extracted SDK `lib` folder. Forward slashes work in this setting. If VS Code still shows errors, open the Command Palette and run **Java: Clean Java Language Server Workspace**, then restart.

### 6. Run the calculator

1. Save your Java file and `launch.json`.
2. Press **Ctrl+Shift+D** to open **Run and Debug**.
3. Select **Run JavaFX Calculator (SDK, no Maven)**.
4. Click the green Run button.

Do not use the plain **Run Java** shortcut if it launches without the JavaFX module-path arguments.

### Troubleshooting

| Error | Likely cause and fix |
| --- | --- |
| `Error occurred during initialization of boot layer` / `Module javafx.controls not found` | Check that `vmArgs` points to the **extracted** `lib` folder and that `javafx.controls.jar` is present. |
| `UnsupportedClassVersionError` or unsupported class-file version | The JavaFX SDK requires a newer JDK. Install a matching SDK/JDK combination. |
| `JavaFX runtime components are missing` | Launch with the provided **Run and Debug** configuration rather than the plain Run Java button. |
| `Could not find or load main class CalculatorApp` | Confirm the filename, class declaration, workspace root, and launch configuration match. |
| Red underlines under `javafx.*` imports | Add the SDK JAR files to `java.project.referencedLibraries` as described above. |

### Before submitting

Test the calculator according to the lab instructions. Submit the required Java source and `AI-Use-Report.md`. Follow the assignment's AI-use policy. **Do not commit the downloaded JavaFX SDK ZIP or its extracted JAR files** to this repository.
