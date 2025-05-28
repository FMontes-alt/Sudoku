# 🧩 Sudoku 3.0

**Sudoku 3.0** es una aplicación Java diseñada para ofrecer una experiencia interactiva del clásico juego de Sudoku. Desarrollado como proyecto académico en el módulo de Programación de 1º DAW, este sistema permite tanto el juego por consola como el uso de una interfaz gráfica intuitiva mediante Java Swing.

> 🎯 El objetivo pedagógico del proyecto ha sido aplicar conceptos de POO, estructuras de datos, control de errores, diseño modular, pruebas unitarias y documentación profesional.

---

## 🌟 Funcionalidades Destacadas

| Funcionalidad                   | Descripción                                                                 |
|-------------------------------|-----------------------------------------------------------------------------|
| 🎮 Juego por consola           | Juega directamente en terminal con entrada numérica                         |
| 🖥️ Interfaz gráfica (Swing)    | Interacción con tablero, botones, feedback visual                           |
| 🧠 Verificador de jugadas       | Marca en rojo/verde según la validez del número introducido                 |
| ♻️ Reinicio de partida         | Reinicia el tablero con el botón correspondiente                            |
| 🔍 Resolución automática       | Algoritmo de backtracking para resolver el tablero                          |
| 💡 Intentos limitados          | Sistema de penalización configurable por errores                            |
| 🧪 Pruebas unitarias (JUnit)   | Verificación del correcto funcionamiento de las clases principales          |

---

## 🔧 Tecnologías y Herramientas

| Tecnología     | Uso Principal                          |
|----------------|----------------------------------------|
| Java 17        | Lenguaje de programación principal     |
| Java Swing     | Creación de la interfaz gráfica        |
| Maven          | Gestión del proyecto y dependencias    |
| JUnit 5        | Pruebas unitarias                      |
| IntelliJ IDEA  | Entorno de desarrollo                  |
| Mermaid.js     | Generación de diagramas UML            |

---

## 🗂️ Arquitectura del Proyecto

```bash
Sudoku/
├── src/
│   ├── core/               # Lógica y UI
│   │   ├── Sudoku.java              # Clase que representa el tablero y su validación
│   │   ├── GenerarSudoku.java       # Algoritmo para generar tableros válidos
│   │   ├── JuegoSudoku.java         # Controlador para la versión consola
│   │   └── SudokuGUI.java           # Interfaz Swing con lógica gráfica
│
├── test/
│   └── core/
│       └── JuegoSudokuTest.java     # Pruebas unitarias con JUnit 5
│
├── resources/
│   └── img/
│       └── interfaz.png             # Capturas de pantalla o assets visuales
│
├── docs/
│   └── Documentacion_Proyecto_Sudoku_FINAL.md  # Documentación técnica y diagramas
│
├── README.md
└── pom.xml
```
