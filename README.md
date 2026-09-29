Markdown
# Assignment 3: Bridge Pattern — Shape Renderer Application

## 📌 General Information
* **Student Name:** Muratbek Ibrai
* **Group:** SE-2529
* **Topic:** Option A (Drawing / Shape)
* **Repository URL:** https://github.com/StudyAitu/Assignment_3_-Bridge-Pattern/edit/main/README.md
* **Base Commit Hash:** `73eba42bc9ddb49241f0bf422724c7ebc31a6630`

---

## 🗺️ Role Map & Class Architecture

| Bridge Role | Class / Interface Name | Source File Path | Description |
| :--- | :--- | :--- | :--- |
| **Abstraction** | `Shape` | `src/Shape.java` | Abstract base class holding ID and `Renderer` reference |
| **Refined Abstraction A1** | `Circle` | `src/Circle.java` | Circle shape storing domain data (`radius=2`) |
| **Refined Abstraction A2** | `Square` | `src/Square.java` | Square shape storing domain data (`side=3`) |
| **Implementor** | `Renderer` | `src/Renderer.java` | Interface defining low-level rendering operations |
| **Implementation I1** | `VectorRenderer` | `src/VectorRenderer.java` | Vector graphic rendering implementation |
| **Implementation I2** | `RasterRenderer` | `src/RasterRenderer.java` | Raster/bitmap graphic rendering implementation |
| **Implementation I3 (Extension)** | `AsciiRenderer` | `src/AsciiRenderer.java` | ASCII art rendering implementation (added independently) |
| **Client** | `Main` | `src/Main.java` | Application entry point and T1–T7 test suite runner |

### Key Method & Reference Locations
* **Bridge Reference Field:** `protected Renderer renderer;` in `src/Shape.java`
* **Execution Gateway:** `public abstract String execute();` in `src/Shape.java` (implemented in `Circle.java` and `Square.java`)
* **Runtime Switch Method:** `public void setImplementation(Renderer renderer)` in `src/Shape.java`
* **T5 Runtime Replacement Check:** Located inside `src/Main.java`

---

## ⚙️ Compilation & Execution Guide

### 1. Structure of `sources.txt`
The `sources.txt` file contains relative paths to all Java source files required for compilation:
```text
src/Renderer.java
src/VectorRenderer.java
src/RasterRenderer.java
src/AsciiRenderer.java
src/Shape.java
src/Circle.java
src/Square.java
src/Main.java
2. Compilation Command
Bash
javac --release 17 -encoding UTF-8 -d out "@sources.txt"
Command Flag Breakdown:
javac — Standard Java compiler utility.
--release 17 — Forces compilation to adhere strictly to the JDK 17 specification and bytecode compatibility.
-encoding UTF-8 — Explicitly sets source file character encoding to prevent cross-platform encoding issues.
-d out — Specifies the destination directory (out) for the compiled .class bytecode files.
"@sources.txt" — Directs the compiler to read the list of source files from the specified text file.
3. Running the Application
Bash
java -cp out Main --demo
Command Flag Breakdown:
java — Java Virtual Machine (JVM) launcher.
-cp out — Sets the Classpath to the out directory containing compiled bytecode.
Main — Specifies the main entry point class containing public static void main(String[] args).
--demo — Command-line argument triggering the automated T1–T7 evaluation suite.
💻 Console UI / User Experience & Error Handling
The application is built with defensive programming and robust CLI user experience principles to handle edge cases gracefully:
1. Execution Without Arguments (java -cp out Main)
When launched without flags, the system displays a helpful usage guide instead of failing unexpectedly:
Plaintext
[ERROR] No operational mode selected!
Usage:
  java -cp out Main --demo             Run standard T1-T7 test suite
  java -cp out Main --interactive      Launch interactive shape rendering CLI
2. Unrecognized Flag Handling (java -cp out Main --unknown)
If an unsupported argument is passed, the CLI catches it and provides guidance:
Plaintext
[ERROR] Unsupported or unrecognized option: '--unknown'
Type 'java -cp out Main --help' for valid usage options.
3. Edge Case & Null Guard Handling
Invalid Dimensions: Constructing shapes with non-positive dimensions (radius <= 0 or side <= 0) throws a descriptive IllegalArgumentException.
Null Bridge References: Passing null to setImplementation(...) is ignored, preserving the current valid renderer and logging a warning to prevent runtime NullPointerException.
🖼️ Expected Demonstration Output (T1–T7 Checks)
Execution of java -cp out Main --demo produces the following output:
Plaintext
======================================================================
                  BRIDGE PATTERN DEMONSTRATION SUITE
======================================================================
T1 PASS | Circle + VectorRenderer | result=VECTOR circle radius=2
T2 PASS | Circle + RasterRenderer | result=RASTER circle radius=2
T3 PASS | Square + VectorRenderer | result=VECTOR square side=3
T4 PASS | Square + RasterRenderer | result=RASTER square side=3
T5 PASS | Circle runtime switch | sameObject=true | stateUnchanged=true | before=VECTOR circle radius=2 | after=RASTER circle radius=2
T6 PASS | Circle + AsciiRenderer | result=ASCII circle radius=2
T7 PASS | Square + AsciiRenderer | result=ASCII square side=3
======================================================================
SUMMARY: 7/7 PASS
======================================================================
Breakdown of Automated Checks (T1–T7):
T1: Tests Circle (A1) combined with VectorRenderer (I1) using domain radius 2.
T2: Tests Circle (A1) combined with RasterRenderer (I2) using domain radius 2.
T3: Tests Square (A2) combined with VectorRenderer (I1) using domain side length 3.
T4: Tests Square (A2) combined with RasterRenderer (I2) using domain side length 3.
T5: Demonstrates runtime implementation switching on a single instance (sameObject=true verified via ==). Confirms internal ID and domain attributes remain unchanged.
T6: Tests Circle (A1) combined with the newly added AsciiRenderer (I3).
T7: Tests Square (A2) combined with the newly added AsciiRenderer (I3).
