# Jokenpo

Android app (Kotlin) for **Rock, Paper, Scissors**, refactored with **Jetpack Navigation**: fragments, navigation graph with a **nested graph** (Player + Result), **NavHost** in `MainActivity`, **NavController**, and **Navigation UI** (Toolbar and Navigation Drawer).

## Requirements

- Android Studio (project with AGP 9+)
- JDK 17+ (Android Studio typically uses the bundled JBR)

## How to Run

1. Clone the repository.
2. Open the project root in Android Studio.
3. **File → Sync Project with Gradle Files**.
4. Run on an emulator or device (**Run**).

## Main Structure

- `HomeFragment` — welcome screen and start game button.
- `game_graph` nested graph: `PlayerFragment` (move selection spinner) and `ResultFragment`.
- `res/navigation/nav_graph.xml` — root graph and nested graph.
- `MainActivity` — `NavHostFragment` plus **AppBar** and **Navigation Drawer** integration via `NavigationUI`.

---
