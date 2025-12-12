# Nimons Cooked

A cooking game inspired by Overcooked, implemented in Java.

## Project Structure

```
nimonscooked/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── model/          # Entity classes
│   │   │   ├── controller/     # Game logic
│   │   │   ├── view/           # UI (CLI/GUI)
│   │   │   ├── util/           # Helper classes
│   │   │   ├── enums/          # Enums
│   │   │   └── Main.java       # Entry point
│   │   └── resources/
│   │       └── maps/           # Game maps
│   └── test/                   # Test files
├── build.gradle                # Gradle build configuration
├── README.md                   # This file
└── .gitignore                  # Git ignore rules
```

## Building the Project

To build the project:

```bash
cd nimonscooked
./gradlew build
```

## Running the Game

To run the game:

```bash
cd nimonscooked
./gradlew run
```

## Testing

To run tests:

```bash
cd nimonscooked
./gradlew test
```

## Requirements

- Java 11 or higher
- Gradle (wrapper included)

## Development

This project follows standard Java OOP principles with a clear separation of concerns:

- **model**: Contains entity classes representing game objects
- **controller**: Contains game logic and flow control
- **view**: Contains UI implementation (CLI/GUI)
- **util**: Contains helper/utility classes
- **enums**: Contains enumeration types

## License

This project is part of IF2010 OOP course assignment.
