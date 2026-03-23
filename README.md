# Jokenpô

App Android (Kotlin) do jogo **Pedra, Papel e Tesoura**, refatorado com **Jetpack Navigation**: fragments, grafo de navegação com **nested graph** (Jogador + Resultado), **NavHost** na `MainActivity`, **NavController** e **Navigation UI** (Toolbar e Navigation Drawer).

## Requisitos

- Android Studio (projeto com AGP 9+)
- JDK 17+ (o Android Studio costuma usar o JBR embutido)

## Como abrir

1. Clone o repositório.
2. Abra a pasta raiz no Android Studio.
3. **File → Sync Project with Gradle Files**.
4. Execute no emulador ou dispositivo (**Run**).

## Estrutura principal

- `InicialFragment` — boas-vindas e botão para iniciar o jogo.
- Sub-grafo `game_graph`: `JogadorFragment` (spinner da jogada) e `ResultadoFragment`.
- `res/navigation/nav_graph.xml` — grafo e nested graph.
- `MainActivity` — `NavHostFragment`, integração com **AppBar** e **Navigation Drawer** via `NavigationUI`.

---
