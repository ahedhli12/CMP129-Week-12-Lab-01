# JavaFX Setup for VS Code (Windows, No Maven Required)

## CMP-129 Week 12 Lab 01 — JavaFX Calculator

Set up the JavaFX SDK one time. After that, use **Run and Debug → Run CMP129 JavaFX**. There is no path prompt on every run.

### Step 1 — Install Java and VS Code
1. Install a full **JDK** (not just a JRE). Use a JavaFX SDK version compatible with your JDK.
2. Open VS Code and install **Extension Pack for Java** (Microsoft).
3. Open the **whole repository folder** in VS Code.
4. Run `java -version` and `javac -version` in the integrated terminal. If VS Code picked the wrong JDK, run **Java: Configure Java Runtime** from the Command Palette.

### Step 2 — Download and extract the JavaFX SDK
1. Download the **SDK** ZIP for your operating system and architecture from [Gluon](https://gluonhq.com/products/javafx/).
2. Extract the ZIP. **Do not use the ZIP file as the path.**
3. Find the extracted `lib` folder. It must contain `javafx.controls.jar`.
4. Copy the full path to that `lib` folder. Example: `C:/Users/Student/Downloads/javafx-sdk-25/lib`.

### Step 3 — Edit both .vscode files
Use **your own** JavaFX folder path, not the example placeholder.

Open `.vscode/settings.json`. Change only this library path, **keeping `/*.jar` at the end**:

```json
"java.project.referencedLibraries": [
  "C:/Users/Student/Downloads/javafx-sdk-25/lib/*.jar"
]
```

Open `.vscode/launch.json`. Change the example `lib` folder path in `vmArgs` to the **same** folder, but **without `/*.jar`**:

```json
"vmArgs": "--module-path \"C:/Users/Student/Downloads/javafx-sdk-25/lib\" --add-modules javafx.controls"
```

Forward slashes are easiest to use in both JSON files, including on Windows. Keep the escaped quotes around the path inside `vmArgs`. Save both files.

**What the two settings do:**
- `settings.json` tells VS Code where to find the JavaFX JAR libraries so that `javafx.*` imports are recognized while editing.
- `launch.json` tells Java where to find JavaFX at run time. The `--add-modules javafx.controls` option loads the JavaFX controls module.

### Step 4 — Run the installation test
1. Open **Run and Debug** with **Ctrl+Shift+D**.
2. Select **Run CMP129 JavaFX**, then click the green Run button.
3. If the menu lists several applications, type the number beside **JavaFXInstallTest** and press Enter.
4. A small window should open. Click **Test Button** and verify that you see **Success! Button clicked.**

Keep `LabLauncher.java` unchanged. It searches the repository root for JavaFX applications, so you do not need to edit `mainClass` for each program. If you later complete `CalculatorApp.java` as a JavaFX `Application`, it will appear in the menu automatically.

### Step 5 — Complete the assignment
Your required file is `CalculatorApp.java`, in the repository root, with **no package statement**. Keep the class named `CalculatorApp` and follow the separate lab instructions. The supplied installation test is only to verify JavaFX; **it is not a calculator solution**.

Do not use the plain **Run Java** shortcut if it does not include the JavaFX VM arguments. Use **Run and Debug → Run CMP129 JavaFX**.

### Troubleshooting

| Problem | Fix |
| --- | --- |
| Red underline under `javafx.*` | Verify the path ending in `lib/*.jar` in `settings.json`. Run **Java: Clean Java Language Server Workspace** if needed. |
| `Module javafx.controls not found` or boot-layer error | Verify that `launch.json` points to the **extracted** `lib` folder, not the ZIP or `bin` folder. |
| `JavaFX runtime components are missing` | Select **Run CMP129 JavaFX** in Run and Debug instead of the regular Run Java button. |
| `UnsupportedClassVersionError` | Your JDK and JavaFX SDK are incompatible; use a compatible pair. |
| `Could not find or load main class LabLauncher` | Open the repository's top-level folder, save files, and verify the Java extensions are installed. |
| No JavaFX app found | Run or restore `JavaFXInstallTest.java`; your calculator must extend `Application` when implemented. |

**No Maven setup or project generator is required for this manual SDK approach.** A `pom.xml` may be present for other workflows, but these instructions use the SDK and VS Code Run and Debug.

Do not commit JavaFX SDK ZIPs or JAR files. Follow the lab's existing assignment and AI-use instructions.
