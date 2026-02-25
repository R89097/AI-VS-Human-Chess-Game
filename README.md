# AI-VS-Human-Chess-Game
Here’s a clear, professional summary you can use in your **README.md**:

---

# Grid Path Game – Java Implementation

This project implements the core logic of a two-player grid-based path-building game in Java. The game is played on an *n × n* grid (typically 5 × 5), where two players alternate placing black and white pieces. The objective is to create a 4-connected path (up, down, left, right only) connecting:

* **Top to bottom**, or
* **Left to right**

The project focuses on implementing missing components in an existing codebase using interfaces, clean object-oriented design, and thorough testing.

---

## 🎯 Project Objectives

* Implement concrete classes for the provided interfaces:

  * `Move`
  * `Grid`
  * `Game`
* Ensure correctness using provided and custom test cases
* Follow Java best practices (clean structure, proper access modifiers, readable code)
* Maintain compatibility with an existing AI opponent

---

## 🧩 Implemented Classes

### `MoveImpl`

Represents a single move in the game:

* Stores row and column positions
* Implements required interface methods
* Overrides `toString()` for formatted output
* Validated with provided unit tests

### `GridImpl`

Represents the game board:

* Maintains grid state
* Handles piece placement and occupancy checks
* Provides a string representation of the board
* Passes all provided grid tests

### `GameImpl`

Implements full game logic:

* Alternating turns between players
* Valid move enforcement
* Win detection using 4-connected path checking
* Draw detection when the grid is full
* Integrates with existing `PathFinder` class

---

## 🧪 Testing

A comprehensive `GameTest` class was developed to:

* Validate all `Game` interface methods
* Detect incorrect implementations
* Cover edge cases including:

  * Invalid moves
  * Win conditions (both directions)
  * Draw scenarios
  * Turn switching logic

The test suite is designed to confidently verify correctness across multiple possible faulty implementations.

---

## 🤖 AI Compatibility

The completed implementation integrates seamlessly with the provided AI package. The game can be played through the terminal using:

```
ai.PlayVsAI
```

The AI plays as **White** and always moves first.

---

## 🛠 Technologies Used

* Java
* Object-Oriented Programming
* Interfaces and Polymorphism
* Unit Testing
* Clean Code Practices

---

## 📌 Key Learning Outcomes

* Designing and implementing interface-based systems
* Separating game logic from AI logic
* Writing robust tests to validate behavior
* Applying clean, maintainable coding standards

---

This project demonstrates strong understanding of Java interfaces, modular design, testing strategy, and integration within an existing codebase.
